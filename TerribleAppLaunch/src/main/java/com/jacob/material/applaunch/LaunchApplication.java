package com.jacob.material.applaunch;

import android.app.Application;
import android.os.SystemClock;

public class LaunchApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(5000);
    }
}
