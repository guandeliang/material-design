/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-17 下午12:59
 *
 */

package com.jacob.material.example.bottomnavigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jacob.utils.JsonUtils;
import com.jacob.utils.WidgetsUtils;
import com.jacob.material.R;
import com.jacob.material.databinding.BottomNavigationDemoIndexFragmentBinding;
import com.jacob.material.example.adapter.GrammyIndexOneAdapter;
import com.jacob.material.example.adapter.GrammyIndexThreeAdapter;
import com.jacob.material.example.adapter.GrammyIndexTwoAdapter;
import com.jacob.material.example.model.Grammy;
import com.jacob.material.example.model.GrammyMultiItemEntity;
import com.jacob.material.widgets.HorizontalEdgeSnapHelper;
import com.jacob.material.widgets.LinearLayoutHorizontalItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class BottomNavigationDemoIndexFragment extends Fragment implements LifecycleObserver {
    private BottomNavigationDemoIndexFragmentBinding binding;

    public BottomNavigationDemoIndexFragment(){
        this.getLifecycle().addObserver(this);
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_navigation_demo_index_fragment, container, false);

        WidgetsUtils.setSystemBarColor(this.getActivity(), R.color.gray_50);
        WidgetsUtils.setSystemBarLight(this.getActivity());

        binding.nestedScrollView.setOnScrollChangeListener(new OnContentScrollChangeListener());
        binding.searchCardView.setOnClickListener(new OnSearchClickListener());
        initBanner();
        initOfficalList();
        initExpertList();
        initCategoryList();
        initRecentList();
        initTogetherList();
        initVipList();
        initVideoList();;
        initOnlyList();
        return binding.getRoot();
    }

    private class OnContentScrollChangeListener implements NestedScrollView.OnScrollChangeListener{
        @Override
        public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            if(scrollY > oldScrollY){
                appBarSlideUp();
            }else{
                appBarSlideDown();
            }
        }
    }

    boolean isAppBarHide = false;

    private void appBarSlideUp(){
        if(isAppBarHide){
            return;
        }
        isAppBarHide = true;
        int moveY = 0 - binding.searchCardView.getHeight() * 2;
        binding.searchCardView.animate().translationY(moveY).setStartDelay(100).setDuration(300).start();
    }

    private void appBarSlideDown(){
        if(!isAppBarHide){
            return;
        }
        isAppBarHide = false;
        binding.searchCardView.animate().translationY(0).setStartDelay(100).setDuration(300).start();
    }


    private class OnSearchClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Navigation.findNavController(getActivity(),  R.id.nav_host_fragment).navigate(R.id.show_search);
        }
    }

    private void initBanner(){
        binding.cardViewSlider.setImageResIds(
                R.drawable.grammy_banner_001,
                R.drawable.grammy_banner_002,
                R.drawable.grammy_banner_003,
                R.drawable.grammy_banner_004
        );
    }

    private void initOfficalList(){
        List<Grammy> allList = JsonUtils.loadGrammy(getResources());
        List<Grammy> officalList = new ArrayList<>();
        for(Grammy grammy:allList){
            if(grammy.getId() > 10300 && grammy.getId() < 10400){
                officalList.add(grammy);
            }
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.officalRecyclerView.setLayoutManager(layoutManager);
        LinearLayoutHorizontalItemDecoration decoration = new LinearLayoutHorizontalItemDecoration(0, 0, 0, 0, WidgetsUtils.dpToPx(getContext(), 8));
        binding.officalRecyclerView.addItemDecoration(decoration);

        HorizontalEdgeSnapHelper snapHelper = new HorizontalEdgeSnapHelper();
        snapHelper.attachToRecyclerView(binding.officalRecyclerView);

        GrammyIndexOneAdapter adapter = new GrammyIndexOneAdapter(officalList, 3, WidgetsUtils.dpToPx(getContext(), 8));
        binding.officalRecyclerView.setAdapter(adapter);
    }

    private void initExpertList(){
        List<Grammy> allList = JsonUtils.loadGrammy(getResources());
        List<Grammy> expertList = new ArrayList<>();
        for(Grammy grammy:allList){
            if(grammy.getId() > 10400 && grammy.getId() < 10500){
                expertList.add(grammy);
            }
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.expertRecyclerView.setLayoutManager(layoutManager);
        LinearLayoutHorizontalItemDecoration decoration = new LinearLayoutHorizontalItemDecoration(0, 0, 0, 0, WidgetsUtils.dpToPx(getContext(), 8));
        binding.expertRecyclerView.addItemDecoration(decoration);

        HorizontalEdgeSnapHelper snapHelper = new HorizontalEdgeSnapHelper();
        snapHelper.attachToRecyclerView(binding.expertRecyclerView);

        GrammyIndexOneAdapter adapter = new GrammyIndexOneAdapter(expertList, 3, WidgetsUtils.dpToPx(getContext(), 8));
        binding.expertRecyclerView.setAdapter(adapter);
    }

    private void initCategoryList(){
        List<Grammy> allList = JsonUtils.loadGrammy(getResources());
        List<Grammy> categoryList = new ArrayList<>();
        for(Grammy grammy:allList){
            if(grammy.getId() > 10500 && grammy.getId() < 10600){
                categoryList.add(grammy);
            }
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.categoryRecyclerView.setLayoutManager(layoutManager);
        LinearLayoutHorizontalItemDecoration decoration = new LinearLayoutHorizontalItemDecoration(0, 0, 0, 0, WidgetsUtils.dpToPx(getContext(), 8));
        binding.categoryRecyclerView.addItemDecoration(decoration);

        HorizontalEdgeSnapHelper snapHelper = new HorizontalEdgeSnapHelper();
        snapHelper.attachToRecyclerView(binding.categoryRecyclerView);

        GrammyIndexOneAdapter adapter = new GrammyIndexOneAdapter(categoryList, 1.5f, WidgetsUtils.dpToPx(getContext(), 8));
        binding.categoryRecyclerView.setAdapter(adapter);
    }

    private void initRecentList(){
        List<Grammy> allList = JsonUtils.loadGrammy(getResources());
        List<Grammy> recentList = new ArrayList<>();
        for(Grammy grammy:allList){
            if(grammy.getId() > 10600 && grammy.getId() < 10700){
                recentList.add(grammy);
            }
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.recentRecyclerView.setLayoutManager(layoutManager);
        LinearLayoutHorizontalItemDecoration decoration = new LinearLayoutHorizontalItemDecoration(0, 0, 0, 0, WidgetsUtils.dpToPx(getContext(), 8));
        binding.recentRecyclerView.addItemDecoration(decoration);

        HorizontalEdgeSnapHelper snapHelper = new HorizontalEdgeSnapHelper();
        snapHelper.attachToRecyclerView(binding.recentRecyclerView);

        GrammyIndexTwoAdapter adapter = new GrammyIndexTwoAdapter(recentList, 3, WidgetsUtils.dpToPx(getContext(), 8));
        binding.recentRecyclerView.setAdapter(adapter);
    }

    private void initTogetherList(){
        List<Grammy> allList = JsonUtils.loadGrammy(getResources());
        List<Grammy> togetherList = new ArrayList<>();
        for(Grammy grammy:allList){
            if(grammy.getId() > 10700 && grammy.getId() < 10800){
                togetherList.add(grammy);
            }
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.togetherRecyclerView.setLayoutManager(layoutManager);
        LinearLayoutHorizontalItemDecoration decoration = new LinearLayoutHorizontalItemDecoration(0, 0, 0, 0, WidgetsUtils.dpToPx(getContext(), 8));
        binding.togetherRecyclerView.addItemDecoration(decoration);

        HorizontalEdgeSnapHelper snapHelper = new HorizontalEdgeSnapHelper();
        snapHelper.attachToRecyclerView(binding.togetherRecyclerView);

        GrammyIndexTwoAdapter adapter = new GrammyIndexTwoAdapter(togetherList, 3, WidgetsUtils.dpToPx(getContext(), 8));
        binding.togetherRecyclerView.setAdapter(adapter);
    }

    private void initVipList(){
        List<GrammyMultiItemEntity> vipList = new ArrayList<>();
        GrammyMultiItemEntity grammyA = new GrammyMultiItemEntity(GrammyMultiItemEntity.ITEM_TYPE_A);
        GrammyMultiItemEntity grammyB = new GrammyMultiItemEntity(GrammyMultiItemEntity.ITEM_TYPE_B);
        GrammyMultiItemEntity grammyC = new GrammyMultiItemEntity(GrammyMultiItemEntity.ITEM_TYPE_C);
        vipList.add(grammyA);
        vipList.add(grammyB);
        vipList.add(grammyC);


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.vipRecyclerView.setLayoutManager(layoutManager);
        LinearLayoutHorizontalItemDecoration decoration = new LinearLayoutHorizontalItemDecoration(0, 0, 0, 0, WidgetsUtils.dpToPx(getContext(), 8));
        binding.vipRecyclerView.addItemDecoration(decoration);

        HorizontalEdgeSnapHelper snapHelper = new HorizontalEdgeSnapHelper();
        snapHelper.attachToRecyclerView(binding.vipRecyclerView);

        GrammyIndexThreeAdapter adapter = new GrammyIndexThreeAdapter(vipList);
        binding.vipRecyclerView.setAdapter(adapter);
    }


    private void initVideoList(){
        List<Grammy> allList = JsonUtils.loadGrammy(getResources());
        List<Grammy> videoList = new ArrayList<>();
        for(Grammy grammy:allList){
            if(grammy.getId() > 10900 && grammy.getId() < 11000){
                videoList.add(grammy);
            }
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.videoRecyclerView.setLayoutManager(layoutManager);
        LinearLayoutHorizontalItemDecoration decoration = new LinearLayoutHorizontalItemDecoration(0, 0, 0, 0, WidgetsUtils.dpToPx(getContext(), 8));
        binding.videoRecyclerView.addItemDecoration(decoration);

        HorizontalEdgeSnapHelper snapHelper = new HorizontalEdgeSnapHelper();
        snapHelper.attachToRecyclerView(binding.videoRecyclerView);

        GrammyIndexTwoAdapter adapter = new GrammyIndexTwoAdapter(videoList, 1.5f, WidgetsUtils.dpToPx(getContext(), 8));
        binding.videoRecyclerView.setAdapter(adapter);
    }

    private void initOnlyList(){
        List<Grammy> allList = JsonUtils.loadGrammy(getResources());
        List<Grammy> onlyList = new ArrayList<>();
        for(Grammy grammy:allList){
            if(grammy.getId() > 11000){
                onlyList.add(grammy);
            }
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.onlyRecyclerView.setLayoutManager(layoutManager);
        LinearLayoutHorizontalItemDecoration decoration = new LinearLayoutHorizontalItemDecoration(0, 0, 0, 0, WidgetsUtils.dpToPx(getContext(), 8));
        binding.onlyRecyclerView.addItemDecoration(decoration);

        HorizontalEdgeSnapHelper snapHelper = new HorizontalEdgeSnapHelper();
        snapHelper.attachToRecyclerView(binding.onlyRecyclerView);

        GrammyIndexOneAdapter adapter = new GrammyIndexOneAdapter(onlyList, 1.5f, WidgetsUtils.dpToPx(getContext(), 8));
        binding.onlyRecyclerView.setAdapter(adapter);
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onLifecycleDistory(){
        binding.cardViewSlider.dispose();
    }
}
