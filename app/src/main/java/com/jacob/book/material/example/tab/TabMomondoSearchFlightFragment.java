/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-28 下午10:04
 *
 */

package com.jacob.book.material.example.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleObserver;

import com.google.android.material.tabs.TabLayout;
import com.jacob.book.material.R;
import com.jacob.book.material.base.TabItemContent;
import com.jacob.book.material.base.TabViewPagerBaseFragment;
import com.jacob.book.material.databinding.TabMomondoSearchFlightFragmentBinding;

public class TabMomondoSearchFlightFragment extends TabViewPagerBaseFragment implements LifecycleObserver {
    private TabMomondoSearchFlightFragmentBinding binding;

    public TabMomondoSearchFlightFragment(){
        super(new TabItemContent("机票", R.drawable.momondo_icon_plane));
        this.getLifecycle().addObserver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.tab_momondo_search_flight_fragment, container, false);
        binding.setShowSingleTravel(true);
        binding.setShowRoundTravel(false);
        binding.setShowMulitTravel(false);
        binding.tabLayout.selectTab(binding.tabLayout.getTabAt(0));

        binding.tabLayout.addOnTabSelectedListener(new TravelCategoryTabSelectedListener());

        return binding.getRoot();
    }

    private class TravelCategoryTabSelectedListener implements TabLayout.OnTabSelectedListener {

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            int position = binding.tabLayout.getSelectedTabPosition();
            if(position == 0){
                binding.setShowSingleTravel(true);
                binding.setShowRoundTravel(false);
                binding.setShowMulitTravel(false);
            }else if(position == 1){
                binding.setShowSingleTravel(false);
                binding.setShowRoundTravel(true);
                binding.setShowMulitTravel(false);
            }else if(position == 2){
                binding.setShowSingleTravel(false);
                binding.setShowRoundTravel(false);
                binding.setShowMulitTravel(true);
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {}

        @Override
        public void onTabReselected(TabLayout.Tab tab) {}
    }

}
