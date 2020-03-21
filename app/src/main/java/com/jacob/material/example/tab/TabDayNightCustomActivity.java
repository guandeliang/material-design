/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-20 下午9:40
 *
 */

package com.jacob.material.example.tab;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.jacob.material.R;
import com.jacob.material.databinding.TabDayNightCustomActivityBinding;

public class TabDayNightCustomActivity extends AppCompatActivity {
    private TabDayNightCustomActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.tab_day_night_custom_activity);

    }

}
