/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-2 下午1:54
 *
 */

package com.jacob.book.material.widgets;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;

import com.google.android.material.appbar.AppBarLayout;
import com.jacob.book.material.R;

public class ThemeSwitchAppBarLayout extends AppBarLayout {
    private String titleText;
    private ImageView menuImageView;
    private TextView titleTextView;
    private ImageView themeImageView;
    private OnClickListener onMenuClickListener;

    public ThemeSwitchAppBarLayout(@NonNull Context context) {
        this(context, null);
    }

    public ThemeSwitchAppBarLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.attr.appBarLayoutStyle);
    }

    public ThemeSwitchAppBarLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        inflate(context, R.layout.widget_theme_switch_app_bar_layout, this);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.theme_switch_app_bar_layout, 0, 0);
        titleText = typedArray.getString(R.styleable.theme_switch_app_bar_layout_title_text);
        int menuImageResId = typedArray.getResourceId(R.styleable.theme_switch_app_bar_layout_menu_icon, R.drawable.icon_arrow_back);
        typedArray.recycle();

        menuImageView = findViewById(R.id.menu_image_view);
        titleTextView = findViewById(R.id.title_text_view);
        themeImageView = findViewById(R.id.theme_image_view);
        menuImageView.setImageResource(menuImageResId);
        titleTextView.setText(titleText);

        menuImageView.setOnClickListener(new OnMenuClickListener());
        themeImageView.setOnClickListener(new OnThemeImageViewCliclLintener());

        initView();
    }

    private void initView(){
        Configuration configuration = getResources().getConfiguration();
        if (configuration == null){
            themeImageView.setVisibility(View.GONE);
            return;
        }

        int uiMode = configuration.uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if(uiMode == Configuration.UI_MODE_NIGHT_YES){//夜间模式
            themeImageView.setImageResource(R.drawable.icon_brightness_1);
        }else if(uiMode == Configuration.UI_MODE_NIGHT_NO){//日间模式
            themeImageView.setImageResource(R.drawable.icon_brightness_2);
        }else if(uiMode == Configuration.UI_MODE_NIGHT_UNDEFINED){//不知道什么模式
            themeImageView.setVisibility(View.GONE);
        }
    }

    public void setTitle(String title){
        this.titleText = title;
        this.titleTextView.setText(titleText);
    }

    public void setOnMenuClickListener(OnClickListener listener){
        this.onMenuClickListener = listener;
    }

    public void setMenuImageDrawable(Drawable drawable){
        this.menuImageView.setImageDrawable(drawable);
    }

    private class OnMenuClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(onMenuClickListener != null){
                onMenuClickListener.onClick(v);
            }
        }
    }

    private class OnThemeImageViewCliclLintener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Configuration configuration = getResources().getConfiguration();
            if (configuration == null){
                themeImageView.setVisibility(View.GONE);
                return;
            }

            int uiMode = configuration.uiMode & Configuration.UI_MODE_NIGHT_MASK;

            if(uiMode == Configuration.UI_MODE_NIGHT_YES){//如何是夜间模式，则切换到日间模式
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }else if(uiMode == Configuration.UI_MODE_NIGHT_NO){//如果是日间模式，则切换的夜间模式
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }else if(uiMode == Configuration.UI_MODE_NIGHT_UNDEFINED){//不知道什么模式
                themeImageView.setVisibility(View.GONE);
            }
        }
    }



}
