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
import com.jacob.material.databinding.BannerNetworkOfflineFragmentBinding;

public class BannerNetworkOfflineFragment extends Fragment implements LifecycleObserver {
    private BannerNetworkOfflineFragmentBinding binding;

    public BannerNetworkOfflineFragment(){
        this.getLifecycle().addObserver(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.banner_network_offline_fragment, container, false);
        return binding.getRoot();
    }


    /*

        try {
            //Intent intent = new Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS);
            //Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
            Intent intent = new Intent(Settings.ACTION_SETTINGS);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Log.e("YourApp", "Activity not found, android settings not launched");
        }


     */
}
