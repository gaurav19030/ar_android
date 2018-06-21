package com.procialize.ingenratytour.Adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.procialize.ingenratytour.ApiConstant.APIService;
import com.procialize.ingenratytour.ApiConstant.ApiUtils;
import com.procialize.ingenratytour.ApiConstant.ProductViewpost;
import com.procialize.ingenratytour.GetterSetter.ProductImageData;
import com.procialize.ingenratytour.GetterSetter.ViewCount;
import com.procialize.ingenratytour.R;
import com.procialize.ingenratytour.Session.SessionManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Naushad on 11/4/2017.
 */

public class ProductVideoAdapter extends BaseAdapter {

    private List<ProductImageData> drawerGalleryArrayList =new ArrayList<>();
    Context context;
    ProductImageData Datum;
    private APIService mAPIService;
    SessionManager sessionManager;
    String token;

    public ProductVideoAdapter(Context context, List<ProductImageData> drawerGalleryArrayList){
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

        sessionManager = new SessionManager(context);
        token = sessionManager.getUserDetails().get(SessionManager.KEY_TOKEN);

        mAPIService = ApiUtils.getAPIService();

        Datum = drawerGalleryArrayList.get(position);

        convertView = View.inflate(context,R.layout.videorow,null);

        TextView nameTv = convertView.findViewById(R.id.nameTv);

        nameTv.setText(Datum.getName());

        nameTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Datum = drawerGalleryArrayList.get(position);

                PosVideoCount(Integer.parseInt(Datum.getId()),token);

                Intent intent = new Intent(Intent.ACTION_VIEW);

                intent.setDataAndType(Uri.parse(Datum.getPath()), "video/*");

                context.startActivity(Intent.createChooser(intent, "Complete action using"));

//                Intent pdfview = new Intent(context, PdfViewActivity.class);
//                pdfview.putExtra("url",GalleryData.getPath());
//                context.startActivity(pdfview);
            }
        });

        return convertView;
    }


    public void PosVideoCount(int id,String token) {

        mAPIService.ProductVideoViewpost(new ProductViewpost(id,token)).enqueue(new Callback<ViewCount>() {
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