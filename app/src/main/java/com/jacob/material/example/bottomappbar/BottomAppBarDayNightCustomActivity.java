/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-1 下午7:28
 *
 */

package com.jacob.material.example.bottomappbar;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.databinding.DataBindingUtil;

import com.jacob.utils.WidgetsUtils;
import com.jacob.material.R;
import com.jacob.material.databinding.BottomAppBarDayNightCustomActivityBinding;

public class BottomAppBarDayNightCustomActivity extends AppCompatActivity {
    private BottomAppBarDayNightCustomActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.bottom_app_bar_day_night_custom_activity);
        initInBoxBottomAppBar();
    }

    @SuppressLint("RestrictedApi")
    private void initInBoxBottomAppBar() {
        binding.bottomAppBar.performShow();

        if (binding.bottomAppBar.getMenu() != null) {
            binding.bottomAppBar.getMenu().clear();
        }
        binding.bottomAppBar.setNavigationIcon(R.drawable.icon_menu);
        binding.bottomAppBar.getNavigationIcon().setTint(WidgetsUtils.getColorValue(this, R.color.white));
        binding.fab.setImageResource(R.drawable.icon_add);
        binding.fab.getDrawable().setTint(WidgetsUtils.getColorValue(this, R.color.white));

        binding.bottomAppBar.inflateMenu(R.menu.regular_menu);
        Menu menu = binding.bottomAppBar.getMenu();

        if (menu instanceof MenuBuilder) {
            MenuBuilder menuBuilder = (MenuBuilder) menu;
            menuBuilder.setOptionalIconsVisible(true);
        }

        for (int i = 0; i < menu.size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            Drawable drawable = menuItem.getIcon();
            if (drawable != null) {
                if(menuItem.getOrder() < 200){
                    WidgetsUtils.setDrawableColor(this, drawable, R.color.white);
                }else{
                    WidgetsUtils.setDrawableColor(this, drawable, android.R.attr.colorPrimary);
                }
            }
        }
    }
}
