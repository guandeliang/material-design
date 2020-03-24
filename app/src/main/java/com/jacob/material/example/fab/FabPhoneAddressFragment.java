/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-17 下午1:06
 *
 */

package com.jacob.material.example.fab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.FragmentNavigator;

import com.google.android.material.tabs.TabLayoutMediator;
import com.jacob.material.R;
import com.jacob.material.base.TabViewPagerAdapter;
import com.jacob.material.base.TabViewPagerBaseFragment;
import com.jacob.material.databinding.FabPhoneAddressFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class FabPhoneAddressFragment extends Fragment implements LifecycleObserver {
    private FabPhoneAddressFragmentBinding binding;
    private FabPhoneViewModel viewModel;
    private List<TabViewPagerBaseFragment> fragmentList;



    public FabPhoneAddressFragment(){
        this.getLifecycle().addObserver(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fab_phone_address_fragment, container, false);
        binding.fabButton.setTransitionName(FabPhoneViewModel.TRANSITION_FAB_TO_VIEW);
        this.startPostponedEnterTransition();

        viewModel = new ViewModelProvider(this.getActivity()).get(FabPhoneViewModel.class);
        binding.setFragment(this);

        fragmentList = new ArrayList<>();
        fragmentList.add(new FabPhoneAddressSpeedDialFragment());
        fragmentList.add(new FabPhoneAddressRecentFragment());
        fragmentList.add(new FabPhoneAddressContactsFragment());

        TabViewPagerAdapter adapter = new TabViewPagerAdapter(this.getActivity(), fragmentList);
        binding.viewPager.setAdapter(adapter);

        TabLayoutMediator mediator = new TabLayoutMediator(
                binding.tabLayout,
                binding.viewPager,
                new TabViewPagerBaseFragment.ConfigurationStrategy(fragmentList)
        );
        mediator.attach();


        return binding.getRoot();
    }

    public void onSearchClick(View view){
        viewModel.getNavController().navigate(R.id.show_search);
    }

    public void onDialClick(View view){
        FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                .addSharedElement(binding.fabButton, FabPhoneViewModel.TRANSITION_FAB_TO_VIEW)
                .build();
        viewModel.getNavController().navigate(R.id.show_dial, null, null, extras);
    }

}
