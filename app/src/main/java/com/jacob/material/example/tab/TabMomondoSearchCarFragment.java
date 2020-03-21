/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-29 下午3:51
 *
 */

package com.jacob.material.example.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleObserver;

import com.google.android.material.tabs.TabLayout;
import com.jacob.material.R;
import com.jacob.material.base.TabItemContent;
import com.jacob.material.base.TabViewPagerBaseFragment;
import com.jacob.material.databinding.TabMomondoSearchCarFragmentBinding;

public class TabMomondoSearchCarFragment extends TabViewPagerBaseFragment implements LifecycleObserver {
    private TabMomondoSearchCarFragmentBinding binding;

    public TabMomondoSearchCarFragment(){
        super(new TabItemContent("租车", R.drawable.momondo_icon_car));
        this.getLifecycle().addObserver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.tab_momondo_search_car_fragment, container, false);
        binding.setShowTo(false);
        binding.tabLayout.selectTab(binding.tabLayout.getTabAt(0));
        binding.tabLayout.addOnTabSelectedListener(new CategoryTabSelectedListener());

/*
        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.mask.animate().scaleX(3f).scaleY(3f) .setDuration(2000);


                //TransitionDrawable drawable = (TransitionDrawable)binding.mask.getBackground();
                //drawable.startTransition(2000);


            }
        });
*/
        return binding.getRoot();
    }

    private class CategoryTabSelectedListener implements TabLayout.OnTabSelectedListener {

        @Override
        public void onTabSelected(TabLayout.Tab tab) {
            int position = binding.tabLayout.getSelectedTabPosition();
            if(position == 0){
                binding.setShowTo(false);
            }else if(position == 1){
                binding.setShowTo(true);
            }
        }

        @Override
        public void onTabUnselected(TabLayout.Tab tab) {}

        @Override
        public void onTabReselected(TabLayout.Tab tab) {}
    }



}
