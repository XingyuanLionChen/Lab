package com.lion.lab.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Scroller;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;

import static com.lion.lab.app.Constants.TAG;

public class ScrollButton extends AppCompatButton {
    private Scroller scroller;
    private float startY;
    private float scrollY;

    public ScrollButton(Context context) {
        this(context, null);
        init(context);
    }

    public ScrollButton(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
        init(context);
    }

    public ScrollButton(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        scroller = new Scroller(context);
        setOnClickListener(v -> Toast.makeText(getContext(), "123", Toast.LENGTH_SHORT).show());
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startY = event.getY();
                scrollY = getScrollY();
                break;
            case MotionEvent.ACTION_MOVE:
                float distance = 4 * (startY - event.getY());
                int dy = 0;
                if (distance > 0) {
                    dy = (int) Math.log(distance);
                } else {
                    dy = -(int) Math.log(-distance);
                }
                dy /= Math.log(2);
                scroller.startScroll(getScrollX(), getScrollY(), 0, dy, 0);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                scroller.startScroll(getScrollX(), getScrollY(), 0, (int) (scrollY - getScrollY()));
                invalidate();
                performClick();
                break;
        }
        return true;
    }

    @Override
    public void computeScroll() {
        if (scroller.computeScrollOffset()) {
            Log.d(TAG, "computeScroll: " + scroller.getCurrY());
            scrollTo(scroller.getCurrX(), scroller.getCurrY());
            postInvalidate();
        }
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }
}
