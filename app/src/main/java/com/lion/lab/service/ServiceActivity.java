package com.lion.lab.service;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.lion.lab.R;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        Intent intent = new Intent(this, LabService.class);
        intent.putExtra("test", "lab");
        startService(intent);
    }
}
