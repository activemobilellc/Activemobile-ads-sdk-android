

package com.wizz.sdk;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

class AppsAd extends WebView {

    Context context;
    public AppsAd(Context context, AttributeSet attrs) {
       super(context, attrs);
        this.context=context;
        initView();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        add_webView();
    }

    public void initView(){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // you should not use a new instance of MyWebView here
        // MyWebView view = (MyWebView) inflater.inflate(R.layout.custom_webview, this);
    }

    protected void add_webView(){
        this.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (true) {
                    try {
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(Uri.parse(url));
                        Activity activity = (Activity) view.getContext();
                        activity.startActivity(intent);
                        return true;
                    } catch (ActivityNotFoundException e)
                    {
                        // Google Play app is not installed, you may want to open the app store link
                        // Link will open your browser
                        Uri uri = Uri.parse(url);
                        view.loadUrl("http://play.google.com/store/apps/" + uri.getHost() + "?" + uri.getQuery());
                        return false;
                    }

                }
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                (new AppWallAd(getContext(), null)).top.setVisibility(VISIBLE);
            }
        });
        this.loadUrl("http://apps.activemobile.com/?k=0&pid=452&sid=190&zid=699&auth=amF3Y41&m=Q1BJ&t=YXBwd2FsbA==&ds=gai");
        this.getSettings().setJavaScriptEnabled(true) ;
        this.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 50));
    }

}
