/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-1 下午6:39
 *
 */

package com.jacob.material.example.bottomappbar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.jacob.material.R;
import com.jacob.material.databinding.BottomAppBarDayNightDefaultActivityBinding;

public class BottomAppBarDayNightDefaultActivity extends AppCompatActivity {
    private BottomAppBarDayNightDefaultActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.bottom_app_bar_day_night_default_activity);
    }

}
