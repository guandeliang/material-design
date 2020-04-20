/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-28 下午10:04
 *
 */

package com.jacob.material.example.banner;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ViewModelProvider;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;
import androidx.vectordrawable.graphics.drawable.AnimatedVectorDrawableCompat;

import com.jacob.http.MdRetrofitFactory;
import com.jacob.material.R;
import com.jacob.material.databinding.BannerNetworkLaunchFragmentBinding;
import com.jacob.material.example.httploader.HttpLoader;
import com.jacob.material.example.model.Thrones;
import com.jacob.temp.TempConstant;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BannerNetworkLaunchFragment extends Fragment implements LifecycleObserver {
    private BannerNetworkLaunchFragmentBinding binding;
    private BannerNetworkViewModel viewModel;
    private Animatable logoAni;

    public BannerNetworkLaunchFragment(){
        this.getLifecycle().addObserver(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.banner_network_launch_fragment, container, false);
        viewModel = new ViewModelProvider(getActivity()).get(BannerNetworkViewModel.class);

        if (Build.VERSION.SDK_INT >= 24) {
            AnimatedVectorDrawable logoDrawable = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.logo_ani, this.getActivity().getTheme());
            binding.logoImageView.setImageDrawable(logoDrawable);
            logoAni = ((Animatable)logoDrawable);
            AnimatedVectorDrawableCompat.registerAnimationCallback(logoDrawable, new AniVectorCallback());
        }else{
            AnimatedVectorDrawableCompat logoCompatDrawable = AnimatedVectorDrawableCompat.create(this.getContext(), R.drawable.logo_ani);
            binding.logoImageView.setImageDrawable(logoCompatDrawable);
            logoAni = ((Animatable)logoCompatDrawable);
            AnimatedVectorDrawableCompat.registerAnimationCallback(logoCompatDrawable, new AniVectorCallback());
        }
        logoAni.start();
        return binding.getRoot();
    }



    private class AniVectorCallback extends Animatable2Compat.AnimationCallback{
        public void onAnimationEnd(Drawable drawable) {
            if(viewModel.getNetworkState() == BannerNetworkViewModel.NetworkState.AVAILABE){
                viewModel.getNavController().navigate(R.id.show_online);
            }else{
                viewModel.getNavController().navigate(R.id.show_offline);
            }
        }
    }




}
