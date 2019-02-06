
package com.haris.android.weather.data.net.retrofit;

import com.haris.android.weather.data.entity.WeatherEntity;
import com.haris.android.weather.data.exception.NetworkConnectionException;
import com.haris.android.weather.data.net.RestApi;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitRestApiImpl implements RestApi {

    private Retrofit retrofit;
    private OkHttpClient.Builder builder;
    private WeatherRetrofitApi weatherRetrofitApi;
    private static final long DEFAULT_TIMEOUT = 5;

    public RetrofitRestApiImpl() {
        builder = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .addInterceptor(chain -> {
                    Request original = chain.request();
                    HttpUrl originalHttpUrl = original.url();

                    HttpUrl url = originalHttpUrl.newBuilder()
                            .addQueryParameter("appid", RestApi.APP_ID)
                            .build();

                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.newBuilder().url(url);

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                });

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(simpleCallAdapter)
                .baseUrl(RestApi.API_BASE_URL)
                .build();

        weatherRetrofitApi = retrofit.create(WeatherRetrofitApi.class);
    }

    private final CallAdapter.Factory simpleCallAdapter = new CallAdapter.Factory() {
        @Override
        public CallAdapter<Object, Object> get(final Type returnType, Annotation[] annotations, Retrofit retrofit) {
            // if returnType is retrofit2.Call, do nothing
            if (returnType.toString().contains("retrofit2.Call")) {
                return null;
            }

            return new CallAdapter<Object, Object>() {
                @Override
                public Type responseType() {
                    return returnType;
                }

                @Override
                public Object adapt(Call<Object> call) {
                    try {
                        return call.execute().body();
                    } catch (Exception e) {
                        throw new RuntimeException(e); // do something better
                    }
                }
            };
        }
    };





    @Override
    public Observable<WeatherEntity> weatherEntity(String lat,String lng) {

        return Observable.create(emitter -> {
            try {
                WeatherEntity response = getWeatherFromApi(lat, lng);
                if (response != null) {
                    emitter.onNext(response);
                    emitter.onComplete();
                } else {
                    emitter.onError(new NetworkConnectionException());
                }
            } catch (Exception e) {
                emitter.onError(new NetworkConnectionException(e.getCause()));
            }
        });
    }




    private WeatherEntity getWeatherFromApi(String lat, String lng) {
        return weatherRetrofitApi.getWeatherDetails(lat,lng);
    }


}
