/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-3-5 下午11:20
 *
 */

package com.jacob.book.material.example.drawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;

import com.jacob.book.material.R;
import com.jacob.book.material.databinding.DrawerTopAppBarOneFragmentBinding;
import com.jacob.book.material.databinding.DrawerTopAppBarTwoFragmentBinding;

public class DrawerTopAppBarTwoFragment extends Fragment implements LifecycleObserver {
    private DrawerTopAppBarTwoFragmentBinding binding;

    public DrawerTopAppBarTwoFragment(){
        this.getLifecycle().addObserver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.drawer_top_app_bar_two_fragment, container, false);
        return binding.getRoot();
    }
}