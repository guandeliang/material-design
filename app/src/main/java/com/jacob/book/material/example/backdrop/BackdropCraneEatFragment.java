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

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleObserver;

import com.jacob.book.material.R;
import com.jacob.book.material.base.TabItemContent;
import com.jacob.book.material.base.TabViewPagerBaseFragment;
import com.jacob.book.material.databinding.BackdropCraneEatFragmentBinding;
import com.jacob.book.material.databinding.BackdropCraneFlyFragmentBinding;

public class BackdropCraneEatFragment extends TabViewPagerBaseFragment implements LifecycleObserver {
    private BackdropCraneEatFragmentBinding binding;

    public BackdropCraneEatFragment(){
        super(new TabItemContent("餐饮", -1));
        this.getLifecycle().addObserver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.backdrop_crane_eat_fragment, container, false);
        return binding.getRoot();
    }

}
