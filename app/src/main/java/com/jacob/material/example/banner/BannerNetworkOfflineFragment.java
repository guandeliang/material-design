/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-28 下午10:04
 *
 */

package com.jacob.material.example.banner;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ViewModelProvider;

import com.jacob.material.R;
import com.jacob.material.databinding.BannerNetworkOfflineFragmentBinding;
import com.jacob.material.generated.callback.OnTextChanged;
import com.jacob.temp.TempConstant;

public class BannerNetworkOfflineFragment extends Fragment implements LifecycleObserver {
    private BannerNetworkOfflineFragmentBinding binding;
    private BannerNetworkViewModel viewModel;

    public BannerNetworkOfflineFragment(){
        this.getLifecycle().addObserver(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.banner_network_offline_fragment, container, false);
        viewModel = new ViewModelProvider(getActivity()).get(BannerNetworkViewModel.class);
        binding.settingButton.setOnClickListener(new SettingButtonClickListener());
        binding.indexButton.setOnClickListener(new IndexButtonClickListener());


        return binding.getRoot();
    }

    private class SettingButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            try {
                Intent intent = new Intent(Settings.ACTION_SETTINGS);
                startActivity(intent);
            } catch (ActivityNotFoundException e) {
                binding.offlineTextView.setText("无法启动系统设置界面，请手动打开手机设置，进行网络连接！");
            }

        }
    }

    private class IndexButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if(viewModel.getNetworkState() == BannerNetworkViewModel.NetworkState.AVAILABE){
                viewModel.getNavController().navigate(R.id.show_online);
            }else{
                binding.motionLayout.setTransitionListener(new BannerTransitionListener());
                binding.motionLayout.setTransitionDuration(300);
                binding.motionLayout.transitionToEnd();
            }

        }
    }

    private class BannerTransitionListener implements MotionLayout.TransitionListener{
        @Override
        public void onTransitionStarted(MotionLayout motionLayout, int startId, int endId) {

        }

        @Override
        public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float progress) {

        }

        @Override
        public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {
            if(currentId == R.id.end){
                binding.motionLayout.setTransitionDuration(300);
                binding.motionLayout.transitionToStart();
            }
        }

        @Override
        public void onTransitionTrigger(MotionLayout motionLayout, int triggerId, boolean positive, float progress) {

        }
    }

}
