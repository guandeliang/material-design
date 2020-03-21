/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-31 下午12:28
 *
 */

package com.jacob.material.main.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.entity.node.BaseNode;
import com.jacob.utils.JsonUtils;
import com.jacob.utils.StringUtils;
import com.jacob.material.R;
import com.jacob.material.databinding.MainExampleListFragmentBinding;
import com.jacob.material.main.adapter.MainExampleNodeAdapter;
import com.jacob.material.main.model.ExampleGroup;
import com.jacob.material.main.model.ExampleItem;

import java.util.ArrayList;
import java.util.List;

public class MainExampleListFragment extends Fragment{
    private MainExampleListFragmentBinding binding;

    public MainExampleListFragment(){

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_example_list_fragment, container, false);

        MainExampleNodeAdapter adapter = new MainExampleNodeAdapter(new OnExampleItemClickListener());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 1, RecyclerView.VERTICAL, false);
        binding.recyclerView.setLayoutManager(gridLayoutManager);
        binding.recyclerView.setAdapter(adapter);

        View header = View.inflate(this.getContext(), R.layout.main_example_list_header, null);
        adapter.setHeaderView(header);

        List<ExampleGroup> exampleGroupList = JsonUtils.loadExampleGroup(getResources());
        List<BaseNode> exampleNodeList = new ArrayList<>();
        exampleNodeList.addAll(exampleGroupList);
        adapter.setNewData(exampleNodeList);


        return binding.getRoot();
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
    }

    private class OnExampleItemClickListener implements MainExampleNodeAdapter.OnItemClickListener{
        @Override
        public void onItemClick(ExampleItem item) {
            if(StringUtils.isBlankString(item.getPath())){
                return;
            }
            try {
                Intent intent = new Intent(MainExampleListFragment.this.getActivity(),  Class.forName(item.getPath()));
                Bundle bundle = ActivityOptions.makeScaleUpAnimation(binding.appBarLayout, 0, 0, 100, 100).toBundle();
                startActivity(intent, bundle);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


}
