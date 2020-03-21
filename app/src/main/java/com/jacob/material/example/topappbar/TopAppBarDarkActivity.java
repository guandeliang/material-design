/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-18 下午2:32
 *
 */

package com.jacob.material.example.topappbar;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.tabs.TabLayoutMediator;
import com.jacob.utils.WidgetsUtils;
import com.jacob.material.R;
import com.jacob.material.base.TabViewPagerAdapter;
import com.jacob.material.base.TabViewPagerBaseFragment;
import com.jacob.material.databinding.TopAppBarDarkActivityBinding;

import java.util.ArrayList;
import java.util.List;

public class TopAppBarDarkActivity extends AppCompatActivity {

    private TopAppBarDarkActivityBinding binding;
    private List<TabViewPagerBaseFragment> fragmentList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.top_app_bar_dark_activity);
        setSupportActionBar(binding.toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.icon_menu);
        ab.setDisplayHomeAsUpEnabled(true);
        binding.toolbar.getNavigationIcon().setTint(WidgetsUtils.getColorValue(this, android.R.attr.textColorPrimaryInverse));
        WidgetsUtils.setSystemBarColor(this, R.color.dark_color_background);

        fragmentList = new ArrayList<>();
        fragmentList.add(new TopAppBarDarkOneFragment());
        fragmentList.add(new TopAppBarDarkTwoFragment());
        fragmentList.add(new TopAppBarDarkThreeFragment());

        TabViewPagerAdapter adapter = new TabViewPagerAdapter(this, fragmentList);
        binding.viewPager.setAdapter(adapter);

        TabLayoutMediator mediator = new TabLayoutMediator(
                binding.tabLayout,
                binding.viewPager,
                new TabViewPagerBaseFragment.ConfigurationStrategy(fragmentList)
        );
        mediator.attach();

    }



}
