/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-16 下午10:32
 *
 */

package com.jacob.material.base;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.List;

public class TabViewPagerAdapter extends FragmentStateAdapter {
    private List<TabViewPagerBaseFragment> fragmentList;

    public TabViewPagerAdapter(FragmentActivity activity, List<TabViewPagerBaseFragment> fragmentList){
        super(activity);
        this.fragmentList = fragmentList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getItemCount() {
        return fragmentList.size();
    }



}
