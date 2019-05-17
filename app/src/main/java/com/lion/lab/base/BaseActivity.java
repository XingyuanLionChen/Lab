package com.lion.lab.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lion.lab.app.Constants;
import com.lion.lab.common.viewholder.ViewHolder;

public class BaseActivity extends AppCompatActivity {
    public static final String TAG = Constants.TAG;
    public ViewHolder viewHolder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewHolder = new ViewHolder(getWindow().getDecorView());
    }
}
