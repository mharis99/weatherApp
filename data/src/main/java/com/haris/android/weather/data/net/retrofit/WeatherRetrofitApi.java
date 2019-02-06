package com.haris.android.weather.data.net.retrofit;

import com.haris.android.weather.data.entity.WeatherEntity;
import com.haris.android.weather.data.net.RestApi;

import retrofit2.http.GET;
import retrofit2.http.Query;


public interface WeatherRetrofitApi {

    @GET(RestApi.WEATHER)
    WeatherEntity getWeatherDetails(@Query("lat") String lat, @Query("lon") String lng);
}
