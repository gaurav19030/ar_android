package com.procialize.ingenratytour.Activity;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
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
import android.widget.TextView;
import android.widget.Toast;

import com.procialize.ingenratytour.ApiConstant.APIService;
import com.procialize.ingenratytour.ApiConstant.ApiUtils;
import com.procialize.ingenratytour.ApiConstant.LoginPost;
import com.procialize.ingenratytour.ApiConstant.OtpPost;
import com.procialize.ingenratytour.GetterSetter.MobileLogin;
import com.procialize.ingenratytour.GetterSetter.MobileLoginData;
import com.procialize.ingenratytour.GetterSetter.OtpData;
import com.procialize.ingenratytour.R;
import com.procialize.ingenratytour.Session.SessionManager;
import com.procialize.ingenratytour.Util.Util;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {


    TextInputEditText mobileEt;
    TextInputLayout inputLayoutmobile;
    Button loginbtn;
    TextView signupbtn,pramotionTv;
    private APIService mAPIService;
    Util.TransparentProgressDialog pd;
    SessionManager sessionManager;
    String name,id,mobile,token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAPIService = ApiUtils.getAPIService();
sessionManager=new SessionManager(LoginActivity.this);

        mobileEt = findViewById(R.id.mobileEt);
        loginbtn = findViewById(R.id.loginbtn);
        signupbtn = findViewById(R.id.signupbtn);
        pramotionTv = findViewById(R.id.pramotionTv);

        inputLayoutmobile = findViewById(R.id.nametextInputLayout);

        signupbtn.setPaintFlags(signupbtn.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        signupbtn.setText("Sign Up");

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(signup);
            }
        });

        pramotionTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.procialize.net/home.php";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });



        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mobile = mobileEt.getText().toString();

                if (mobile.length()==0)
                {
                    inputLayoutmobile.setError("Please Enter Mobile Number");
                }else if (mobile.length()<10)
                {
                    inputLayoutmobile.setError("Please Enter Valid Mobile Number");
                }else
                {
                    inputLayoutmobile.setError(null);

                    Login(mobileEt.getText().toString());

                }
            }
        });
    }




    public void Login(String mobileno) {

        try {
            if (pd == null) {
                pd = new Util.TransparentProgressDialog(LoginActivity.this);
                pd.show();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        try {
            if (pd == null) {
                pd = new Util.TransparentProgressDialog(LoginActivity.this);
                pd.show();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        mAPIService.MobileLogin(new LoginPost(mobileno)).enqueue(new Callback<MobileLogin>() {
            @Override
            public void onResponse(Call<MobileLogin> call, Response<MobileLogin> response) {

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
                    showLoginDataResponse(response);
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
            public void onFailure(Call<MobileLogin> call, Throwable t) {
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

    private void showLoginDataResponse(Response<MobileLogin> response) {


        if (response.body().getSuccess().equals("true")) {

            id=response.body().getData().getId();
            token=response.body().getData().getAccessToken();

            showOtpDialog();

        } else {
            Toast.makeText(this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
        }
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
                    Otp(otpEt.getText().toString(),id,token);
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


    public void Otp(String otp,String id,String accesstoken) {

        try {
            if (pd == null) {
                pd = new Util.TransparentProgressDialog(LoginActivity.this);
                pd.show();
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        mAPIService.Otp(new OtpPost(otp,id,accesstoken)).enqueue(new Callback<OtpData>() {
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
            finish();

        } else {
            Toast.makeText(this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
