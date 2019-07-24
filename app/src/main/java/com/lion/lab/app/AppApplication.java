package com.lion.lab.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jeremyliao.liveeventbus.LiveEventBus;
import com.lion.lab.http.HttpClient;

public class AppApplication extends Application {
    private static AppApplication instance;

    public static AppApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        initInstance();
        initLiveData();
        initFresco();
        initHttpClient();
        initDpi();
    }

    private void initInstance() {
        instance = this;
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

    private void initDpi() {
        Density.setDensity(this, 360);

    }

    private void initHttpClient() {
        HttpClient.init();
    }
}
