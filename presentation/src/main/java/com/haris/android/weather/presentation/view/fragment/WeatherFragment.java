package com.haris.android.weather.presentation.view.fragment;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.haris.android.weather.presentation.R;

import com.haris.android.weather.presentation.internal.di.components.WeatherComponent;
import com.haris.android.weather.presentation.model.WeatherModel;
import com.haris.android.weather.presentation.presenter.WeatherPresenter;
import com.haris.android.weather.presentation.view.WeatherView;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;


public class WeatherFragment extends BaseFragment implements WeatherView {


    @Inject
    WeatherPresenter weatherDetailsPresenter;

    @Bind(R.id.rl_progress)
    RelativeLayout rl_progress;
    @Bind(R.id.rl_retry)
    RelativeLayout rl_retry;
    @Bind(R.id.bt_retry)
    Button bt_retry;


    @Bind(R.id.weatherTemperature)
    TextView weatherTemperature;

    @Bind(R.id.weatherTime)
    TextView weatherTime;

    @Bind(R.id.weatherLocation)
    TextView weatherLocation;

    private Location currentLocation;


    public static WeatherFragment forWeather() {
        return new WeatherFragment();
    }

    public WeatherFragment() {
        setRetainInstance(true);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getComponent(WeatherComponent.class).inject(this);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View fragmentView = inflater.inflate(R.layout.fragment_weather, container, false);
        ButterKnife.bind(this, fragmentView);
        return fragmentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.weatherDetailsPresenter.setView(this);
        if (savedInstanceState == null) {
            getLastLocation();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        this.weatherDetailsPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        this.weatherDetailsPresenter.pause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.weatherDetailsPresenter.destroy();
    }

    @Override
    public void renderWeather(WeatherModel weatherModel) {
        if (weatherModel != null) {

            this.weatherTemperature.setText(context().getString(R.string.temperature_value, String.valueOf(weatherModel.getMain().getTemp())));
            this.weatherLocation.setText(context().getString(R.string.location_value, weatherModel.getName()));
            this.weatherTime.setText(context().getString(R.string.time_value, weatherModel.getWeatherTime()));

        }
    }

    @Override
    public void showLoading() {
        this.rl_progress.setVisibility(View.VISIBLE);
        this.getActivity().setProgressBarIndeterminateVisibility(true);
    }

    @Override
    public void hideLoading() {
        this.rl_progress.setVisibility(View.GONE);
        this.getActivity().setProgressBarIndeterminateVisibility(false);
    }

    @Override
    public void showRetry() {
        this.rl_retry.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        this.rl_retry.setVisibility(View.GONE);
    }

    @Override
    public void showError(String message) {
        this.showToastMessage(message);
    }

    @Override
    public Context context() {
        return getActivity().getApplicationContext();
    }


    private void loadWeatherDetails(Location location, boolean isForceUpdate) {

        String locationLat;
        String locationLng;

        if (location == null) {
            Toast.makeText(context(), context().getString(R.string.location_not_found), Toast.LENGTH_SHORT).show();


            //Latvia coordinates (if location permission is not given or location is not fetched)
            locationLat = context().getString(R.string.dummy_test_latvia_latitude);
            locationLng = context().getString(R.string.dummy_test_latvia_longitude);

        } else {

            locationLat = String.valueOf(location.getLatitude());
            locationLng = String.valueOf(location.getLongitude());
        }

        if (this.weatherDetailsPresenter != null) {
            this.weatherDetailsPresenter.initialize(locationLat, locationLng, isForceUpdate);
        }
    }


    @OnClick(R.id.bt_retry)
    void onButtonRetryClick() {
        WeatherFragment.this.loadWeatherDetails(currentLocation, true);
    }


    public void getLastLocation() {

        FusedLocationProviderClient locationClient = getFusedLocationProviderClient(context());

        if (ActivityCompat.checkSelfPermission(context(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(context(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(context(), context().getString(R.string.require_permission), Toast.LENGTH_SHORT).show();
            Dexter.withActivity(getActivity()).withPermissions(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION

            ).withListener(new MultiplePermissionsListener() {
                @Override
                public void onPermissionsChecked(MultiplePermissionsReport report) {

                    if (report.areAllPermissionsGranted()) {
                        getLastLocation();
                    } else {
                        WeatherFragment.this.loadWeatherDetails(currentLocation, false);
                    }

                }


                @Override
                public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                    WeatherFragment.this.loadWeatherDetails(currentLocation, false);


                }
            }).check();

        } else {

            locationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    setLocation(location);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    e.printStackTrace();
                    WeatherFragment.this.loadWeatherDetails(currentLocation, false);
                }
            });

        }


    }


    public void setLocation(Location location) {
        this.currentLocation = location;
        WeatherFragment.this.loadWeatherDetails(this.currentLocation, false);
    }


    @Override
    public boolean onOptionsItemSelected(android.view.MenuItem item) {

        if (item.getItemId() == R.id.action_save) {

            WeatherFragment.this.loadWeatherDetails(this.currentLocation, true);

        }
        return super.onOptionsItemSelected(item);
    }

}

