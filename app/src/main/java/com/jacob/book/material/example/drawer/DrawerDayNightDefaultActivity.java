/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-20 下午9:40
 *
 */

package com.jacob.book.material.example.drawer;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.jacob.book.material.R;
import com.jacob.book.material.databinding.DrawerDayNightDefaultActivityBinding;
import com.jacob.book.temp.TempConstant;

public class DrawerDayNightDefaultActivity extends AppCompatActivity {
    private DrawerDayNightDefaultActivityBinding binding;
    private boolean isGravityLeft;
    private boolean isShowCloseButton;
    private int scrimColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.drawer_day_night_default_activity);

        if(savedInstanceState != null){
            isGravityLeft = savedInstanceState.getBoolean("isGravityLeft");
            isShowCloseButton = savedInstanceState.getBoolean("isShowCloseButton");
            scrimColor = savedInstanceState.getInt("scrimColor");
            if(scrimColor != 0x99000000){
                binding.drawerLayout.setScrimColor(scrimColor);
                binding.drawerLayout.setStatusBarBackgroundColor(scrimColor);
            }
        }else{
            isGravityLeft = true;
            isShowCloseButton = false;
            scrimColor = 0x99000000;
        }

        binding.themeSwitchAppBarLayout.setOnMenuClickListener(new OnMenuClickListener());
        binding.setActivity(this);
        binding.setIsGravityLeft(isGravityLeft);
        binding.setIsShowCloseButton(isShowCloseButton);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putBoolean("isGravityLeft", isGravityLeft);
        savedInstanceState.putBoolean("isShowCloseButton", isShowCloseButton);
        savedInstanceState.putInt("scrimColor", scrimColor);
    }


    private class OnMenuClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            binding.drawerLayout.openDrawer(binding.navigationView);
        }
    }

    public void onGravityCheckedChanged(RadioGroup radioGroup, int id) {
        if(id == R.id.gravity_start_radio_button){
            isGravityLeft = true;
        }else{
            isGravityLeft = false;
        }
        binding.setIsGravityLeft(isGravityLeft);
    }

    public void onLockCheckedChanged(RadioGroup radioGroup, int id) {
        if(id == R.id.lock_open_radio_button){
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
            isShowCloseButton = true;
        }else if(id == R.id.lock_close_radio_button){
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            isShowCloseButton = false;
        }else{
            binding.drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            isShowCloseButton = false;
        }
        binding.setIsShowCloseButton(isShowCloseButton);
    }

    public void onShowButtonClick(){
        binding.drawerLayout.openDrawer(binding.navigationView);
    }

    public void onCloseButtonClick(){
        binding.drawerLayout.closeDrawer(binding.navigationView);
    }

    public void onScrimColorClick(View view){
        ColorDrawable colorDrawable = (ColorDrawable)view.getBackground();
        scrimColor = colorDrawable.getColor();
        binding.drawerLayout.setScrimColor(scrimColor);
        binding.drawerLayout.setStatusBarBackgroundColor(scrimColor);
    }

    //对于没有通过set方法的属性，需要自己编写适配器进行绑定
    //适配器会自动进行注册
    @BindingAdapter("android:layout_gravity")
    public static void setLayoutGravity(NavigationView view, int gravity) {
        DrawerLayout.LayoutParams params = new DrawerLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.gravity = gravity;
        view.setLayoutParams(params);
    }
}
