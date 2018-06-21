package com.procialize.ingenratytour.Activity;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.multidex.MultiDex;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import com.procialize.ingenratytour.R;

public class PrivacyPolicyActivity extends AppCompatActivity {
    private Toolbar toolbar;
    WebView infoTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privacy_policy);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        MultiDex.install(this);
        toolbar =  findViewById(R.id.toolbar);
        infoTv =  findViewById(R.id.infoTv);
        toolbar.setTitle("");
        toolbar.setSubtitle("");
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();


        infoTv.loadUrl("http://siemensit.in/policy.html");

        if (actionBar != null)
        {
            final Drawable upArrow = getResources().getDrawable(R.drawable.ic_back);
            upArrow.setColorFilter(getResources().getColor(R.color.colortheme), PorterDuff.Mode.SRC_ATOP);
            getSupportActionBar().setHomeAsUpIndicator(upArrow);
            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setHomeAsUpIndicator(R.drawable.Your_Icon); // set a custom icon for the default home button
        }

    }
}
