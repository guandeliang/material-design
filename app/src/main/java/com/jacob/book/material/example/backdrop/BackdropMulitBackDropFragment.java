/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-3-5 下午11:27
 *
 */

package com.jacob.book.material.example.backdrop;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import androidx.databinding.Observable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ViewModelProvider;

import com.jacob.book.material.BR;
import com.jacob.book.material.base.RandomImagePresenter;
import com.jacob.book.material.databinding.BackdropMulitBackDropFragmentBinding;
import com.jacob.book.temp.TempConstant;

public class BackdropMulitBackDropFragment extends Fragment implements LifecycleObserver {
    private BackdropMulitBackDropFragmentBinding binding;
    private BackdropMulitBackViewModel viewModel;

    public BackdropMulitBackDropFragment(){
        this.getLifecycle().addObserver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = BackdropMulitBackDropFragmentBinding.inflate(inflater);
        //binding = DataBindingUtil.inflate(inflater, R.layout.backdrop_mulit_back_drop_fragment, container, false);
        viewModel = new ViewModelProvider(getActivity()).get(BackdropMulitBackViewModel.class);
        viewModel.addOnPropertyChangedCallback(new ViewModelPropertyChangedCallback());

        setRandomData();



        ViewTreeObserver viewTreeObserver = binding.dropConstraintLayout.getViewTreeObserver();
        if(viewTreeObserver.isAlive()){
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    binding.dropConstraintLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    viewModel.setDropViewHeight(binding.dropConstraintLayout.getHeight());
                }
            });
        }


        return binding.getRoot();
    }

    private void setRandomData(){
        binding.include.setH(RandomImagePresenter.ImageViewCategory.H_RECTANGLE);
        binding.include.setS(RandomImagePresenter.ImageViewCategory.SQUARE);
        binding.include.setV(RandomImagePresenter.ImageViewCategory.V_RECTANGLE);
    }


    private class ViewModelPropertyChangedCallback extends Observable.OnPropertyChangedCallback {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
            if(propertyId == BR.isDropDown){
                if(viewModel.isDropDown()){
                    binding.dropContentEventHostFrameLayout.setVisibility(View.VISIBLE);
                }else{
                    binding.dropContentEventHostFrameLayout.setVisibility(View.GONE);
                }
            }
            if(propertyId == BR.backAction){
                setRandomData();
            }

        }
    }


}
