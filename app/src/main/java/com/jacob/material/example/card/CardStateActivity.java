/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-31 下午5:05
 *
 */

package com.jacob.material.example.card;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.jacob.material.R;
import com.jacob.material.databinding.CardStateActivityBinding;

public class CardStateActivity extends AppCompatActivity {
    private CardStateActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.card_state_activity);


        binding.cardViewCheckable.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View view) {
                binding.cardViewCheckable.setChecked(!binding.cardViewCheckable.isChecked());
                return true;
            }
        });

        binding.cardViewHovered.setHovered(true);
        binding.cardViewActivated.setActivated(true);

    }

}
