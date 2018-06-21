package com.procialize.ingenratytour.Activity;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.multidex.MultiDex;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.procialize.ingenratytour.ApiConstant.APIService;
import com.procialize.ingenratytour.ApiConstant.ApiUtils;
import com.procialize.ingenratytour.ApiConstant.ProductsPost;
import com.procialize.ingenratytour.ApiConstant.SubscribePost;
import com.procialize.ingenratytour.GetterSetter.ProductData;
import com.procialize.ingenratytour.GetterSetter.Subscribe;
import com.procialize.ingenratytour.R;
import com.procialize.ingenratytour.Session.SessionManager;
import com.procialize.ingenratytour.Util.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SubscribeActivity extends AppCompatActivity {

    private Toolbar toolbar;
    Button submitbtn;
    EditText nameEt,emailEt,mobileEt;
    CheckBox condCheck;
    Context context;
    LayoutInflater inflater;
    Spinner productSpinner;
    APIService mAPIService;
    Util.TransparentProgressDialog pd;
    public static List<String>productlist,productid;
    String productId;
    SessionManager sessionManager;
    String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);
        MultiDex.install(this);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        mAPIService = ApiUtils.getAPIService();
        sessionManager = new SessionManager(SubscribeActivity.this);


        toolbar =  findViewById(R.id.toolbar);
        submitbtn =  findViewById(R.id.submitbtn);
        nameEt =  findViewById(R.id.nameEt);
        emailEt =  findViewById(R.id.emailEt);
        mobileEt =  findViewById(R.id.mobileEt);
        condCheck =  findViewById(R.id.condCheck);
        productSpinner =  findViewById(R.id.spinner);

        context=getApplicationContext();
        inflater=getLayoutInflater();


        productlist = new ArrayList<String>();
        productid = new ArrayList<String>();


        toolbar.setTitle("");
        toolbar.setSubtitle("");
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            final Drawable upArrow = getResources().getDrawable(R.drawable.ic_back);
            upArrow.setColorFilter(getResources().getColor(R.color.colortheme), PorterDuff.Mode.SRC_ATOP);
            getSupportActionBar().setHomeAsUpIndicator(upArrow);
//          actionBar.setHomeAsUpIndicator(R.drawable.Your_Icon); // set a custom icon for the default home button
        }

        if (Util.isNetworkConnected(this)) {
            token = sessionManager.getUserDetails().get(SessionManager.KEY_TOKEN);
            GetProduct(token);
        }else
        {
            Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_SHORT).show();

        }

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitbtn.startAnimation(AnimationUtils.loadAnimation(SubscribeActivity.this, R.anim.onclick));
                performSubmition();
            }
        });

        productSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                productId = productid.get(position);
                Log.e("productId",productId);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    private void performSubmition() {

        if (nameEt.getText().toString().equals(""))
        {
            Util.customtoast(context,inflater,"Please Enter Name");
        }else if (emailEt.getText().toString().equals(""))
        {
            Util.customtoast(context,inflater,"Please Enter Email");

        }else if (mobileEt.getText().toString().equals(""))
        {
            Util.customtoast(context,inflater,"Please Enter Mobile");
        }else if (!isValidEmail(emailEt.getText().toString()))
        {
            Util.customtoast(context,inflater,"Please Enter Valid Email");
        }else if (!isValidMobile(mobileEt.getText().toString()) || mobileEt.getText().toString().length()<10)
        {
            Util.customtoast(context,inflater,"Please Enter Valid Mobile No.");
        }else if (!condCheck.isChecked())
        {
            Util.customtoast(context,inflater,"Please Accept Terms and Condition");
        }
        else
        {
            if (Util.isNetworkConnected(this)) {
                PostSubscribe("xyz", emailEt.getText().toString().trim(), mobileEt.getText().toString().trim(), productId,token);
            }else
            {
                Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_SHORT).show();

            }
        }
    }

    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }



    public void PostSubscribe(String username,String mailid,String number,String id,String token) {

        try {
            if (pd == null) {
                pd = new Util.TransparentProgressDialog(SubscribeActivity.this);
                pd.show();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        mAPIService.SubscribePost(new SubscribePost(username,mailid,number,id,token)).enqueue(new Callback<Subscribe>() {
            @Override
            public void onResponse(Call<Subscribe> call, Response<Subscribe> response) {

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
            public void onFailure(Call<Subscribe> call, Throwable t) {
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

    public void showResponse(Response<Subscribe> response) {

        if (response.body().getSuccess().equals("true")) {

            Toast.makeText(this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
            cleardata();
        } else {
            Toast.makeText(this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
            cleardata();
        }
    }


    public void GetProduct(String token) {

        try {
            if (pd == null) {
                pd = new Util.TransparentProgressDialog(SubscribeActivity.this);
                pd.show();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        mAPIService.ProductGet(new ProductsPost(token)).enqueue(new Callback<ProductData>() {
            @Override
            public void onResponse(Call<ProductData> call, Response<ProductData> response) {

                if(response.isSuccessful()) {
                    Log.i("hit", "post submitted to API." + response.body().toString());


                    showProductResponse(response);
                }else
                {
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
            public void onFailure(Call<ProductData> call, Throwable t) {
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

    public void showProductResponse(Response<ProductData> response) {

        if (response.body().getSuccess().equals("true")) {

            Log.e("hit", response.body().getData().size()+"");

            for(int i=0;i<response.body().getData().size();i++)
            {
                productlist.add(response.body().getData().get(i).getProductName());
                productid.add(response.body().getData().get(i).getId());
            }



            try {
                if (pd.isShowing()) {
                    pd.dismiss();
                }
            }catch (Exception e)
            {
                e.printStackTrace();
            }

            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, productlist);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            productSpinner.setAdapter(dataAdapter);



        }else
        {
//            Log.e("hit", response.body().getData().size()+"");
            Toast.makeText(this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void cleardata()
    {
        nameEt.setText("");
        emailEt.setText("");
        mobileEt.setText("");


        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, productlist);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        productSpinner.setAdapter(dataAdapter);

    }

}
