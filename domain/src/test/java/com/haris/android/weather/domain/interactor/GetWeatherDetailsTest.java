
package com.haris.android.weather.domain.interactor;

import com.haris.android.weather.domain.executor.PostExecutionThread;
import com.haris.android.weather.domain.executor.ThreadExecutor;
import com.haris.android.weather.domain.interactor.GetWeatherDetails.Params;
import com.haris.android.weather.domain.repository.WeatherRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

@RunWith(MockitoJUnitRunner.class)
public class GetWeatherDetailsTest {

    private static final String FAKE_LAT = "25";

    private static final String FAKE_LNG = "55";

  private GetWeatherDetails getWeatherDetails;

  @Mock private WeatherRepository mockWeatherRepository;
  @Mock private ThreadExecutor mockThreadExecutor;
  @Mock private PostExecutionThread mockPostExecutionThread;

  @Rule public ExpectedException expectedException = ExpectedException.none();

  @Before
  public void setUp() {
    getWeatherDetails = new GetWeatherDetails(mockWeatherRepository, mockThreadExecutor,
        mockPostExecutionThread);
  }

  @Test
  public void testGetWeatherDetailsUseCaseObservableHappyCase() {
    getWeatherDetails.buildUseCaseObservable(Params.forWeather(FAKE_LAT,FAKE_LNG,true));

    verify(mockWeatherRepository).weather(FAKE_LAT,FAKE_LNG,true);
    verifyNoMoreInteractions(mockWeatherRepository);
    verifyZeroInteractions(mockPostExecutionThread);
    verifyZeroInteractions(mockThreadExecutor);
  }

  @Test
  public void testShouldFailWhenNoOrEmptyParameters() {
    expectedException.expect(NullPointerException.class);
    getWeatherDetails.buildUseCaseObservable(null);
  }
}
