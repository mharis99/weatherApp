package com.haris.android.weather.presentation.view;

import com.haris.android.weather.presentation.model.WeatherModel;

public interface WeatherView extends LoadDataView {

    void renderWeather(WeatherModel weatherModel);
}
