
package com.haris.android.weather.presentation.internal.di.components;

import com.haris.android.weather.presentation.internal.di.PerActivity;
import com.haris.android.weather.presentation.internal.di.modules.ActivityModule;
import com.haris.android.weather.presentation.internal.di.modules.WeatherModule;
import com.haris.android.weather.presentation.view.fragment.WeatherFragment;

import dagger.Component;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, WeatherModule.class})
public interface WeatherComponent extends ActivityComponent {
    void inject(WeatherFragment weatherFragment);
}
