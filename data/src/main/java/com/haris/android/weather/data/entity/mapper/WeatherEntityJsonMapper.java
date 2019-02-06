
package com.haris.android.weather.data.entity.mapper;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.haris.android.weather.data.entity.WeatherEntity;

import java.lang.reflect.Type;

import javax.inject.Inject;


public class WeatherEntityJsonMapper {

    private final Gson gson;

    @Inject
    public WeatherEntityJsonMapper() {
        this.gson = new Gson();
    }


    public WeatherEntity transformWeatherEntity(String weatherJsonResponse) throws JsonSyntaxException {
        final Type weatherEntityType = new TypeToken<WeatherEntity>() {
        }.getType();
        return this.gson.fromJson(weatherJsonResponse, weatherEntityType);
    }

}
