/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-28 下午10:04
 *
 */

package com.jacob.material.example.fab;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleObserver;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.jacob.material.R;
import com.jacob.material.base.TabItemContent;
import com.jacob.material.base.TabViewPagerBaseFragment;
import com.jacob.material.databinding.FabPhoneAddressContactsFragmentBinding;
import com.jacob.material.databinding.FabPhoneAddressRecentFragmentBinding;
import com.jacob.material.example.adapter.AddressBookAdapter;
import com.jacob.material.example.model.AddressBook;
import com.jacob.material.widgets.GridLayoutVertialItemDecoration;
import com.jacob.utils.JsonUtils;
import com.jacob.utils.WidgetsUtils;

import java.util.ArrayList;
import java.util.List;

public class FabPhoneAddressContactsFragment extends TabViewPagerBaseFragment implements LifecycleObserver {
    private FabPhoneAddressContactsFragmentBinding binding;

    public FabPhoneAddressContactsFragment(){
        super(new TabItemContent("联系人", -1));
        this.getLifecycle().addObserver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fab_phone_address_contacts_fragment, container, false);
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
            if(ab.getId() >= 60){
                result.add(ab);
            }
        }
        return result;
    }


    private class OnAddressBookItemClickListener implements OnItemClickListener {
        @Override
        public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

        }
    }
}
