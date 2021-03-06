/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-17 下午1:06
 *
 */

package com.jacob.material.example.bottomnavigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleObserver;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.jacob.utils.JsonUtils;
import com.jacob.utils.WidgetsUtils;
import com.jacob.material.R;
import com.jacob.material.base.TabItemContent;
import com.jacob.material.base.TabViewPagerBaseFragment;
import com.jacob.material.databinding.BottomNavigationDemoLibraryLiveFragmentBinding;
import com.jacob.material.example.adapter.GrammyLibraryLiveAdapter;
import com.jacob.material.example.model.Thrones;
import com.jacob.material.widgets.GridLayoutVertialItemDecoration;

import java.util.List;

public class BottomNavigationDemoLibraryLiveFragment extends TabViewPagerBaseFragment implements LifecycleObserver {
    private BottomNavigationDemoLibraryLiveFragmentBinding binding;

    public BottomNavigationDemoLibraryLiveFragment(){
        super(new TabItemContent("在线直播", -1));
        this.getLifecycle().addObserver(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_navigation_demo_library_live_fragment, container, false);

        //下面代码是为了了把动画传递到RecyclerView中
        this.postponeEnterTransition();
        binding.recyclerView.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener(){
                    @Override
                    public boolean onPreDraw() {
                        binding.recyclerView.getViewTreeObserver().removeOnPreDrawListener(this);
                        startPostponedEnterTransition();//没有这个行代码就没有动画效果
                        return false;
                    }
                }
        );


        initLiveList();
        return binding.getRoot();
    }


    private void initLiveList(){

        List<Thrones> list = JsonUtils.loadThrones(getResources());

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);

        int px_08 = WidgetsUtils.dpToPx(getContext(), 8);
        int px_16 = WidgetsUtils.dpToPx(getContext(), 16);
        GridLayoutVertialItemDecoration decoration = new GridLayoutVertialItemDecoration(px_16, px_16, px_08, px_08);
        binding.recyclerView.addItemDecoration(decoration);

        GrammyLibraryLiveAdapter adapter = new GrammyLibraryLiveAdapter(list);
        View header = View.inflate(this.getContext(), R.layout.grammy_library_header, null);
        adapter.setHeaderView(header);

        adapter.setOnItemClickListener(new OnLibraryItemClickListener());

        binding.recyclerView.setAdapter(adapter);
    }

    private class OnLibraryItemClickListener implements OnItemClickListener {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            Bundle bundle = new Bundle();
            GrammyLibraryLiveAdapter liveAdapter = (GrammyLibraryLiveAdapter)adapter;
            Thrones thrones = liveAdapter.getData().get(position);
            bundle.putSerializable(BottomNavigationDemoLibraryDetailFragment.PARAM_THERONES_ITEM, thrones);

            ImageView shareImageView = view.findViewById(R.id.image_view);
            FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                    .addSharedElement(shareImageView, shareImageView.getTransitionName())
                    .build();

            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).navigate(R.id.show_detail, bundle, null, extras);
        }
    }



}
