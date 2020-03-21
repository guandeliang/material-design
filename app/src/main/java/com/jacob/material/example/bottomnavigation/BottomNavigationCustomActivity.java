/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-19 上午9:42
 *
 */

package com.jacob.material.example.bottomnavigation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.jacob.utils.WidgetsUtils;
import com.jacob.material.R;
import com.jacob.material.databinding.BottomNavigationCustomActivityBinding;

public class BottomNavigationCustomActivity extends AppCompatActivity {
    private BottomNavigationCustomActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.bottom_navigation_custom_activity);
        WidgetsUtils.setSystemBarColor(this, R.color.dark_color_background);
        WidgetsUtils.setSystemBarDark(this);


    }


}
