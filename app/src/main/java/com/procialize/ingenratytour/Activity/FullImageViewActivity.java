package com.procialize.ingenratytour.Activity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.github.chrisbanes.photoview.PhotoView;
import com.procialize.ingenratytour.R;

public class FullImageViewActivity extends AppCompatActivity {

    PhotoView fullimagIv;
    ImageView back;
    ProgressBar progressBar;
    String url,name="";
    TextView nameTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_image_view);

        fullimagIv= findViewById(R.id.fullimagIv);
        progressBar= findViewById(R.id.progressBar);
        back= findViewById(R.id.back);
        nameTv= findViewById(R.id.nameTv);

        Intent i =getIntent();

        if (i!=null)
        {
            url = i.getStringExtra("url");
            name = i.getStringExtra("name");
            Log.e("url",url);
        }

        nameTv.setText(name);

        if (url!=null) {


            Glide.with(this).load(url)
                    .apply(RequestOptions.skipMemoryCacheOf(false))
                    .apply(RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.AUTOMATIC)).listener(new RequestListener<Drawable>() {
                @Override
                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                    e.printStackTrace();
                    return false;
                }

                @Override
                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, com.bumptech.glide.load.DataSource dataSource, boolean isFirstResource) {

                    progressBar.setVisibility(View.GONE);
//                    fullimagIv.setVisibility(View.VISIBLE);
                    return false;
                }

            }).into(fullimagIv).onLoadStarted(this.getDrawable(R.drawable.imgh));
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}
