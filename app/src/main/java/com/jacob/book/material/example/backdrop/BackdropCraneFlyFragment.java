/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-28 下午10:04
 *
 */

package com.jacob.book.material.example.backdrop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleObserver;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.tabs.TabLayout;
import com.jacob.book.JsonUtils;
import com.jacob.book.WidgetsUtils;
import com.jacob.book.material.R;
import com.jacob.book.material.base.TabItemContent;
import com.jacob.book.material.base.TabViewPagerBaseFragment;
import com.jacob.book.material.databinding.BackdropCraneFlyFragmentBinding;
import com.jacob.book.material.databinding.TabMomondoSearchFlightFragmentBinding;
import com.jacob.book.material.example.adapter.GeneralAdapterOne;
import com.jacob.book.material.example.model.GeneralData;
import com.jacob.book.material.widgets.LinearLayoutVertialItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class BackdropCraneFlyFragment extends TabViewPagerBaseFragment implements LifecycleObserver {
    private BackdropCraneFlyFragmentBinding binding;
    private boolean isShowBack;
    private int backHeight;

    public BackdropCraneFlyFragment(){
        super(new TabItemContent("机票", -1));
        this.getLifecycle().addObserver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.backdrop_crane_fly_fragment, container, false);
        binding.setFragement(this);

        //在三个Fragment中使用不同的方式获取back高度，纯粹是为了演示ViewPager2需要
        //因为ViewPager2对于第二个Fragment根本不会执行GlobalLayoutListener
        ViewTreeObserver viewTreeObserver = binding.backConstrainLayout.getViewTreeObserver();
        if(viewTreeObserver.isAlive()){
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    binding.backConstrainLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    backHeight = binding.backConstrainLayout.getHeight();
                    binding.dropEventHostFrameLayout.setTranslationY(backHeight);
                }
            });
        }

        isShowBack = true;
        binding.switchImageView.setImageResource(R.drawable.icon_keyboard_arrow_up);
        initDataList();
        return binding.getRoot();
    }


    private void initDataList(){
        List<GeneralData> allList = JsonUtils.loadGeneralDatas(getResources());
        List<GeneralData> resultList = new ArrayList<>();
        for(GeneralData data:allList){
            if("101".equals(data.getCategory())){
                resultList.add(data);
            }
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);

        int px_08 = WidgetsUtils.dpToPx(getContext(), 8);
        int px_16 = WidgetsUtils.dpToPx(getContext(), 16);

        LinearLayoutVertialItemDecoration decoration = new LinearLayoutVertialItemDecoration(px_16, px_16, px_08);
        binding.recyclerView.addItemDecoration(decoration);

        GeneralAdapterOne adapter = new GeneralAdapterOne(resultList);

        binding.recyclerView.setAdapter(adapter);
    }

    public void onDropButtonClick(){
        if(!isShowBack){
            isShowBack = true;
            binding.switchImageView.setImageResource(R.drawable.icon_keyboard_arrow_up);
            binding.dropEventHostFrameLayout.animate().translationY(backHeight).setDuration(300);
        }else{
            isShowBack = false;
            binding.switchImageView.setImageResource(R.drawable.icon_keyboard_arrow_down);
            binding.dropEventHostFrameLayout.animate().translationY(0).setDuration(300);
        }
    }


}
