package com.procialize.ingenratytour.Activity;

import android.content.Intent;
import android.os.Handler;
import android.support.multidex.MultiDex;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.procialize.ingenratytour.R;
import com.procialize.ingenratytour.Session.SessionManager;

public class SplashActivity extends AppCompatActivity {


    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;


    // Used to load the 'native-lib' library on application startup.
//    static {
//        System.loadLibrary("native-lib");
//    }

    ImageView imageview;
    SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        MultiDex.install(this);
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);

        imageview=findViewById(R.id.imageview);

        Animation zoomin = AnimationUtils.loadAnimation(this, R.anim.zoomin);
        Animation zoomout = AnimationUtils.loadAnimation(this, R.anim.zoomout);
        imageview.startAnimation(zoomout);

        sessionManager =  new SessionManager(SplashActivity.this);

        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity


                if (sessionManager.isLoggedIn())
                {
                    Intent i = new Intent(SplashActivity.this, HomeActivity.class);
                    startActivity(i);
                    finish();

                }else
                {
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();

                }


            }
        }, SPLASH_TIME_OUT);

        // Example of a call to a native method
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
//    public native String stringFromJNI();
}
