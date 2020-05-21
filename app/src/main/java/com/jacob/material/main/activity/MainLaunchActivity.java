/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-1 下午2:24
 *
 */

package com.jacob.material.main.activity;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.databinding.DataBindingUtil;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import com.jacob.material.R;
import com.jacob.material.databinding.MainLaunchActivityBinding;

public class MainLaunchActivity extends AppCompatActivity {
    private MainLaunchActivityBinding binding;
    private Animatable logoAni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.main_launch_activity);

        if (Build.VERSION.SDK_INT >= 24) {
            AnimatedVectorDrawable logoDrawable = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.main_logo_ani, getTheme());
            binding.logoImageView.setImageDrawable(logoDrawable);
            logoAni = ((Animatable)logoDrawable);
            AnimatedVectorDrawableCompat.registerAnimationCallback(logoDrawable, new AniVectorCallback());
        }else{
            AnimatedVectorDrawableCompat logoCompatDrawable = AnimatedVectorDrawableCompat.create(this, R.drawable.main_logo_ani);
            binding.logoImageView.setImageDrawable(logoCompatDrawable);
            logoAni = ((Animatable)logoCompatDrawable);
            AnimatedVectorDrawableCompat.registerAnimationCallback(logoCompatDrawable, new AniVectorCallback());
        }
        logoAni.start();
    }

    private class AniVectorCallback extends Animatable2Compat.AnimationCallback{
        public void onAnimationEnd(Drawable drawable) {
            Intent intent = new Intent();
            intent.setClass(MainLaunchActivity.this, MainNavigationActivity.class);
            startActivity(intent);
            MainLaunchActivity.this.overridePendingTransition(R.anim.main_nav_activity_alpha_scale_enter, android.R.anim.fade_out);
            finish();
        }
    }
}
