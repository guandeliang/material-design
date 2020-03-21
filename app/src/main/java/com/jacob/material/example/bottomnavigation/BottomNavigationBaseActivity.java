/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-17 下午9:49
 *
 */

package com.jacob.material.example.bottomnavigation;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jacob.utils.WidgetsUtils;
import com.jacob.material.R;
import com.jacob.material.databinding.BottomNavigationBaseActivityBinding;

public class BottomNavigationBaseActivity extends AppCompatActivity {
    private BottomNavigationBaseActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.bottom_navigation_base_activity);

        WidgetsUtils.setSystemBarColor(this, R.color.gray_50);
        WidgetsUtils.setSystemBarLight(this);

        binding.bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavItemSelectedListener());
        binding.bottomNavigationView.setSelectedItemId(R.id.action_favorite);
        binding.nestedScrollView.setOnScrollChangeListener(new OnContentScrollChangeListener());

    }


    private class BottomNavItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            String title = item.getTitle().toString();
            binding.searchTextView.setText(title);

            return true;
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
