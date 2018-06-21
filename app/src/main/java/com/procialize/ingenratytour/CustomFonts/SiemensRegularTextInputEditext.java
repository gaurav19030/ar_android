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
public class SiemensRegularTextInputEditext extends TextInputEditText {

    public SiemensRegularTextInputEditext(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public SiemensRegularTextInputEditext(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SiemensRegularTextInputEditext(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "SiemensRegular.otf");
        setTypeface(tf ,1);

    }

}