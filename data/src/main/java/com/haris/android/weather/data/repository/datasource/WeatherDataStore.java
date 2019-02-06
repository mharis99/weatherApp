
package com.haris.android.weather.data.repository.datasource;

import com.haris.android.weather.data.entity.WeatherEntity;

import io.reactivex.Observable;


public interface WeatherDataStore {


    Observable<WeatherEntity> weatherEntityDetails(String lat, String lng);


}
