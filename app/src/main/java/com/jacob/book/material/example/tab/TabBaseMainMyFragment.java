/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-23 下午8:55
 *
 */

package com.jacob.book.material.example.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleObserver;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jacob.book.JsonUtils;
import com.jacob.book.WidgetsUtils;
import com.jacob.book.material.R;
import com.jacob.book.material.base.TabItemContent;
import com.jacob.book.material.base.TabViewPagerBaseFragment;
import com.jacob.book.material.databinding.TabBaseMainMyFragmentBinding;
import com.jacob.book.material.example.adapter.BookAdapter;
import com.jacob.book.material.example.model.Book;
import com.jacob.book.material.widgets.HorizontalEdgeSnapHelper;
import com.jacob.book.material.widgets.LinearLayoutHorizontalItemDecoration;
import com.jacob.book.material.widgets.NestedScrollableHost;

import java.util.List;

public class TabBaseMainMyFragment extends TabViewPagerBaseFragment implements LifecycleObserver {
    private TabBaseMainMyFragmentBinding binding;

    public TabBaseMainMyFragment(){
        super(new TabItemContent("我的", -1));
        this.getLifecycle().addObserver(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.tab_base_main_my_fragment, container, false);
        initBookList();
        return binding.getRoot();
    }


    private void initBookList(){
        List<Book> list = JsonUtils.loadBooks(getResources());

        binding.recyclerViewHost.setOrientation(NestedScrollableHost.HORIZONTAL);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);
        LinearLayoutHorizontalItemDecoration decoration = new LinearLayoutHorizontalItemDecoration(0, 0, 0, 0, WidgetsUtils.dpToPx(getContext(), 8));
        binding.recyclerView.addItemDecoration(decoration);

        HorizontalEdgeSnapHelper snapHelper = new HorizontalEdgeSnapHelper();
        snapHelper.attachToRecyclerView(binding.recyclerView);

        BookAdapter adapter = new BookAdapter(list, 2.5f, WidgetsUtils.dpToPx(getContext(), 8));

        binding.recyclerView.setAdapter(adapter);
    }

}
