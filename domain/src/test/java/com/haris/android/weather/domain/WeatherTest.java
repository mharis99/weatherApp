
package com.haris.android.weather.domain;



import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WeatherTest {

  private static final long FAKE_WEATHER_ID = 8;

  private DomainWeather weather;

  @Before
  public void setUp() {
    weather = new DomainWeather(FAKE_WEATHER_ID);
  }

  @Test
  public void testWeatherConstructorHappyCase() {
    final long weatherId = weather.getId();

    assertThat(weatherId).isEqualTo(FAKE_WEATHER_ID);
  }
}
