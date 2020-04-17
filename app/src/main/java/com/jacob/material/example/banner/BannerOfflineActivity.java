/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-2 下午3:03
 *
 */

package com.jacob.material.example.banner;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.snackbar.Snackbar;
import com.jacob.material.R;
import com.jacob.material.databinding.BannerOfflineActivityBinding;

public class BannerOfflineActivity extends AppCompatActivity {

    private BannerOfflineActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.banner_offline_activity);
    }


}
