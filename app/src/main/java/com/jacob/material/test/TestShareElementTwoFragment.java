/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-16 下午2:45
 *
 */

package com.jacob.material.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.transition.TransitionInflater;

import com.jacob.material.R;
import com.jacob.material.databinding.TestShareElementTwoFragmentBinding;

public class TestShareElementTwoFragment extends Fragment implements LifecycleObserver {
    private TestShareElementTwoFragmentBinding binding;

    public TestShareElementTwoFragment(){
        this.getLifecycle().addObserver(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.test_share_element_two_fragment, container, false);

        this.setSharedElementEnterTransition(TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move).setDuration(200));


        return binding.getRoot();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
