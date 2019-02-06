package com.haris.android.weather.presentation.presenter;

import android.support.annotation.NonNull;

import com.haris.android.weather.domain.DomainWeather;
import com.haris.android.weather.domain.exception.DefaultErrorBundle;
import com.haris.android.weather.domain.exception.ErrorBundle;
import com.haris.android.weather.domain.interactor.DefaultObserver;
import com.haris.android.weather.domain.interactor.GetWeatherDetails;

import com.haris.android.weather.presentation.model.WeatherModel;
import com.haris.android.weather.presentation.timer.WeatherInterval;
import com.haris.android.weather.presentation.timer.WeatherTimer;
import com.haris.android.weather.presentation.exception.ErrorMessageFactory;
import com.haris.android.weather.presentation.internal.di.PerActivity;
import com.haris.android.weather.presentation.mapper.WeatherModelDataMapper;

import com.haris.android.weather.presentation.view.WeatherView;

import javax.inject.Inject;


@PerActivity
public class WeatherPresenter implements Presenter, WeatherInterval {

    private WeatherView viewDetailsView;

    private final GetWeatherDetails getWeatherDetailsUseCase;
    private final WeatherModelDataMapper weatherModelDataMapper;
    private String latitude;
    private String longitude;
    private WeatherTimer weatherTimer;

    @Inject
    public WeatherPresenter(GetWeatherDetails getWeatherDetailsUseCase, WeatherModelDataMapper weatherModelDataMapper) {
        this.getWeatherDetailsUseCase = getWeatherDetailsUseCase;
        this.weatherModelDataMapper = weatherModelDataMapper;
    }

    public void setView(@NonNull WeatherView view) {
        this.viewDetailsView = view;
    }

    @Override
    public void resume() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void destroy() {
        this.getWeatherDetailsUseCase.dispose();
        this.viewDetailsView = null;
        this.weatherTimer.cancel();
    }


    public void initialize(String lat, String lng, boolean isForceUpdate) {
        this.hideViewRetry();
        this.showViewLoading();
        this.latitude = lat;
        this.longitude = lng;
        this.weatherTimer = new WeatherTimer(this);
        this.getWeatherDetails(lat, lng, isForceUpdate);

    }

    private void getWeatherDetails(String lat, String lng, boolean isForceUpdate) {
        this.getWeatherDetailsUseCase.execute(new WeatherDetailsObserver(), GetWeatherDetails.Params.forWeather(lat, lng, isForceUpdate));
    }

    private void showViewLoading() {
        this.viewDetailsView.showLoading();
    }

    private void hideViewLoading() {
        this.viewDetailsView.hideLoading();
    }

    private void showViewRetry() {
        this.viewDetailsView.showRetry();
    }

    private void hideViewRetry() {
        this.viewDetailsView.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.viewDetailsView.context(), errorBundle.getException());
        this.viewDetailsView.showError(errorMessage);
    }

    private void showWeatherDetailsInView(DomainWeather weather) {
        final WeatherModel weatherModel = this.weatherModelDataMapper.transform(weather);
        this.viewDetailsView.renderWeather(weatherModel);
    }

    @Override
    public void onWeatherIntervalUpdate() {
        this.getWeatherDetails(this.latitude, this.longitude, true);

    }

    private final class WeatherDetailsObserver extends DefaultObserver<DomainWeather> {

        @Override
        public void onComplete() {
            WeatherPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            WeatherPresenter.this.hideViewLoading();
            WeatherPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            WeatherPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(DomainWeather weather) {
            WeatherPresenter.this.showWeatherDetailsInView(weather);
        }
    }


}










