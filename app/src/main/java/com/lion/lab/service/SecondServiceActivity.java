package com.lion.lab.service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.jeremyliao.liveeventbus.LiveEventBus;
import com.lion.lab.R;
import com.lion.lab.base.BaseActivity;

public class SecondServiceActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_service);

        LabServiceConnection labServiceConnection = new LabServiceConnection();
        bindService(new Intent(this, LabService.class), labServiceConnection, Context.BIND_AUTO_CREATE);
        unbindService(labServiceConnection);
        LiveEventBus.get()
                .with(ServiceActivity.BINDING_STATE)
                .post(ServiceActivity.BOUND);
    }

    private class LabServiceConnection implements ServiceConnection {
        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            Log.d(TAG, "onServiceConnected");
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            Log.d(TAG, "onServiceDisconnected");
        }
    }
}
