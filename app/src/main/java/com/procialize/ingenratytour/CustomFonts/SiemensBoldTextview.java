package com.procialize.ingenratytour.CustomFonts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by Naushad on 11/1/2017.
 */

@SuppressLint("AppCompatCustomView")
public class SiemensBoldTextview extends TextView {

    public SiemensBoldTextview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public SiemensBoldTextview(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SiemensBoldTextview(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "SiemensSansBold.ttf");
        setTypeface(tf ,1);

    }

}

//    Roboto-Light.ttf

