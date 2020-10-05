package com.wizz.sdk;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class AppWallAd extends LinearLayout {
    Context context;
    AppsAd ad;
    ImageView imgClose, imgInfo;
    TextView txtClosed;
    protected static LinearLayout top;

    public AppWallAd(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context= context;
        init(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ad= (AppsAd) findViewById(R.id.app_ads);
        imgInfo= (ImageView) findViewById(R.id.imgInfo);
        imgClose=  (ImageView) findViewById(R.id.imgClose);
        txtClosed= (TextView) findViewById(R.id.txtClosed);
        top= (LinearLayout) findViewById(R.id.top_layout1);

        imgClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ad.setVisibility(GONE);
                txtClosed.setVisibility(VISIBLE);
            }
        });
    }

    protected void initView(){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.custom_app_wall, this, true);
    }
}
