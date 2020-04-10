/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-31 下午5:05
 *
 */

package com.jacob.material.example.chip;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.jacob.material.R;
import com.jacob.material.databinding.ChipDayNightCustomActivityBinding;
import com.jacob.material.databinding.ChipDayNightDefaultActivityBinding;
import com.jacob.temp.TempConstant;
import com.jacob.utils.WidgetsUtils;

public class ChipDayNightCustomActivity extends AppCompatActivity {
    private ChipDayNightCustomActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.chip_day_night_custom_activity);

        setEntryChipIcon(binding.entryChip001, R.drawable.header_001);
        setEntryChipIcon(binding.entryChip002, R.drawable.header_002);
        setEntryChipIcon(binding.entryChip003, R.drawable.header_003);



        setCheckedIconColor(binding.filterChip003);
        setCheckedIconColor(binding.filterChip002);
        setCheckedIconColor(binding.filterChip001);
/*
        OnFilterChipCheckChangeListener chipCheckChangeListener = new OnFilterChipCheckChangeListener();
        binding.filterChip001.setOnCheckedChangeListener(chipCheckChangeListener);
        binding.filterChip002.setOnCheckedChangeListener(chipCheckChangeListener);
        binding.filterChip003.setOnCheckedChangeListener(chipCheckChangeListener);

 */
    }

    private void setEntryChipIcon(Chip chip, int resId) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
        RoundedBitmapDrawable rnd = (RoundedBitmapDrawable) RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        rnd.setCircular(true);
        chip.setChipIcon(rnd);
    }

    private void setCheckedIconColor(Chip chip){
        //每个Chip都要使用不同的drawable实例，否则颜色会乱
        Drawable drawable = AppCompatResources.getDrawable(this, R.drawable.icon_check_circle_outline);
        WidgetsUtils.setDrawableColor(ChipDayNightCustomActivity.this, drawable, R.attr.colorSecondary);
        chip.setCheckedIcon(drawable);
    }


    private class OnFilterChipCheckChangeListener implements CompoundButton.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        }
    }


}
