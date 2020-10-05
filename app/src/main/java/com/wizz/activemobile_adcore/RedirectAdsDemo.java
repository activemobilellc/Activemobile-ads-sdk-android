package com.wizz.activemobile_adcore;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.wizz.sdk.RedirectAd;

public class RedirectAdsDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_redirect_ads_demo);

        RedirectAd redirectAd= new RedirectAd(getApplicationContext());
        redirectAd.recieveUrl();
    }
}