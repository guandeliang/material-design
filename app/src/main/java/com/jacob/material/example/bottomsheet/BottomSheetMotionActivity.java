/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-8 下午8:53
 *
 */

package com.jacob.material.example.bottomsheet;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.jacob.material.R;
import com.jacob.material.databinding.BottomSheetMotionActivityBinding;

public class BottomSheetMotionActivity extends AppCompatActivity {
    private BottomSheetMotionActivityBinding binding;
    private BottomSheetMotionViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.bottom_sheet_motion_activity);
        viewModel = new ViewModelProvider(this).get(BottomSheetMotionViewModel.class);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);

        viewModel.setNavController(navController);
    }
}
