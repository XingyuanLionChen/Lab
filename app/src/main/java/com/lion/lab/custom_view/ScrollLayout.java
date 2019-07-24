package com.lion.lab.custom_view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

import androidx.recyclerview.widget.RecyclerView;

import static com.lion.lab.app.Constants.TAG;

public class ScrollLayout extends LinearLayout {
    private Scroller scroller;
    private float startY;
    private float scrollY;
    private View view;

    public ScrollLayout(Context context) {
        this(context, null);
        init(context);
    }

    public ScrollLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init(context);
    }

    public ScrollLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        scroller = new Scroller(context);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        view = getChildAt(0);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (view instanceof RecyclerView) {
            switch (ev.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    startY = ev.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    if (!view.canScrollVertically(1) && (ev.getY() < startY)) {
                        return true;
                    }
                    if (!view.canScrollVertically(-1) && (ev.getY() > startY)) {
                        return true;
                    }
                    break;
            }
        }
        return false;
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
                int dy;
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
