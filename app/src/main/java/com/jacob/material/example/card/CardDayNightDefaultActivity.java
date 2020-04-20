/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-31 下午5:05
 *
 */

package com.jacob.material.example.card;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.jacob.material.R;
import com.jacob.material.databinding.ButtonDayNightDefaultActivityBinding;
import com.jacob.material.databinding.CardDayNightDefaultActivityBinding;

public class CardDayNightDefaultActivity extends AppCompatActivity {
    private CardDayNightDefaultActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.card_day_night_default_activity);
        binding.ratingRatingBar005.setRate(0.9f);
    }

}
