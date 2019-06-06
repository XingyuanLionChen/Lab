package com.lion.lab.fresco;

import android.os.Bundle;

import com.lion.lab.R;
import com.lion.lab.base.BaseActivity;

public class FrescoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fresco);

        String imageURI = "http://mmbiz.qpic.cn/mmbiz/PwIlO51l7wuFyoFwAXfqPNETWCibjNACIt6ydN7vw8LeIwT7IjyG3eeribmK4rhibecvNKiaT2qeJRIWXLuKYPiaqtQ/0";
        viewHolder.setImageURI(R.id.sdvFresco, imageURI);
    }
}
