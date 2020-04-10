/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-31 下午5:05
 *
 */

package com.jacob.material.example.button;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.jacob.material.R;
import com.jacob.material.databinding.ButtonLowEmphasisActivityBinding;
import com.jacob.material.databinding.ButtonMiddleEmphasisActivityBinding;
import com.jacob.utils.WidgetsUtils;

public class ButtonLowEmphasisActivity extends AppCompatActivity {
    private ButtonLowEmphasisActivityBinding binding;
    private MaterialButton[] materialButtons;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.button_low_emphasis_activity);
        binding.setActivity(this);

        //WidgetsUtils.setSystemBarColor(this, R.color.transparent_white_20);
        //WidgetsUtils.setSystemBarLight(this);
        //WidgetsUtils.setNavigationBarColor(this, android.R.attr.colorBackground);

        materialButtons = new MaterialButton[8];
        materialButtons[0] = binding.toolsButton001;
        materialButtons[1] = binding.toolsButton002;
        materialButtons[2] = binding.toolsButton003;
        materialButtons[3] = binding.toolsButton004;
        materialButtons[4] = binding.toolsButton005;
        materialButtons[5] = binding.toolsButton006;
        materialButtons[6] = binding.toolsButton007;
        materialButtons[7] = binding.toolsButton008;

        setButtonChecked(binding.toolsButton001);


    }

    private void setButtonChecked(View view){
        for(MaterialButton mb:materialButtons){
            if(mb.getId() == view.getId()){
                mb.setChecked(true);
            }else{
                mb.setChecked(false);
            }
        }
    }

    public void onToolsButtonClick(View view){
        setButtonChecked(view);
    }


}
