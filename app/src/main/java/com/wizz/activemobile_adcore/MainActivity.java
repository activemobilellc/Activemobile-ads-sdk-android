package com.wizz.activemobile_adcore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.wizz.sdk.VideoAd;

public class MainActivity extends AppCompatActivity {

    Button text, image, redirect, apps, videos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text= (Button) findViewById(R.id.Text_Banner);
        image=(Button) findViewById(R.id.Image_Banner);
        redirect= (Button) findViewById(R.id.Redirect_Ad);
        apps= (Button) findViewById(R.id.Apps_Ad);
        videos= (Button) findViewById(R.id.Video_Ad);

        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, TextBannerDemo.class);
                startActivity(intent);
            }
        });

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, ImageBannerDemo.class);
                startActivity(intent);
            }
        });

        redirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, RedirectAdsDemo.class);
                startActivity(intent);
            }
        });

        apps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this, AppsAdDemo.class);
                startActivity(intent);
            }
        });

        videos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VideoAd ads= new VideoAd(MainActivity.this, getApplicationContext());
                ads.runAd();
            }
        });

    }
}