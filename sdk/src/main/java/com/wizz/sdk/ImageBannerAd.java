package com.wizz.sdk;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class ImageBannerAd extends LinearLayout {

    Context context;
    ImageBanner imageBanner;
    ImageView imgClose, imgInfo;
    protected static LinearLayout top;
    TextView txtClosed;

    public ImageBannerAd(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context= context;
        init(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        imageBanner= (ImageBanner) findViewById(R.id.imgBanner);
        imgInfo= (ImageView) findViewById(R.id.imgInfo);
        imgClose=  (ImageView) findViewById(R.id.imgClose);
        txtClosed= (TextView) findViewById(R.id.txtClosed);
        top= (LinearLayout) findViewById(R.id.top_layout2);

        imgClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                imageBanner.setVisibility(GONE);
                txtClosed.setVisibility(VISIBLE);
            }
        });

//        Thread t1= new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true){
//                    try {
//                        Thread.sleep(5000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    Log.d(TAG, "run: bhens");
//                    imageBanner.invalidate();
//                }
//            }
//        });
//        t1.start();
    }

    protected void initView(){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.custom_image_banner, this, true);
    }
}
