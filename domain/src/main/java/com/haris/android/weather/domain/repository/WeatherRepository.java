
package com.haris.android.weather.domain.repository;

import com.haris.android.weather.domain.DomainWeather;

import io.reactivex.Observable;


public interface WeatherRepository {

    Observable<DomainWeather> weather(String lat, String lng,boolean isForceUpdate);


}
