package com.procialize.ingenratytour.CustomFonts;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.CheckBox;

/**
 * Created by Naushad on 11/4/2017.
 */


@SuppressLint("AppCompatCustomView")
public class RegularCheckBox extends CheckBox {

    // Constructors
    public RegularCheckBox(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }
    public RegularCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    public RegularCheckBox(Context context) {
        super(context);
        init();
    }

    // This class requires myfont.ttf to be in the assets/fonts folder
    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "SiemensRegular.otf");
        setTypeface(tf);
    }
}