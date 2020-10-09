package com.wizz.sdk;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ext.ima.ImaAdsLoader;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.ads.AdsMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import org.json.JSONArray;
import org.json.JSONObject;

public class Temp123Class extends AppCompatActivity {

    private PlayerView playerView;
    private SimpleExoPlayer player;
    private ImaAdsLoader adsLoader;
    ProgressBar progressBar;
    String tracking_events, popUrl, video_url1, video_url2, video_duration, is_skipable, response_status;
    String start_url, first_quartile_url, midpoint_url, third_quartile_url, end_url, impression_url, on_click_url1, on_click_url2;
    int duration;
    ConstraintLayout layout;

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_pop_ad);

        playerView = findViewById(R.id.player_view);
        textView= (TextView) findViewById(R.id.text_view);
        layout= (ConstraintLayout) findViewById(R.id.layout);
        textView.setEnabled(false);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recieveJSONLevel1();

        // Create an AdsLoader with the ad tag url.
        adsLoader = new ImaAdsLoader(this, Uri.parse(""));
//        progressBar= (ProgressBar) findViewById(R.id.progress_bar);
//        progressBar.setVisibility(View.INVISIBLE);
    }


    private void releasePlayer() {
        adsLoader.setPlayer(null);
        playerView.setPlayer(null);
        player.release();
        player = null;
    }

    private void initializePlayer() {
        // Create a SimpleExoPlayer and set is as the player for content and ads.
        player = new SimpleExoPlayer.Builder(this).build();
        playerView.setPlayer(player);
        playerView.setShowBuffering(PlayerView.SHOW_BUFFERING_WHEN_PLAYING);
        adsLoader.setPlayer(player);

        DataSource.Factory dataSourceFactory =
                new DefaultDataSourceFactory(this, Util.getUserAgent(this, "Adcore"));

        ProgressiveMediaSource.Factory mediaSourceFactory =
                new ProgressiveMediaSource.Factory(dataSourceFactory);

        // Create the MediaSource for the content you wish to play. http://dl59.y2mate.com/?file=M3R4SUNiN3JsOHJ6WWQ2a3NQS1Y5ZGlxVlZIOCtyZ1hrTm8xMHowbVNwaFB0SWdxMmVlaE1OMEVDcUlDd3JXREJZOWsyQVRmYS9LZkdUQ2Y1TXgyY2oyTTU0Sjd2emJEOXJFd1dOMTVDMU9xdmYrc2d5Vmppd0t3TFA3QUJld1RQMUY1NWhKRnluU2V6ZVhSOXhiM3ZqYmc3RjZMWXlzZXZ6NDBMdmJDOUpwRzFtalllOGpnMXAwS2tnZUxzNnBrNFBlSGxoWGYvS1JzN0l4RkVFbGtKOVpZd1lyOHpmWFlyRW9jM2NoSmd4VHorckgvVjhsekQ3UERhaUVoYnc9PQ%3D%3D
        MediaSource mediaSource =
                mediaSourceFactory.createMediaSource(Uri.parse(video_url1));

        // Create the AdsMediaSource using the AdsLoader and the MediaSource.
        AdsMediaSource adsMediaSource =
                new AdsMediaSource(mediaSource, dataSourceFactory, adsLoader, playerView);

        // Prepare the content and ad to be played with the SimpleExoPlayer.
        player.prepare(adsMediaSource);

        // Set PlayWhenReady. If true, content and ads autoplay.
        player.setPlayWhenReady(true);

        player.addListener(new Player.EventListener() {
            @Override
            public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
                if(playbackState== Player.STATE_READY){
                    //enabling click
                    layout.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            hitURL(on_click_url1);
                            Intent i = new Intent(Intent.ACTION_VIEW);
                            i.setData(Uri.parse(popUrl));
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getApplicationContext().startActivity(i);
                        }
                    });

                    //hit impression api
                    hitURL(impression_url);

                    //Hit start Tracking
                    hitURL(start_url);

                    long temp= player.getContentDuration();
                    duration= Math.round(temp/1000);
                    Thread thread = new Thread(){
                        public void run(){
                            double elapsed;
                            boolean FQ=false;
                            boolean MID= false;
                            boolean TQ= false;
                            while (true){
                                if(player!=null){
//                                    progressBar.setProgress((int)((player.getCurrentPosition()/temp)*10));
                                    for(int i=0; i<duration; i++){
                                        final int temp=i;
                                        if(player!=null){
                                            if((player.getCurrentPosition()>=(i*1000)) && (player.getCurrentPosition()<=((i+1)*1000))){
                                                if(is_skipable.equalsIgnoreCase("true")){
                                                    if(i>=5){
                                                        runOnUiThread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                textView.setEnabled(true);
                                                                textView.setText("Skip Ad");
                                                            }
                                                        });
                                                    }else{
                                                        runOnUiThread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                textView.setText("Skip Ad In "+(5-temp));
                                                            }
                                                        });
                                                    }
                                                }else{
                                                    runOnUiThread(new Runnable() {
                                                        @Override
                                                        public void run() {
                                                            textView.setText("Ending In "+(duration-temp));
                                                        }
                                                    });

                                                }

                                                elapsed= ((double) i/duration)*100;
                                                if(elapsed>25 && elapsed<=50){
                                                    //firstQuartile
                                                    if(!FQ){
                                                        //access api
                                                        hitURL(first_quartile_url);
                                                        FQ=true;
                                                    }
                                                }else if(elapsed>50 && elapsed<=75){
                                                    //midpoint
                                                    if(!MID){
                                                        //access api
                                                        hitURL(midpoint_url);
                                                        MID=true;
                                                    }
                                                }else if(elapsed>75 && elapsed<=100){
                                                    //thirdQuartile
                                                    if(!TQ){
                                                        //access api
                                                        hitURL(third_quartile_url);
                                                        TQ=true;
                                                    }
                                                }
                                            }
                                        }else{
                                            break;
                                        }
                                    }

                                }else{
                                    break;
                                }
                            }
                        }
                    };
                    thread.start();

                }
                if (playbackState == Player.STATE_ENDED) {
                    //complete//access api
                    hitURL(end_url);
                    finish();
                }
            }
        });
    }

    public void recieveJSONLevel1(){
        String url_request="http://video.activemobile.com/?k=0&pid=452&sid=190&zid=409&auth=amF3Y41&t=dmlkZW8=&m=Q1BN&source_id=d&video=https://file-examples-com.github.io/uploads/2017/04/file_example_MP4_480_1_5MG.mp4&width=320&height=240&type=api&VAST=1";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url_request,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion


                        try {
                            Log.i("THISTAG", "onResponse: "+response);
                            //starting json parsing

                            JSONObject obj = new JSONObject(response);
                            video_url2= obj.getString("video_url"); // for image banner
                            tracking_events= obj.getString("tracking_links");
                            on_click_url2= obj.getString("clickUrl");

                            recieveJSON();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }

    //xml: http://ads.activemobile.com/vpaid/xml_parser.php?solo=1&data=MjAyMC0wOS0xNz0wNDoyNTozMTMwMTAwMDAtfC0wem9VaU93elBGMTBkNU04NHNzZTIwMjAtMDktMTc9MDQ6MjU6MzIyNzEzMTY4MTItfC1bUkFORE9NX0lEXSxkLDE5MCw0MDksdmlkZW8sNDA0Nzc0OCw4MCwxLDQ1Miw0NTIsNDUyXzQwOV9kLDEwMy4xMDQuMjEzLjI0NiwsTEcgIE5leHVzIDUsdGFibGV0LCwsQW5kcm9pZCxMRyAgTmV4dXMgNSwsTEcsLCwsLDIwMjAtMDktMTcsMDQ6Mjk6MzIsMDQsMSwxLDAsVU5LTk9XTi18LVlFUy0xLTEtMS0xLTEtMC0wLTAtMC0wLTAtMC0wLTAtfC1odHRwOi8vd3d3Lmdvb2dsZS5jb20tfC1odHRwOi8vYWRtaW4uYWN0aXZlbW9iaWxlLmNvbS92aWRlby80NTIvNDUyXzE1NzAwMjRfMzY5NTUyMDE1Ni18LTMyMC18LTI0MC18LVZJREV
    public void recieveJSON(){
        //String url_request="http://ads.activemobile.com/vpaid/video_tracking_events.php?solo=1&data=MjAyMC0wOS0yMT0wMzo0NTo0MTM2NDAwMDAtfC1CVlM3bUhUeWlZQ2hGdEJmOVllQjIwMjAtMDktMjE9MDM6NDU6NDExODg0MzQ1MjgtfC1bUkFORE9NX0lEXSxkLDE5MCw0MDksdmlkZW8sNDA0Nzc0OCw4MCwxLDQ1Miw0NTIsNDUyXzQwOV9kLDEwMy4xMDQuMjEzLjI0NiwsTEcgIE5leHVzIDUsdGFibGV0LCwsQW5kcm9pZCxMRyAgTmV4dXMgNSwsTEcsLCwsLDIwMjAtMDktMjEsMDM6NDg6NDEsMDMsMSwxLDAsVU5LTk9XTi18LVlFUy0xLTEtMS0xLTEtMC0wLTAtMC0wLTAtMC0wLTAtfC1odHRwOi8vd3d3Lmdvb2dsZS5jb20tfC1odHRwOi8vYWRtaW4uYWN0aXZlbW9iaWxlLmNvbS92aWRlby80NTIvNDUyXzE1NzAwMjRfMzY5NTUyMDE1Ni18LTMyMC18LTI0MC18LVZJREVP";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, tracking_events,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion


                        try {
                            Log.i("THISTAG", "onResponse: "+response);
                            //starting json parsing

                            JSONObject obj = new JSONObject(response);

                            JSONObject resp= obj.getJSONObject("data");
                            is_skipable= resp.getString("skipable_event"); // for image banner
//                            is_skipable="true";
                            start_url= resp.getString("start_event");
                            first_quartile_url= resp.getString("first_quartile_event");
                            midpoint_url= resp.getString("mid_point_event");
                            third_quartile_url= resp.getString("third_quartile_event");
                            end_url= resp.getString("complete_event");
                            impression_url= resp.getString("impression_event");
                            on_click_url1= resp.getString("start_event");
                            popUrl= resp.getString("click_event");

                            JSONArray files= resp.getJSONArray("files");
                            JSONObject temp;
                            for(int i=0; i<files.length(); i++){
                                temp= files.getJSONObject(i);
                                if(temp.getString("bitrate").equalsIgnoreCase("1024") && temp.getString("type").contains("mp4")){
                                    video_url1= temp.getString("url");
                                    break;
                                }
                            }
                            initializePlayer();

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }


    public void hitURL(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                        Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

//                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }



//    @Override
//    public void onStart() {
//        super.onStart();
//        //
//        if (Util.SDK_INT > 23) {
//            initializePlayer();
//            if (playerView != null) {
//                playerView.onResume();
//            }
//        }
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        if (Util.SDK_INT <= 23 || player == null) {
//            initializePlayer();
//            if (playerView != null) {
//                playerView.onResume();
//            }
//        }
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//        if (Util.SDK_INT <= 23) {
//            if (playerView != null) {
//                playerView.onPause();
//            }
//            releasePlayer();
//        }
//    }

    @Override
    public void onStop() {
        super.onStop();
        if (Util.SDK_INT > 23) {
            if (playerView != null) {
                playerView.onPause();
            }
            releasePlayer();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adsLoader.release();
    }
}