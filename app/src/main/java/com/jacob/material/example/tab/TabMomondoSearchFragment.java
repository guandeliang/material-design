/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-25 下午2:51
 *
 */

package com.jacob.material.example.tab;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowInsets;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.jacob.material.R;
import com.jacob.material.base.TabViewPagerAdapter;
import com.jacob.material.base.TabViewPagerBaseFragment;
import com.jacob.material.databinding.TabMomondoSearchFragmentBinding;
import com.jacob.temp.TempConstant;

import java.util.ArrayList;
import java.util.List;

public class TabMomondoSearchFragment extends Fragment implements LifecycleObserver {
    private TabMomondoSearchFragmentBinding binding;
    private TabMomondoViewModel viewModel;
    private List<TabViewPagerBaseFragment> fragmentList;
    private TabViewPagerAdapter adapter;

    public TabMomondoSearchFragment(){
        this.getLifecycle().addObserver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.tab_momondo_search_fragment, container, false);
        viewModel = new ViewModelProvider(getActivity()).get(TabMomondoViewModel.class);
        binding.menuImageView.setOnClickListener(new OnMenuClickLIstener());

        binding.linearLayout.setOnApplyWindowInsetsListener(new ApplyWindowInsetsListener());


        fragmentList = new ArrayList<>();
        fragmentList.add(new TabMomondoSearchFlightFragment());
        fragmentList.add(new TabMomondoSearchHotelFragment());
        fragmentList.add(new TabMomondoSearchCarFragment());

        adapter = new TabViewPagerAdapter(this.getActivity(), fragmentList);
        binding.viewPager.setAdapter(adapter);

        TabLayoutMediator mediator = new TabLayoutMediator(
                binding.tabLayout,
                binding.viewPager,
                new MainTabConfigurationStrategy()
        );
        mediator.attach();

        binding.viewPager.setPageTransformer(new ScrollTransformer());


        return binding.getRoot();
    }

    private class OnMenuClickLIstener implements View.OnClickListener{
        public void onClick(View v) {
            viewModel.setAction(TabMomondoViewModel.ACTION_SHOW_DRAWER);
        }

    }

    private class ApplyWindowInsetsListener implements View.OnApplyWindowInsetsListener {
        @Override
        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            int statusBarHeight = windowInsets.getSystemWindowInsetTop();
            binding.statusBarBackgroundView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, statusBarHeight));
            return windowInsets;
        }
    }

    private class MainTabConfigurationStrategy implements TabLayoutMediator.TabConfigurationStrategy {
        @Override
        public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.momondo_search_tab_item, binding.tabLayout, false);
            ImageView imageView = view.findViewById(R.id.image_view);
            imageView.setImageResource(fragmentList.get(position).getTabItemContent().getIconRecId());
            TextView titleTextView = view.findViewById(R.id.title_text_view);
            titleTextView.setText(fragmentList.get(position).getTabItemContent().getTitle());
            tab.setCustomView(view);
        }
    }


    private class ScrollTransformer implements ViewPager2.PageTransformer{
        @Override
        public void transformPage(@NonNull View page, float position) {
            Log.d(TempConstant.LOG_TAG, "position = " + position);
            int pageWidth = page.getWidth();
            int pageHeight = page.getHeight();
            if (position < -1) {
                //page.setAlpha(0f);
            } else if (position <= 1) { // [-1,1]
                float absPosition = Math.abs(position);
                if(position < 0){//page at left
                    page.setPivotX(0);
                    page.setPivotY(pageHeight/2);
                    page.setRotationY(2*absPosition);
                }else if(position > 0){//pege at right
                    page.setPivotX(pageWidth);
                    page.setPivotY(pageHeight/2);
                    page.setRotationY(0- 2*absPosition);
                }
            } else {
                //page.setAlpha(0f);
            }
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
