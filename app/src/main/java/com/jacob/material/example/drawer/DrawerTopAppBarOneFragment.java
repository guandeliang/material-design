/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-3-1 上午11:00
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
import com.jacob.material.databinding.DrawerTopAppBarOneFragmentBinding;

public class DrawerTopAppBarOneFragment extends Fragment implements LifecycleObserver {
    private DrawerTopAppBarOneFragmentBinding binding;

    public DrawerTopAppBarOneFragment(){
        this.getLifecycle().addObserver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.drawer_top_app_bar_one_fragment, container, false);
        return binding.getRoot();
    }
}
