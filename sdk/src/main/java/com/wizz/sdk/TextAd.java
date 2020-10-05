package com.wizz.sdk;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class TextAd extends LinearLayout {
    Context context;
    TextBanner txtBanner;
    ImageView imgClose, imgInfo;
    TextView txtClosed;
    protected static LinearLayout top;

    public TextAd(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context= context;
        init(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        txtBanner= (TextBanner) findViewById(R.id.txtBanner);
        imgInfo= (ImageView) findViewById(R.id.imgInfo);
        imgClose=  (ImageView) findViewById(R.id.imgClose);
        txtClosed= (TextView) findViewById(R.id.txtClosed);
        top= (LinearLayout) findViewById(R.id.top_layout);

        imgClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                txtBanner.setVisibility(GONE);
                txtClosed.setVisibility(VISIBLE);
            }
        });
        Thread t1= new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    txtBanner.invalidate();
                }
            }
        });
        t1.start();

    }

    protected void initView(){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.custom_text_banner, this, true);
    }
}
