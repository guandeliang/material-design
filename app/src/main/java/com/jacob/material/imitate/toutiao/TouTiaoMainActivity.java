/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-20 上午9:13
 *
 */

package com.jacob.material.imitate.toutiao;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import android.view.animation.LinearInterpolator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.jacob.material.R;
import com.jacob.material.databinding.TouTiaoMainActivityBinding;

public class TouTiaoMainActivity extends AppCompatActivity implements LifecycleObserver {
    private TouTiaoMainActivityBinding binding;
    private int currSearchTextViewIdx = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.tou_tiao_main_activity);
        binding.appBarLayout.setOnApplyWindowInsetsListener(new ApplyWindowInsetsListener());

        this.getLifecycle().addObserver(this);
        startAnim();
    }

    private class ApplyWindowInsetsListener implements View.OnApplyWindowInsetsListener {
        @Override
        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            int statusBarHeight = windowInsets.getSystemWindowInsetTop();
            binding.statusBarBackgroundLinearLayout.setLayoutParams(new ConstraintLayout.LayoutParams(CoordinatorLayout.LayoutParams.MATCH_PARENT, statusBarHeight));
            return windowInsets;
        }
    }


    private void scrollSearchText(){
        int textHeight = binding.searchTextViewOne.getHeight();
        int layoutHeight = binding.searchTextLayout.getHeight();
        int textY = (layoutHeight - textHeight)/2;

        if(currSearchTextViewIdx == 1){
            currSearchTextViewIdx = 2;
            ObjectAnimator aniOne = ObjectAnimator.ofFloat(binding.searchTextViewOne, View.Y, 0 - textHeight - 10);
            aniOne.setDuration(1000);
            aniOne.start();

            ObjectAnimator aniTwo = ObjectAnimator.ofFloat(binding.searchTextViewTwo, View.Y, layoutHeight + 10);
            aniTwo.setDuration(0);
            aniTwo.start();
            binding.searchTextViewTwo.setVisibility(View.VISIBLE);

            ObjectAnimator aniThree = ObjectAnimator.ofFloat(binding.searchTextViewTwo, View.Y, textY);
            aniThree.setDuration(1000);
            aniThree.start();
        }else{
            currSearchTextViewIdx = 1;
            ObjectAnimator aniOne = ObjectAnimator.ofFloat(binding.searchTextViewTwo, View.Y, 0 - textHeight - 10);
            aniOne.setDuration(1000);
            aniOne.start();

            ObjectAnimator aniTwo = ObjectAnimator.ofFloat(binding.searchTextViewOne, View.Y, layoutHeight + 10);
            aniTwo.setDuration(0);
            aniTwo.start();
            binding.searchTextViewTwo.setVisibility(View.VISIBLE);

            ObjectAnimator aniThree = ObjectAnimator.ofFloat(binding.searchTextViewOne, View.Y, textY);
            aniThree.setDuration(1000);
            aniThree.start();
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onLifecyDestory() {
        stopAnim();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    public ValueAnimator valueAnimator;

    public void startAnim() {
        stopAnim();
        startViewAnim(0, 1, 4000);
    }

    public void stopAnim() {
        if (valueAnimator != null) {
            valueAnimator.removeAllUpdateListeners();
            valueAnimator.removeAllListeners();
            valueAnimator.setRepeatCount(0);
            valueAnimator.end();
            valueAnimator.cancel();
            valueAnimator = null;
        }
    }


    private void startViewAnim(int start, int end, long time) {
        valueAnimator = ValueAnimator.ofFloat(start, end);
        valueAnimator.setDuration(time);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);

        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {}

            @Override
            public void onAnimationStart(Animator animation, boolean isReverse) {}

            @Override
            public void onAnimationEnd(Animator animation) {}

            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {}

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                //放在这里是为了延后4秒启动动画
                scrollSearchText();
            }


        });


        if (!valueAnimator.isRunning()) {
            valueAnimator.start();
        }

    }


}
