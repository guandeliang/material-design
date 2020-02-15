package com.jacob.book.material.example.bottomnavigation;

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
import com.jacob.book.material.databinding.BottomNavigationDemoLibraryFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class BottomNavigationDemoLibraryFragment extends Fragment implements LifecycleObserver {
    private BottomNavigationDemoLibraryFragmentBinding binding;
    private List<TabBaseFragment> fragmentList;

    public BottomNavigationDemoLibraryFragment(){
        this.getLifecycle().addObserver(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_navigation_demo_library_fragment, container, false);

        WidgetsUtils.setSystemBarColor(this.getActivity(), R.color.gray_50);
        WidgetsUtils.setSystemBarLight(this.getActivity());

        fragmentList = new ArrayList<>();
        fragmentList.add(new BottomNavigationDemoLibraryAlbumFragment());
        fragmentList.add(new BottomNavigationDemoLibraryArtistFragment());
        fragmentList.add(new BottomNavigationDemoLibraryLiveFragment());

        TabBaseAdapter adapter = new TabBaseAdapter(this.getActivity(), fragmentList);
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
