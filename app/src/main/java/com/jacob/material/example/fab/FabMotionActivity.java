/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-8 下午8:53
 *
 */

package com.jacob.material.example.fab;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.transition.MaterialArcMotion;
import com.google.android.material.transition.MaterialContainerTransform;
import com.jacob.material.R;
import com.jacob.material.databinding.FabMotionActivityBinding;
import com.jacob.material.databinding.FabRelateActionActivityBinding;
import com.jacob.material.widgets.EventHostFrameLayout;
import com.jacob.temp.TempConstant;
import com.jacob.utils.WidgetsUtils;

public class FabMotionActivity extends AppCompatActivity {

    private FabMotionActivityBinding binding;
    private boolean isShowShareCardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.fab_motion_activity);
        binding.setActivity(this);
        isShowShareCardView = false;

        setSupportActionBar(binding.materialToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.icon_menu);
        ab.setDisplayHomeAsUpEnabled(true);
        binding.materialToolbar.getNavigationIcon().setTint(WidgetsUtils.getColorValue(this, android.R.attr.textColorPrimary));
        binding.eventHostFrameLayout.setOnMotionEventListener(new OnEventHostMotionListener());

    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.regular_menu, menu);

        if(menu instanceof MenuBuilder){
            MenuBuilder menuBuilder = (MenuBuilder) menu;
            menuBuilder.setOptionalIconsVisible(true);
        }

        for(int i = 0; i < menu.size(); i++){
            MenuItem menuItem = menu.getItem(i);
            Drawable drawable = menuItem.getIcon();
            if(drawable != null) {
                WidgetsUtils.setDrawableColor(FabMotionActivity.this, drawable, android.R.attr.textColorPrimary);
            }
        }
        return true;
    }

    private void showOrHideShareCardView(boolean showOrHide){
        if(showOrHide == true ==  isShowShareCardView){
            return;
        }

        if(!isShowShareCardView){
            isShowShareCardView = true;
            MaterialContainerTransform transform = new MaterialContainerTransform(this);
            transform.setStartView(binding.fabButton);
            transform.setEndView(binding.shareCardView);
            transform.setPathMotion(new MaterialArcMotion());
            transform.setScrimColor(Color.TRANSPARENT);
            transform.setDuration(300);
            TransitionManager.beginDelayedTransition((ViewGroup) binding.getRoot(), transform);
            binding.fabButton.setVisibility(View.GONE);
            binding.shareCardView.setVisibility(View.VISIBLE);
            binding.eventHostFrameLayout.setVisibility(View.VISIBLE);
        }else{
            isShowShareCardView = false;
            MaterialContainerTransform transform = new MaterialContainerTransform(this);
            transform.setStartView(binding.shareCardView);
            transform.setEndView(binding.fabButton);
            transform.setPathMotion(new MaterialArcMotion());
            transform.setScrimColor(Color.TRANSPARENT);
            transform.setDuration(300);
            TransitionManager.beginDelayedTransition((ViewGroup) binding.getRoot(), transform);
            binding.fabButton.setVisibility(View.VISIBLE);
            binding.shareCardView.setVisibility(View.GONE);
            binding.eventHostFrameLayout.setVisibility(View.GONE);
        }
    }

    public void onShareCardViewItemClick(View view, CharSequence text){
        Snackbar snackbar = Snackbar.make(binding.constraintLayout, text, Snackbar.LENGTH_LONG);
        snackbar.setAnchorView(binding.fabButton);
        snackbar.show();

        showOrHideShareCardView(false);
    }

    public void onMainFabClick(View view){
        showOrHideShareCardView(true);
    }




    private class OnEventHostMotionListener implements EventHostFrameLayout.OnMotionEventListener{
        public void onEventHostMotion(EventHostFrameLayout view, MotionEvent ev) {
            showOrHideShareCardView(false);
        }
    }





}
