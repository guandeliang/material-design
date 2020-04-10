/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-31 下午5:05
 *
 */

package com.jacob.material.example.button;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.jacob.material.R;
import com.jacob.material.databinding.ButtonMiddleEmphasisActivityBinding;
import com.jacob.utils.WidgetsUtils;

public class ButtonMiddleEmphasisActivity extends AppCompatActivity {
    private ButtonMiddleEmphasisActivityBinding binding;
    private BottomSheetBehavior sheetBehavior;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.button_middle_emphasis_activity);

        //WidgetsUtils.setSystemBarColor(this, R.color.transparent_white_20);
        //WidgetsUtils.setSystemBarLight(this);
        //WidgetsUtils.setNavigationBarColor(this, android.R.attr.colorBackground);

        sheetBehavior = BottomSheetBehavior.from(binding.bottomSheetConstraintLayout);
        sheetBehavior.setFitToContents(true);
        sheetBehavior.setHideable(false);
        sheetBehavior.setPeekHeight(WidgetsUtils.dpToPx(this, 113));
        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

    }


}
