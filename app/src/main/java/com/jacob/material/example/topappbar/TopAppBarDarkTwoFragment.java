/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-18 下午2:36
 *
 */

package com.jacob.material.example.topappbar;


import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.jacob.utils.WidgetsUtils;
import com.jacob.material.R;
import com.jacob.material.base.TabItemContent;
import com.jacob.material.base.TabViewPagerBaseFragment;
import com.jacob.material.databinding.TopAppBarDarkTwoFragmentBinding;

import java.util.ArrayList;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopAppBarDarkTwoFragment extends TabViewPagerBaseFragment {

    private TopAppBarDarkTwoFragmentBinding binding;
    private Animatable ringAni;


    public TopAppBarDarkTwoFragment() {
        super(new TabItemContent("本周活跃度", -1));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.getLifecycle().addObserver(new FragmentLifecycleObserver());
        binding = DataBindingUtil.inflate(inflater, R.layout.top_app_bar_dark_two_fragment, container, false);

        if (Build.VERSION.SDK_INT >= 24) {
            AnimatedVectorDrawable ringDrawable = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.three_ring_ani, getActivity().getTheme());
            binding.ringImageView.setImageDrawable(ringDrawable);
            ringAni = ((Animatable)ringDrawable);
            AnimatedVectorDrawableCompat.registerAnimationCallback(ringDrawable, new AniVectorCallback());
        }else{
            AnimatedVectorDrawableCompat ringCompatDrawable = AnimatedVectorDrawableCompat.create(this.getContext(), R.drawable.three_ring_ani);
            binding.ringImageView.setImageDrawable(ringCompatDrawable);
            ringAni = ((Animatable)ringCompatDrawable);
            AnimatedVectorDrawableCompat.registerAnimationCallback(ringCompatDrawable, new AniVectorCallback());
        }


        initLineChart();
        return binding.getRoot();
    }

    private void initLineChart(){
        binding.lineChart.setTouchEnabled(false);
        binding.lineChart.setNoDataText("正在刷新数据");
        binding.lineChart.getDescription().setEnabled(false);
        binding.lineChart.setDrawGridBackground(false);
        binding.lineChart.getLegend().setEnabled(false);
        XAxis x = binding.lineChart.getXAxis();
        x.setDrawGridLines(false);
        x.setDrawAxisLine(false);
        x.setDrawLabels(false);

        YAxis y = binding.lineChart.getAxisLeft();
        y.setDrawGridLines(false);
        y.setDrawAxisLine(false);

        binding.lineChart.getAxisLeft().setEnabled(false);
        binding.lineChart.getAxisLeft().setGranularityEnabled(false);
        binding.lineChart.getAxisLeft().setDrawAxisLine(false);

        binding.lineChart.getAxisRight().setEnabled(false);
        binding.lineChart.getAxisRight().setGranularityEnabled(false);
        binding.lineChart.getAxisRight().setDrawAxisLine(false);

        binding.lineChart.setPadding(0,0,0,0);
    }

    private void initData(){
        if(binding.lineChart.getData() != null){
            binding.lineChart.getData().clearValues();
        }
        Random random = new Random();
        ArrayList valuesListOne = new ArrayList();
        ArrayList valuesListTwo = new ArrayList();
        ArrayList valuesListThree = new ArrayList();
        for(int i=0; i<7; i++){
            valuesListOne.add(new Entry(i, random.nextInt(100) + 20));
            valuesListTwo.add(new Entry(i, random.nextInt(100) + 20));
            valuesListThree.add(new Entry(i, random.nextInt(100) + 20));
        }

        LineDataSet lineDataSetOne = new LineDataSet(valuesListOne, "测试数据一");
        lineDataSetOne.setDrawIcons(false);
        lineDataSetOne.setColor(WidgetsUtils.getColorValue(this.getActivity(), R.color.dark_color_primary_green_500));
        lineDataSetOne.setDrawCircles(false);
        lineDataSetOne.setDrawValues(false);
        lineDataSetOne.setLabel(null);
        lineDataSetOne.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSetOne.setLineWidth(2f);


        LineDataSet lineDataSetTwo = new LineDataSet(valuesListTwo, "测试数据二");
        lineDataSetTwo.setDrawIcons(false);
        lineDataSetTwo.setColor(WidgetsUtils.getColorValue(this.getActivity(), R.color.dark_color_secondary_orange_500));
        lineDataSetTwo.setDrawCircles(false);
        lineDataSetTwo.setDrawValues(false);
        lineDataSetTwo.setLabel(null);
        lineDataSetTwo.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSetTwo.setLineWidth(2f);

        LineDataSet lineDataSetThree = new LineDataSet(valuesListThree, "测试数据三");
        lineDataSetThree.setDrawIcons(false);
        lineDataSetThree.setColor(WidgetsUtils.getColorValue(this.getActivity(), R.color.dark_color_assist_yellow_500));
        lineDataSetThree.setDrawCircles(false);
        lineDataSetThree.setDrawValues(false);
        lineDataSetThree.setLabel(null);
        lineDataSetThree.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        lineDataSetThree.setLineWidth(2f);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSetOne);
        dataSets.add(lineDataSetTwo);
        dataSets.add(lineDataSetThree);

        LineData data = new LineData(dataSets);
        binding.lineChart.setData(data);
        binding.lineChart.invalidate();
    }

    private class AniVectorCallback extends Animatable2Compat.AnimationCallback{
        public void onAnimationEnd(Drawable drawable) {
            initData();
        }
    }


    private class FragmentLifecycleObserver implements LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        public void onResume(){
            if(!ringAni.isRunning()){
                ringAni.start();
            }
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        public void onDestory(){
            if(ringAni.isRunning()){
                ringAni.stop();
            }
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        public void onPause(){
            if(ringAni.isRunning()){
                ringAni.stop();
            }
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        public void onStop(){
            if(ringAni.isRunning()){
                ringAni.stop();
            }
        }
    }


}
