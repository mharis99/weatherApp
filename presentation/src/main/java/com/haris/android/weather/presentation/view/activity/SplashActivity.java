package com.haris.android.weather.presentation.view.activity;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.haris.android.weather.presentation.R;

public class SplashActivity extends AppCompatActivity {


    int SPLASH_TIME_OUT = 3000;// 3 secs


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                startActivity(new Intent(SplashActivity.this, MainActivity.class));

                finish();
            }
        }, SPLASH_TIME_OUT);


    }
}