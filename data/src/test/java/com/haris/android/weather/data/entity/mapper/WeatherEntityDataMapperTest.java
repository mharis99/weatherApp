
package com.haris.android.weather.data.entity.mapper;

import com.haris.android.weather.data.entity.WeatherEntity;
import com.haris.android.weather.domain.DomainWeather;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;



import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(MockitoJUnitRunner.class)
public class WeatherEntityDataMapperTest {

  private static final int FAKE_WEATHER_ID = 123;
  private static final String FAKE_NAME = "Karachi";

  private WeatherEntityDataMapper weatherEntityDataMapper;

  @Before
  public void setUp() throws Exception {
    weatherEntityDataMapper = new WeatherEntityDataMapper();
  }

  @Test
  public void testTransformWeatherEntity() {
    WeatherEntity weatherEntity = createFakeWeatherEntity();
    DomainWeather weather = weatherEntityDataMapper.transform(weatherEntity);

    assertThat(weather, is(instanceOf(DomainWeather.class)));
    assertThat(weather.getId(), is(FAKE_WEATHER_ID));
    assertThat(weather.getName(), is(FAKE_NAME));
  }



  private WeatherEntity createFakeWeatherEntity() {
    WeatherEntity weatherEntity = new WeatherEntity();
    weatherEntity.setId(FAKE_WEATHER_ID);
    weatherEntity.setName(FAKE_NAME);

    return weatherEntity;
  }
}
