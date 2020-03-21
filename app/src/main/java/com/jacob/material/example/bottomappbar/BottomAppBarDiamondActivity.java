/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-1 下午5:43
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

import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.jacob.utils.WidgetsUtils;
import com.jacob.material.R;
import com.jacob.material.databinding.BottomAppBarDiamondActivityBinding;
import com.jacob.material.widgets.BottomAppBarCutCornersTopEdge;

public class BottomAppBarDiamondActivity extends AppCompatActivity {
    private BottomAppBarDiamondActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.bottom_app_bar_diamond_activity);

        WidgetsUtils.setSystemBarColor(this, R.color.gray_300);
        WidgetsUtils.setSystemBarLight(this);
        binding.fab.getDrawable().setTint(WidgetsUtils.getColorValue(this, R.color.white));

        BottomAppBarTopEdgeTreatment topEdge = new BottomAppBarCutCornersTopEdge(
                binding.bottomAppBar.getFabCradleMargin(),
                binding.bottomAppBar.getFabCradleRoundedCornerRadius(),
                binding.bottomAppBar.getCradleVerticalOffset());
        MaterialShapeDrawable babBackground = (MaterialShapeDrawable) binding.bottomAppBar.getBackground();

        babBackground.setShapeAppearanceModel(
                babBackground.getShapeAppearanceModel()
                        .toBuilder()
                        .setTopEdge(topEdge)
                        .build());

        initInBoxBottomAppBar();
    }


    @SuppressLint("RestrictedApi")
    private void initInBoxBottomAppBar(){
        binding.bottomAppBar.performShow();

        if(binding.bottomAppBar.getMenu() != null){
            binding.bottomAppBar.getMenu().clear();
        }
        binding.bottomAppBar.setNavigationIcon(R.drawable.icon_menu);
        binding.bottomAppBar.getNavigationIcon().setTint(WidgetsUtils.getColorValue(this, android.R.attr.textColorPrimary));

        binding.bottomAppBar.inflateMenu(R.menu.bottom_app_bar_diamond_menu);
        Menu menu = binding.bottomAppBar.getMenu();

        if(menu instanceof MenuBuilder){
            MenuBuilder menuBuilder = (MenuBuilder) menu;
            menuBuilder.setOptionalIconsVisible(true);
        }

        for(int i = 0; i < menu.size(); i++){
            MenuItem menuItem = menu.getItem(i);
            Drawable drawable = menuItem.getIcon();
            if(drawable != null) {
                WidgetsUtils.setDrawableColor(this, drawable, android.R.attr.textColorPrimary);
            }
        }
    }


}
