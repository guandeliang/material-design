/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-20 下午9:40
 *
 */

package com.jacob.material.example.tab;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.palette.graphics.Palette;

import com.jacob.material.R;
import com.jacob.material.databinding.TabBaseActivityBinding;

public class TabBaseActivity extends AppCompatActivity {
    private TabBaseActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.tab_base_activity);


        //Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.weipinhui_light_103);

        //Palette p = Palette.from(bitmap).generate(); 创建同步调色板

        //createPaletteAsync(bitmap);

    }

    public void createPaletteAsync(Bitmap bitmap) {
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette p) {
                int color = p.getMutedColor(Color.DKGRAY);
                binding.getRoot().setBackgroundColor(color);
            }
        });
    }




}
