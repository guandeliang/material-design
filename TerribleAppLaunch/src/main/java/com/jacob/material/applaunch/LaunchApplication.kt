package com.jacob.material.applaunch

import android.app.Application
import android.os.SystemClock

class LaunchApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        //模拟初始化所消耗的时间
        SystemClock.sleep(200)
    }
}