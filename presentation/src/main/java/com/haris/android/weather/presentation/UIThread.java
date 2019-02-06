
package com.haris.android.weather.presentation;

import com.haris.android.weather.domain.executor.PostExecutionThread;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

import javax.inject.Inject;
import javax.inject.Singleton;


@Singleton
public class UIThread implements PostExecutionThread {

    @Inject
    UIThread() {
    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
