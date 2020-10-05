package com.wizz.sdk;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

class TextBanner extends androidx.appcompat.widget.AppCompatTextView {

    String title, description, url, type, response_status, clickUrl;
    Context context;

    public TextBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context= context;
        initView();


    }

    public void initView(){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // you should not use a new instance of MyWebView here
        // MyWebView view = (MyWebView) inflater.inflate(R.layout.custom_webview, this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        recieveUrl();
    }

    protected void init(){
        String simple= title+"\n\n"+url+"\n\n"+description+"\n\n";
        String htmlText= "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            this.setText(Html.fromHtml("<h2>"+title+"</h2><br><p>"+description+"</p><br/><p style=\"color:blue; text-decoration: underline;\">Go to "+url+"</p>", Html.FROM_HTML_MODE_COMPACT));
        } else {
            this.setText(Html.fromHtml("<h2>"+title+"</h2><br><p>"+description+"</p><br/><p style=\"color:blue; text-decoration: underline;\">Go to "+url+"</p>"));
        }
        this.setBackgroundColor(Color.WHITE);
        this.setTextColor(Color.BLACK);
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(clickUrl));
                context.startActivity(i);
            }
        });

    }

    public void recieveUrl(){
        //creating a string request to send request to the url
        //text banner request:
        String url_request= "http://api.activemobile.com/?k=0&pid=452&sid=339&zid=561&auth=amF3Y41&t=dGV4dA==&m=Q1BD&keyword=all&source_id=sk-text&ip_addr=119.153.130.111&ref_url=s&user_agent=mozila&mod=api";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_request,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion

                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);

                            //we have the array named hero inside the object
                            //so here we are getting that json array
                            response_status= obj.getString("status");

                            //now looping through all the elements of the json array
                            JSONObject resp= obj.getJSONObject("response");
                            url= resp.getString("display_url"); //for text banner
                            type= resp.getString("type");
                            clickUrl= resp.getString("clickURL");
                            title= resp.getString("title");
                            description= resp.getString("description");

                            (new TextAd(getContext(), null)).top.setVisibility(VISIBLE);
                           init();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(context);

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }
}
