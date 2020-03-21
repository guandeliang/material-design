/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-3 下午8:20
 *
 */

package com.jacob.material.example.topappbar;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.appbar.AppBarLayout;
import com.jacob.utils.WidgetsUtils;
import com.jacob.material.R;
import com.jacob.material.databinding.TopAppBarScrollingActivityBinding;

public class TopAppBarScrollingActivity extends AppCompatActivity {

    private TopAppBarScrollingActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.top_app_bar_scrolling_activity);
        binding.statusBarBackgroundView.bringToFront();
        binding.appBarLayout.setOnApplyWindowInsetsListener(new ApplyWindowInsetsListener());
        binding.appBarLayout.addOnOffsetChangedListener(new AppBarLayoutOnOffsetChangedListener());

        setSupportActionBar(binding.toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.icon_arrow_back);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("新闻");
        binding.toolbar.getNavigationIcon().setTint(WidgetsUtils.getColorValue(this, android.R.attr.textColorPrimaryInverse));

    }
    private class ApplyWindowInsetsListener implements View.OnApplyWindowInsetsListener {
        @Override
        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            int statusBarHeight = windowInsets.getSystemWindowInsetTop();
            binding.statusBarBackgroundView.setLayoutParams(new CoordinatorLayout.LayoutParams(CoordinatorLayout.LayoutParams.MATCH_PARENT, statusBarHeight));
            //默认情况下，新创建的AppBarLayout.LayoutParams中，ScrollFlag标记位，SCROLL_FLAG_SCROLL
            //因为这个问题，排查了很久的BUG，一直搞明白，为什么Layout布局文件中的设置没有效果
            //AppBarLayout.LayoutParams params = new AppBarLayout.LayoutParams(AppBarLayout.LayoutParams.MATCH_PARENT, AppBarLayout.LayoutParams.WRAP_CONTENT);

            //下面的方法麻烦点，但是可以把布局文件中关于ScrollFlag的设置带过来。
            ViewGroup.LayoutParams vgParams = binding.toolbar.getLayoutParams();
            if(vgParams instanceof  AppBarLayout.LayoutParams){
                AppBarLayout.LayoutParams ablParams = (AppBarLayout.LayoutParams)vgParams;
                ablParams.setMargins(0,statusBarHeight,0,0);
                binding.toolbar.setLayoutParams(ablParams);
            }
            return windowInsets;
        }
    }

    private class AppBarLayoutOnOffsetChangedListener implements AppBarLayout.OnOffsetChangedListener {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar_news_menu, menu);
        if(menu instanceof MenuBuilder){
            MenuBuilder menuBuilder = (MenuBuilder) menu;
            menuBuilder.setOptionalIconsVisible(true);
        }

        for(int i = 0; i < menu.size(); i++){
            MenuItem menuItem = menu.getItem(i);
            Drawable drawable = menuItem.getIcon();
            WidgetsUtils.setDrawableColor(TopAppBarScrollingActivity.this, drawable, android.R.attr.textColorPrimaryInverse);
        }
        return true;
    }


}
