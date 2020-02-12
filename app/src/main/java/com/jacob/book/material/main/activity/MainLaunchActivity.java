package com.jacob.book.material.main.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import com.jacob.book.WidgetsUtils;
import com.jacob.book.material.R;
import com.jacob.book.material.databinding.MainLaunchActivityBinding;
import com.jacob.book.material.example.topappbar.TopAppBarDarkTwoFragment;


public class MainLaunchActivity extends AppCompatActivity {
    private MainLaunchActivityBinding binding;
    private Animatable logoAni;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.main_launch_activity);
        initStyle(null);


        if (Build.VERSION.SDK_INT >= 24) {
            AnimatedVectorDrawable logoDrawable = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.logo_ani, getTheme());
            binding.logoImageView.setImageDrawable(logoDrawable);
            logoAni = ((Animatable)logoDrawable);
            AnimatedVectorDrawableCompat.registerAnimationCallback(logoDrawable, new AniVectorCallback());
        }else{
            AnimatedVectorDrawableCompat logoCompatDrawable = AnimatedVectorDrawableCompat.create(this, R.drawable.logo_ani);
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
            finish();
        }
    }

    private void initStyle(Configuration configuration){
        if(configuration == null){
            configuration = getResources().getConfiguration();
        }
        if (configuration == null){
            return;
        }

        int currentNightMode = configuration.uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if(currentNightMode == Configuration.UI_MODE_NIGHT_YES){//夜间模式

        }else if(currentNightMode == Configuration.UI_MODE_NIGHT_NO){//日间模式
            WidgetsUtils.setSystemBarLight(this);
        }else if(currentNightMode == Configuration.UI_MODE_NIGHT_UNDEFINED){//不知道什么模式

        }
    }


    @Override
    public void onConfigurationChanged(@NonNull Configuration configuration) {
        super.onConfigurationChanged(configuration);
        initStyle(configuration);
    }


}
