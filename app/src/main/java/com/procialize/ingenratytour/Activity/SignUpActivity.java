package com.procialize.ingenratytour.Activity;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.procialize.ingenratytour.Adapter.ProductVideoAdapter;
import com.procialize.ingenratytour.ApiConstant.APIService;
import com.procialize.ingenratytour.ApiConstant.ApiUtils;
import com.procialize.ingenratytour.ApiConstant.OtpPost;
import com.procialize.ingenratytour.ApiConstant.ProductImagepost;
import com.procialize.ingenratytour.ApiConstant.RegisterPost;
import com.procialize.ingenratytour.GetterSetter.OtpData;
import com.procialize.ingenratytour.GetterSetter.ProductImages;
import com.procialize.ingenratytour.GetterSetter.Register;
import com.procialize.ingenratytour.GetterSetter.RegisterData;
import com.procialize.ingenratytour.R;
import com.procialize.ingenratytour.Session.SessionManager;
import com.procialize.ingenratytour.Util.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    TextInputEditText firstnameEt,lastnameEt,mobileEt,locationEt;
    TextInputLayout firstnametextInputLayout,lastnametextInputLayout,mobiletextInputLayout,locationtextInputLayout;
    Button submitbtn;

    private APIService mAPIService;
    Util.TransparentProgressDialog pd;
    SessionManager sessionManager;
    private static String name,mobile,id,token;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAPIService = ApiUtils.getAPIService();

        sessionManager = new SessionManager(this);

        mobileEt = findViewById(R.id.mobileEt);
        firstnameEt = findViewById(R.id.firstnameEt);
        lastnameEt = findViewById(R.id.lastnameEt);
        locationEt = findViewById(R.id.locationEt);


        submitbtn = findViewById(R.id.submitbtn);

        firstnametextInputLayout = findViewById(R.id.firstnametextInputLayout);
        lastnametextInputLayout = findViewById(R.id.lastnametextInputLayout);
        mobiletextInputLayout = findViewById(R.id.mobiletextInputLayout);
        locationtextInputLayout = findViewById(R.id.locationtextInputLayout);



        submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mobile = mobileEt.getText().toString();
                String firstname = firstnameEt.getText().toString();
                String lastname = lastnameEt.getText().toString();
                String location = locationEt.getText().toString();

                if (firstname.length()==0)
                {
                    firstnametextInputLayout.setError("Please Enter First Name");
                    lastnametextInputLayout.setError(null);
                    mobiletextInputLayout.setError(null);
                    locationtextInputLayout.setError(null);


                }else if (lastname.length()==0)
                {
                    lastnametextInputLayout.setError("Please Enter First Name");
                    firstnametextInputLayout.setError(null);
                    mobiletextInputLayout.setError(null);
                    locationtextInputLayout.setError(null);
                }else if (mobile.length()==0)
                {
                    mobiletextInputLayout.setError("Please Enter Mobile Number");
                    firstnametextInputLayout.setError(null);
                    lastnametextInputLayout.setError(null);
                    locationtextInputLayout.setError(null);

                }else if (mobile.length()<10)
                {
                    mobiletextInputLayout.setError("Please Enter Valid Mobile Number");
                    firstnametextInputLayout.setError(null);
                    lastnametextInputLayout.setError(null);
                    locationtextInputLayout.setError(null);
                }else if(location.length()==0)
                {

                    locationtextInputLayout.setError("Please Enter Location");
                    firstnametextInputLayout.setError(null);
                    lastnametextInputLayout.setError(null);
                    mobiletextInputLayout.setError(null);
                }else
                {
                    firstnametextInputLayout.setError(null);
                    lastnametextInputLayout.setError(null);
                    mobiletextInputLayout.setError(null);
                    locationtextInputLayout.setError(null);

                    if (Util.isNetworkConnected(SignUpActivity.this)) {
                        try {
                            if (pd == null) {
                                pd = new Util.TransparentProgressDialog(SignUpActivity.this);
                                pd.show();
                            }
                        }catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                        Register(firstname,lastname,mobile,location,"Android","1","");
                    }else
                    {
                        Toast.makeText(getApplicationContext(),"No Internet Connection",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }


    public void showOtpDialog() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this,R.style.DialogSlideAnim);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.custom_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText otpEt =  dialogView.findViewById(R.id.otpEt);
        final Button subbtn =  dialogView.findViewById(R.id.subbtn);
        final Button cancelbtn =  dialogView.findViewById(R.id.cancelbtn);

        final TextInputLayout otptextInputLayout = dialogView.findViewById(R.id.otptextInputLayout);

        final AlertDialog b = dialogBuilder.create();

        subbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String otp = otpEt.getText().toString();


                if (otp.length()==0)
                {
                    otptextInputLayout.setError("Please Enter Otp");
                }else
                {
                    otptextInputLayout.setError(null);

                    if (Util.isNetworkConnected(SignUpActivity.this))
                    {
                        Otp(otp,id);
                    }
                }
            }
        });


        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                b.dismiss();
            }
        });

        b.show();
    }


    public void Register(String firstname,String lastname,String mobileno,String location,String devicetype,String deviceid,String token) {

        try {
            if (pd == null) {
                pd = new Util.TransparentProgressDialog(SignUpActivity.this);
                pd.show();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        mAPIService.Register(new RegisterPost(firstname,lastname,mobileno,location,devicetype,deviceid,token)).enqueue(new Callback<RegisterData>() {
            @Override
            public void onResponse(Call<RegisterData> call, Response<RegisterData> response) {

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
            public void onFailure(Call<RegisterData> call, Throwable t) {
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

    public void showResponse(Response<RegisterData> response) {

        if (response.body().getSuccess().equals("true")) {

            Log.e("hit", response.body().getData() + "");

            name = response.body().getData().getFirstName()+" "+response.body().getData().getLastName();
            mobile = response.body().getData().getMobileNo();
            id= String.valueOf(response.body().getData().getId());
            token =response.body().getData().getAccess_token();

            showOtpDialog();

        } else {
            Toast.makeText(this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    public void Otp(String otp,String id) {

        try {
            if (pd == null) {
                pd = new Util.TransparentProgressDialog(SignUpActivity.this);
                pd.show();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        mAPIService.Otp(new OtpPost(otp,id,"")).enqueue(new Callback<OtpData>() {
            @Override
            public void onResponse(Call<OtpData> call, Response<OtpData> response) {

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
                    showOtpDataResponse(response);
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
            public void onFailure(Call<OtpData> call, Throwable t) {
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

    private void showOtpDataResponse(Response<OtpData> response) {


        if (response.body().getSuccess().equals("true")) {

            sessionManager.createLoginSession(name,mobile,id,token);
            Intent i = new Intent(getApplicationContext(),HomeActivity.class);
            startActivity(i);

        } else {
            Toast.makeText(this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
