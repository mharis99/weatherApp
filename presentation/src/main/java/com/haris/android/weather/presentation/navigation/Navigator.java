
package com.haris.android.weather.presentation.navigation;

import android.content.Context;
import android.content.Intent;

import com.haris.android.weather.presentation.view.activity.WeatherActivity;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class Navigator {

    @Inject
    public Navigator() {
        //empty
    }

    public void navigateToWeather(Context context) {
        if (context != null) {

            Intent intentToLaunch = WeatherActivity.getCallingIntent(context);
            context.startActivity(intentToLaunch);
        }
    }


}
