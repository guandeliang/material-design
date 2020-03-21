/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-18 上午11:40
 *
 */

package com.jacob.material.example.bottomnavigation;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jacob.utils.ViewAnimation;
import com.jacob.utils.WidgetsUtils;
import com.jacob.material.R;
import com.jacob.material.databinding.BottomNavigationBasePrimaryActivityBinding;

public class BottomNavigationBasePrimaryActivity extends AppCompatActivity {
    private BottomNavigationBasePrimaryActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.bottom_navigation_base_primary_activity);

        WidgetsUtils.setSystemBarColor(this, R.color.gray_50);
        WidgetsUtils.setSystemBarLight(this);

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavItemSelectedListener());
        binding.bottomNavigationView.setSelectedItemId(R.id.action_train);
        binding.imageButton.setSelected(false);
        binding.imageButton.setOnClickListener(new OnImageButtonClickListener());


        binding.nestedScrollView.setOnScrollChangeListener(new OnContentScrollChangeListener());

    }


    private class BottomNavItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            String title = item.getTitle().toString();
            binding.searchTextView.setText(title);

            switch (item.getItemId()) {
                case R.id.action_train:
                case R.id.action_car:
                case R.id.action_bike:
                case R.id.action_user:
                    ViewAnimation.fadeOutIn(binding.includeLayout.constraintLayout);
                    binding.imageButton.setSelected(false);
                    return true;
                default:
                    return false;
            }
        }
    }

    private class OnImageButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            ViewAnimation.fadeOutIn(binding.includeLayout.constraintLayout);
            binding.bottomNavigationView.setSelectedItemId(R.id.action_blank);
            binding.imageButton.setSelected(true);
            binding.searchTextView.setText("小蝙蝠飞吧");

        }
    }

    private class OnContentScrollChangeListener implements NestedScrollView.OnScrollChangeListener{
        @Override
        public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            if(scrollY > oldScrollY){
                appBarSlideUp();
            }else{
                appBarSlideDown();
            }
        }
    }

    boolean isAppBarHide = false;

    private void appBarSlideUp(){
        if(isAppBarHide){
            return;
        }
        isAppBarHide = true;
        int moveY = 0 - binding.searchCardView.getHeight() * 2;
        binding.searchCardView.animate().translationY(moveY).setStartDelay(100).setDuration(300).start();
    }

    private void appBarSlideDown(){
        if(!isAppBarHide){
            return;
        }
        isAppBarHide = false;
        binding.searchCardView.animate().translationY(0).setStartDelay(100).setDuration(300).start();
    }


}
