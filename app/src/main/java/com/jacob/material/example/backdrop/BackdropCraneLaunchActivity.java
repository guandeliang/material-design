/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-1 下午2:24
 *
 */

package com.jacob.material.example.backdrop;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import com.google.android.material.transition.MaterialContainerTransformSharedElementCallback;
import com.jacob.material.R;
import com.jacob.material.databinding.BackdropCraneLaunchActivityBinding;


public class BackdropCraneLaunchActivity extends AppCompatActivity implements LifecycleObserver{
    private BackdropCraneLaunchActivityBinding binding;
    private Animatable logoAni;
    private boolean hasStartMainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getLifecycle().addObserver(this);
        //启动Activity动画效果，也可以在在Style正设置
        this.getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        //配合目标Activity中的setEnterSharedElementCallback使用，否则会出现重影，毛边等现象
        this.setExitSharedElementCallback(new MaterialContainerTransformSharedElementCallback());
        //避免Shared Elements被遮盖
        this.getWindow().setSharedElementsUseOverlay(false);
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.backdrop_crane_launch_activity);
        hasStartMainActivity = false;

        if (Build.VERSION.SDK_INT >= 24) {
            AnimatedVectorDrawable logoDrawable = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.crane_logo_ani, getTheme());
            binding.logoImageView.setImageDrawable(logoDrawable);
            logoAni = ((Animatable)logoDrawable);
            AnimatedVectorDrawableCompat.registerAnimationCallback(logoDrawable, new AniVectorCallback());
        }else{
            AnimatedVectorDrawableCompat logoCompatDrawable = AnimatedVectorDrawableCompat.create(this, R.drawable.crane_logo_ani);
            binding.logoImageView.setImageDrawable(logoCompatDrawable);
            logoAni = ((Animatable)logoCompatDrawable);
            AnimatedVectorDrawableCompat.registerAnimationCallback(logoCompatDrawable, new AniVectorCallback());
        }
        logoAni.start();
    }


    private class AniVectorCallback extends Animatable2Compat.AnimationCallback{
        public void onAnimationEnd(Drawable drawable) {
            hasStartMainActivity = true;
            Intent intent = new Intent();
            intent.setClass(BackdropCraneLaunchActivity.this, BackdropCraneMainActivity.class);
            binding.logoImageView.setImageResource(R.drawable.crane_logo_no_background);
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(BackdropCraneLaunchActivity.this, binding.logoImageView, "logoImageView");
            startActivity(intent, options.toBundle());
            //finishAfterTransition();//无法监听到share element 动画结束，只能通过Lifecycle控制
        }
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    public void onResumeEvent() {
        //如果启动Main Activity到一半，被中断返回
        if(hasStartMainActivity){
            finish();
        }
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    public void onStopEvent() {
        //如果已经完全启动Main Activity
        if(hasStartMainActivity){
            finish();
        }
    }

}
