/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-17 下午1:06
 *
 */

package com.jacob.material.example.fab;

import android.os.Bundle;
import android.transition.ArcMotion;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;

import com.google.android.material.transition.MaterialContainerTransform;
import com.jacob.material.R;
import com.jacob.material.databinding.FabPhoneCallFragmentBinding;
import com.jacob.material.example.model.AddressBook;
import com.jacob.utils.WidgetsUtils;

public class FabPhoneCallFragment extends Fragment implements LifecycleObserver {
    public static String PARAM_ADDRESS_BOOK = "PARAM_ADDRESS_BOOK";
    public static String PARAM_TRANSITION_TO_FULL = "PARAM_TRANSITION_TO_FULL";
    public static String PARAM_TRANSITION_TO_IMAGE = "PARAM_TRANSITION_TO_IMAGE";

    private FabPhoneCallFragmentBinding binding;


    public FabPhoneCallFragment(){
        this.getLifecycle().addObserver(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fab_phone_call_fragment, container, false);

        AddressBook addressBook = (AddressBook)getArguments().getSerializable(PARAM_ADDRESS_BOOK);
        if(addressBook.getId() > 0){
            int bookImageResId = getContext().getResources().getIdentifier(addressBook.getFileName(), "drawable", getContext().getPackageName());
            binding.headerImageView.setImageResource(bookImageResId);
            binding.titleTextView.setText(addressBook.getTitle());
            binding.addressTextView.setText(addressBook.getMobile() + " 黑龙江哈尔滨 电信");
        }else{
            binding.titleTextView.setText(addressBook.getMobile());
            binding.addressTextView.setText("黑龙江哈尔滨 电信");
        }

        String toFullTransitionName = getArguments().getString(PARAM_TRANSITION_TO_FULL);
        binding.coordinatorLayout.setTransitionName(toFullTransitionName);
        String toImageTransitionName = getArguments().getString(PARAM_TRANSITION_TO_IMAGE);
        binding.headerImageView.setTransitionName(toImageTransitionName);

        if(toFullTransitionName != null | toImageTransitionName != null){
            MaterialContainerTransform transform = new MaterialContainerTransform();
            //transform.addTarget(binding.coordinatorLayout);//这句不能加，加了就出问题，
            transform.setDuration(500);
            transform.setPathMotion(new ArcMotion());
            transform.setScrimColor(WidgetsUtils.getColorValue(getContext(), R.attr.scrimBackground));
            this.setSharedElementEnterTransition(transform);
            this.setSharedElementReturnTransition(transform);
        }





        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
