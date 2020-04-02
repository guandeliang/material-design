/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-8 下午8:53
 *
 */

package com.jacob.material.example.bottomsheet;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.jacob.material.R;
import com.jacob.material.databinding.BottomSheetDayNightCustomActivityBinding;
import com.jacob.material.databinding.BottomSheetDayNightDefaultActivityBinding;
import com.jacob.utils.WidgetsUtils;

public class BottomSheetDayNightCustomActivity extends AppCompatActivity {

    private BottomSheetDayNightCustomActivityBinding binding;
    private MenuBuilder menuBuilder;
    private int uiMode;
    private BottomSheetBehavior sheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.bottom_sheet_day_night_custom_activity);
        WidgetsUtils.setNavigationBarColor(this, android.R.attr.colorBackground);


        setSupportActionBar(binding.materialToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.icon_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        sheetBehavior = BottomSheetBehavior.from(binding.bottomSheetConstraintLayout);
        sheetBehavior.setFitToContents(true);
        sheetBehavior.setHideable(true);
        sheetBehavior.setPeekHeight(0);
        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.bottom_sheet_menu, menu);
        menuBuilder = (MenuBuilder)menu;

        intiMenuItem();
        return true;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_sheet){
            if(sheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED){
                sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            }else{
                sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
            }
        }


        return true;
    }


    @SuppressLint("RestrictedApi")
    private void intiMenuItem(){
        Configuration configuration = getResources().getConfiguration();
        if (configuration == null){
            menuBuilder.findItem(R.id.action_theme).setVisible(false);
            return;
        }

        uiMode = configuration.uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if(uiMode != Configuration.UI_MODE_NIGHT_YES && uiMode != Configuration.UI_MODE_NIGHT_NO){
            menuBuilder.findItem(R.id.action_theme).setVisible(false);
            return;
        }

        if(uiMode == Configuration.UI_MODE_NIGHT_YES){//夜间模式
            menuBuilder.findItem(R.id.action_theme).getActionView().setSelected(false);
        }else if(uiMode == Configuration.UI_MODE_NIGHT_NO){
            menuBuilder.findItem(R.id.action_theme).getActionView().setSelected(true);
        }

        menuBuilder.findItem(R.id.action_theme).getActionView().setOnClickListener(new OnThemeSwitchClickListener());
    }


    private class OnThemeSwitchClickListener implements View.OnClickListener{
        @SuppressLint("RestrictedApi")
        @Override
        public void onClick(View view) {
            if(uiMode == Configuration.UI_MODE_NIGHT_YES){//夜间模式
                menuBuilder.findItem(R.id.action_theme).getActionView().setSelected(true);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }else if(uiMode == Configuration.UI_MODE_NIGHT_NO){
                menuBuilder.findItem(R.id.action_theme).getActionView().setSelected(false);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        }
    }





}
