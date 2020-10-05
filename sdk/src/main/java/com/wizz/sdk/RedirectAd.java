package com.wizz.sdk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class RedirectAd {
    String response_status, url, type;
    Context context;
    public RedirectAd(Context context){
        this.context= context;
    }

    public void recieveUrl(){
        String url_request= "http://api.activemobile.com/?k=0&pid=452&sid=339&zid=1309&auth=amF3Y41&t=cG9w&m=Q1BN&source_id=SK-REDIRECT&ip_addr=119.153.130.111&ref_url=s&user_agent=mozila&mod=api";
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

                            //now looping through all the elements of the json array
                            response_status= obj.getString("status");
                            JSONObject resp= obj.getJSONObject("response");
                            url= resp.getString("pop_url"); //for redirect ad
                            type= resp.getString("type");
                            //clickUrl= resp.getString("clickURL");

                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(url));
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(i);

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
