/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-1 下午12:06
 *
 */

package com.jacob.book.material.example.topappbar;


import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.jacob.book.WidgetsUtils;
import com.jacob.book.material.R;
import com.jacob.book.material.base.TabItemContent;
import com.jacob.book.material.base.TabViewPagerBaseFragment;
import com.jacob.book.material.databinding.TopAppBarDarkOneFragmentBinding;

import java.util.ArrayList;
import java.util.Random;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopAppBarDarkOneFragment extends TabViewPagerBaseFragment {

    private TopAppBarDarkOneFragmentBinding binding;


    public TopAppBarDarkOneFragment() {
        super(new TabItemContent("今日活跃度", -1));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.top_app_bar_dark_one_fragment, container, false);
        initData();
        return binding.getRoot();
    }

    private void initData(){
        final String[] hours = new String[]{"00:00", "04:00", "08:00", "12:00", "16:00", "20:00", "24:00"};
        ValueFormatter formatter = new ValueFormatter() {
            @Override
            public String getAxisLabel(float value, AxisBase axis) {
                return hours[(int) value];
            }
        };

        binding.lineChart.setTouchEnabled(false);
        binding.lineChart.getDescription().setEnabled(false);
        binding.lineChart.setDrawGridBackground(false);
        binding.lineChart.getLegend().setEnabled(false);
        XAxis x = binding.lineChart.getXAxis();
        x.setValueFormatter(formatter);
        x.setTextColor(ContextCompat.getColor(this.getActivity(), R.color.gray_700));
        x.setPosition(XAxis.XAxisPosition.BOTTOM);
        x.setEnabled(true);
        x.setDrawGridLines(false);
        x.setAxisLineColor(ContextCompat.getColor(this.getActivity(), R.color.gray_700));

        YAxis y = binding.lineChart.getAxisLeft();
        y.setLabelCount(5,true);
        y.setTextColor(ContextCompat.getColor(this.getActivity(), R.color.gray_700));
        y.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        y.setDrawGridLines(true);
        y.setDrawAxisLine(false);
        y.setAxisLineColor(ContextCompat.getColor(this.getActivity(), R.color.gray_700));

        binding.lineChart.getAxisLeft().setGranularityEnabled(false);
        binding.lineChart.getAxisRight().setEnabled(false);


        Random random = new Random();
        ArrayList valueList = new ArrayList();
        for(int i=0; i<6; i++){
            int randomValue = random.nextInt(100) + 20;
            valueList.add(new Entry(i, randomValue));
        }

        LineDataSet dataSet = new LineDataSet(valueList, "测试数据");

        dataSet.setDrawIcons(false);
        dataSet.setColor(WidgetsUtils.getColorValue(this.getActivity(), R.color.dark_color_primary_green_300));
        dataSet.setCircleColor(ContextCompat.getColor(this.getActivity(), R.color.gray_700));
        dataSet.setCircleHoleColor(Color.TRANSPARENT);
        dataSet.setDrawValues(false);
        dataSet.setLabel(null);
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSet.setLineWidth(1f);
        dataSet.setCircleRadius(3f);

        ArrayList<ILineDataSet> dataSetList = new ArrayList<>();

        dataSetList.add(dataSet);
        LineData data = new LineData(dataSetList);
        binding.lineChart.setData(data);




    }


}
