package com.lion.lab.base;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public abstract class BaseView extends View {

    public BaseView(Context context) {
        super(context);
        init();
    }

    public BaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BaseView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BaseView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    public abstract void init();
}
