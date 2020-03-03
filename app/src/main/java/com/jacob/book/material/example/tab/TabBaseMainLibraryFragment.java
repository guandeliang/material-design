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
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.jacob.book.JsonUtils;
import com.jacob.book.WidgetsUtils;
import com.jacob.book.material.R;
import com.jacob.book.material.base.TabItemContent;
import com.jacob.book.material.base.TabViewPagerBaseFragment;
import com.jacob.book.material.databinding.TabBaseMainLibraryFragmentBinding;
import com.jacob.book.material.example.adapter.BookAdapter;
import com.jacob.book.material.example.model.Book;
import com.jacob.book.material.widgets.GridLayoutVertialItemDecoration;

import java.util.List;

public class TabBaseMainLibraryFragment extends TabViewPagerBaseFragment implements LifecycleObserver {
    private TabBaseMainLibraryFragmentBinding binding;

    public TabBaseMainLibraryFragment(){
        super(new TabItemContent("书目", -1));
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
        int px_16 = WidgetsUtils.dpToPx(getContext(), 16);
        GridLayoutVertialItemDecoration decoration = new GridLayoutVertialItemDecoration(px_16, px_16, px_08, px_08);
        binding.recyclerView.addItemDecoration(decoration);

        BookAdapter adapter = new BookAdapter(list, -1, 0);
        adapter.setOnItemClickListener(new OnBookItemClickListener());

        binding.recyclerView.setAdapter(adapter);
    }

    private class OnBookItemClickListener implements OnItemClickListener {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            Bundle bundle = new Bundle();
            BookAdapter bookAdapter = (BookAdapter)adapter;
            Book book = bookAdapter.getData().get(position);
            bundle.putSerializable(TabBaseDetailFragment.PARAM_BOOK, book);
            Navigation.findNavController(getActivity(),  R.id.nav_host_fragment).navigate(R.id.show_detail, bundle);

        }

    }


}
