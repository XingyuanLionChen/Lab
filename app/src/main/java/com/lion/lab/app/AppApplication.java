package com.lion.lab.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jeremyliao.liveeventbus.LiveEventBus;

public class AppApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initLiveData();
        initFresco();
    }

    private void initLiveData() {
        LiveEventBus.get()
                .config()
                .supportBroadcast(this)
                .lifecycleObserverAlwaysActive(true);
    }

    private void initFresco() {
        Fresco.initialize(this);
    }
}
