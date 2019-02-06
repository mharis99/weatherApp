
package com.haris.android.weather.data.net;

import com.haris.android.weather.data.entity.WeatherEntity;

import io.reactivex.Observable;


public interface RestApi {


    String API_BASE_URL = "http://api.openweathermap.org/";

    String WEATHER = "data/2.5/weather";

    String APP_ID = "cda64c2235cb297ce21ec796ed8d2048";


    Observable<WeatherEntity> weatherEntity(String lat, String lng);

}
