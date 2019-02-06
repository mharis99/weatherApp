
package com.haris.android.weather.data.repository.datasource;

import com.haris.android.weather.data.ApplicationTestCase;
import com.haris.android.weather.data.cache.WeatherCache;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.robolectric.RuntimeEnvironment;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

public class WeatherDataStoreFactoryTest extends ApplicationTestCase {

  private static final int FAKE_WEATHER_ID = 11;

  private WeatherDataStoreFactory weatherDataStoreFactory;

  @Mock private WeatherCache mockWeatherCache;

  @Before
  public void setUp() {
    weatherDataStoreFactory = new WeatherDataStoreFactory(RuntimeEnvironment.application, mockWeatherCache);
  }

  @Test
  public void testCreateDiskDataStore() {
    given(mockWeatherCache.isCached(FAKE_WEATHER_ID)).willReturn(true);
    given(mockWeatherCache.isExpired()).willReturn(false);

    WeatherDataStore weatherDataStore = weatherDataStoreFactory.create(FAKE_WEATHER_ID);

    assertThat(weatherDataStore, is(notNullValue()));
    assertThat(weatherDataStore, is(instanceOf(DiskWeatherDataStore.class)));

    verify(mockWeatherCache).isCached(FAKE_WEATHER_ID);
    verify(mockWeatherCache).isExpired();
  }

  @Test
  public void testCreateCloudDataStore() {
    given(mockWeatherCache.isExpired()).willReturn(true);
    given(mockWeatherCache.isCached(FAKE_WEATHER_ID)).willReturn(false);

    WeatherDataStore weatherDataStore = weatherDataStoreFactory.create(FAKE_WEATHER_ID,true);

    assertThat(weatherDataStore, is(notNullValue()));
    assertThat(weatherDataStore, is(instanceOf(CloudWeatherDataStore.class)));

    verify(mockWeatherCache).isExpired();
  }
}
