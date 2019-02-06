
package com.haris.android.weather.domain.executor;

import io.reactivex.Scheduler;


public interface PostExecutionThread {
    Scheduler getScheduler();
}
