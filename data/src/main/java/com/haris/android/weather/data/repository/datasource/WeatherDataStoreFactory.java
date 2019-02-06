
package com.haris.android.weather.data.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import com.haris.android.weather.data.cache.WeatherCache;
import com.haris.android.weather.data.net.RestApi;
import com.haris.android.weather.data.net.retrofit.RetrofitRestApiImpl;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class WeatherDataStoreFactory {

    private final Context context;
    private final WeatherCache weatherCache;

    @Inject
    WeatherDataStoreFactory(@NonNull Context context, @NonNull WeatherCache weatherCache) {
        this.context = context.getApplicationContext();
        this.weatherCache = weatherCache;
    }


    public WeatherDataStore create(int weatherId, boolean isForceCloudUpdate) {
        WeatherDataStore weatherDataStore;

        if (!this.weatherCache.isExpired() && this.weatherCache.isCached(weatherId) && !isForceCloudUpdate) {
            weatherDataStore = new DiskWeatherDataStore(this.weatherCache);
        } else {
            weatherDataStore = createCloudDataStore();
        }


        return weatherDataStore;
    }


    public WeatherDataStore createCloudDataStore() {
        final RestApi restApi = new RetrofitRestApiImpl();
        return new CloudWeatherDataStore(restApi, this.weatherCache);
    }
}
