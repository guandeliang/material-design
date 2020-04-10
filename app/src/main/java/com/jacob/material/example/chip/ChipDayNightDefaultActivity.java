/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-31 下午5:05
 *
 */

package com.jacob.material.example.chip;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.jacob.material.R;
import com.jacob.material.databinding.ButtonDayNightDefaultActivityBinding;
import com.jacob.material.databinding.ChipDayNightDefaultActivityBinding;
import com.jacob.utils.WidgetsUtils;

public class ChipDayNightDefaultActivity extends AppCompatActivity {
    private ChipDayNightDefaultActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.chip_day_night_default_activity);
        binding.entryChipEditText.setOnEditorActionListener(new ChipEditTextActionListener());


    }

    private class ChipEditTextActionListener implements TextView.OnEditorActionListener{
        @Override
        public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
            if(actionId == EditorInfo.IME_ACTION_DONE){
                String text= binding.entryChipEditText.getText().toString();
                if(!text.isEmpty()){
                    addChipToGroup(text, binding.entryChipGroup);
                    binding.entryChipEditText.setText("");
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }

        }
    }

    private void addChipToGroup(String text, ChipGroup chipGroup) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.header_001);
        RoundedBitmapDrawable rnd = (RoundedBitmapDrawable) RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        rnd.setCircular(true);

        Chip chip = new Chip(this);
        chip.setChipIcon(rnd);
        chip.setText(text);
        chip.setCloseIconVisible(true);
        chip.setCloseIconResource(R.drawable.icon_remove_circle_outline);
        chip.setCheckable(false);
        chip.setClickable(false);
        chipGroup.addView(chip);
        chip.setOnCloseIconClickListener(view -> {chipGroup.removeView(chip);});
    }

}
