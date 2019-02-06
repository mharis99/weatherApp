
package com.haris.android.weather.test.presenter;

import android.content.Context;

import com.haris.android.weather.domain.interactor.GetWeatherDetails;
import com.haris.android.weather.domain.interactor.GetWeatherDetails.Params;
import com.haris.android.weather.presentation.mapper.WeatherModelDataMapper;
import com.haris.android.weather.presentation.presenter.WeatherPresenter;
import com.haris.android.weather.presentation.view.WeatherView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.observers.DisposableObserver;

import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class WeatherDetailsPresenterTest {

    private static final String FAKE_LAT = "25";

    private static final String FAKE_LNG = "55";

  private WeatherPresenter weatherDetailsPresenter;

  @Mock private Context mockContext;
  @Mock private WeatherView mockWeatherDetailsView;
  @Mock private GetWeatherDetails mockGetWeatherDetails;
  @Mock private WeatherModelDataMapper mockWeatherModelDataMapper;

  @Before
  public void setUp() {
    weatherDetailsPresenter = new WeatherPresenter(mockGetWeatherDetails, mockWeatherModelDataMapper);
    weatherDetailsPresenter.setView(mockWeatherDetailsView);
  }

  @Test
  @SuppressWarnings("unchecked")
  public void testWeatherDetailsPresenterInitialize() {
    given(mockWeatherDetailsView.context()).willReturn(mockContext);

    weatherDetailsPresenter.initialize(FAKE_LAT,FAKE_LNG,true);

    verify(mockWeatherDetailsView).hideRetry();
    verify(mockWeatherDetailsView).showLoading();
    verify(mockGetWeatherDetails).execute(any(DisposableObserver.class), any(Params.class));
  }
}
