package com.haris.android.weather.presentation.timer;


import java.util.Timer;
import java.util.TimerTask;

public class WeatherTimer extends TimerTask {

    private Timer timer;

    private WeatherInterval weatherTimeUpdate;

    private boolean hasStarted = false;

    public WeatherTimer(WeatherInterval timeUpdate) {
        weatherTimeUpdate = timeUpdate;
        timer = new Timer();
        timer.scheduleAtFixedRate(this, 0, 600000);
    }

    @Override
    public void run() {
        this.hasStarted = true;
        this.weatherTimeUpdate.onWeatherIntervalUpdate();
    }

    public boolean hasRunStarted() {
        return this.hasStarted;
    }


}