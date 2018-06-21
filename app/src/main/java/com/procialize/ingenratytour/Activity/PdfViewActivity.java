package com.procialize.ingenratytour.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.procialize.ingenratytour.R;
import com.procialize.ingenratytour.Util.Util;

public class PdfViewActivity extends AppCompatActivity {

    WebView pdfview;
    String url;
//    ProgressDialog pDialog;
    ProgressBar progressBar;
    ImageView back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf_view);

        pdfview = findViewById(R.id.pdfview);
        progressBar = findViewById(R.id.progressBar);
        back = findViewById(R.id.back);

//        pDialog = new ProgressDialog(PdfViewActivity.this);
//        pDialog.setTitle("PDF");
//        pDialog.setMessage("Loading...");
//        pDialog.setIndeterminate(false);
//        pDialog.setCancelable(false);


        Intent intent = getIntent();

        if (intent != null) {
            url = intent.getStringExtra("url");
        }

        Log.e("url", url);

        if (Util.isNetworkConnected(this)) {

            pdfview.loadUrl("http://docs.google.com/gview?embedded=true&url=" + url);

            WebSettings settings = pdfview.getSettings();
            settings.setJavaScriptEnabled(true);
            settings.setAllowFileAccessFromFileURLs(true);
            settings.setAllowUniversalAccessFromFileURLs(true);
            settings.setBuiltInZoomControls(true);
            pdfview.setWebViewClient(new WebViewClient() {
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

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
