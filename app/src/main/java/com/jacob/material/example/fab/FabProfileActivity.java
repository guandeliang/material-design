/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-19 上午9:42
 *
 */

package com.jacob.material.example.fab;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.appbar.AppBarLayout;
import com.jacob.utils.WidgetsUtils;
import com.jacob.material.R;
import com.jacob.material.databinding.FabProfileActivityBinding;

public class FabProfileActivity extends AppCompatActivity {
    private FabProfileActivityBinding binding;
    private int layoutWidth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.fab_profile_activity);
        WidgetsUtils.setSystemBarLight(this);

        layoutWidth = 0;
        binding.appBarLayout.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int left, int top, int right, int bottom, int oldLeft, int oldTop, int oldRight, int oldBottom) {
                binding.appBarLayout.removeOnLayoutChangeListener(this);
                layoutWidth = binding.appBarLayout.getWidth();
            }
        });


        binding.appBarLayout.addOnOffsetChangedListener(new AppBarLayoutOnOffsetChangedListener());
        binding.motionLayout.setTransitionListener(new MotionTransitionListener());

    }

    private class MotionTransitionListener implements MotionLayout.TransitionListener{
        @Override
        public void onTransitionStarted(MotionLayout motionLayout, int startId, int endId) {
            if(startId == R.id.start){//收缩
                binding.extendedFab.shrink();
            }else if(startId == R.id.end){//展开
                binding.extendedFab.extend();
            }
        }

        @Override
        public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float progress) {
            float distance = layoutWidth/2f - WidgetsUtils.dpToPx(FabProfileActivity.this, 56/2 + 16);
            binding.extendedFab.setTranslationX(distance*progress);
        }

        @Override
        public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {
            if(currentId == R.id.start){//展开
                binding.extendedFab.extend();;
            }else if(currentId == R.id.end){//收缩
                binding.extendedFab.shrink();
            }
        }

        @Override
        public void onTransitionTrigger(MotionLayout motionLayout, int triggerId, boolean positive, float progress) {
        }

    }



    private class AppBarLayoutOnOffsetChangedListener implements AppBarLayout.OnOffsetChangedListener {
        @Override
        public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
            float progress = (0-verticalOffset) / (float)binding.appBarLayout.getTotalScrollRange();
            binding.motionLayout.setProgress(progress);
        }
    }
}
