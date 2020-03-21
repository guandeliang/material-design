/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-17 下午1:09
 *
 */

package com.jacob.material.example.bottomnavigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.transition.Transition;
import androidx.transition.TransitionInflater;

import com.jacob.utils.JsonUtils;
import com.jacob.utils.WidgetsUtils;
import com.jacob.material.R;
import com.jacob.material.databinding.BottomNavigationDemoLibraryDetailFragmentBinding;
import com.jacob.material.example.adapter.GrammyLibraryDetailAdapter;
import com.jacob.material.example.model.Grammy;
import com.jacob.material.example.model.Thrones;

import java.util.ArrayList;
import java.util.List;

public class BottomNavigationDemoLibraryDetailFragment extends Fragment implements LifecycleObserver {
    public static String PARAM_THERONES_ITEM = "PARAM_THERONES_ITEM";


    private BottomNavigationDemoLibraryDetailFragmentBinding binding;

    public BottomNavigationDemoLibraryDetailFragment(){
        this.getLifecycle().addObserver(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_navigation_demo_library_detail_fragment, container, false);

        WidgetsUtils.setSystemBarColor(this.getActivity(), R.color.gray_50);
        WidgetsUtils.setSystemBarLight(this.getActivity());

        Thrones thrones = (Thrones)getArguments().getSerializable(PARAM_THERONES_ITEM);
        int headerResId = getContext().getResources().getIdentifier(thrones.getFileName(), "drawable", getContext().getPackageName());
        binding.headerImageView.setImageResource(headerResId);
        binding.titleTextView.setText(thrones.getTitle());


        binding.headerImageView.setTransitionName(thrones.getFileName());
        Transition transition = TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move).setDuration(500);
        this.setSharedElementEnterTransition(transition);

        binding.backImageView.setOnClickListener(new OnBackClickListener());

        initDetailList();

        return binding.getRoot();
    }

    private void initDetailList(){
        List<Grammy> allList = JsonUtils.loadGrammy(getResources());
        List<Grammy> librarylList = new ArrayList<>();
        for(Grammy grammy:allList){
            if("album".equals(grammy.getCategory())){
                librarylList.add(grammy);
            }
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);

        GrammyLibraryDetailAdapter adapter = new GrammyLibraryDetailAdapter(librarylList);
        binding.recyclerView.setAdapter(adapter);
    }

    private class OnBackClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).popBackStack();
        }
    }

}
