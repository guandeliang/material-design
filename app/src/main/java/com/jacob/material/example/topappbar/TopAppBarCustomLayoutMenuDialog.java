/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-31 下午8:08
 *
 */

package com.jacob.material.example.topappbar;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.jacob.material.R;
import com.jacob.material.databinding.TopAppBarCustomLayoutMenuDialogBinding;


public class TopAppBarCustomLayoutMenuDialog extends DialogFragment {
    private TopAppBarCustomLayoutMenuDialogBinding binding;
    private TopAppBarCustomLayoutViewModel viewModel;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.top_app_bar_custom_layout_menu_dialog, container, false);
        viewModel = new ViewModelProvider(getActivity()).get(TopAppBarCustomLayoutViewModel.class);
        binding.shareGroup.setOnClickListener(new OnShareClickListener());
        binding.favoriteGroup.setOnClickListener(new OnFavoriteClickListener());
        binding.searchGroup.setOnClickListener(new OnSearchClickListener());
        binding.userGroup.setOnClickListener(new OnUserClickListener());
        return binding.getRoot();
    }

    private class OnShareClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            viewModel.setOperator(TopAppBarCustomLayoutViewModel.Operator.SHARE);
            dismiss();
        }
    }

    private class OnFavoriteClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            viewModel.setOperator(TopAppBarCustomLayoutViewModel.Operator.FAVORITE);
            dismiss();
        }
    }

    private class OnSearchClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            viewModel.setOperator(TopAppBarCustomLayoutViewModel.Operator.SEARCH);
            dismiss();
        }
    }

    private class OnUserClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            viewModel.setOperator(TopAppBarCustomLayoutViewModel.Operator.USER);
            dismiss();
        }
    }
}
