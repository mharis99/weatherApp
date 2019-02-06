
package com.haris.android.weather.data.cache.serializer;

import com.haris.android.weather.data.entity.WeatherEntity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class SerializerTest {



    private static String JSON_RESPONSE="{\"coord\":" +
     "{\"lon\":139.01,\"lat\":35.02}," +
      "\"weather\":" +
       "[{\"id\":800,\"main\":\"Clear\",\"description\":\"clear sky\",\"icon\":\"01n\"}]," +
        "\"base\":\"stations\",\"main\":" +
         "{\"temp\":285.514,\"pressure\":1013.75,\"humidity\":100,\"temp_min\":285.514,\"temp_max\":285.514,\"s" +
          "ea_level\":1023.22,\"grnd_level\":1013.75},\"wind\":" +
           "{\"speed\":5.52,\"deg\":311},\"clouds\":{\"all\":0},\"dt\":1485792967,\"sys\":" +
            "{\"message\":0.0025,\"country\":\"JP\",\"sunrise\":1485726240,\"sunset\":1485763863},\"" +
             "id\":1907296,\"name\":\"Tawarano\",\"cod\":200}";


  private Serializer serializer;

  @Before
  public void setUp() {
    serializer = new Serializer();
  }

  @Test
  public void testSerializeHappyCase() {
    final WeatherEntity weatherEntityOne = serializer.deserialize(JSON_RESPONSE, WeatherEntity.class);
    final String jsonString = serializer.serialize(weatherEntityOne, WeatherEntity.class);
    final WeatherEntity weatherEntityTwo = serializer.deserialize(jsonString, WeatherEntity.class);

    assertThat(weatherEntityOne.getId(), is(weatherEntityTwo.getId()));
    assertThat(weatherEntityOne.getName(), is(equalTo(weatherEntityTwo.getName())));
  }

  @Test
  public void testDesearializeHappyCase() {
    final WeatherEntity weatherEntity = serializer.deserialize(JSON_RESPONSE, WeatherEntity.class);

    assertThat(weatherEntity.getId(), is(1));
    assertThat(weatherEntity.getName(), is("Dubai"));
  }
}
