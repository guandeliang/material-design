/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-31 下午5:05
 *
 */

package com.jacob.material.example.button;

import android.os.Bundle;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.jacob.material.R;
import com.jacob.material.databinding.ButtonDayNightCustomActivityBinding;
import com.jacob.material.databinding.ButtonDayNightDefaultActivityBinding;

public class ButtonDayNightCustomActivity extends AppCompatActivity {
    private ButtonDayNightCustomActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.button_day_night_custom_activity);

        binding.enabledSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(isChecked){
                    binding.shape4dpUnelevatedButton.setEnabled(true);
                    binding.shape8dpUnelevatedButton.setEnabled(true);
                    binding.shapeRoundUnelevatedButton.setEnabled(true);

                    binding.shape4dpOutlineButton.setEnabled(true);
                    binding.shape8dpOutlineButton.setEnabled(true);
                    binding.shapeRoundOutlineButton.setEnabled(true);

                    binding.buttonIconLeft.setEnabled(true);
                    binding.buttonIconRight.setEnabled(true);
                    binding.buttonIconOnly.setEnabled(true);
                }else{
                    binding.shape4dpUnelevatedButton.setEnabled(false);
                    binding.shape8dpUnelevatedButton.setEnabled(false);
                    binding.shapeRoundUnelevatedButton.setEnabled(false);

                    binding.shape4dpOutlineButton.setEnabled(false);
                    binding.shape8dpOutlineButton.setEnabled(false);
                    binding.shapeRoundOutlineButton.setEnabled(false);

                    binding.buttonIconLeft.setEnabled(false);
                    binding.buttonIconRight.setEnabled(false);
                    binding.buttonIconOnly.setEnabled(false);
                }


            }
        });
    }

}
