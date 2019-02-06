
package com.haris.android.weather.data.repository.datasource;

import com.haris.android.weather.data.cache.WeatherCache;
import com.haris.android.weather.data.entity.WeatherEntity;

import io.reactivex.Observable;


class DiskWeatherDataStore implements WeatherDataStore {

    private final WeatherCache weatherCache;



    DiskWeatherDataStore(WeatherCache weatherCache) {
        this.weatherCache = weatherCache;
    }


    @Override
    public Observable<WeatherEntity> weatherEntityDetails(String lat, String lng) {
        return this.weatherCache.get(lat, lng);
    }
}
