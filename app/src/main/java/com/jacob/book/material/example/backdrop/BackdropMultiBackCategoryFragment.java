/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-3-5 下午11:27
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
import com.jacob.book.material.databinding.BackdropMultiBackCategoryFragmentBinding;

public class BackdropMultiBackCategoryFragment extends Fragment implements LifecycleObserver {
    private BackdropMultiBackCategoryFragmentBinding binding;

    public BackdropMultiBackCategoryFragment(){
        this.getLifecycle().addObserver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.backdrop_multi_back_category_fragment, container, false);
        return binding.getRoot();
    }
}
