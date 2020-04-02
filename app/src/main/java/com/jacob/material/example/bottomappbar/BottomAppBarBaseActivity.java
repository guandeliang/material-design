/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-8 下午8:51
 *
 */

package com.jacob.material.example.bottomappbar;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.slider.Slider;
import com.jacob.utils.WidgetsUtils;
import com.jacob.material.R;
import com.jacob.material.databinding.BottomAppBarBaseActivityBinding;

public class BottomAppBarBaseActivity extends AppCompatActivity {
    private BottomAppBarBaseActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.bottom_app_bar_base_activity);

        binding.bottomAppBar.getNavigationIcon().setTint(WidgetsUtils.getColorValue(this, android.R.attr.textColorPrimaryInverse));
        binding.fab.getDrawable().setTint(WidgetsUtils.getColorValue(this, R.color.white));

        initMaterialToolbarMenu();
        initBottomAppBarMenu();


        binding.fabAlignmentRadioGroup.setOnCheckedChangeListener(new OnFabAlignmentCheckedChangeListener());
        binding.fabAnimationModeRadioGroup.setOnCheckedChangeListener(new OnFabAnimationModeCheckedChangeListener());
        binding.fabCradleRoundedCornerRadiusSlider.addOnChangeListener(new FabCradleRoundedCornerRadiusSliderOnChangeListener());
        binding.fabCradleMarginSlider.addOnChangeListener(new FabCradleMarginSliderOnChangeListener());
        binding.fabCradleVerticalOffsetSlider.addOnChangeListener(new FabCradleVerticalOffsetSliderOnChangeListener());
        binding.fabCustomSizeSlider.addOnChangeListener(new FabCustomSizeSliderOnChangeListener());
        binding.bottomAppBarHideOnScrollRadioGroup.setOnCheckedChangeListener(new OnScrollHideCheckedChangeListener());
    }

    @SuppressLint("RestrictedApi")
    private void initMaterialToolbarMenu(){
        if(binding.materialToolbar.getMenu() != null){
            binding.materialToolbar.getMenu().clear();
        }
        binding.materialToolbar.inflateMenu(R.menu.bottom_app_bar_menu_one);
        Menu menu = binding.materialToolbar.getMenu();
        if(menu instanceof MenuBuilder){
            MenuBuilder menuBuilder = (MenuBuilder) menu;
            menuBuilder.setOptionalIconsVisible(true);
        }
    }

    @SuppressLint("RestrictedApi")
    private void initBottomAppBarMenu(){
        if(binding.bottomAppBar.getMenu() != null){
            binding.bottomAppBar.getMenu().clear();
        }
        binding.bottomAppBar.inflateMenu(R.menu.bottom_app_bar_menu_two);
    }


    private class OnFabAlignmentCheckedChangeListener implements RadioGroup.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int id) {
            if(id == R.id.fab_alignment_center_radio_button){
                binding.bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);
            }else{
                binding.bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
            }
        }
    }

    private class OnFabAnimationModeCheckedChangeListener implements RadioGroup.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int id) {
            if(id == R.id.fab_animation_mode_scale_radio_button){
                binding.bottomAppBar.setFabAnimationMode(BottomAppBar.FAB_ANIMATION_MODE_SCALE);
            }else{
                binding.bottomAppBar.setFabAnimationMode(BottomAppBar.FAB_ANIMATION_MODE_SLIDE);
            }
        }
    }

    private class FabCradleRoundedCornerRadiusSliderOnChangeListener implements Slider.OnChangeListener{
        @Override
        public void onValueChange(Slider slider, float value, boolean fromUser) {
            int pxValue = WidgetsUtils.dpToPx(BottomAppBarBaseActivity.this, (int)value);
            binding.bottomAppBar.setFabCradleRoundedCornerRadius(pxValue);
        }
    }

    private class FabCradleMarginSliderOnChangeListener implements Slider.OnChangeListener{
        @Override
        public void onValueChange(Slider slider, float value, boolean fromUser) {
            int pxValue = WidgetsUtils.dpToPx(BottomAppBarBaseActivity.this, (int)value);
            binding.bottomAppBar.setFabCradleMargin(pxValue);
        }
    }

    private class FabCradleVerticalOffsetSliderOnChangeListener implements Slider.OnChangeListener{
        @Override
        public void onValueChange(Slider slider, float value, boolean fromUser) {
            int pxValue = WidgetsUtils.dpToPx(BottomAppBarBaseActivity.this, (int)value);
            binding.bottomAppBar.setCradleVerticalOffset(pxValue);
        }
    }

    private class FabCustomSizeSliderOnChangeListener implements Slider.OnChangeListener{
        @Override
        public void onValueChange(Slider slider, float value, boolean fromUser) {
            int pxValue = WidgetsUtils.dpToPx(BottomAppBarBaseActivity.this, (int)value);
            binding.fab.setCustomSize(pxValue);
        }
    }

    private class OnScrollHideCheckedChangeListener implements RadioGroup.OnCheckedChangeListener{
        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int id) {
            if(id == R.id.bottom_app_bar_hide_on_scroll_true_radio_button){
                binding.bottomAppBar.setHideOnScroll(true);
            }else{
                //确保处于显示状态之后再改变是否与滚动关联，否则可能不会显示
                binding.bottomAppBar.performShow();
                binding.bottomAppBar.setHideOnScroll(false);
            }
        }
    }

}
