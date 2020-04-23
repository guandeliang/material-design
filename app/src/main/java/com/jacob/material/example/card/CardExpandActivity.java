/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-31 下午5:05
 *
 */

package com.jacob.material.example.card;

import android.animation.Animator;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.WindowCompat;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.transition.Fade;
import androidx.transition.Slide;
import androidx.transition.Transition;
import androidx.transition.TransitionManager;
import androidx.transition.Visibility;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.slider.Slider;
import com.jacob.material.R;
import com.jacob.material.databinding.CardDayNightCustomActivityBinding;
import com.jacob.material.databinding.CardExpandActivityBinding;
import com.jacob.material.example.adapter.MarvelNewsAdapter;
import com.jacob.material.example.fab.FabBehaviorActivity;
import com.jacob.material.example.model.MarvelNews;
import com.jacob.temp.TempConstant;
import com.jacob.utils.JsonUtils;
import com.jacob.utils.WidgetsUtils;

import java.util.List;

public class CardExpandActivity extends AppCompatActivity {
    private CardExpandActivityBinding binding;
    private float lastSliderValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.card_expand_activity);
        WidgetsUtils.setSystemBarColor(this, R.attr.colorPrimary);
        WidgetsUtils.setSystemBarDark(this);
        WidgetsUtils.setNavigationBarColor(this, R.attr.colorPrimary);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setNestedScrollingEnabled(false);

        List<MarvelNews> newList = JsonUtils.loadMarvelNews(getResources());
        MarvelNewsAdapter adapter = new MarvelNewsAdapter(newList, R.layout.marvel_news_holder);

        binding.recyclerView.setAdapter(adapter);

        binding.expandButton.setOnClickListener(new ExpandButtonClickListener());
        binding.nestedScrollView.setOnScrollChangeListener(new OnContentScrollChangeListener());

        lastSliderValue = binding.weatherTimeSlider.getValue();
        setWeatherInfo(lastSliderValue);
        binding.weatherTimeSlider.addOnChangeListener(new WeatherTimeSliderChangeListener());


    }

    private class WeatherTimeSliderChangeListener implements Slider.OnChangeListener{
        @Override
        public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
            binding.weatherInfoConstraintLayout.animate().cancel();
            if(value > lastSliderValue){
                binding.weatherInfoConstraintLayout.animate().translationX(-300).alpha(0).setDuration(200).setListener(new WeatherInfoAnimatorListener(value));
            }else if(value < lastSliderValue){
                binding.weatherInfoConstraintLayout.animate().translationX(300).alpha(0).setDuration(200).setListener(new WeatherInfoAnimatorListener(value));
            }else{
                binding.weatherInfoConstraintLayout.setTranslationX(0);
                binding.weatherInfoConstraintLayout.animate().alpha(1);
            }
        }
    }

    private void setWeatherInfo(float time){
        class WeatherInfo{
            String content;
            String temperature;
            int imageResId;
            String precipitation;
            String wind;
            WeatherInfo(String content, String temperature, int imageResId, String precipitation, String wind){
                this.content = content;
                this.temperature = temperature;
                this.imageResId = imageResId;
                this.precipitation = precipitation;
                this.wind = wind;
            }
        }

        WeatherInfo[] infos = new WeatherInfo[7];
        infos[0] = new WeatherInfo("星期三 00:00 晴", "12", R.drawable.weather_002, "降雨概率 10%", "风速0.2公里/小时");
        infos[1] = new WeatherInfo("星期三 04:00 晴", "16", R.drawable.weather_004, "降雨概率 15%", "风速1.3公里/小时");
        infos[2] = new WeatherInfo("星期三 08:00 晴", "18", R.drawable.weather_006, "降雨概率 20%", "风速2.4公里/小时");
        infos[3] = new WeatherInfo("星期三 12:00 晴", "20", R.drawable.weather_008, "降雨概率 25%", "风速3.5公里/小时");
        infos[4] = new WeatherInfo("星期三 16:00 晴", "15", R.drawable.weather_010, "降雨概率 17%", "风速4.6公里/小时");
        infos[5] = new WeatherInfo("星期三 20:00 晴", "13", R.drawable.weather_012, "降雨概率 13%", "风速5.7公里/小时");
        infos[6] = new WeatherInfo("星期三 24:00 晴", "11", R.drawable.weather_014, "降雨概率 11%", "风速6.8公里/小时");

        int idx = (int)(time/4f);

        binding.weatherInfoTextView.setText(infos[idx].content);
        binding.weatherTemperatureTextView.setText(infos[idx].temperature);
        binding.weatherImageView.setImageResource(infos[idx].imageResId);
        binding.weatherPrecipitationTextView.setText(infos[idx].precipitation);
        binding.weatherWindTextView.setText(infos[idx].wind);

        binding.upImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.nestedScrollView.smoothScrollTo(0, 0);
            }
        });

    }


    private class WeatherInfoAnimatorListener implements Animator.AnimatorListener{
        private float value;
        private boolean isCancel;

        public WeatherInfoAnimatorListener(float value){
            this.value = value;
            this.isCancel = false;
        }

        @Override
        public void onAnimationStart(Animator animator) {}

        @Override
        public void onAnimationEnd(Animator animator) {
            Log.d(TempConstant.LOG_TAG, "onAnimationEnd");
            animator.removeListener(this);
            if(isCancel || value == lastSliderValue) {
                binding.weatherInfoConstraintLayout.setTranslationX(0);
                binding.weatherInfoConstraintLayout.animate().alpha(1);
            }else{
                if (value > lastSliderValue){
                    binding.weatherInfoConstraintLayout.setTranslationX(300);
                    binding.weatherInfoConstraintLayout.animate().translationX(0).alpha(1).setDuration(200);
                }else{
                    binding.weatherInfoConstraintLayout.setTranslationX(-300);
                    binding.weatherInfoConstraintLayout.animate().translationX(0).alpha(1).setDuration(200);
                }
            }
            lastSliderValue = value;
            setWeatherInfo(lastSliderValue);
        }

        @Override
        public void onAnimationCancel(Animator animator) {
            this.isCancel = true;
        }

        @Override
        public void onAnimationRepeat(Animator animator) {}

    }



    private class ExpandButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if(binding.weatherMoreConstraintLayout.getVisibility() == View.GONE){
                Transition transition = new Slide(Gravity.BOTTOM);
                transition.setDuration(500);
                TransitionManager.beginDelayedTransition(binding.weatherConstraintLayout,  transition);
                binding.weatherMoreConstraintLayout.setVisibility(View.VISIBLE);
                binding.expandButton.setText("收起更多日期");
            }else{
                Transition transition = new Slide(Gravity.TOP);
                transition.setDuration(500);
                TransitionManager.beginDelayedTransition(binding.weatherConstraintLayout,  transition);
                binding.weatherMoreConstraintLayout.setVisibility(View.GONE);
                binding.expandButton.setText("查看更多日期");
            }
        }
    }

    private class OnContentScrollChangeListener implements NestedScrollView.OnScrollChangeListener{
        @Override
        public void onScrollChange(NestedScrollView nestedScrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            Rect displayFrameRect = new Rect();
            nestedScrollView.getWindowVisibleDisplayFrame(displayFrameRect);
            int statusBarHeight = displayFrameRect.top;
            int topBarHeight = WidgetsUtils.dpToPx(CardExpandActivity.this, 56);

            int[] newsCardViewLocationOnScreen = new int[2];
            binding.newsCardView.getLocationOnScreen(newsCardViewLocationOnScreen);

            int newsCardViewTop = newsCardViewLocationOnScreen[1] - statusBarHeight;

            int dp_8 = WidgetsUtils.dpToPx(CardExpandActivity.this, 8);

            if(newsCardViewTop < topBarHeight){
                binding.toolBarConstraintLayout.setVisibility(View.VISIBLE);
                binding.newsCardView.setCardBackgroundColor(WidgetsUtils.getColorValue(CardExpandActivity.this, R.attr.colorSurface));
                WidgetsUtils.setSystemBarColor(CardExpandActivity.this, R.attr.colorSurface);
                WidgetsUtils.setSystemBarLight(CardExpandActivity.this);
                WidgetsUtils.setNavigationBarColor(CardExpandActivity.this, R.attr.colorSurface);
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams)binding.newsCardView.getLayoutParams();
                layoutParams.setMargins(0, 0, 0, 0);
                binding.newsCardView.setLayoutParams(layoutParams);
                binding.recyclerView.setPadding(dp_8*3, 0, dp_8*3, 0);
            }else{
                binding.toolBarConstraintLayout.setVisibility(View.GONE);
                binding.newsCardView.setCardBackgroundColor(WidgetsUtils.getColorValue(CardExpandActivity.this, R.color.transparent_white_80));
                WidgetsUtils.setSystemBarColor(CardExpandActivity.this, R.attr.colorPrimary);
                WidgetsUtils.setSystemBarDark(CardExpandActivity.this);
                WidgetsUtils.setNavigationBarColor(CardExpandActivity.this, R.attr.colorPrimary);

                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams)binding.newsCardView.getLayoutParams();
                layoutParams.setMargins(dp_8, 0, dp_8, 0);
                binding.newsCardView.setLayoutParams(layoutParams);
                binding.recyclerView.setPadding(dp_8*2, 0, dp_8*2, 0);
            }
        }
    }



}
