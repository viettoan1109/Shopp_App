package com.actvn.shopapp.customview;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatEditText;

public class CustomEdittext extends AppCompatEditText {
    public CustomEdittext(Context context) {
        super(context);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "medium.ttf");

    }

    public CustomEdittext(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "medium.ttf");
        this.setTypeface(typeface);

    }

    public CustomEdittext(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Typeface typeface = Typeface.createFromAsset(context.getAssets(), "medium.ttf");
        this.setTypeface(typeface);
    }
}
