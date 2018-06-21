package com.procialize.ingenratytour.CustomFonts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.support.design.widget.TextInputEditText;
import android.util.AttributeSet;

/**
 * Created by Naushad on 11/1/2017.
 */

@SuppressLint("AppCompatCustomView")
public class SiemensBoldTextInputEditext extends TextInputEditText {

    public SiemensBoldTextInputEditext(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public SiemensBoldTextInputEditext(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SiemensBoldTextInputEditext(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "SiemensSansBold.ttf");
        setTypeface(tf ,1);

    }

}