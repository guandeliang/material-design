/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-26 下午1:34
 *
 */

package com.jacob.material.example.topappbar;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
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
import com.jacob.utils.WidgetsUtils;
import com.jacob.material.R;
import com.jacob.material.databinding.TopAppBarCollapseActivityBinding;

public class TopAppBarCollapseActivity extends AppCompatActivity {

    private TopAppBarCollapseActivityBinding binding;


    //没有使用CollapsingToolbarLayout控制Title文字
    //是为了让文字位于Toolbar下方
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.top_app_bar_collapse_activity);
        binding.appBarLayout.setOnApplyWindowInsetsListener(new ApplyWindowInsetsListener());
        binding.appBarLayout.addOnOffsetChangedListener(new AppBarLayoutOnOffsetChangedListener());

        //将Title LinearLayout高度作为toolbar Bottom Padding
        //因为CollapsingToolbarLayout中，可收缩内容收缩会与Pin内容底部对齐
        //如果不在Pin内容中保留足够的空白，会导致收缩内容与Pin内容重叠
        binding.appBarLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                binding.appBarLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                int titleLayoutHeight = binding.titleLinearLayout.getHeight();
                int left = binding.toolbar.getPaddingLeft();
                int top = binding.toolbar.getPaddingTop();
                int right = binding.toolbar.getPaddingRight();
                int bottom = titleLayoutHeight;
                binding.toolbar.setPadding(left, top, right, bottom);
            }
        });


        setSupportActionBar(binding.toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.icon_arrow_back);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayShowTitleEnabled(true);
        ab.setTitle(null);


    }

    //将Status Bar Height设置为toolbar top padding
    //否则toolbar会向上移动到Status Bar区域
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

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar_news_menu, menu);
        if(menu instanceof MenuBuilder){
            MenuBuilder menuBuilder = (MenuBuilder) menu;
            menuBuilder.setOptionalIconsVisible(true);
        }
        return true;
    }


}
