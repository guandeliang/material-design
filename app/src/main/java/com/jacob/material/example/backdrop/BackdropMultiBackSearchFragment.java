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
import android.widget.TextView;

import androidx.constraintlayout.helper.widget.Layer;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ViewModelProvider;

import com.jacob.material.R;
import com.jacob.material.databinding.BackdropMultiBackSearchFragmentBinding;

public class BackdropMultiBackSearchFragment extends Fragment implements LifecycleObserver {
    private BackdropMultiBackSearchFragmentBinding binding;
    private BackdropMulitBackViewModel viewModel;

    public BackdropMultiBackSearchFragment(){
        this.getLifecycle().addObserver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.backdrop_multi_back_search_fragment, container, false);
        viewModel = new ViewModelProvider(getActivity()).get(BackdropMulitBackViewModel.class);
        binding.setFragment(this);
        binding.setViewModel(viewModel);

        ViewTreeObserver viewTreeObserver = binding.nestedScrollView.getViewTreeObserver();
        if(viewTreeObserver.isAlive()){
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    binding.nestedScrollView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    int scrollViewHeight = binding.nestedScrollView.getHeight();
                    int contentViewHeight = binding.constraintLayout.getHeight();
                    viewModel.setSearchBottomRestSpace(scrollViewHeight - contentViewHeight);
                }
            });
        }
        return binding.getRoot();
    }

    public void onLayerClick(View view){
        if(view instanceof Layer){
            Layer layer = (Layer)view;
            int[] referencedIds = layer.getReferencedIds();
            for(int id : referencedIds){
                View referencedView = binding.getRoot().findViewById(id);
                if(referencedView != null && referencedView instanceof TextView){
                    TextView textView = (TextView)referencedView;
                    binding.searchEditText.setText(textView.getText());
                }
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
