/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-8 下午8:53
 *
 */

package com.jacob.material.example.bottomsheet;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.jacob.material.R;
import com.jacob.material.databinding.BottomSheetFullActivityBinding;
import com.jacob.temp.TempConstant;
import com.jacob.utils.WidgetsUtils;

public class BottomSheetFullActivity extends AppCompatActivity {

    private BottomSheetFullActivityBinding binding;
    private BottomSheetBehavior sheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.bottom_sheet_full_activity);
        WidgetsUtils.setSystemBarColor(BottomSheetFullActivity.this, R.color.gray_300);
        WidgetsUtils.setSystemBarLight(BottomSheetFullActivity.this);


        sheetBehavior = BottomSheetBehavior.from(binding.bottomSheetLinearLayout);
        sheetBehavior.setFitToContents(false);
        sheetBehavior.setHideable(false);
        sheetBehavior.setPeekHeight(WidgetsUtils.dpToPx(this, 72));
        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        sheetBehavior.addBottomSheetCallback(new BottomSheetBehaviorCallback());

        binding.collapseSheetImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        });
    }


    private class BottomSheetBehaviorCallback extends BottomSheetBehavior.BottomSheetCallback{
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if(newState == BottomSheetBehavior.STATE_EXPANDED){
                binding.motionLayout.setProgress(1f);
            }else if(newState == BottomSheetBehavior.STATE_COLLAPSED || newState == BottomSheetBehavior.STATE_HALF_EXPANDED){
                binding.motionLayout.setProgress(0f);
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
            float halfRatio =  sheetBehavior.getHalfExpandedRatio();
            float progress = (slideOffset - halfRatio)/(1f - halfRatio);
            if(progress >= 0){
                binding.motionLayout.setProgress(progress);
            }else{
                binding.motionLayout.setProgress(0f);
            }
        }
    }



}
