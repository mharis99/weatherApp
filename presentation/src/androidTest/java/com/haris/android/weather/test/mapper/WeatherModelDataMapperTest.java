
package com.haris.android.weather.test.mapper;

import com.haris.android.weather.domain.DomainWeather;
import com.haris.android.weather.presentation.mapper.WeatherModelDataMapper;

import junit.framework.TestCase;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class WeatherModelDataMapperTest extends TestCase {

  private static final int FAKE_WEATHER_ID = 123;
  private static final String FAKE_NAME = "Latvia";

  private WeatherModelDataMapper weatherModelDataMapper;

  @Override protected void setUp() throws Exception {
    super.setUp();
    weatherModelDataMapper = new WeatherModelDataMapper();
  }

  public void testTransformWeather() {
    DomainWeather weather = createFakeWeather();
    WeatherModel weatherModel = weatherModelDataMapper.transform(weather);

    assertThat(weatherModel, is(instanceOf(WeatherModel.class)));
    assertThat(weatherModel.getId(), is(FAKE_WEATHER_ID));
    assertThat(weatherModel.getName(), is(FAKE_NAME));
  }



  private DomainWeather createFakeWeather() {
    DomainWeather weather = new DomainWeather(FAKE_WEATHER_ID);
    weather.setName(FAKE_NAME);

    return weather;
  }
}
