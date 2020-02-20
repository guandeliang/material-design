/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-15 下午7:10
 *
 */

package com.jacob.book.material.test;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;

import com.jacob.book.material.R;
import com.jacob.book.material.databinding.TestShareElementOneFragmentBinding;

public class TestShareElementOneFragment extends Fragment implements LifecycleObserver {
    private TestShareElementOneFragmentBinding binding;

    public TestShareElementOneFragment(){
        this.getLifecycle().addObserver(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.test_share_element_one_fragment, container, false);
        binding.imageView.setOnClickListener(new OnImageViewClickListener());
        return binding.getRoot();
    }

    private class OnImageViewClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                    .addSharedElement(binding.imageView, "image_view")
                    .build();

            Navigation.findNavController(getActivity(),  R.id.nav_host_fragment).navigate(R.id.show_two, null, null, extras);

        }
    }



}
