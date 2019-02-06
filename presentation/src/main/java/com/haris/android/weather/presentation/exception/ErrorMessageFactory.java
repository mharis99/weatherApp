
package com.haris.android.weather.presentation.exception;

import android.content.Context;

import com.haris.android.weather.data.exception.WeatherNotAvailableException;
import com.haris.android.weather.data.exception.NetworkConnectionException;
import com.haris.android.weather.presentation.R;


public class ErrorMessageFactory {

    private ErrorMessageFactory() {
        //empty
    }


    public static String create(Context context, Exception exception) {
        String message = context.getString(R.string.exception_message_generic);

        if (exception instanceof NetworkConnectionException) {
            message = context.getString(R.string.exception_message_no_connection);
        } else if (exception instanceof WeatherNotAvailableException) {
            message = context.getString(R.string.exception_message_weather_not_found);
        }

        return message;
    }
}
