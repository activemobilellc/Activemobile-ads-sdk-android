package com.wizz.activemobile_adcore;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.wizz.sdk.TextAd;

public class TextBannerDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_banner_demo);

        TextAd banner= (TextAd) findViewById(R.id.TBanner);
    }
}