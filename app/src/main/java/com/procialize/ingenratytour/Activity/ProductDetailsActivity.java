package com.procialize.ingenratytour.Activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.multidex.MultiDex;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.RelativeLayout;

import com.procialize.ingenratytour.R;

public class ProductDetailsActivity extends AppCompatActivity {

    RelativeLayout VideoRl,ImageRl,BroucherRl;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        MultiDex.install(this);


        toolbar =  findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setSubtitle("");
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
        {
            final Drawable upArrow = getResources().getDrawable(R.drawable.ic_back);
            upArrow.setColorFilter(getResources().getColor(R.color.colortheme), PorterDuff.Mode.SRC_ATOP);
            getSupportActionBar().setHomeAsUpIndicator(upArrow);
            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setHomeAsUpIndicator(R.drawable.Your_Icon); // set a custom icon for the default home button
        }


        VideoRl = findViewById(R.id.VideoRl);
        ImageRl = findViewById(R.id.ImageRl);
        BroucherRl = findViewById(R.id.BroucherRl);

        VideoRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent video = new Intent(getApplicationContext(),ProductVideoActivity.class);
                video.putExtra("id","6");
                startActivity(video);
            }
        });

        ImageRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent video = new Intent(getApplicationContext(),ProductImageActivity.class);
                video.putExtra("id","6");
                startActivity(video);
            }
        });

        BroucherRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent video = new Intent(getApplicationContext(),ProductBroucherActivity.class);
                video.putExtra("id","6");
                startActivity(video);
            }
        });
    }
}
