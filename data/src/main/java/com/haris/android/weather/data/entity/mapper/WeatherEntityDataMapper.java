
package com.haris.android.weather.data.entity.mapper;

import com.haris.android.weather.data.entity.WeatherEntity;
import com.haris.android.weather.domain.DomainWeather;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class WeatherEntityDataMapper {

    @Inject
    WeatherEntityDataMapper() {
    }


    public DomainWeather transform(WeatherEntity weather) {
        if (weather == null) {
            throw new IllegalArgumentException("Cannot transform a null value");
        }
        final DomainWeather weatherModel = new DomainWeather();

        weatherModel.setMain(new DomainWeather.Main(weather.getMain().getTemp()));

        weatherModel.setName(weather.getName());

        weatherModel.setWeatherTime(weather.getWeatherTime());


        return weatherModel;
    }


}
