/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-31 下午5:05
 *
 */

package com.jacob.material.example.banner;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.jacob.material.R;
import com.jacob.material.databinding.BannerBaseActivityBinding;

public class BannerBaseActivity extends AppCompatActivity {
    private BannerBaseActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.banner_base_activity);

        binding.fixButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.motionLayout.setTransitionDuration(300);
                binding.motionLayout.transitionToEnd();
            }
        });
    }


}
