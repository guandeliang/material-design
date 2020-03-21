/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-26 下午1:34
 *
 */

package com.jacob.material.example.fab;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.WindowInsets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.jacob.utils.WidgetsUtils;
import com.jacob.material.R;
import com.jacob.material.databinding.FabBehaviorActivityBinding;

public class FabBehaviorActivity extends AppCompatActivity {

    private FabBehaviorActivityBinding binding;

    private int logoBeginTop;
    private int logoBeginLeft;
    private int subTitleBeginTop;
    private int subTitleBeginLeft;

    private int logoEndTop;
    private int logoEndLeft;
    private int subTitleEndTop;
    private int subTitleEndLeft;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.fab_behavior_activity);

        binding.appBarLayout.setOnApplyWindowInsetsListener(new ApplyWindowInsetsListener());
        binding.appBarLayout.addOnLayoutChangeListener(new AppBarLayoutOnLayoutChangeListener());
        binding.appBarLayout.addOnOffsetChangedListener(new AppBarLayoutOnOffsetChangedListener());



        ForegroundColorSpan fColorSpanOne = new ForegroundColorSpan(WidgetsUtils.getColorValue(this, android.R.attr.colorSecondary));
        ForegroundColorSpan fColorSpanTwo = new ForegroundColorSpan(WidgetsUtils.getColorValue(this, android.R.attr.colorSecondary));

        SpannableStringBuilder contentDescStr = new SpannableStringBuilder();
        contentDescStr.append("黑太阳餐厅位于河北省以北60公里的黑木崖，");
        contentDescStr.append("距离您现目前为止不到5公里", fColorSpanOne, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        contentDescStr.append("该餐厅原为日月神教总部食堂，随着神教势力日益扩大，为了让更多人体验神教文化，决定对外开放。");
        contentDescStr.append("餐厅集");
        contentDescStr.append("旅游、休闲、娱乐", fColorSpanTwo, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        contentDescStr.append("于一体，每周二、四、六对外开放，欢迎仰慕神教的各界人士参观旅游。");
        binding.contentDescTextView.setText(contentDescStr);

    }

    //init Toolbar size
    private class ApplyWindowInsetsListener implements View.OnApplyWindowInsetsListener {
        @Override
        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            int toolBarTopPadding = windowInsets.getSystemWindowInsetTop();
            int toolbarContentHeight = WidgetsUtils.dpToPx(FabBehaviorActivity.this, 56);
            int toolbarBottomPadding = WidgetsUtils.dpToPx(FabBehaviorActivity.this, 28);
            int toolbarHeight = toolBarTopPadding + toolbarContentHeight + toolbarBottomPadding;
            binding.toolbar.setLayoutParams(new CollapsingToolbarLayout.LayoutParams(AppBarLayout.LayoutParams.MATCH_PARENT, toolbarHeight));
            binding.toolbar.setPadding(0, toolBarTopPadding, 0, toolbarBottomPadding);
            return windowInsets;
        }
    }

    //calc translation begin and end point
    private class AppBarLayoutOnLayoutChangeListener implements View.OnLayoutChangeListener{
        @Override
        public void onLayoutChange(View view, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
            //滑动过程中，数据每次都会发生变化，因此只获得首次数据
            if(logoEndLeft > 0){
                return;
            }

            logoBeginTop = binding.logoImageView.getTop();
            logoBeginLeft = binding.logoImageView.getLeft();
            subTitleBeginTop = binding.subTitleTextView.getTop();
            subTitleBeginLeft = binding.subTitleTextView.getLeft();


            int appBarLayoutHeight = binding.appBarLayout.getHeight();
            int toolbarEndTop = appBarLayoutHeight - binding.toolbar.getHeight();

            logoEndLeft = WidgetsUtils.dpToPx(FabBehaviorActivity.this, 16);
            logoEndTop = toolbarEndTop + binding.toolbar.getPaddingTop() + WidgetsUtils.dpToPx(FabBehaviorActivity.this, 8);

            subTitleEndLeft = WidgetsUtils.dpToPx(FabBehaviorActivity.this, 88);
            subTitleEndTop = logoEndTop + WidgetsUtils.dpToPx(FabBehaviorActivity.this, 36);
        }

    }

    //translation view that out of CollapsingToolbarLayout control
    private class AppBarLayoutOnOffsetChangedListener implements AppBarLayout.OnOffsetChangedListener {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            float progress = (0-verticalOffset) / (float)binding.appBarLayout.getTotalScrollRange();

            float logoTranslationY = (logoEndTop - logoBeginTop)*progress;
            float logoTranslationX = (logoEndLeft - logoBeginLeft)*progress;
            binding.logoImageView.setTranslationY(logoTranslationY);
            binding.logoImageView.setTranslationX(logoTranslationX);
            binding.logoImageView.setScaleX(1 - progress/4);
            binding.logoImageView.setScaleY(1 - progress/4);

            float subTitleTranslationY = (subTitleEndTop - subTitleBeginTop)*progress;
            float subTitleTranslationX = (subTitleEndLeft - subTitleBeginLeft)*progress;
            binding.subTitleTextView.setTranslationY(subTitleTranslationY);
            binding.subTitleTextView.setTranslationX(subTitleTranslationX);


            //because FAB has bug with app bar layout
            //use programe control fab shrink or extend
            if(progress > 0.5){
                if(binding.extendedFab.isExtended()){
                    binding.extendedFab.shrink();
                }
            }else{
                if(!binding.extendedFab.isExtended()){
                    binding.extendedFab.extend();
                }
            }

        }
    }
}
