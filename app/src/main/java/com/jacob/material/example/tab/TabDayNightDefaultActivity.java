/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-19 上午9:42
 *
 */

package com.jacob.material.example.tab;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.jacob.material.R;
import com.jacob.material.databinding.TabDayNightDefaultActivityBinding;
import com.jacob.material.example.adapter.TabDayNightAdapter;

public class TabDayNightDefaultActivity extends AppCompatActivity {
    private TabDayNightDefaultActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.tab_day_night_default_activity);

        TabDayNightAdapter adapter = new TabDayNightAdapter();
        binding.viewPager.setAdapter(adapter);

        TabLayoutMediator mediator = new TabLayoutMediator(
                binding.tabLayout,
                binding.viewPager,
                new MainTabConfigurationStrategy(adapter)
        );
        mediator.attach();
    }

    private class MainTabConfigurationStrategy implements TabLayoutMediator.TabConfigurationStrategy {
        private TabDayNightAdapter adapter;

        public MainTabConfigurationStrategy(TabDayNightAdapter adapter){
            this.adapter = adapter;
        }

        @Override
        public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

            tab.setText(adapter.getItem(position));

        }
    }

}
