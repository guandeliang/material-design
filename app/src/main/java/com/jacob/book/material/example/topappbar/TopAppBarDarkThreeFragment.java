package com.jacob.book.material.example.topappbar;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.jacob.book.material.R;
import com.jacob.book.material.base.TabBaseFragment;
import com.jacob.book.material.databinding.TopAppBarDarkThreeFragmentBinding;


/**
 * A simple {@link Fragment} subclass.
 */
public class TopAppBarDarkThreeFragment extends TabBaseFragment {

    private TopAppBarDarkThreeFragmentBinding binding;


    public TopAppBarDarkThreeFragment() {
        super("最新用户");
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.top_app_bar_dark_three_fragment, container, false);
        return binding.getRoot();
    }



}
