
package com.haris.android.weather.presentation.internal.di.components;

import android.app.Activity;

import com.haris.android.weather.presentation.internal.di.PerActivity;
import com.haris.android.weather.presentation.internal.di.modules.ActivityModule;

import dagger.Component;


@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
interface ActivityComponent {
    Activity activity();
}
