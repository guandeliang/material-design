package com.jacob.material.applaunch

import android.app.Application
import android.os.SystemClock

class LaunchApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //Simulate time consuming task
        SystemClock.sleep(100)
    }

}