
package com.haris.android.weather.presentation.internal.di.components;

import android.content.Context;

import com.haris.android.weather.domain.executor.PostExecutionThread;
import com.haris.android.weather.domain.executor.ThreadExecutor;
import com.haris.android.weather.domain.repository.WeatherRepository;
import com.haris.android.weather.presentation.view.activity.BaseActivity;
import com.haris.android.weather.presentation.internal.di.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);


    Context context();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();

    WeatherRepository weatherRepository();
}
