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
public class TabBaseFragment extends Fragment {
    private String title;
    private int iconRecId;
    private ViewPager2 parentViewPager2;

    /*
     * parentViewPager2 传入Fragment是为了处理同向滑动元件问题
     * 如果上级不是ViewPager2
     * 或者没有同向滑动元件
     * 可以不传递本参数
     */
    public TabBaseFragment(String title, int iconRecId) {
        this.title = title;
        this.iconRecId = iconRecId;
    }

    public String getTitle(){
        return this.title;
    }

    public int getIconRecId() {
        return iconRecId;
    }


    public static class ConfigurationStrategy implements TabLayoutMediator.TabConfigurationStrategy{
        private List<TabBaseFragment> fragmentList;
        public ConfigurationStrategy(List<TabBaseFragment> fragmentList){
            this.fragmentList = fragmentList;
        }

        @Override
        public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
            tab.setText(fragmentList.get(position).getTitle());
            if(fragmentList.get(position).getIconRecId() > 0){
                tab.setIcon(fragmentList.get(position).getIconRecId());
            }
        }
    }

}
