/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-28 下午10:04
 *
 */

package com.jacob.material.example.banner;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;

import com.jacob.material.R;
import com.jacob.material.databinding.BannerOfflineLaunchFragmentBinding;
import com.jacob.material.databinding.BannerOfflineMainFragmentBinding;

public class BannerOfflineMainFragment extends Fragment implements LifecycleObserver {
    private BannerOfflineMainFragmentBinding binding;

    public BannerOfflineMainFragment(){
        this.getLifecycle().addObserver(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.banner_offline_main_fragment, container, false);
        return binding.getRoot();
    }


}
