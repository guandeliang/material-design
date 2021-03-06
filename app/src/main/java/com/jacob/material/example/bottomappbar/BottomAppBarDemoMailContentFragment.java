/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-16 下午2:16
 *
 */

package com.jacob.material.example.bottomappbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.jacob.material.R;
import com.jacob.material.databinding.BottomAppBarDemoMailContentFragmentBinding;
import com.jacob.material.example.model.Mail;

public class BottomAppBarDemoMailContentFragment extends Fragment implements LifecycleObserver {
    public static String PARAM_MAIL_ITEM = "PARAM_MAIL_ITEM";

    private BottomAppBarDemoMailContentFragmentBinding binding;
    private BottomAppBarDemoViewModel viewModel;
    private Mail mail;

    public BottomAppBarDemoMailContentFragment(){
        this.getLifecycle().addObserver(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_app_bar_demo_mail_content_fragment, container, false);
        mail = (Mail)getArguments().getSerializable(PARAM_MAIL_ITEM);
        viewModel = new ViewModelProvider(getActivity()).get(BottomAppBarDemoViewModel.class);

        int headerResId = getContext().getResources().getIdentifier(mail.getHeaderFile(), "drawable", getContext().getPackageName());
        binding.headerImageView.setImageResource(headerResId);

        binding.backImageView.setOnClickListener(new OnBackImageClickListener());

        return binding.getRoot();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private void onLifecycleStart(){
        viewModel.getFragmentLiveData().postValue(BottomAppBarDemoViewModel.FragmentEnum.MAIL_CONTENT);
    }


    private class OnBackImageClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            Navigation.findNavController(BottomAppBarDemoMailContentFragment.this.getActivity(), R.id.nav_host_fragment).popBackStack();
        }
    }

}
