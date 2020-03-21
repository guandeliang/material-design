/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-3-5 下午11:27
 *
 */

package com.jacob.material.example.drawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;

import com.jacob.material.R;
import com.jacob.material.base.RandomImagePresenter;
import com.jacob.material.databinding.DrawerTopAppBarRandomFragmentBinding;

public class DrawerTopAppBarRandomFragment extends Fragment implements LifecycleObserver {
    private DrawerTopAppBarRandomFragmentBinding binding;

    public DrawerTopAppBarRandomFragment(){
        this.getLifecycle().addObserver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.drawer_top_app_bar_random_fragment, container, false);
        binding.include.setH(RandomImagePresenter.ImageViewCategory.H_RECTANGLE);
        binding.include.setS(RandomImagePresenter.ImageViewCategory.SQUARE);
        binding.include.setV(RandomImagePresenter.ImageViewCategory.V_RECTANGLE);
        return binding.getRoot();
    }




}
