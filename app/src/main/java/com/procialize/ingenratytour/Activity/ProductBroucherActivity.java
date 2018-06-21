package com.procialize.ingenratytour.Activity;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.multidex.MultiDex;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.GridLayoutAnimationController;
import android.widget.GridView;
import android.widget.Toast;

import com.procialize.ingenratytour.Adapter.ProductBroucherAdapter;
import com.procialize.ingenratytour.ApiConstant.APIService;
import com.procialize.ingenratytour.ApiConstant.ApiUtils;
import com.procialize.ingenratytour.ApiConstant.ProductImagepost;
import com.procialize.ingenratytour.GetterSetter.ProductImages;
import com.procialize.ingenratytour.R;
import com.procialize.ingenratytour.Session.SessionManager;
import com.procialize.ingenratytour.Util.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductBroucherActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private APIService mAPIService;
    Util.TransparentProgressDialog pd;
    GridView imagegrid;
    ProductBroucherAdapter productBroucherAdapter;
    String id;
    SessionManager sessionManager;
    String token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_broucher);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        MultiDex.install(this);
        mAPIService = ApiUtils.getAPIService();

        Intent intent = getIntent();

        if (intent!=null)
        {
            id = intent.getStringExtra("id");
        }

        sessionManager = new SessionManager(ProductBroucherActivity.this);


        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setSubtitle("");
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            final Drawable upArrow = getResources().getDrawable(R.drawable.ic_back);
            upArrow.setColorFilter(getResources().getColor(R.color.colortheme), PorterDuff.Mode.SRC_ATOP);
            getSupportActionBar().setHomeAsUpIndicator(upArrow);
            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setHomeAsUpIndicator(R.drawable.Your_Icon); // set a custom icon for the default home button
        }

        imagegrid = findViewById(R.id.imagegrid);

        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_up_dialog);
        GridLayoutAnimationController controller = new GridLayoutAnimationController(animation, .2f, .2f);
        imagegrid.setLayoutAnimation(controller);



        if (Util.isNetworkConnected(this)) {
            try {
                if (pd == null) {
                    pd = new Util.TransparentProgressDialog(ProductBroucherActivity.this);
                    pd.show();
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }
            token = sessionManager.getUserDetails().get(SessionManager.KEY_TOKEN);
            GetBroucher(Integer.parseInt(id),token);
        }else
        {
            Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_SHORT).show();

        }
    }


    public void GetBroucher(int id,String token) {
        mAPIService.ProductBroucherGet(new ProductImagepost(id,token)).enqueue(new Callback<ProductImages>() {
            @Override
            public void onResponse(Call<ProductImages> call, Response<ProductImages> response) {

                if (response.isSuccessful()) {
                    Log.i("hit", "post submitted to API." + response.body().toString());
                    try {
                        if (pd.isShowing()) {
                            pd.dismiss();
                        }
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    showResponse(response);
                } else {
                    try {
                        if (pd.isShowing()) {
                            pd.dismiss();
                        }
                    }catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                    Log.i("hit", "post submitted to API Wrong." + response.body().toString());
                }
            }

            @Override
            public void onFailure(Call<ProductImages> call, Throwable t) {
                Log.e("hit", "Unable to submit post to API.");
                try {
                    if (pd.isShowing()) {
                        pd.dismiss();
                    }
                }catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    public void showResponse(Response<ProductImages> response) {

        if (response.body().getSuccess().equals("true")) {

            Log.e("hit", response.body().getData().size() + "");

            productBroucherAdapter = new ProductBroucherAdapter(this,response.body().getData());
            imagegrid.setAdapter(productBroucherAdapter);


        } else {
            Toast.makeText(this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}

