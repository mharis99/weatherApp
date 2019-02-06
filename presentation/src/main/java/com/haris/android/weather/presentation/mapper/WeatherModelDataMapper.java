package com.haris.android.weather.presentation.mapper;

import com.haris.android.weather.domain.DomainWeather;

import com.haris.android.weather.presentation.internal.di.PerActivity;
import com.haris.android.weather.presentation.model.WeatherModel;

import javax.inject.Inject;


@PerActivity
public class WeatherModelDataMapper {

    @Inject
    public WeatherModelDataMapper() {
    }

    public WeatherModel transform(DomainWeather weather) {
        if (weather == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final WeatherModel weatherModel = new WeatherModel();

        weatherModel.setMain(new WeatherModel.Main(weather.getMain().getTemp()));
        weatherModel.setName(weather.getName());

        weatherModel.setWeatherTime(weather.getWeatherTime());


        return weatherModel;
    }


}

