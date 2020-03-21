/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-1 上午9:38
 *
 */

package com.jacob.material.example.topappbar;

import android.graphics.Paint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.jacob.utils.WidgetsUtils;
import com.jacob.material.R;
import com.jacob.material.databinding.TopAppBarLightActivityBinding;

public class TopAppBarLightActivity extends AppCompatActivity {

    private TopAppBarLightActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.top_app_bar_light_activity);
        //这个图标的颜色很折磨人
        WidgetsUtils.setSystemBarColor(this, R.color.gray_50);
        WidgetsUtils.setSystemBarLight(this);

        binding.suggestOneOneOldPriceTextView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        binding.suggestOneOneOldPriceTextView.getPaint().setAntiAlias(true);

        binding.suggestOneTwoOldPriceTextView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        binding.suggestOneTwoOldPriceTextView.getPaint().setAntiAlias(true);

        binding.suggestOneThreeOldPriceTextView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        binding.suggestOneThreeOldPriceTextView.getPaint().setAntiAlias(true);

        binding.suggestTwoOneOldPriceTextView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        binding.suggestTwoOneOldPriceTextView.getPaint().setAntiAlias(true);

        binding.suggestTwoTwoOldPriceTextView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        binding.suggestTwoTwoOldPriceTextView.getPaint().setAntiAlias(true);

        binding.suggestTwoThreeOldPriceTextView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        binding.suggestTwoThreeOldPriceTextView.getPaint().setAntiAlias(true);


        binding.suggestThreeOneOldPriceTextView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        binding.suggestThreeOneOldPriceTextView.getPaint().setAntiAlias(true);

        binding.suggestThreeTwoOldPriceTextView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        binding.suggestThreeTwoOldPriceTextView.getPaint().setAntiAlias(true);

        binding.suggestThreeThreeOldPriceTextView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        binding.suggestThreeThreeOldPriceTextView.getPaint().setAntiAlias(true);


        binding.suggestFourOneOldPriceTextView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        binding.suggestFourOneOldPriceTextView.getPaint().setAntiAlias(true);

        binding.suggestFourTwoOldPriceTextView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        binding.suggestFourTwoOldPriceTextView.getPaint().setAntiAlias(true);

        binding.suggestFourThreeOldPriceTextView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        binding.suggestFourThreeOldPriceTextView.getPaint().setAntiAlias(true);


        binding.suggestFiveOneOldPriceTextView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        binding.suggestFiveOneOldPriceTextView.getPaint().setAntiAlias(true);

        binding.suggestFiveTwoOldPriceTextView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        binding.suggestFiveTwoOldPriceTextView.getPaint().setAntiAlias(true);

        binding.suggestFiveThreeOldPriceTextView.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        binding.suggestFiveThreeOldPriceTextView.getPaint().setAntiAlias(true);
    }





}
