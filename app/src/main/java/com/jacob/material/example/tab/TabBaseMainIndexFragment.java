/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-19 上午9:42
 *
 */

package com.jacob.material.example.tab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.navigation.Navigation;

import com.jacob.utils.JsonUtils;
import com.jacob.material.R;
import com.jacob.material.base.TabItemContent;
import com.jacob.material.base.TabViewPagerBaseFragment;
import com.jacob.material.databinding.TabBaseMainIndexFragmentBinding;
import com.jacob.material.example.model.Book;

import java.util.List;

public class TabBaseMainIndexFragment extends TabViewPagerBaseFragment implements LifecycleObserver {
    private TabBaseMainIndexFragmentBinding binding;
    private List<Book> bookList;

    public TabBaseMainIndexFragment(){
        super(new TabItemContent("首页", -1));
        this.getLifecycle().addObserver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.tab_base_main_index_fragment, container, false);
        bookList = JsonUtils.loadBooks(getResources());

        binding.cardView001.setOnClickListener(new OnBookClickListener(0));
        binding.cardView002.setOnClickListener(new OnBookClickListener(1));
        binding.cardView003.setOnClickListener(new OnBookClickListener(2));




        initBanner();
        return binding.getRoot();
    }

    private void initBanner(){

        binding.cardViewSlider.setParentHorizontalScrallable(true);//上级可以横向滚动
        binding.cardViewSlider.setImageResIds(
                R.drawable.book_001_001,
                R.drawable.book_001_002,
                R.drawable.book_001_003,
                R.drawable.book_001_004,
                R.drawable.book_001_005
        );
    }


    private class OnBookClickListener implements View.OnClickListener {
        private int position;

        public OnBookClickListener(int position){
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            Bundle bundle = new Bundle();
            if(bookList != null && position >=0 && position < bookList.size()){
                Book book = bookList.get(position);
                bundle.putSerializable(TabBaseDetailFragment.PARAM_BOOK, book);
            }
            Navigation.findNavController(getActivity(),  R.id.nav_host_fragment).navigate(R.id.show_detail, bundle);
        }
    }



    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onLifecycleDistory(){
        binding.cardViewSlider.dispose();
    }

}
