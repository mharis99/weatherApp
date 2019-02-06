package com.haris.android.weather.presentation.view.activity;

import android.os.Bundle;
import android.widget.Button;

import com.haris.android.weather.presentation.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {

    @Bind(R.id.btn_checkWeather)
    Button btn_checkWeather;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setActionBarIcon();


    }

    private void setActionBarIcon() {
    }


    @OnClick(R.id.btn_checkWeather)
    void navigateToWeatherDetails() {
        this.navigator.navigateToWeather(this);
    }

}
