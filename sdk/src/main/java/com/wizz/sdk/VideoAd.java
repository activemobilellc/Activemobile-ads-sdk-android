package com.wizz.sdk;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class VideoAd {
    Context context;
    Activity activity;
    public VideoAd(Activity activity, Context context){
        this.activity= activity;
        this.context= context;
    }

    public void runAd(){
        Intent intent= new Intent(activity, Temp123Class.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}
