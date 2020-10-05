package com.wizz.sdk;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

class Banner extends WebView {
    ViewGroup.LayoutParams params;
    ImageView imageView;
    int res;
    Context context;
    String url, type, response_status, clickUrl;
    public Banner(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context=context;
        initView();
    }

    public Banner(Context context){
        super(context);
        this.context=context;
        //initView();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }

  //  oncre
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        recieveUrl();
        //add_webView();
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int heightMeasureSpec_custom = MeasureSpec.makeMeasureSpec(
                Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec_custom);
        params = getLayoutParams();
        params.height = getMeasuredHeight();
        params.width= getMeasuredWidth();
    }

    public void add_layout(){
        LinearLayout linearLayout= new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);

        linearLayout.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));

       imageView = new ImageView(context);

//setting image resource


//setting image position
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));

//adding view to layout
        linearLayout.addView(imageView);
        ;
    }

    public void add_webView(){
        String ImgTag= "";
        final String theJS=
//                "alert(\"here\");\n"+
//                "var myDate = new Date();\n" +
//                        "myDate.setDate(myDate.getDate() + 1);\n" +
//                        "document.cookie = cookieName +\"=\" + cookieValue + \";expires=\" + myDate + \"; path=/;domain=.activemobile.com\";\n" +
//                        "dropCookie();\n" +
                "function load_js(){\n" +
                        "\tvar head= document.getElementsByTagName('head')[0];\n" +
                        "\tvar script= document.createElement('script');\n" +
                        "\tscript.type= 'text/javascript';\n" +
                        "\tscript.id= 'AMscript435';\n" +
                        "\tscript.src= 'http://ad.activemobile.com/k=0&pid=452&sid=190&zid=435&auth=amF3Y41&t=dGV4dA==&m=Q1BD&source_id=default&keyword=all&rot=2704277&mod=j';\n" +
                        "\thead.appendChild(script);\n" +
                        "\t}\n" +
                        "function reloaddd(){\n" +
                        "\tclearInterval(nIntervId);\n" +
                        "\tload_js();\n" +
                        "\t}\n" +
                        "\n" +
                        "nIntervId = setInterval(reloaddd, 30000);\n" +
                        "document.write('<div id=\"servediv\"> <div id=\"AM_2704277\" style=\"width: 300px;height: 50px;border:1px solid #000;font-family:arial;\"><p style=\"margin-top:2px;margin-left:2px;overflow: hidden;\"><span style=\"font-size:18px;color:blue;font-weight: bold;\">test</span><p><span style=\"font-size: 15px;\"><a style=\"color:green;\" href=\"http://aclk.activemobile.com/lid=QU0tSElUUy0yMDIwLTA3LTMwPTA1OjI1OjQ0MjQzMjAwMDB8QU0tQ0xJQ0s=|ZGVmYXVsdCwxOTAsNDM1LHRleHQsMjcwNDI3Nyw4MCw0NTIsNDUyLDQ1Ml80MzVfZGVmYXVsdCwxMTkuMTUzLjEzMC4xMTEsYWxsLEFwcGxlICBpUGhvbmUsdGFibGV0LGlPUyxBcHBsZSAgaVBob25lLEFwcGxlLCwsMjAyMC0wNy0zMCwwNToyNTo0NCwwNSxQVENMLDAsT0ZG&cid=2704277&flag=camp&ghs=MTAuMC41LjIyLTcwMzI=&gh=MTE5LjE1My4xMzAuMTExLTQzNS00NTItMTkw\" target= \"_blank \">www.googl.com</a></span><br /><span style=\"font-size:15px;\">sdahdjkasd</span><br /></div></div>');\n";
        this.setWebViewClient(new WebViewClient());
        this.getSettings().setJavaScriptEnabled(true) ;
//        this.clearHistory();
//        this.clearCache(false);
        //this.loadData(data, "text/html; charset=UTF-8", null);
//        this.loadUrl("javascript:"+theJS
//                )

        String imgHtml="<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<style>\n" +
                "img {\n" +
                "  display: block;\n" +
                "  margin-left: auto;\n" +
                "  margin-right: auto;\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<img src=\""+url+"\" alt=\"Paris\" style=\"width:70%;\" width=\"350\" height=\"50\">\n" +
                "\n" +
                "</body>\n" +
                "</html>";
        if(url.contains("http")){ //this is for text banner
            this.loadUrl(url);
        }else{
            this.loadUrl("http://"+url);
        }
        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(clickUrl));
                context.startActivity(i);
                return true;
            }
        });
        this.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 50));
//        if (Build.VERSION.SDK_INT >= 21) {
//            // AppRTC requires third party cookies to work
//            CookieManager cookieManager = CookieManager.getInstance();
//            cookieManager.setAcceptCookie(true);
//            cookieManager.acceptCookie();
//            cookieManager.setAcceptFileSchemeCookies(true);
//            cookieManager.getInstance().setAcceptCookie(true);
//        }
    }

    public void customload(){
        // Creating a new RelativeLayout
        RelativeLayout relativeLayout = new RelativeLayout(context);

        // Defining the RelativeLayout layout parameters.
        // In this case I want to fill its parent
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);

        // Creating a new TextView
        TextView tv = new TextView(context);
        tv.setText("Test");

        // Defining the layout parameters of the TextView
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);

        // Setting the parameters on the TextView
        tv.setLayoutParams(lp);

        // Adding the TextView to the RelativeLayout as a child
        relativeLayout.addView(tv);

        // Setting the RelativeLayout as our content view
//        context(relativeLayout, rlp);
    }

    public void loadview(String url){}


    public void getBannerAdd(){}

    public void initView(){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // you should not use a new instance of MyWebView here
        // MyWebView view = (MyWebView) inflater.inflate(R.layout.custom_webview, this);
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

                            add_webView();
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
