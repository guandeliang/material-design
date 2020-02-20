/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-19 上午9:22
 *
 */

package com.jacob.book.material.example.bottomnavigation;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.CutCornerTreatment;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.RoundedCornerTreatment;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.jacob.book.WidgetsUtils;
import com.jacob.book.material.R;
import com.jacob.book.material.databinding.BottomNavigationDarkActivityBinding;

public class BottomNavigationDarkActivity extends AppCompatActivity {
    private BottomNavigationDarkActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.bottom_navigation_dark_activity);
        WidgetsUtils.setSystemBarColor(this, R.color.dark_color_background);

        setLabelCorner(binding.stateTextView001, R.color.material_color_deep_orange);
        setLabelCorner(binding.stateTextView002, R.color.material_color_green);
        setLabelCorner(binding.stateTextView003, R.color.material_color_cyan);

        setLabelCorner(binding.stateTextView004, R.color.transparent_white_09_04);
        setLabelCorner(binding.stateTextView005, R.color.transparent_white_09_04);
        setLabelCorner(binding.stateTextView006, R.color.transparent_white_09_04);

    }

    private void setLabelCorner(TextView label, int colorResId){
        ShapeAppearanceModel.Builder builder = ShapeAppearanceModel.builder();
        builder.setBottomLeftCorner(CornerFamily.ROUNDED, WidgetsUtils.dpToPx(this, 8));
        builder.setBottomRightCorner(CornerFamily.ROUNDED, WidgetsUtils.dpToPx(this, 8));

        MaterialShapeDrawable drawable = new MaterialShapeDrawable(builder.build());
        drawable.setTint(WidgetsUtils.getColorValue(this, colorResId));

        label.setBackground(drawable);
    }



}
