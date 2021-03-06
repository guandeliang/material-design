/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-3-5 下午11:27
 *
 */

package com.jacob.material.example.backdrop;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ViewModelProvider;

import com.jacob.material.R;
import com.jacob.material.databinding.BackdropMultiBackCategoryFragmentBinding;

public class BackdropMultiBackCategoryFragment extends Fragment implements LifecycleObserver {
    private BackdropMultiBackCategoryFragmentBinding binding;
    private BackdropMulitBackViewModel viewModel;

    public BackdropMultiBackCategoryFragment(){
        this.getLifecycle().addObserver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.backdrop_multi_back_category_fragment, container, false);
        viewModel = new ViewModelProvider(getActivity()).get(BackdropMulitBackViewModel.class);
        binding.setFragment(this);

        ViewTreeObserver viewTreeObserver = binding.nestedScrollView.getViewTreeObserver();
        if(viewTreeObserver.isAlive()){
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    binding.nestedScrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    int scrollViewHeight = binding.nestedScrollView.getHeight();
                    int contentViewHeight = binding.constraintLayout.getHeight();
                    viewModel.setCategoryBottomRestSpace(scrollViewHeight - contentViewHeight);
                }
            });
        }
        return binding.getRoot();
    }

    public void onButtonClick(View view){
        if(view.getId() == R.id.ok_button){
            viewModel.setBackAction(BackdropMulitBackViewModel.BackAction.CATEGORY_OK);
        }
        if(view.getId() == R.id.clear_button){
            viewModel.setBackAction(BackdropMulitBackViewModel.BackAction.CATEGORY_CLEAR);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
