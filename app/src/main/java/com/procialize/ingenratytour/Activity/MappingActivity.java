package com.procialize.ingenratytour.Activity;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.procialize.ingenratytour.ApiConstant.APIService;
import com.procialize.ingenratytour.ApiConstant.ApiUtils;
import com.procialize.ingenratytour.ApiConstant.ProductsPost;
import com.procialize.ingenratytour.GetterSetter.MapGetter;
import com.procialize.ingenratytour.R;
import com.procialize.ingenratytour.Session.SessionManager;
import com.procialize.ingenratytour.Util.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MappingActivity extends AppCompatActivity
{

    ImageView back;
    private APIService mAPIService;
    Util.TransparentProgressDialog pd;
    SessionManager sessionManager;
    String token;
    WebView contactWV;
ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapping);

        back= findViewById(R.id.back);
        contactWV =  findViewById(R.id.contactwebview);
        progressBar =  findViewById(R.id.progressBar);
        mAPIService = ApiUtils.getAPIService();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        if (Util.isNetworkConnected(this)) {

            try {
                if (pd == null) {
                    pd = new Util.TransparentProgressDialog(MappingActivity.this);
                    pd.show();
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }

            sessionManager=  new SessionManager(this);
            token = sessionManager.getUserDetails().get(SessionManager.KEY_TOKEN);
            GetMap();
        }else
        {
            Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_SHORT).show();
        }
    }



    public void GetMap() {
        mAPIService.getMap().enqueue(new Callback<MapGetter>() {
            @Override
            public void onResponse(Call<MapGetter> call, Response<MapGetter> response) {

                if(response.isSuccessful()) {

                    showResponse(response);
                    Log.d("MainActivity", "posts loaded from API");
                }else {
                    int statusCode  = response.code();
                    // handle request errors depending on status code
                }
            }

            @Override
            public void onFailure(Call<MapGetter> call, Throwable t) {
                Log.d("MainActivity", "error loading from API");

            }
        });
    }

    public void showResponse(Response<MapGetter> response) {

        if (pd.isShowing())
        {
            pd.dismiss();
        }

        if (response.body().getStatus().equalsIgnoreCase("Success")) {

            String link_name = response.body().getData().getLinkName();
            Log.e("name",link_name);

            if (Util.isNetworkConnected(this)) {

                contactWV.loadUrl(link_name);

                WebSettings settings = contactWV.getSettings();
                settings.setJavaScriptEnabled(true);
                settings.setAllowFileAccessFromFileURLs(true);
                settings.setAllowUniversalAccessFromFileURLs(true);
                settings.setBuiltInZoomControls(true);
                contactWV.setWebViewClient(new WebViewClient() {
                    @Override
                    public void onPageStarted(WebView view, String url, Bitmap favicon) {
                        super.onPageStarted(view, url, favicon);
                        progressBar.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onPageFinished(WebView view, String url) {
                        super.onPageFinished(view, url);
                        progressBar.setVisibility(View.GONE);
                    }
                });

            }else {
                Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_SHORT).show();

            }

        }else
        {
            Toast.makeText(this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

}
