/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-10 下午3:36
 *
 */

package com.jacob.book.material.base;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabViewPagerBaseFragment extends Fragment {
    private TabItemContent tabItemContent;

    public TabViewPagerBaseFragment(TabItemContent tabItemContent) {
        this.tabItemContent = tabItemContent;
    }

    public TabItemContent getTabItemContent(){
        return this.tabItemContent;
    }


    public static class ConfigurationStrategy implements TabLayoutMediator.TabConfigurationStrategy{
        private List<TabViewPagerBaseFragment> fragmentList;
        public ConfigurationStrategy(List<TabViewPagerBaseFragment> fragmentList){
            this.fragmentList = fragmentList;
        }

        @Override
        public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
            tab.setText(fragmentList.get(position).getTabItemContent().getTitle());
            if(fragmentList.get(position).getTabItemContent().getIconRecId() > 0){
                tab.setIcon(fragmentList.get(position).getTabItemContent().getIconRecId());
            }
        }
    }

}
