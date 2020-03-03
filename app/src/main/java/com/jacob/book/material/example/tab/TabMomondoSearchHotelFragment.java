/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-29 下午3:50
 *
 */

package com.jacob.book.material.example.tab;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;

import com.jacob.book.material.R;
import com.jacob.book.material.base.TabItemContent;
import com.jacob.book.material.base.TabViewPagerBaseFragment;
import com.jacob.book.material.databinding.TabMomondoSearchHotelFragmentBinding;
import com.jacob.book.temp.TempConstant;

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
