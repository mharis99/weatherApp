
package com.haris.android.weather.presentation.internal.di.modules;

import android.content.Context;

import com.haris.android.weather.data.cache.WeatherCache;
import com.haris.android.weather.data.cache.WeatherCacheImpl;
import com.haris.android.weather.data.executor.JobExecutor;
import com.haris.android.weather.data.repository.WeatherDataRepository;
import com.haris.android.weather.domain.executor.PostExecutionThread;
import com.haris.android.weather.domain.executor.ThreadExecutor;
import com.haris.android.weather.domain.repository.WeatherRepository;
import com.haris.android.weather.presentation.AndroidApplication;
import com.haris.android.weather.presentation.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;


@Module
public class ApplicationModule {
    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides
    @Singleton
    WeatherCache provideWeatherCache(WeatherCacheImpl WeatherCache) {
        return WeatherCache;
    }

    @Provides
    @Singleton
    WeatherRepository provideWeatherRepository(WeatherDataRepository WeatherDataRepository) {
        return WeatherDataRepository;
    }
}
