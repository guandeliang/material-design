/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-29 下午3:50
 *
 */

package com.jacob.material.example.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleObserver;

import com.jacob.material.R;
import com.jacob.material.base.TabItemContent;
import com.jacob.material.base.TabViewPagerBaseFragment;
import com.jacob.material.databinding.TabMomondoSearchHotelFragmentBinding;

public class TabMomondoSearchHotelFragment extends TabViewPagerBaseFragment implements LifecycleObserver {
    private TabMomondoSearchHotelFragmentBinding binding;

    public TabMomondoSearchHotelFragment(){
        super(new TabItemContent("酒店", R.drawable.momondo_icon_bed));
        this.getLifecycle().addObserver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.tab_momondo_search_hotel_fragment, container, false);

        return binding.getRoot();
    }


}
