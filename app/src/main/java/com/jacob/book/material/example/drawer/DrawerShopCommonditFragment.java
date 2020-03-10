/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-3-5 下午11:21
 *
 */

package com.jacob.book.material.example.drawer;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.chad.library.BR;
import com.jacob.book.JsonUtils;
import com.jacob.book.material.R;
import com.jacob.book.material.base.LivelyShopViewModel;
import com.jacob.book.material.databinding.DrawerShopCommonditFragmentBinding;
import com.jacob.book.material.example.adapter.LivelyCommoditAdapter;
import com.jacob.book.material.example.model.Commodit;

import java.util.ArrayList;
import java.util.List;

public class DrawerShopCommonditFragment extends Fragment implements LifecycleObserver {
    private DrawerShopCommonditFragmentBinding binding;
    private LivelyShopViewModel viewModel;
    private LivelyCommoditAdapter adapter;

    public DrawerShopCommonditFragment(){
        this.getLifecycle().addObserver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.drawer_shop_commondit_fragment, container, false);
        viewModel = new ViewModelProvider(getActivity()).get(LivelyShopViewModel.class);
        viewModel.addOnPropertyChangedCallback(new ViewModelPropertyChangedCallback());


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2, GridLayoutManager.HORIZONTAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position % 3 == 2 ? 2 : 1;
            }
        });

        binding.recyclerView.setLayoutManager(gridLayoutManager);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.dp_4)*6;
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.dp_4)*4;
        DrawerShopItemDecoration decoration = new DrawerShopItemDecoration(largePadding, smallPadding);
        binding.recyclerView.addItemDecoration(decoration);
        adapter = new LivelyCommoditAdapter(getContext(), new ArrayList<>());
        binding.recyclerView.setAdapter(adapter);

        viewModel.setCurrCategory(LivelyShopViewModel.Category.CLTHES);

        return binding.getRoot();
    }

    private class ViewModelPropertyChangedCallback extends Observable.OnPropertyChangedCallback {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
            if(propertyId != BR.currCategory){
                return;
            }
            LivelyShopViewModel.Category currCategory = viewModel.getCurrCategory();
            List<Commodit> commoditList = getCommoditList(currCategory.getId());
            if(adapter != null){
                adapter.setDataList(commoditList);
                binding.recyclerView.scrollToPosition(0);
            }
        }
    }

    private List<Commodit> getCommoditList(int category){
        List<Commodit> commoditList = JsonUtils.loadCommodits(getResources());
        List<Commodit> result = new ArrayList<>();
        for(Commodit c:commoditList){
            if(category == c.getCategory()){
                result.add(c);
            }
        }
        return result;
    }





}
