package com.wizz.activemobile_adcore;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Application extends android.app.Application {

    public static final String TAG = Application.class
            .getSimpleName();

    private RequestQueue mRequestQueue;
    private static Context mContext;

    private static Application mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mContext = getApplicationContext();
//        PaymentConfiguration.init(
//                getApplicationContext(),
//                getResources().getString(R.string.Publishablekey));
        //FontsOverride.setDefaultFont(this, "MONOSPACE", "segoeui.TTF");
       // MultiDex.install(this);
        handleSSLHandshake();
        Log.e("DefaultOn","On");
        //http://162.244.77.2:9002/
    }
    @SuppressLint("TrulyRandom")
    public static void handleSSLHandshake() {
        try {
            TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers()
                {
                    return new X509Certificate[0];
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String authType) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String authType) {
                }
            }};

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
//            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
//                @Override
//                public boolean verify(String arg0, SSLSession arg1) {
//                    return true;
//                }
//            });
        } catch (Exception ignored) {
        }}
    public static synchronized Application getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }



    public static Context getContext() {
        return mContext;
    }

}
