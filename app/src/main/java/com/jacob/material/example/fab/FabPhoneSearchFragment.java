/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-17 下午1:06
 *
 */

package com.jacob.material.example.fab;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jacob.material.R;
import com.jacob.material.databinding.FabPhoneSearchFragmentBinding;
import com.jacob.material.example.adapter.SortableAddressBookAdapter;
import com.jacob.material.example.model.AddressBook;
import com.jacob.material.widgets.LinearLayoutVertialItemDecoration;
import com.jacob.utils.JsonUtils;
import com.jacob.utils.WidgetsUtils;

import java.util.ArrayList;
import java.util.List;

public class FabPhoneSearchFragment extends Fragment implements LifecycleObserver {
    private FabPhoneSearchFragmentBinding binding;
    private FabPhoneViewModel viewModel;
    private List<AddressBook> list;
    private SortableAddressBookAdapter adapter;

    public FabPhoneSearchFragment(){
        this.getLifecycle().addObserver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fab_phone_search_fragment, container, false);
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

        viewModel = new ViewModelProvider(this.getActivity()).get(FabPhoneViewModel.class);
        binding.setFragment(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);

        int px_24 = WidgetsUtils.dpToPx(getContext(), 24);
        LinearLayoutVertialItemDecoration decoration = new LinearLayoutVertialItemDecoration(0, px_24, 0);
        binding.recyclerView.addItemDecoration(decoration);

        list = JsonUtils.loadAddressBooks(getResources());
        adapter = new SortableAddressBookAdapter(list, new OnAddressBookItemClickListener());

        binding.recyclerView.setAdapter(adapter);

        return binding.getRoot();
    }


    private class OnAddressBookItemClickListener implements SortableAddressBookAdapter.OnItemClickListener{
        @Override
        public void onItemClick(SortableAddressBookAdapter.AddressBookViewHolder holder, AddressBook addressBook) {
            //需要实现从ITEM 到全屏，同时共享图片的转换
            //键盘的现实状态会影响动画效果
            //无论是否显示，都关闭键盘，并且返回执行关闭操作之间，键盘是否处于显示状态
            //如果关闭之前，键盘处于显示状态，则返回true，这种情况下不执行过渡动画
            boolean isKeyboardVisibile = dismissKeyboard();


            Bundle bundle = new Bundle();
            bundle.putSerializable(FabPhoneCallFragment.PARAM_ADDRESS_BOOK, addressBook);
            bundle.putString(FabPhoneCallFragment.PARAM_TRANSITION_TO_IMAGE, holder.getHeaderImageView().getTransitionName());
            bundle.putString(FabPhoneCallFragment.PARAM_TRANSITION_TO_FULL, holder.getRoot().getTransitionName());

            FragmentNavigator.Extras extras = null;
            if(!isKeyboardVisibile){//如果不是刚刚关闭键盘
                extras = new FragmentNavigator.Extras.Builder()
                        .addSharedElement(holder.getHeaderImageView(), holder.getHeaderImageView().getTransitionName())
                        .addSharedElement(holder.getRoot(), holder.getRoot().getTransitionName()).build();

            }
            viewModel.getNavController().navigate(R.id.show_call, bundle, null,extras);
        }
    }


    public void onSearchTextChanged(CharSequence text) {
        List<AddressBook> addList = new ArrayList<>();
        List<AddressBook> removeList = new ArrayList<>();

        String searchText = text.toString();
        if(searchText == null || searchText.length() == 0){
            addList.addAll(list);
        }else{
            for(AddressBook ab:list){
                if(ab.getTitle().indexOf(searchText) >=0 || ab.getMobile().indexOf(searchText) >=0){
                    addList.add(ab);
                }else{
                    removeList.add(ab);
                }
            }
        }
        adapter.setData(addList, removeList);
    }


    public void onBackClick(View v) {
        viewModel.setAction(FabPhoneViewModel.Action.BACK);
    }

    public boolean dismissKeyboard(){
        InputMethodManager inputMethodManager =(InputMethodManager)this.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        return inputMethodManager.hideSoftInputFromWindow(binding.searchEditText.getWindowToken(), 0);
    }


}
