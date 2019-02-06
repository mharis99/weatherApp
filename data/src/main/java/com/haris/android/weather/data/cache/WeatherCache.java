
package com.haris.android.weather.data.cache;

import com.haris.android.weather.data.entity.WeatherEntity;

import io.reactivex.Observable;

public interface WeatherCache {


    Observable<WeatherEntity> get(String lat, String lng);


    void put(WeatherEntity weatherEntity);


    boolean isCached(final int weatherId);


    boolean isExpired();

    void evictAll();
}
