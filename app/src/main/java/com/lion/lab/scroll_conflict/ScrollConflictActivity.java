package com.lion.lab.scroll_conflict;

import android.os.Bundle;
import android.util.Log;

import com.lion.lab.R;
import com.lion.lab.base.BaseActivity;

public class ScrollConflictActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_conflict);

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        Log.d(TAG, "onCreate: " + viewHolder.getView(R.id.svScrollConflict).getMeasuredHeight() + " " +
                viewHolder.getView(R.id.svScrollConflict).getHeight());
    }
}
