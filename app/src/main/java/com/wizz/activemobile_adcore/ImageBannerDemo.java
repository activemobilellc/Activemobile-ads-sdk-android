package com.wizz.activemobile_adcore;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.wizz.sdk.ImageBannerAd;

public class ImageBannerDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_banner_demo);

        ImageBannerAd banner= (ImageBannerAd) findViewById(R.id.IBanner);
    }
}