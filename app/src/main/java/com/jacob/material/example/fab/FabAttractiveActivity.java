/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-19 上午9:42
 *
 */

package com.jacob.material.example.fab;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.jacob.material.R;
import com.jacob.material.databinding.FabAttractiveActivityBinding;

public class FabAttractiveActivity extends AppCompatActivity {
    private FabAttractiveActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.fab_attractive_activity);
    }


}
