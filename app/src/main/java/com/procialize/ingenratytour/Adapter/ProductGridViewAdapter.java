package com.procialize.ingenratytour.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.procialize.ingenratytour.Activity.FullImageViewActivity;
import com.procialize.ingenratytour.Activity.PdfViewActivity;
import com.procialize.ingenratytour.ApiConstant.APIService;
import com.procialize.ingenratytour.ApiConstant.ApiUtils;
import com.procialize.ingenratytour.ApiConstant.ProductViewpost;
import com.procialize.ingenratytour.GetterSetter.ProductImageData;
import com.procialize.ingenratytour.GetterSetter.ViewCount;
import com.procialize.ingenratytour.R;
import com.procialize.ingenratytour.Session.SessionManager;
import com.procialize.ingenratytour.Util.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Naushad on 11/4/2017.
 */

public class ProductGridViewAdapter extends BaseAdapter {

    private List<ProductImageData> drawerGalleryArrayList =new ArrayList<>();
    Context context;
    private APIService mAPIService;
    ProductImageData Datum;
    SessionManager sessionManager;
     String token;
    public ProductGridViewAdapter(Context context, List<ProductImageData> drawerGalleryArrayList){
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

        Datum = new ProductImageData();
        mAPIService = ApiUtils.getAPIService();
        Datum = drawerGalleryArrayList.get(position);

        convertView = View.inflate(context,R.layout.gaaleryrow,null);

        TextView nameTv = convertView.findViewById(R.id.nameTv);
        final ImageView imageIv = convertView.findViewById(R.id.gridiv);
        final ProgressBar progressBar = convertView.findViewById(R.id.progressBar);
        sessionManager = new SessionManager(context);
        final String token = sessionManager.getUserDetails().get(SessionManager.KEY_TOKEN);

        nameTv.setText(Datum.getName());


        imageIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Datum = drawerGalleryArrayList.get(position);


                PosImageCount(Integer.parseInt(Datum.getId()),token);

                Intent i = new Intent(context, FullImageViewActivity.class);
//                ActivityOptionsCompat optionCompact = ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) context,imageIv,"myImage");
                i.putExtra("name",Datum.getName());
                i.putExtra("url",Datum.getPath());
                context.startActivity(i);
//                context.startActivity(i,optionCompact.toBundle());
            }
        });


        if (Datum.getPath()!=null) {


            Glide.with(context).load(Datum.getPath())
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


    public void PosImageCount(int id,String token) {

        mAPIService.ProductImageViewPost(new ProductViewpost(id,token)).enqueue(new Callback<ViewCount>() {
            @Override
            public void onResponse(Call<ViewCount> call, Response<ViewCount> response) {

                if (response.isSuccessful()) {
                    Log.i("hit", "post submitted to API." + response.body().toString());

                    showResponse(response);
                } else {

                    Log.i("hit", "post submitted to API Wrong." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<ViewCount> call, Throwable t) {
                Log.e("hit", "Unable to submit post to API.");

            }
        });
    }

    public void showResponse(Response<ViewCount> response) {

        if (response.body().getSuccess().equals("true")) {

            Log.e("hit","hit");
        } else {
            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}