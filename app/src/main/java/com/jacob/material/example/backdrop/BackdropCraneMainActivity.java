/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-1 下午2:24
 *
 */

package com.jacob.material.example.backdrop;

import android.os.Bundle;
import android.transition.Transition;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.android.material.transition.MaterialArcMotion;
import com.google.android.material.transition.MaterialContainerTransform;
import com.google.android.material.transition.MaterialContainerTransformSharedElementCallback;
import com.jacob.material.R;
import com.jacob.material.base.TabViewPagerAdapter;
import com.jacob.material.base.TabViewPagerBaseFragment;
import com.jacob.material.databinding.BackdropCraneMainActivityBinding;

import java.util.ArrayList;
import java.util.List;


public class BackdropCraneMainActivity extends AppCompatActivity implements LifecycleObserver {
    private BackdropCraneMainActivityBinding binding;
    private BackdropCraneModel viewModel;
    private List<TabViewPagerBaseFragment> fragmentList;
    private TabViewPagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getLifecycle().addObserver(this);
        //启动Activity动画效果，也可以在在Style正设置
        this.getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        //setExitSharedElementCallback使用，否则会出现重影，毛边等现象
        this.setEnterSharedElementCallback(new MaterialContainerTransformSharedElementCallback());
        //禁止进场动画被遮盖
        this.getWindow().setAllowEnterTransitionOverlap(false);

        binding = DataBindingUtil.setContentView(this, R.layout.backdrop_crane_main_activity);
        viewModel = new ViewModelProvider(this).get(BackdropCraneModel.class);

        binding.logoImageView.setTransitionName("logoImageView");
        MaterialContainerTransform transform = new MaterialContainerTransform(this);
        transform.addTarget(binding.logoImageView);
        transform.setDuration(500);
        transform.setPathMotion(new MaterialArcMotion());
        transform.addListener(new EnterTransitionListener());
        this.getWindow().setSharedElementEnterTransition(transform);
        //https://medium.com/@skydoves/building-a-beautiful-disney-android-application-1-material-motion-systems-34607eae2501
        //must behind main layout transition name
        super.onCreate(savedInstanceState);


        fragmentList = new ArrayList<>();
        fragmentList.add(new BackdropCraneFlyFragment());
        fragmentList.add(new BackdropCraneSleepFragment());
        fragmentList.add(new BackdropCraneEatFragment());

        adapter = new TabViewPagerAdapter(this, fragmentList);
        binding.viewPager.setAdapter(adapter);

        TabLayoutMediator mediator = new TabLayoutMediator(
                binding.tabLayout,
                binding.viewPager,
                new MainTabConfigurationStrategy()
        );
        mediator.attach();

        binding.viewPager.animate().translationY(0).setDuration(500);
    }



    private class MainTabConfigurationStrategy implements TabLayoutMediator.TabConfigurationStrategy {
        @Override
        public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
            tab.setText(fragmentList.get(position).getTabItemContent().getTitle());
        }
    }

    private class EnterTransitionListener implements Transition.TransitionListener {
        @Override
        public void onTransitionStart(Transition transition) {
        }

        @Override
        public void onTransitionEnd(Transition transition) {
        }

        @Override
        public void onTransitionCancel(Transition transition) {
        }

        @Override
        public void onTransitionPause(Transition transition) {
        }

        @Override
        public void onTransitionResume(Transition transition) {
        }
    }


}
