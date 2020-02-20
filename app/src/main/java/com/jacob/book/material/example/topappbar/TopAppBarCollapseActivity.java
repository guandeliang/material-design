/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-26 下午1:34
 *
 */

package com.jacob.book.material.example.topappbar;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.core.view.ViewCompat;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.appbar.AppBarLayout;
import com.jacob.book.WidgetsUtils;
import com.jacob.book.material.R;
import com.jacob.book.material.databinding.TopAppBarCollapseActivityBinding;
import com.jacob.book.temp.TempConstant;

public class TopAppBarCollapseActivity extends AppCompatActivity {

    private TopAppBarCollapseActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.top_app_bar_collapse_activity);
        binding.appBarLayout.setOnApplyWindowInsetsListener(new ApplyWindowInsetsListener());
        binding.appBarLayout.addOnOffsetChangedListener(new AppBarLayoutOnOffsetChangedListener());

        binding.appBarLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                int titleLayoutHeight = binding.titleLinearLayout.getHeight();
                Log.d(TempConstant.LOG_TAG, "2 TopAppBarCollapseActivity titleLayoutHeight = " + titleLayoutHeight);
                int left = binding.toolbar.getPaddingLeft();
                int top = binding.toolbar.getPaddingTop();
                int right = binding.toolbar.getPaddingRight();
                int bottom = titleLayoutHeight;
                binding.toolbar.setPadding(left, top, right, bottom);
                binding.appBarLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });

        setSupportActionBar(binding.toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.icon_arrow_back);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowTitleEnabled(true);
        ab.setTitle(null);
        binding.toolbar.getNavigationIcon().setTint(WidgetsUtils.getColorValue(this, android.R.attr.textColorPrimaryInverse));
        binding.floatingActionButton.getDrawable().setTint(WidgetsUtils.getColorValue(this, android.R.attr.textColorPrimaryInverse));


    }

    private class ApplyWindowInsetsListener implements View.OnApplyWindowInsetsListener {
        @Override
        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            int statusBarHeight = windowInsets.getSystemWindowInsetTop();
            //在这里不能使用margin，因为margin在收缩的时候会被忽略
            int left = binding.toolbar.getPaddingLeft();
            int top = statusBarHeight;
            int right = binding.toolbar.getPaddingRight();
            int bottom = binding.toolbar.getPaddingBottom();
            binding.toolbar.setPadding(left, top, right, bottom);
            return windowInsets;
        }
    }

    private enum State {
        EXPANDED,
        COLLAPSED,
        IDLE
    }

    private class AppBarLayoutOnOffsetChangedListener implements AppBarLayout.OnOffsetChangedListener {
        private State currState = State.IDLE;


        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            if (binding.collapsingToolbarLayout.getHeight() + verticalOffset < 2 * ViewCompat.getMinimumHeight(binding.collapsingToolbarLayout)) {
                binding.floatingActionButton.show();
            } else {
                binding.floatingActionButton.hide();
            }

            if (verticalOffset == 0) {
                if (currState != State.EXPANDED) {
                    onStateChanged(appBarLayout, verticalOffset, State.EXPANDED);
                }
                currState = State.EXPANDED;
            } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                if (currState != State.COLLAPSED) {
                    onStateChanged(appBarLayout, verticalOffset, State.COLLAPSED);
                }
                currState = State.COLLAPSED;
            } else {
                if (currState != State.IDLE) {
                    onStateChanged(appBarLayout, verticalOffset, State.IDLE);
                }
                currState = State.IDLE;
            }
        }

        public void onStateChanged(AppBarLayout appBarLayout, int verticalOffset, State state){
        }
    }

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
            WidgetsUtils.setDrawableColor(TopAppBarCollapseActivity.this, drawable, android.R.attr.textColorPrimaryInverse);
        }
        return true;
    }


}
