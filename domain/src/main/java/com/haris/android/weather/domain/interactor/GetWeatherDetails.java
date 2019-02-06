package com.haris.android.weather.domain.interactor;

import com.haris.android.weather.domain.DomainWeather;
import com.haris.android.weather.domain.executor.PostExecutionThread;
import com.haris.android.weather.domain.executor.ThreadExecutor;
import com.haris.android.weather.domain.repository.WeatherRepository;

import javax.inject.Inject;

import io.reactivex.Observable;


public class GetWeatherDetails extends UseCase<DomainWeather, GetWeatherDetails.Params> {

    private final WeatherRepository weatherRepository;

    @Inject
    GetWeatherDetails(WeatherRepository weatherRepository, ThreadExecutor threadExecutor,
                      PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.weatherRepository = weatherRepository;
    }


    @Override
    Observable<DomainWeather> buildUseCaseObservable(Params params) {
        return this.weatherRepository.weather(params.lat, params.lng, params.isForceUpdate);
    }


    public static final class Params {

        private final String lat;
        private final String lng;
        private final boolean isForceUpdate;

        private Params(String lat, String lng, boolean isForceUpdate) {

            this.lat = lat;
            this.lng = lng;
            this.isForceUpdate=isForceUpdate;
        }

        public static GetWeatherDetails.Params forWeather(String lat, String lng, boolean isForceUpdate) {
            return new GetWeatherDetails.Params(lat, lng, isForceUpdate);
        }
    }



}
