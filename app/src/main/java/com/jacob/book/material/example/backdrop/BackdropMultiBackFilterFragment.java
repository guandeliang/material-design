/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-3-11 上午8:41
 *
 */

package com.jacob.book.material.example.backdrop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;

import com.jacob.book.material.R;
import com.jacob.book.material.databinding.BackdropMultiBackFilterFragmentBinding;

public class BackdropMultiBackFilterFragment extends Fragment implements LifecycleObserver {
    private BackdropMultiBackFilterFragmentBinding binding;

    public BackdropMultiBackFilterFragment(){
        this.getLifecycle().addObserver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.backdrop_multi_back_filter_fragment, container, false);
        binding.setFragment(this);
        return binding.getRoot();
    }

    public void onColorClick(View view){
        view.setSelected(!view.isSelected());
    }
}
