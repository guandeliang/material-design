/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-1 下午10:13
 *
 */

package com.jacob.material.example.bottomnavigation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.jacob.material.R;
import com.jacob.material.databinding.BottomNavigationDayNightDefaultActivityBinding;

public class BottomNavigationDayNightDefaultActivity extends AppCompatActivity {
    private BottomNavigationDayNightDefaultActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.bottom_navigation_day_night_default_activity);
    }

}
