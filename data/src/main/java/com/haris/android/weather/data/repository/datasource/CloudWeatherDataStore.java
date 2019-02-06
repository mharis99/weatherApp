
package com.haris.android.weather.data.repository.datasource;

import com.haris.android.weather.data.cache.WeatherCache;
import com.haris.android.weather.data.entity.WeatherEntity;
import com.haris.android.weather.data.net.RestApi;

import io.reactivex.Observable;


class CloudWeatherDataStore implements WeatherDataStore {

    private final RestApi restApi;
    private final WeatherCache weatherCache;


    CloudWeatherDataStore(RestApi restApi, WeatherCache weatherCache) {
        this.restApi = restApi;
        this.weatherCache = weatherCache;
    }


    @Override
    public Observable<WeatherEntity> weatherEntityDetails(String lat, String lng) {
        return this.restApi.weatherEntity(lat, lng).doOnNext(CloudWeatherDataStore.this.weatherCache::put);
    }

}
