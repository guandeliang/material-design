package com.jacob.material.appstart;

import android.app.Application;
import android.util.Log;

public class IntroBlankApplication extends Application {
    @Override
    public void onCreate() {
        try {
            for(int i=0; i<10; i++){
                Thread.sleep(500);
                Log.d("App_Start_Time", "Application onCreate is sleep");
            }
        }catch (Exception e){
        }
        super.onCreate();
    }
}
