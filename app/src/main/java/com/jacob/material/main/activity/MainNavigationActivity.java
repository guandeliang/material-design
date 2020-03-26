/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-2 下午3:03
 *
 */

package com.jacob.material.main.activity;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.snackbar.Snackbar;
import com.jacob.material.R;

public class MainNavigationActivity extends AppCompatActivity {

    private long lastPressBackTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_navigation_activity);
        lastPressBackTime = 0;
        this.getOnBackPressedDispatcher().addCallback(new BackPressedCallback());

    }

    private class BackPressedCallback extends OnBackPressedCallback {
        private BackPressedCallback(){
            super(true);
        }

        @Override
        public void handleOnBackPressed() {
            long now = System.currentTimeMillis();
            if(now - lastPressBackTime < 2000){
                finish();
            }else{
                lastPressBackTime = now;
                Snackbar snackbar = Snackbar
                        .make(findViewById(R.id.frame_layout), "再按一次退出", Snackbar.LENGTH_LONG);
                snackbar.show();


            }
        }
    }

}
