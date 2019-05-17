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

/**
 * Context.BIND_AUTO_CREATE used when client bind to service but does not know if service started
 * or not?
 */
public class ServiceActivity extends BaseActivity {
    public static final String BINDING_STATE = "BINDING_STATE";
    public static final int BOUND = 0;
    private Intent intent;
    private LabServiceConnection labServiceConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        intent = new Intent(this, LabService.class);

        labServiceConnection = new LabServiceConnection();

        viewHolder.setOnClickListener(R.id.btnNewActivity, v -> {
            Intent intent = new Intent(ServiceActivity.this, SecondServiceActivity.class);
            startActivity(intent);
        });

        case10();
    }

    /**
     * startService makes onCreate, onStartCommand called
     * stopService makes onDestroy called
     */
    private void case0() {
        startService(intent);//onCreate, onStartCommand
        stopService(intent);//onDestroy
    }

    /**
     * noting happened, started service or Context.BIND_AUTO_CREATE flag required
     */
    private void case1() {
        bindService(intent, labServiceConnection, 0);
        unbindService(labServiceConnection);
    }

    /**
     * onDestroy has not been called, service still alive
     */
    private void case2() {
        startService(intent);//onCreate, onStartCommand
        bindService(intent, labServiceConnection, 0);//onBind
        unbindService(labServiceConnection);//onUnbind
    }

    /**
     * stopService will unbind self
     */
    private void case3() {
        startService(intent);//onCreate, onStartCommand
        bindService(intent, labServiceConnection, 0);//onBind
        stopService(intent);//onUnbind, onDestroy
    }

    /**
     * onCreate, onBind, onUnbind, onDestroy will only be called once
     * onStartCommand can be called multi-times
     */
    private void case4() {
        startService(intent);//onCreate, onStartCommand
        startService(intent);//onStartCommand
        bindService(intent, labServiceConnection, 0);//onBind
        bindService(intent, labServiceConnection, 0);
        stopService(intent);//onUnbind onDestroy
        stopService(intent);
    }

    /**
     * java.lang.IllegalArgumentException: Service not registerer
     * service can only be bound or unbound to once
     */
    private void case5() {
        startService(intent);//onCreate, onStartCommand
        bindService(intent, labServiceConnection, 0);//onBind
        bindService(intent, labServiceConnection, 0);
        unbindService(labServiceConnection);//onUnbind
        unbindService(labServiceConnection);//exception
        stopService(intent);
    }

    /**
     * while BIND_AUTO_CREATE will create the service,its onStartCommand method will still only be
     * called due to an explicit call to startService
     */
    private void case6() {
        bindService(intent, labServiceConnection, Context.BIND_AUTO_CREATE);//onCreate, onBind
        unbindService(labServiceConnection);//onUnbind, onDestroy
    }

    /**
     * service cannot be stopped or unbound to by stopService with BIND_AUTO_CREATE
     */
    private void case7() {
        startService(intent);//onCreate, onStartCommand
        bindService(intent, labServiceConnection, Context.BIND_AUTO_CREATE);//onBind
        stopService(intent);
    }

    /**
     * service still alive, BIND_AUTO_CREATE blocked stopService totally
     */
    private void case8() {
        bindService(intent, labServiceConnection, Context.BIND_AUTO_CREATE);//onCreate, onBind
        stopService(intent);
    }

    /**
     * if startService called, unbindService cannot make service dead
     */
    private void case9() {
        startService(intent);//onCreate, onStartCommand
        bindService(intent, labServiceConnection, Context.BIND_AUTO_CREATE);//onBind
        unbindService(labServiceConnection);//onUnbind
    }

    /**
     * if startService called, stopService has to be called
     */
    private void case10() {
        startService(intent);//onCreate, onStartCommand
        bindService(intent, labServiceConnection, Context.BIND_AUTO_CREATE);//onBind
        unbindService(labServiceConnection);//onUnbind
        stopService(intent);//onDestroy
    }

    /**
     * service still alive, seems BIND_AUTO_CREATE blocked stopService totally
     */
    private void case11() {
        startService(intent);

        LiveEventBus.get()
                .with(BINDING_STATE, Integer.class)
                .observe(this, state -> {
                    if (state == BOUND) {
                        stopService(intent);
                    }
                });
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
