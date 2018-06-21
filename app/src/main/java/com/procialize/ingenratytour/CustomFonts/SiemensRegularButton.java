package com.procialize.ingenratytour.CustomFonts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by Naushad on 11/1/2017.
 */

@SuppressLint("AppCompatCustomView")
public class SiemensRegularButton extends Button {

    public SiemensRegularButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public SiemensRegularButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SiemensRegularButton(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "SiemensRegular.otf");
        setTypeface(tf ,1);

    }

}

//    Roboto-Light.ttf

