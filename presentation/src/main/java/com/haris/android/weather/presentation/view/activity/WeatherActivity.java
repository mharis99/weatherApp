package com.haris.android.weather.presentation.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;

import com.haris.android.weather.presentation.R;
import com.haris.android.weather.presentation.internal.di.HasComponent;

import com.haris.android.weather.presentation.internal.di.components.DaggerWeatherComponent;
import com.haris.android.weather.presentation.internal.di.components.WeatherComponent;
import com.haris.android.weather.presentation.view.fragment.WeatherFragment;

public class WeatherActivity extends BaseActivity implements HasComponent<WeatherComponent> {


    public static Intent getCallingIntent(Context context) {
        Intent callingIntent = new Intent(context, WeatherActivity.class);
        return callingIntent;
    }

    private WeatherComponent weatherComponent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_weather);

        this.initializeActivity();
        this.initializeInjector();

    }




    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        android.view.MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }


    private void initializeActivity() {
        addFragment(R.id.fragmentContainer, WeatherFragment.forWeather());
    }

    private void initializeInjector() {
        this.weatherComponent = DaggerWeatherComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
    }


    @Override
    public WeatherComponent getComponent() {
        return weatherComponent;
    }


    @Override
    protected void onPause() {
        super.onPause();

    }




}



