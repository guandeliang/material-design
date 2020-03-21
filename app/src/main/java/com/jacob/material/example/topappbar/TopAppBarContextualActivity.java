/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-8 下午8:53
 *
 */

package com.jacob.material.example.topappbar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ArgbEvaluator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.WindowInsets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StorageStrategy;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.snackbar.Snackbar;
import com.jacob.utils.JsonUtils;
import com.jacob.utils.WidgetsUtils;
import com.jacob.material.R;
import com.jacob.material.databinding.TopAppBarContextualActivityBinding;
import com.jacob.material.example.adapter.ExampleTrackerImageAdapter;
import com.jacob.material.example.model.ExampleImage;
import com.jacob.material.widgets.StaggeredGridSpaceItemDecoration;

import java.util.List;

public class TopAppBarContextualActivity extends AppCompatActivity {
    private static boolean STATE_NORMAL = false;
    private static boolean STATE_SELECTING = true;

    private TopAppBarContextualActivityBinding binding;
    private SelectionTracker tracker;
    private ExampleTrackerImageAdapter adapter;
    private ActionBar actionBar;
    private Drawable drawableMenuToClear;
    private Drawable drawableClearToMenu;
    private Animatable aniMenuToClear;
    private Animatable aniClearToMenu;
    private Menu actionMenu;
    private boolean state;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.top_app_bar_contextual_activity);

        binding.appBarLayout.setOnApplyWindowInsetsListener(new ApplyWindowInsetsListener());


        drawableMenuToClear = WidgetsUtils.getColorDrawable(this, R.drawable.icon_toolbar_home_menu_to_clear_ani, android.R.attr.textColorPrimaryInverse);
        drawableClearToMenu = WidgetsUtils.getColorDrawable(this, R.drawable.icon_toolbar_home_clear_to_menu_ani, android.R.attr.textColorPrimaryInverse);
        aniMenuToClear = ((Animatable)drawableMenuToClear);
        aniClearToMenu = ((Animatable)drawableClearToMenu);
        state = STATE_NORMAL;

        setSupportActionBar(binding.toolbar);
        actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);


        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        binding.recyclerView.setLayoutManager(layoutManager);

        StaggeredGridSpaceItemDecoration decoration = new StaggeredGridSpaceItemDecoration(WidgetsUtils.dpToPx(this, 8), 2);
        binding.recyclerView.addItemDecoration(decoration);

        adapter = new ExampleTrackerImageAdapter(this);
        binding.recyclerView.setAdapter(adapter);

        tracker = new SelectionTracker.Builder<>(
                "ExampleImageSelectionTrackerId",
                binding.recyclerView,
                new ExampleTrackerImageAdapter.KeyProvider(adapter),
                new ExampleTrackerImageAdapter.DetailsLookup(binding.recyclerView),
                StorageStrategy.createLongStorage()).withSelectionPredicate(new ExampleTrackerImageAdapter.SelectionPredicate(adapter))
                .build();
        tracker.addObserver(new SelectionObserver(tracker));
        adapter.setTracker(tracker);

        List<ExampleImage> imageList = JsonUtils.loadExampleImage(getResources());
        adapter.setItems(imageList);
    }


    private class ApplyWindowInsetsListener implements View.OnApplyWindowInsetsListener {
        @Override
        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            int statusBarHeight = windowInsets.getSystemWindowInsetTop();
            binding.statusBarBackgroundView.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, statusBarHeight));
            return windowInsets;
        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar_contextual_menu, menu);
        actionMenu = menu;

        if(menu instanceof MenuBuilder){
            MenuBuilder menuBuilder = (MenuBuilder) menu;
            menuBuilder.setOptionalIconsVisible(true);
        }

        for(int i = 0; i < menu.size(); i++){
            MenuItem menuItem = menu.getItem(i);
            Drawable drawable = menuItem.getIcon();
            if(drawable != null) {
                if(menuItem.getOrder() < 200){
                    WidgetsUtils.setDrawableColor(TopAppBarContextualActivity.this, drawable, android.R.attr.textColorPrimaryInverse);
                }else{
                    WidgetsUtils.setDrawableColor(TopAppBarContextualActivity.this, drawable, android.R.attr.textColorPrimary);
                }
            }
        }

        toolbarInit();
        return true;
    }

    private class SelectionObserver extends SelectionTracker.SelectionObserver<Long> {
        private SelectionTracker tracker;
        public SelectionObserver(SelectionTracker tracker){
            this.tracker = tracker;
        }

        @Override
        public void onItemStateChanged(@NonNull Long key, boolean selected) {
            if(tracker.getSelection().isEmpty()){
                if(state == STATE_SELECTING){//取消选择状态
                    state = STATE_NORMAL;
                    toolbarFromSelectinglToNormal();
                    actionBar.setTitle("图片");
                }
            }else{
                actionBar.setTitle(tracker.getSelection().size() + "张");
                if(state == STATE_NORMAL){//进入选择状态
                    state = STATE_SELECTING;
                    toolbarFromNormalToSelecting();
                }
            }
        }
    }

    private void toolbarInit(){
        actionBar.setHomeAsUpIndicator(drawableMenuToClear);
        for(int i = 0; i < actionMenu.size(); i++){
            MenuItem menuItem = actionMenu.getItem(i);
            if(menuItem.getOrder() < 200){
                menuItem.setVisible(false);
            }
        }
    }

    private void toolbarFromNormalToSelecting(){
        //菜单图标动画
        actionBar.setHomeAsUpIndicator(drawableMenuToClear);
        aniMenuToClear.start();

        //显示功能图标
        for(int i = 0; i < actionMenu.size(); i++){
            MenuItem menuItem = actionMenu.getItem(i);
            if(menuItem.getOrder() < 200){
                menuItem.setVisible(true);
            }
        }

        int appBarWidth = binding.appBarLayout.getWidth();
        int appBarHeight = binding.appBarLayout.getHeight();

        //改变背景色动画
        binding.revealView.setVisibility(View.VISIBLE);
        int centerX = WidgetsUtils.dpToPx(this, 28);
        int centerY = appBarHeight - WidgetsUtils.dpToPx(this, 28);
        float radius = Math.max(appBarWidth, appBarHeight) * 1.2f;

        Animator revealAnimator = ViewAnimationUtils.createCircularReveal(binding.revealView, centerX, centerY, 0f, radius);

        revealAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animator) {
                binding.toolbarBackgroundView.setBackgroundResource(R.color.gray_1000);
                binding.revealView.setVisibility(View.INVISIBLE);
            }
        });

        binding.revealView.setBackgroundColor(Color.BLACK);
        revealAnimator.start();

    }

    private void toolbarFromSelectinglToNormal(){
        //菜单图标动画
        actionBar.setHomeAsUpIndicator(drawableClearToMenu);
        aniClearToMenu.start();

        //隐藏功能图标
        for(int i = 0; i < actionMenu.size(); i++){
            MenuItem menuItem = actionMenu.getItem(i);
            if(menuItem.getOrder() < 200){
                menuItem.setVisible(false);
            }
        }

        int appBarWidth = binding.appBarLayout.getWidth();
        int appBarHeight = binding.appBarLayout.getHeight();

        //改变背景色动画
        binding.revealView.setVisibility(View.VISIBLE);
        int centerX = WidgetsUtils.dpToPx(this, 28);
        int centerY = appBarHeight - WidgetsUtils.dpToPx(this, 28);

        float radius = Math.max(appBarWidth, appBarHeight) * 1.2f;
        Animator revealAnimator = ViewAnimationUtils.createCircularReveal(binding.revealView, centerX, centerY, radius, 0f);

        revealAnimator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animator) {
                binding.toolbarBackgroundView.setBackgroundColor(WidgetsUtils.getColorValue(TopAppBarContextualActivity.this, android.R.attr.colorPrimary));
                binding.revealView.setVisibility(View.INVISIBLE);
            }
        });

        binding.revealView.setBackgroundColor(WidgetsUtils.getColorValue(TopAppBarContextualActivity.this, android.R.attr.colorPrimary));
        revealAnimator.start();

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_luanch: {
                int selectCount = tracker.getSelection().size();
                Snackbar snackbar = Snackbar
                        .make(binding.constraintLayout, "一共选择了" + selectCount + "张图片", Snackbar.LENGTH_LONG)
                        .setAction("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        });
                snackbar.show();
                return true;
            }
            case R.id.action_delete: {
                int errorColor = WidgetsUtils.getColorValue(TopAppBarContextualActivity.this, android.R.attr.colorError);
                int primaryColor = WidgetsUtils.getColorValue(TopAppBarContextualActivity.this, android.R.attr.colorPrimary);
                Snackbar snackbar = Snackbar
                        .make(binding.constraintLayout, "要删除选中的图片吗？", Snackbar.LENGTH_LONG)
                        .setBackgroundTint(errorColor)
                        .setActionTextColor(primaryColor)
                        .setAction("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        });
                snackbar.show();
                return true;
            }

            case R.id.action_camera: {
                return true;
            }
            case R.id.action_folder: {
                return true;
            }
            case R.id.action_web: {
                return true;
            }
            default: {//处理导航按钮
                if(state == STATE_SELECTING){
                    tracker.clearSelection();
                }else{
                    Snackbar snackbar = Snackbar
                            .make(binding.constraintLayout, "返回上一个界面吗？", Snackbar.LENGTH_LONG)
                            .setAction("确定", new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    TopAppBarContextualActivity.this.finish();
                                }
                            });
                    snackbar.show();
                }
                return true;
            }
        }
    }

    //https://proandroiddev.com/colored-tab-animated-like-google-play-store-7202ac60da9c
    private int getBackgroundColor(int position, float positionOffset) {
        int startColor;
        int endColor;

        if (position==0) {
            startColor = Color.BLUE;
            endColor = Color.RED;
        } else {
            startColor = Color.RED;
            endColor = Color.BLUE;
        }
        ArgbEvaluator argbEvaluator = new ArgbEvaluator();
        int color = (int) argbEvaluator.evaluate(positionOffset, startColor, endColor);
        return color;
    }

}
