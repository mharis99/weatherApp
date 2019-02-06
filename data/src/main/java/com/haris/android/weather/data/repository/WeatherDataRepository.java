
package com.haris.android.weather.data.repository;

import com.haris.android.weather.data.entity.mapper.WeatherEntityDataMapper;
import com.haris.android.weather.data.repository.datasource.WeatherDataStore;
import com.haris.android.weather.data.repository.datasource.WeatherDataStoreFactory;
import com.haris.android.weather.domain.DomainWeather;
import com.haris.android.weather.domain.repository.WeatherRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Observable;


@Singleton
public class WeatherDataRepository implements WeatherRepository {

    private final WeatherDataStoreFactory weatherDataStoreFactory;
    private final WeatherEntityDataMapper weatherEntityDataMapper;


    @Inject
    WeatherDataRepository(WeatherDataStoreFactory dataStoreFactory,
                          WeatherEntityDataMapper weatherEntityDataMapper) {
        this.weatherDataStoreFactory = dataStoreFactory;
        this.weatherEntityDataMapper = weatherEntityDataMapper;
    }


    @Override
    public Observable<DomainWeather> weather(String lat, String lng, boolean isForceUpdate) {
        final WeatherDataStore weatherDataStore = this.weatherDataStoreFactory.create(0,isForceUpdate);
        return weatherDataStore.weatherEntityDetails(lat, lng).map(this.weatherEntityDataMapper::transform);
    }
}
