package com.procialize.ingenratytour.CustomFonts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Naushad on 11/1/2017.
 */

@SuppressLint("AppCompatCustomView")
public class SiemensRegularEditext extends EditText {

    public SiemensRegularEditext(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public SiemensRegularEditext(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SiemensRegularEditext(Context context) {
        super(context);
        init();
    }

    public void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(), "SiemensRegular.otf");
        setTypeface(tf ,1);

    }

}