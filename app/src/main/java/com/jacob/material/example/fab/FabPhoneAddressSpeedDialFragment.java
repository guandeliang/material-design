/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-28 下午10:04
 *
 */

package com.jacob.material.example.fab;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.jacob.material.R;
import com.jacob.material.base.TabItemContent;
import com.jacob.material.base.TabViewPagerBaseFragment;
import com.jacob.material.databinding.FabPhoneAddressSpeedDialFragmentBinding;
import com.jacob.material.example.adapter.AddressBookAdapter;
import com.jacob.material.example.model.AddressBook;
import com.jacob.material.widgets.GridLayoutVertialItemDecoration;
import com.jacob.temp.TempConstant;
import com.jacob.utils.JsonUtils;
import com.jacob.utils.WidgetsUtils;

import java.util.ArrayList;
import java.util.List;

public class FabPhoneAddressSpeedDialFragment extends TabViewPagerBaseFragment implements LifecycleObserver {
    private FabPhoneAddressSpeedDialFragmentBinding binding;
    private FabPhoneViewModel viewModel;

    public FabPhoneAddressSpeedDialFragment(){
        super(new TabItemContent("快速拨号", -1));
        this.getLifecycle().addObserver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fab_phone_address_speed_dial_fragment, container, false);
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

        List<AddressBook> list = getRecentAddressBooks();

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);

        int px_24 = WidgetsUtils.dpToPx(getContext(), 24);
        GridLayoutVertialItemDecoration decoration = new GridLayoutVertialItemDecoration(px_24, px_24, px_24, px_24);
        binding.recyclerView.addItemDecoration(decoration);

        AddressBookAdapter adapter = new AddressBookAdapter(list);

        adapter.setOnItemClickListener(new OnAddressBookItemClickListener());

        binding.recyclerView.setAdapter(adapter);

        return binding.getRoot();
    }

    private List<AddressBook> getRecentAddressBooks(){
        List<AddressBook> list = JsonUtils.loadAddressBooks(getResources());
        List<AddressBook> result = new ArrayList<>();
        for(AddressBook ab:list){
            if(ab.getId() <= 30){
                result.add(ab);
            }
        }
        return result;
    }


    private class OnAddressBookItemClickListener implements OnItemClickListener {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
            AddressBookAdapter addressBookAdapter = (AddressBookAdapter)adapter;
            ImageView imageView = view.findViewById(R.id.image_view);

            Bundle bundle = new Bundle();
            bundle.putSerializable(FabPhoneCallFragment.PARAM_ADDRESS_BOOK, addressBookAdapter.getData().get(position));
            bundle.putString(FabPhoneCallFragment.PARAM_TRANSITION_TO_IMAGE, imageView.getTransitionName());

            FragmentNavigator.Extras extras =  new FragmentNavigator.Extras.Builder()
                    .addSharedElement(imageView, imageView.getTransitionName()).build();

            viewModel.getNavController().navigate(R.id.show_call, bundle, null,extras);
        }
    }
}
