package com.procialize.ingenratytour.Util;

import android.app.Dialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.procialize.ingenratytour.R;

import java.net.InetAddress;

/**
 * Created by Naushad on 11/6/2017.
 */

public class Util {



    public static void customtoast(Context context, LayoutInflater inflater, String message) {

        View customToastroot =inflater.inflate(R.layout.customtoast, null);

        TextView textTv = customToastroot.findViewById(R.id.textTv);

        Toast customtoast=new Toast(context);

        customtoast.setView(customToastroot);
        customtoast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL,0, 0);
        customtoast.setDuration(Toast.LENGTH_LONG);
        textTv.setText(message);
        customtoast.show();
    }


    public static class TransparentProgressDialog extends Dialog {

        public TransparentProgressDialog(Context context) {
            super(context, R.style.AppTheme);

            WindowManager.LayoutParams wlmp = getWindow().getAttributes();

            wlmp.gravity = Gravity.CENTER_HORIZONTAL;
            getWindow().setAttributes(wlmp);
            setTitle(null);
            setCancelable(false);
            setOnCancelListener(null);
            View view = LayoutInflater.from(context).inflate(
                    R.layout.custom_progress_dialog, null);
            setContentView(view);
        }
    }


    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("www.google.com"); //You can replace it with your name
            return !ipAddr.equals("");

        } catch (Exception e) {
            return false;
        }

    }
}
