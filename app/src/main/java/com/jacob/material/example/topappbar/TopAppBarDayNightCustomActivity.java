/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-1 下午6:54
 *
 */

package com.jacob.material.example.topappbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.snackbar.Snackbar;
import com.jacob.material.R;
import com.jacob.material.databinding.TopAppBarDayNightCustomActivityBinding;
import com.jacob.utils.WidgetsUtils;

public class TopAppBarDayNightCustomActivity extends AppCompatActivity {
    private TopAppBarDayNightCustomActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.top_app_bar_day_night_custom_activity);

        binding.materialToolbar.getNavigationIcon().setTint(WidgetsUtils.getColorValue(this, R.color.white));
        setSupportActionBar(binding.materialToolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
    }


    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.regular_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite: {
                Snackbar snackbar = Snackbar.make(binding.linearLayout, "喜欢吗？", Snackbar.LENGTH_LONG);
                snackbar.show();
                return true;
            }
            case R.id.action_search: {
                Snackbar snackbar = Snackbar.make(binding.linearLayout, "想要查找什么呢？", Snackbar.LENGTH_LONG);
                snackbar.show();
                return true;
            }

            case R.id.action_share: {
                Snackbar snackbar = Snackbar.make(binding.linearLayout, "分享吧，好人！", Snackbar.LENGTH_LONG);
                snackbar.show();
                return true;
            }
            case R.id.action_help: {
                Snackbar snackbar = Snackbar.make(binding.linearLayout, "书里有全部帮助内容！", Snackbar.LENGTH_LONG);
                snackbar.show();
                return true;
            }
            case R.id.action_setting: {
                Snackbar snackbar = Snackbar.make(binding.linearLayout, "没做设置功能！", Snackbar.LENGTH_LONG);
                snackbar.show();
                return true;
            }
            default: {//处理导航按钮
                Snackbar snackbar = Snackbar.make(binding.linearLayout, "后面有导航的例子", Snackbar.LENGTH_LONG);
                snackbar.show();
                return true;
            }
        }
    }




}
