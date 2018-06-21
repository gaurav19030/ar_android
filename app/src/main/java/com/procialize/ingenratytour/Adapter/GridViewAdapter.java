package com.procialize.ingenratytour.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.procialize.ingenratytour.Activity.FullImageViewActivity;
import com.procialize.ingenratytour.GetterSetter.GalleryData;
import com.procialize.ingenratytour.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Naushad on 11/4/2017.
 */

public class GridViewAdapter extends BaseAdapter {

    private List<GalleryData> drawerGalleryArrayList =new ArrayList<>();
    Context context;
    GalleryData Datum;
    public GridViewAdapter (Context context, List<GalleryData> drawerGalleryArrayList){
     this.context=context;
     this.drawerGalleryArrayList = drawerGalleryArrayList;
    }

    @Override
    public int getCount() {
        return drawerGalleryArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return drawerGalleryArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        Datum = new GalleryData();

        Datum = drawerGalleryArrayList.get(position);

        convertView = View.inflate(context,R.layout.gaaleryrow,null);

        TextView nameTv = convertView.findViewById(R.id.nameTv);
        final ImageView imageIv = convertView.findViewById(R.id.gridiv);
        final ProgressBar progressBar = convertView.findViewById(R.id.progressBar);

        nameTv.setText(Datum.getImageName());

        imageIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Datum = drawerGalleryArrayList.get(position);

                Intent i = new Intent(context, FullImageViewActivity.class);
//                ActivityOptionsCompat optionCompact = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,imageIv,"myImage");
                i.putExtra("url",Datum.getImagePath());
                i.putExtra("name",Datum.getImageName());
                context.startActivity(i);
//                context.startActivity(i,optionCompact.toBundle());
            }
        });


        if (Datum.getImagePath()!=null) {


            Glide.with(context).load(Datum.getImagePath())
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
                    imageIv.setVisibility(View.VISIBLE);
                    return false;
                }

            }).into(imageIv).onLoadStarted(context.getDrawable(R.drawable.imgh));

        }

        return convertView;
    }

}