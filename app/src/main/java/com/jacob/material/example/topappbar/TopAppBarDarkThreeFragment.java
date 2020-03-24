/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-26 下午1:34
 *
 */

package com.jacob.material.example.topappbar;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.jacob.material.R;
import com.jacob.material.base.TabItemContent;
import com.jacob.material.base.TabViewPagerBaseFragment;
import com.jacob.material.databinding.TopAppBarDarkThreeFragmentBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopAppBarDarkThreeFragment extends TabViewPagerBaseFragment {

    private TopAppBarDarkThreeFragmentBinding binding;


    public TopAppBarDarkThreeFragment() {
        super(new TabItemContent("最新用户", -1));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.top_app_bar_dark_three_fragment, container, false);
        return binding.getRoot();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
