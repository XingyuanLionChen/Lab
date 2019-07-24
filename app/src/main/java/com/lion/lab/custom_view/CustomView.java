package com.lion.lab.custom_view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;

import com.lion.lab.R;
import com.lion.lab.base.BaseView;

public class CustomView extends BaseView {
    private Bitmap background;
    private Bitmap decorate;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public void init() {
        background = BitmapFactory.decodeResource(getResources(), R.drawable.place_holder);
        decorate = BitmapFactory.decodeResource(getResources(), R.drawable.android);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawBitmap(background, 0, 0, null);
        canvas.drawBitmap(decorate, 10, 10, null);
    }
}
