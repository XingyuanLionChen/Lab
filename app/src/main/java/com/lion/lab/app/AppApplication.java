package com.lion.lab.app;

import android.app.Application;

import com.jeremyliao.liveeventbus.LiveEventBus;

public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        LiveEventBus.get()
                .config()
                .supportBroadcast(this)
                .lifecycleObserverAlwaysActive(true);
    }
}
