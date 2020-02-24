/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-19 上午9:42
 *
 */

package com.jacob.book.material.example.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

import com.jacob.book.material.R;
import com.jacob.book.material.base.TabBaseFragment;
import com.jacob.book.material.databinding.TabBaseMainIndexFragmentBinding;

public class TabBaseMainIndexFragment extends TabBaseFragment implements LifecycleObserver {
    private TabBaseMainIndexFragmentBinding binding;

    public TabBaseMainIndexFragment(){
        super("首页", R.drawable.icon_home);
        this.getLifecycle().addObserver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.tab_base_main_index_fragment, container, false);
        initBanner();
        return binding.getRoot();
    }

    private void initBanner(){

        binding.cardViewSlider.setParentHorizontalScrallable(true);//上级可以横向滚动
        binding.cardViewSlider.setImageResIds(
                R.drawable.book_001_001,
                R.drawable.book_001_002,
                R.drawable.book_001_003,
                R.drawable.book_001_004,
                R.drawable.book_001_005
        );
    }



    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onLifecycleDistory(){
        binding.cardViewSlider.dispose();
    }

}
