/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-10 下午3:36
 *
 */

package com.jacob.book.material.base;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class TabBaseFragment extends Fragment {
    private String title;

    public TabBaseFragment(String title) {
        this.title = title;
    }

    public String getTitle(){
        return this.title;
    }


    public static class ConfigurationStrategy implements TabLayoutMediator.TabConfigurationStrategy{
        private List<TabBaseFragment> fragmentList;
        public ConfigurationStrategy(List<TabBaseFragment> fragmentList){
            this.fragmentList = fragmentList;
        }

        @Override
        public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
            tab.setText(fragmentList.get(position).getTitle());
        }
    }

}
