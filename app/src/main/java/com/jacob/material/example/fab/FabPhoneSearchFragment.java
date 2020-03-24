/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-17 下午1:06
 *
 */

package com.jacob.material.example.fab;

import android.content.Context;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.TransitionManager;
import android.transition.Visibility;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.tabs.TabLayoutMediator;
import com.google.android.material.transition.FadeThrough;
import com.google.android.material.transition.MaterialArcMotion;
import com.google.android.material.transition.MaterialContainerTransform;
import com.google.android.material.transition.MaterialFade;
import com.google.android.material.transition.MaterialFadeThrough;
import com.jacob.material.R;
import com.jacob.material.base.TabViewPagerAdapter;
import com.jacob.material.base.TabViewPagerBaseFragment;
import com.jacob.material.databinding.FabPhoneAddressFragmentBinding;
import com.jacob.material.databinding.FabPhoneSearchFragmentBinding;
import com.jacob.material.example.adapter.AddressBookAdapter;
import com.jacob.material.example.adapter.GeneralAdapterOne;
import com.jacob.material.example.adapter.SortableAddressBookAdapter;
import com.jacob.material.example.backdrop.BackdropCraneMainActivity;
import com.jacob.material.example.model.AddressBook;
import com.jacob.material.widgets.LinearLayoutVertialItemDecoration;
import com.jacob.temp.TempConstant;
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
        this.startPostponedEnterTransition();

        binding = DataBindingUtil.inflate(inflater, R.layout.fab_phone_search_fragment, container, false);
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

            Bundle bundle = new Bundle();
            bundle.putSerializable(FabPhoneCallFragment.PARAM_ADDRESS_BOOK, addressBook);
            bundle.putString(FabPhoneCallFragment.PARAM_TRANSITION_TO_FULL, FabPhoneViewModel.TRANSITION_FAB_TO_FULL);

            binding.searchCardView.setTransitionName(FabPhoneViewModel.TRANSITION_FAB_TO_FULL);
            FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                    .addSharedElement(binding.searchCardView, FabPhoneViewModel.TRANSITION_FAB_TO_FULL)
                    .build();

            viewModel.getNavController().navigate(R.id.show_call, bundle, null, extras);


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



    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void onLifecycleResume(){
        if(binding.searchEditText.requestFocus()){
            InputMethodManager imm =(InputMethodManager)this.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(binding.searchEditText, InputMethodManager.SHOW_IMPLICIT);
        }
    }

}
