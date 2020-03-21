/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-19 上午9:42
 *
 */

package com.jacob.material.example.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.LinearLayout;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;

import com.google.android.material.tabs.TabLayoutMediator;
import com.jacob.material.R;
import com.jacob.material.base.TabViewPagerAdapter;
import com.jacob.material.base.TabViewPagerBaseFragment;
import com.jacob.material.databinding.TabBaseMainFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class TabBaseMainFragment extends Fragment implements LifecycleObserver {
    private TabBaseMainFragmentBinding binding;
    private List<TabViewPagerBaseFragment> fragmentList;

    public TabBaseMainFragment(){
        this.getLifecycle().addObserver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.tab_base_main_fragment, container, false);
        binding.linearLayout.setOnApplyWindowInsetsListener(new ApplyWindowInsetsListener());

        fragmentList = new ArrayList<>();
        fragmentList.add(new TabBaseMainIndexFragment());
        fragmentList.add(new TabBaseMainLibraryFragment());
        fragmentList.add(new TabBaseMainMyFragment());
        TabViewPagerAdapter adapter = new TabViewPagerAdapter(getActivity(), fragmentList);
        binding.viewPager.setAdapter(adapter);

        TabLayoutMediator mediator = new TabLayoutMediator(
                binding.tabLayout,
                binding.viewPager,
                new TabViewPagerBaseFragment.ConfigurationStrategy(fragmentList)
        );
        mediator.attach();


        return binding.getRoot();
    }

    private class ApplyWindowInsetsListener implements View.OnApplyWindowInsetsListener {
        @Override
        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            int statusBarHeight = windowInsets.getSystemWindowInsetTop();
            binding.statusBarBackgroundView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, statusBarHeight));
            return windowInsets;
        }
    }


}
