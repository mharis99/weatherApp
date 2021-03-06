
package com.haris.android.weather.test.exception;

import android.test.AndroidTestCase;

import com.haris.android.weather.data.exception.WeatherNotAvailableException;
import com.haris.android.weather.data.exception.NetworkConnectionException;
import com.haris.android.weather.presentation.R;
import com.haris.android.weather.presentation.exception.ErrorMessageFactory;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ErrorMessageFactoryTest extends AndroidTestCase {

  @Override protected void setUp() throws Exception {
    super.setUp();
  }

  public void testNetworkConnectionErrorMessage() {
    String expectedMessage = getContext().getString(R.string.exception_message_no_connection);
    String actualMessage = ErrorMessageFactory.create(getContext(),
        new NetworkConnectionException());

    assertThat(actualMessage, is(equalTo(expectedMessage)));
  }

  public void testWeatherNotFoundErrorMessage() {
    String expectedMessage = getContext().getString(R.string.exception_message_weather_not_found);
    String actualMessage = ErrorMessageFactory.create(getContext(), new WeatherNotAvailableException());

    assertThat(actualMessage, is(equalTo(expectedMessage)));
  }
}
