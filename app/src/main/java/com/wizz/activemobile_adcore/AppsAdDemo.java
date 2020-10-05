package com.wizz.activemobile_adcore;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.wizz.sdk.AppWallAd;

public class AppsAdDemo extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps_ad_demo);

        AppWallAd ad= (AppWallAd) findViewById(R.id.appsBanner);

    }
}