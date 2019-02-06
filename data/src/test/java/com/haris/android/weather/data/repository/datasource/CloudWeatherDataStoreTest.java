
package com.haris.android.weather.data.repository.datasource;

import com.haris.android.weather.data.cache.WeatherCache;

import com.haris.android.weather.data.entity.Response;
import com.haris.android.weather.data.net.RestApi;
import com.haris.android.weather.data.entity.WeatherEntity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Observable;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class CloudWeatherDataStoreTest {

    private static final String FAKE_LAT = "25";

    private static final String FAKE_LNG = "55";


    private CloudWeatherDataStore cloudWeatherDataStore;

    @Mock
    private RestApi mockRestApi;
    @Mock
    private WeatherCache mockWeatherCache;

    @Before
    public void setUp() {
        cloudWeatherDataStore = new CloudWeatherDataStore(mockRestApi, mockWeatherCache);
    }



    @Test
    public void testGetWeatherEntityDetailsFromApi() {
        WeatherEntity fakeWeatherEntity = new WeatherEntity();
        Observable<WeatherEntity> fakeObservable = Observable.just(fakeWeatherEntity);
        given(mockRestApi.weatherEntity(FAKE_LAT,FAKE_LNG)).willReturn(fakeObservable);

        cloudWeatherDataStore.weatherEntityDetails(FAKE_LAT,FAKE_LNG);

        verify(mockRestApi).weatherEntity(FAKE_LAT,FAKE_LNG);
    }


}
