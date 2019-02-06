
package com.haris.android.weather.data.repository;

import com.haris.android.weather.data.entity.WeatherEntity;
import com.haris.android.weather.data.entity.mapper.WeatherEntityDataMapper;
import com.haris.android.weather.data.repository.datasource.WeatherDataStore;
import com.haris.android.weather.data.repository.datasource.WeatherDataStoreFactory;


import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class WeatherDataRepositoryTest {

    private static final String FAKE_LAT = "25";
    private static final String FAKE_LNG = "55";

  private static final int FAKE_WEATHER_ID = 123;


  private WeatherDataRepository weatherDataRepository;

  @Mock private WeatherDataStoreFactory mockWeatherDataStoreFactory;
  @Mock private WeatherEntityDataMapper mockWeatherEntityDataMapper;
  @Mock private WeatherDataStore mockWeatherDataStore;



  @Before
  public void setUp() {
    weatherDataRepository = new WeatherDataRepository(mockWeatherDataStoreFactory, mockWeatherEntityDataMapper);
    given(mockWeatherDataStoreFactory.create(anyInt())).willReturn(mockWeatherDataStore);
    given(mockWeatherDataStoreFactory.createCloudDataStore()).willReturn(mockWeatherDataStore);
  }



  @Test
  public void testGetWeatherHappyCase() {
    WeatherEntity weatherEntity = new WeatherEntity();
    given(mockWeatherDataStore.weatherEntityDetails(FAKE_LAT,FAKE_LNG)).willReturn(Observable.just(weatherEntity));
    weatherDataRepository.weather(FAKE_LAT,FAKE_LNG);

    verify(mockWeatherDataStoreFactory).create(FAKE_WEATHER_ID);
    verify(mockWeatherDataStore).weatherEntityDetails(FAKE_LAT,FAKE_LNG);
  }
}
