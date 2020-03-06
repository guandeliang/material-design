/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-3-5 下午11:21
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
import com.jacob.book.material.databinding.DrawerTopAppBarFiveFragmentBinding;
import com.jacob.book.material.databinding.DrawerTopAppBarTwoFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class DrawerTopAppBarFiveFragment extends Fragment implements LifecycleObserver {
    private DrawerTopAppBarFiveFragmentBinding binding;

    public DrawerTopAppBarFiveFragment(){
        this.getLifecycle().addObserver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.drawer_top_app_bar_five_fragment, container, false);
        return binding.getRoot();
    }





}
