/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-31 下午5:05
 *
 */

package com.jacob.material.example.chip;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.chip.Chip;
import com.google.android.material.snackbar.Snackbar;
import com.jacob.material.R;
import com.jacob.material.databinding.ChipChoiceActivityBinding;
import com.jacob.temp.TempConstant;
import com.jacob.utils.WidgetsUtils;

public class ChipChoiceActivity extends AppCompatActivity {
    private ChipChoiceActivityBinding binding;
    private Chip[] sizeChips;
    private Chip[] colorChips;
    private Chip[] priceChips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.chip_choice_activity);
        binding.setActivity(this);

        sizeChips = new Chip[6];
        sizeChips[0] = binding.sizeXsChoiceChip;
        sizeChips[1] = binding.sizeSChoiceChip;
        sizeChips[2] = binding.sizeMChoiceChip;
        sizeChips[3] = binding.sizeLChoiceChip;
        sizeChips[4] = binding.sizeXlChoiceChip;
        sizeChips[5] = binding.sizeXxlChoiceChip;

        colorChips = new Chip[6];
        colorChips[0] = binding.color1ChoiceChip;
        colorChips[1] = binding.color2ChoiceChip;
        colorChips[2] = binding.color3ChoiceChip;
        colorChips[3] = binding.color4ChoiceChip;
        colorChips[4] = binding.color5ChoiceChip;
        colorChips[5] = binding.color6ChoiceChip;

        priceChips = new Chip[3];
        priceChips[0] = binding.price1ChoiceChip;
        priceChips[1] = binding.price2ChoiceChip;
        priceChips[2] = binding.price3ChoiceChip;

        for(Chip c:colorChips){
            Drawable drawable = AppCompatResources.getDrawable(this, R.drawable.icon_check);
            WidgetsUtils.setDrawableColor(ChipChoiceActivity.this, drawable, R.color.gray_200);
            c.setCheckedIcon(drawable);
        }

    }

    public void onSizeCheckedChanged(CompoundButton compoundButton, boolean b){
        if(!b){
            return;
        }
        for(Chip c:sizeChips){
            if(c.getId() != compoundButton.getId()){
                c.setChecked(false);
            }
        }
    }


    public void onColorCheckedChanged(CompoundButton compoundButton, boolean b){
        if(!b){
            return;
        }

        for(Chip c:colorChips){
            if(c.getId() != compoundButton.getId()){
                c.setChecked(false);
            }
        }
    }

    public void onPriceCheckedChanged(CompoundButton compoundButton, boolean b){
        if(!b){
            return;
        }

        for(Chip c:priceChips){
            if(c.getId() != compoundButton.getId()){
                c.setChecked(false);
            }
        }
    }

    public void onClearClick(View view){
        for(Chip c:sizeChips){
            c.setChecked(false);
        }
        for(Chip c:colorChips){
            c.setChecked(false);
        }
        for(Chip c:priceChips){
            c.setChecked(false);
        }
    }

    public void onOkClick(View view){
        Snackbar snackbar = Snackbar.make(binding.constraintLayout, "查看筛选结果", Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
