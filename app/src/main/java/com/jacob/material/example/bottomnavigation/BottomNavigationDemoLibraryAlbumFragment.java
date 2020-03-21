/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-17 上午10:45
 *
 */

package com.jacob.material.example.bottomnavigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jacob.utils.JsonUtils;
import com.jacob.utils.WidgetsUtils;
import com.jacob.material.R;
import com.jacob.material.base.TabItemContent;
import com.jacob.material.base.TabViewPagerBaseFragment;
import com.jacob.material.databinding.BottomNavigationDemoLibraryAlbumFragmentBinding;
import com.jacob.material.example.adapter.GrammyLibraryAlbumAdapter;
import com.jacob.material.example.model.Grammy;
import com.jacob.material.widgets.LinearLayoutVertialItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class BottomNavigationDemoLibraryAlbumFragment extends TabViewPagerBaseFragment {
    private BottomNavigationDemoLibraryAlbumFragmentBinding binding;

    public BottomNavigationDemoLibraryAlbumFragment(){
        super(new TabItemContent("经典唱片", -1));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_navigation_demo_library_album_fragment, container, false);

        initAlbumList();
        return binding.getRoot();
    }

    private void initAlbumList(){
        List<Grammy> allList = JsonUtils.loadGrammy(getResources());
        List<Grammy> librarylList = new ArrayList<>();
        for(Grammy grammy:allList){
            if("album".equals(grammy.getCategory())){
                librarylList.add(grammy);
            }
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);

        int px_08 = WidgetsUtils.dpToPx(getContext(), 8);
        int px_16 = WidgetsUtils.dpToPx(getContext(), 16);

        LinearLayoutVertialItemDecoration decoration = new LinearLayoutVertialItemDecoration(px_16, px_16, px_08);
        binding.recyclerView.addItemDecoration(decoration);

        GrammyLibraryAlbumAdapter adapter = new GrammyLibraryAlbumAdapter(librarylList);

        View header = View.inflate(this.getContext(), R.layout.grammy_library_header, null);
        adapter.setHeaderView(header);



        binding.recyclerView.setAdapter(adapter);
    }


}
