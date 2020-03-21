/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-3 下午11:49
 *
 */

package com.jacob.material.example.bottomappbar;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.snackbar.Snackbar;
import com.jacob.utils.WidgetsUtils;
import com.jacob.material.R;
import com.jacob.material.databinding.BottomAppBarDemoActivityBinding;

public class BottomAppBarDemoActivity extends AppCompatActivity {
    private BottomAppBarDemoActivityBinding binding;
    private BottomAppBarDemoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.bottom_app_bar_demo_activity);

        WidgetsUtils.setSystemBarColor(this, R.color.gray_50);
        WidgetsUtils.setSystemBarLight(this);

        viewModel = new ViewModelProvider(this).get(BottomAppBarDemoViewModel.class);
        viewModel.getFragmentLiveData().observe(this, new FragmentChangeObserver());
        viewModel.getActionMsgLiveData().observe(this, new ActionMsgChangeObserver());

    }

    private class FragmentChangeObserver implements Observer<BottomAppBarDemoViewModel.FragmentEnum> {
        @Override
        public void onChanged(@Nullable BottomAppBarDemoViewModel.FragmentEnum fragment) {
            if(BottomAppBarDemoViewModel.FragmentEnum.IN_BOX.equals(fragment)){
                initInBoxBottomAppBar();
            }else if(BottomAppBarDemoViewModel.FragmentEnum.MAIL_CONTENT.equals(fragment)){
                initMailContentBottomAppBar();
            }
        }
    }

    private class ActionMsgChangeObserver implements Observer<String> {
        @Override
        public void onChanged(@Nullable String msg) {
            Snackbar snackbar = Snackbar
                    .make(binding.coordinatorLayout, msg, Snackbar.LENGTH_LONG)
                    .setAnchorView(binding.fab);
            snackbar.show();

        }
    }

    @SuppressLint("RestrictedApi")
    private void initInBoxBottomAppBar(){
        binding.bottomAppBar.performShow();

        if(binding.bottomAppBar.getMenu() != null){
            binding.bottomAppBar.getMenu().clear();
        }
        binding.bottomAppBar.setNavigationIcon(R.drawable.icon_menu);
        binding.bottomAppBar.getNavigationIcon().setTint(WidgetsUtils.getColorValue(this, android.R.attr.textColorPrimary));

        binding.fab.setImageResource(R.drawable.icon_add);
        binding.fab.getDrawable().setTint(WidgetsUtils.getColorValue(this, android.R.attr.textColorPrimaryInverse));
        binding.bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);


        binding.bottomAppBar.inflateMenu(R.menu.bottom_app_bar_demo_in_box_menu);
        Menu menu = binding.bottomAppBar.getMenu();

        if(menu instanceof MenuBuilder){
            MenuBuilder menuBuilder = (MenuBuilder) menu;
            menuBuilder.setOptionalIconsVisible(true);
        }

        for(int i = 0; i < menu.size(); i++){
            MenuItem menuItem = menu.getItem(i);
            Drawable drawable = menuItem.getIcon();
            if(drawable != null) {
                if(menuItem.getOrder() < 200){
                    WidgetsUtils.setDrawableColor(this, drawable, android.R.attr.textColorPrimary);
                }else{
                    WidgetsUtils.setDrawableColor(this, drawable, android.R.attr.colorPrimary);
                }
            }
        }
        binding.bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_CENTER);

       binding.bottomAppBar.setNavigationOnClickListener(new OnInBoxBottomAppBarNavigatiOnClickListener());
       binding.bottomAppBar.setOnMenuItemClickListener(new OnInBoxBottomAppBarMenuClickListener());
       binding.fab.setOnClickListener(new OnInBoxFabClickListener());
    }



    private class OnInBoxBottomAppBarNavigatiOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            viewModel.getActionMsgLiveData().postValue("点这里应该显示一个侧滑菜单的，但是没有做啊。");
        }
    }

    private class OnInBoxBottomAppBarMenuClickListener implements Toolbar.OnMenuItemClickListener {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if(item.getItemId() == R.id.action_search){
                viewModel.getActionMsgLiveData().postValue("查找一般都找不到真正想要的东西。");
            }
            return true;
        }
    }

    private class OnInBoxFabClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            viewModel.getActionMsgLiveData().postValue("编写新的邮件。");
        }
    }


    @SuppressLint("RestrictedApi")
    private void initMailContentBottomAppBar(){
        binding.bottomAppBar.performShow();

        if(binding.bottomAppBar.getMenu() != null){
            binding.bottomAppBar.getMenu().clear();
        }
        binding.bottomAppBar.setNavigationIcon(null);

        binding.fab.setImageResource(R.drawable.icon_reply);
        binding.fab.getDrawable().setTint(WidgetsUtils.getColorValue(this, android.R.attr.textColorPrimaryInverse));

        binding.bottomAppBar.inflateMenu(R.menu.bottom_app_bar_demo_content_menu);
        Menu menu = binding.bottomAppBar.getMenu();

        if(menu instanceof MenuBuilder){
            MenuBuilder menuBuilder = (MenuBuilder) menu;
            menuBuilder.setOptionalIconsVisible(true);
        }

        for(int i = 0; i < menu.size(); i++){
            MenuItem menuItem = menu.getItem(i);
            Drawable drawable = menuItem.getIcon();
            if(drawable != null) {
                if(menuItem.getOrder() < 200){
                    WidgetsUtils.setDrawableColor(this, drawable, android.R.attr.textColorPrimary);
                }else{
                    WidgetsUtils.setDrawableColor(this, drawable, android.R.attr.colorPrimary);
                }
            }
        }
        binding.bottomAppBar.setFabAlignmentMode(BottomAppBar.FAB_ALIGNMENT_MODE_END);
        binding.fab.setOnClickListener(new OnContentFabClickListener());
    }


    private class OnContentFabClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Navigation.findNavController(BottomAppBarDemoActivity.this, R.id.nav_host_fragment).popBackStack();
        }
    }

}
