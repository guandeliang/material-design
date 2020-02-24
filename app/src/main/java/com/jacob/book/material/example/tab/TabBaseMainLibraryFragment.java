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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jacob.book.JsonUtils;
import com.jacob.book.WidgetsUtils;
import com.jacob.book.material.R;
import com.jacob.book.material.base.TabBaseFragment;
import com.jacob.book.material.databinding.TabBaseMainLibraryFragmentBinding;
import com.jacob.book.material.example.adapter.BookAdapter;
import com.jacob.book.material.example.model.Book;
import com.jacob.book.material.widgets.GridLayoutVertialItemDecoration;

import java.util.List;

public class TabBaseMainLibraryFragment extends TabBaseFragment implements LifecycleObserver {
    private TabBaseMainLibraryFragmentBinding binding;

    public TabBaseMainLibraryFragment(){
        super("书目", R.drawable.icon_photo_library);
        this.getLifecycle().addObserver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.tab_base_main_library_fragment, container, false);
        initBookList();
        return binding.getRoot();
    }

    private void initBookList(){
        List<Book> list = JsonUtils.loadBooks(getResources());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);
        int px_08 = WidgetsUtils.dpToPx(getContext(), 8);
        GridLayoutVertialItemDecoration decoration = new GridLayoutVertialItemDecoration(px_08, px_08, px_08, px_08);
        binding.recyclerView.addItemDecoration(decoration);

        BookAdapter adapter = new BookAdapter(list, -1, 0);

        binding.recyclerView.setAdapter(adapter);
    }
}
