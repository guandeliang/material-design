/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-19 上午9:42
 *
 */

package com.jacob.book.material.example.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;

import com.google.android.material.tabs.TabLayoutMediator;
import com.jacob.book.WidgetsUtils;
import com.jacob.book.material.R;
import com.jacob.book.material.base.TabBaseAdapter;
import com.jacob.book.material.base.TabBaseFragment;
import com.jacob.book.material.databinding.TabBaseMainFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class TabBaseMainFragment extends Fragment implements LifecycleObserver {
    private TabBaseMainFragmentBinding binding;
    private List<TabBaseFragment> fragmentList;

    public TabBaseMainFragment(){
        this.getLifecycle().addObserver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.tab_base_main_fragment, container, false);

        binding.toolbar.setNavigationIcon(R.drawable.icon_menu);
        binding.toolbar.getNavigationIcon().setTint(WidgetsUtils.getColorValue(this.getActivity(), android.R.attr.textColorPrimaryInverse));

        fragmentList = new ArrayList<>();
        fragmentList.add(new TabBaseMainIndexFragment());
        fragmentList.add(new TabBaseMainLibraryFragment());
        fragmentList.add(new TabBaseMainMyFragment());
        TabBaseAdapter adapter = new TabBaseAdapter(getActivity(), fragmentList);
        binding.viewPager.setAdapter(adapter);

        TabLayoutMediator mediator = new TabLayoutMediator(
                binding.tabLayout,
                binding.viewPager,
                new TabBaseFragment.ConfigurationStrategy(fragmentList)
        );
        mediator.attach();


        return binding.getRoot();
    }

}
