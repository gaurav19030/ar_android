package com.procialize.ingenratytour.Activity;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.multidex.MultiDex;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;


import com.procialize.ingenratytour.ApiConstant.APIService;
import com.procialize.ingenratytour.ApiConstant.ApiUtils;
import com.procialize.ingenratytour.GetterSetter.QuizData;
import com.procialize.ingenratytour.GetterSetter.ShareExperience;
import com.procialize.ingenratytour.GetterSetter.ShareExperienceListPost;
import com.procialize.ingenratytour.GetterSetter.ShareExperiencePost;
import com.procialize.ingenratytour.R;
import com.procialize.ingenratytour.Session.SessionManager;
import com.procialize.ingenratytour.Util.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShareExperienceActivity extends AppCompatActivity {

    private Toolbar toolbar;
    SessionManager sessionManager;
    String token;
    Util.TransparentProgressDialog pd;
    APIService mAPIService;
    CheckBox outstandcheck,excelentcheck,verygocheck,goocheck,satisfyheck;
    Button submitbtn;
    String chekbtnans;
    EditText eventEt,feedEt;
    int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_experience);
        MultiDex.install(this);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        mAPIService = ApiUtils.getAPIService();
        sessionManager = new SessionManager(ShareExperienceActivity.this);

        token = sessionManager.getUserDetails().get(SessionManager.KEY_TOKEN);

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


        outstandcheck = findViewById(R.id.outstandcheck);
        excelentcheck = findViewById(R.id.excelentcheck);
        verygocheck = findViewById(R.id.verygocheck);
        goocheck = findViewById(R.id.goocheck);
        satisfyheck = findViewById(R.id.satisfyheck);

        eventEt = findViewById(R.id.eventEt);
        feedEt = findViewById(R.id.feedEt);

        submitbtn = findViewById(R.id.submitbtn);

        outstandcheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    chekbtnans = "Outstanding";

                    excelentcheck.setChecked(false);
                    verygocheck.setChecked(false);
                    goocheck.setChecked(false);
                    satisfyheck.setChecked(false);
                }else {
                    outstandcheck.setChecked(false);
                }
            }
        });

        excelentcheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    chekbtnans = "Excelent";
                    outstandcheck.setChecked(false);
                    verygocheck.setChecked(false);
                    goocheck.setChecked(false);
                    satisfyheck.setChecked(false);
                }else {
                    excelentcheck.setChecked(false);
                }
            }
        });

        verygocheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    chekbtnans = "Very Good";
                    outstandcheck.setChecked(false);
                    goocheck.setChecked(false);
                    excelentcheck.setChecked(false);
                    satisfyheck.setChecked(false);
                }else {
                    verygocheck.setChecked(false);
                }
            }
        });

        goocheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    chekbtnans = "Good";
                    outstandcheck.setChecked(false);
                    verygocheck.setChecked(false);
                    excelentcheck.setChecked(false);
                    satisfyheck.setChecked(false);
                }else {
                    goocheck.setChecked(false);
                }
            }
        });


        satisfyheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    chekbtnans = "Satisfactory";
                    outstandcheck.setChecked(false);
                    verygocheck.setChecked(false);
                    excelentcheck.setChecked(false);
                    goocheck.setChecked(false);
                }else {
                    satisfyheck.setChecked(false);
                }
            }
        });

        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (eventEt.getText().toString().equals(""))
                {
//                    Toast.makeText(getApplicationContext(),"Please Enter How Was The Event?",Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),"Please share your experience",Toast.LENGTH_LONG).show();
                }else if (feedEt.getText().toString().equals(""))
                {
//                    Toast.makeText(getApplicationContext(),"Please Enter Feedback",Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),"Please share your experience",Toast.LENGTH_LONG).show();
                }else if (chekbtnans.equals(""))
                {
//                    Toast.makeText(getApplicationContext(),"Please Select One Checkbox",Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),"Please share your experience",Toast.LENGTH_LONG).show();
                }else
                {
                    if (flag == 0)
                    {
                    String id = "1$#2$#3";
                    String ans = eventEt.getText().toString()+"$#"+chekbtnans+"$#"+feedEt.getText().toString();

                    if (Util.isNetworkConnected(getApplicationContext())) {
                        token = sessionManager.getUserDetails().get(SessionManager.KEY_TOKEN);
                        ShareExperiencePost(token,id,ans);
                    }else
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_SHORT).show();
                    }
                    }else
                    {

                        cleartext();
                        Toast.makeText(ShareExperienceActivity.this,"You have already submitted your experience",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        if (Util.isNetworkConnected(getApplicationContext())) {
//            token = sessionManager.getUserDetails().get(SessionManager.KEY_TOKEN);
            ShareExperienceListPost(token);
        }else
        {
            Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_SHORT).show();
        }
    }


    public void ShareExperiencePost(String token,String questionid,String ans) {

        try {
            if (pd == null) {
                pd = new Util.TransparentProgressDialog(ShareExperienceActivity.this);
                pd.show();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        mAPIService.ShareExperiencePost(new ShareExperiencePost(token,questionid,ans)).enqueue(new Callback<ShareExperience>() {
            @Override
            public void onResponse(Call<ShareExperience> call, Response<ShareExperience> response) {

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
            public void onFailure(Call<ShareExperience> call, Throwable t) {
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

    public void showResponse(Response<ShareExperience> response) {

        if (response.body().getStatus().equals("success")) {

            Toast.makeText(this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
            finish();

        } else {
            Toast.makeText(this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
        }
    }



    public void ShareExperienceListPost(String token) {

        try {
            if (pd == null) {
                pd = new Util.TransparentProgressDialog(ShareExperienceActivity.this);
                pd.show();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        mAPIService.ShareExperienceListPost(new ShareExperienceListPost(token)).enqueue(new Callback<QuizData>() {
            @Override
            public void onResponse(Call<QuizData> call, Response<QuizData> response) {

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
                    showListResponse(response);
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
            public void onFailure(Call<QuizData> call, Throwable t) {
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

    public void showListResponse(Response<QuizData> response) {

        if (response.body().getStatus().equals("success")) {

            flag = response.body().getQuizFlag();

        } else {
            Toast.makeText(this, response.body().getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    public void cleartext()
    {
        eventEt.setText("");
        feedEt.setText("");
    }
}
