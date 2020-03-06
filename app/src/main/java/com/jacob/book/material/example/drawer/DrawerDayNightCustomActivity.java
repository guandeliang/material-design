/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-3-4 下午11:31
 *
 */

package com.jacob.book.material.example.drawer;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;

import com.jacob.book.material.R;
import com.jacob.book.material.databinding.DrawerDayNightCustomActivityBinding;

public class DrawerDayNightCustomActivity extends AppCompatActivity {
    private DrawerDayNightCustomActivityBinding binding;
    private DrawerArrowDrawable menuDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.drawer_day_night_custom_activity);
        binding.themeSwitchAppBarLayout.setOnMenuClickListener(new OnMenuClickListener());
        binding.drawerLayout.addDrawerListener(new CustomDrawerListener());
        menuDrawable = new DrawerArrowDrawable(this);
        menuDrawable.setColor(Color.WHITE);
        binding.themeSwitchAppBarLayout.setMenuImageDrawable(menuDrawable);

    }

    private class OnMenuClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(binding.drawerLayout.isDrawerVisible(binding.navigationView)){
                binding.drawerLayout.closeDrawer(binding.navigationView);
            }else{
                binding.drawerLayout.openDrawer(binding.navigationView);
            }
        }
    }

    private class CustomDrawerListener implements DrawerLayout.DrawerListener {
        @Override
        public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
            binding.nestedScrollView.setTranslationX(slideOffset*drawerView.getWidth());
            menuDrawable.setProgress(slideOffset);
        }

        @Override
        public void onDrawerOpened(@NonNull View drawerView) {}

        @Override
        public void onDrawerClosed(@NonNull View drawerView) {}

        @Override
        public void onDrawerStateChanged(int newState) {}
    }

}
