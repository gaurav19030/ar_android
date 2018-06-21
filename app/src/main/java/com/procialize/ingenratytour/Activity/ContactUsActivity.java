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

public class ContactUsActivity extends AppCompatActivity {
    private Toolbar toolbar;
    WebView contactWV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        MultiDex.install(this);

        toolbar =  findViewById(R.id.toolbar);
        contactWV =  findViewById(R.id.contactwebview);
        toolbar.setTitle("");
        toolbar.setSubtitle("");
        setSupportActionBar(toolbar);

        contactWV.loadUrl("http://siemensit.in/contact_us.html");

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
        {
            final Drawable upArrow = getResources().getDrawable(R.drawable.ic_back);
            upArrow.setColorFilter(getResources().getColor(R.color.colortheme), PorterDuff.Mode.SRC_ATOP);
            getSupportActionBar().setHomeAsUpIndicator(upArrow);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }
}
