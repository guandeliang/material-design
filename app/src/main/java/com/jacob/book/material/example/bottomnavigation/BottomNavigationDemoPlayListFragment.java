package com.jacob.book.material.example.bottomnavigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.jacob.book.JsonUtils;
import com.jacob.book.material.R;
import com.jacob.book.material.databinding.BottomNavigationDemoPlayListFragmentBinding;
import com.jacob.book.material.example.adapter.GrammyPlayListAdapter;
import com.jacob.book.material.example.model.Grammy;

import java.util.ArrayList;
import java.util.List;

public class BottomNavigationDemoPlayListFragment extends Fragment implements LifecycleObserver {
    private BottomNavigationDemoPlayListFragmentBinding binding;
    private BottomSheetBehavior sheetBehavior;

    public BottomNavigationDemoPlayListFragment(){
        this.getLifecycle().addObserver(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_navigation_demo_play_list_fragment, container, false);

        initPlayListList();

        sheetBehavior = BottomSheetBehavior.from(binding.listCardView);
        sheetBehavior.setFitToContents(true);
        sheetBehavior.setHideable(false);
        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

        return binding.getRoot();
    }


    private void initPlayListList(){
        List<Grammy> allList = JsonUtils.loadGrammy(getResources());
        List<Grammy> playListList = new ArrayList<>();
        for(Grammy grammy:allList){
            if("album".equals(grammy.getCategory())){
                playListList.add(grammy);
            }
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);


        DividerItemDecoration decoration = new DividerItemDecoration(getContext(), RecyclerView.VERTICAL);
        binding.recyclerView.addItemDecoration(decoration);

        GrammyPlayListAdapter adapter = new GrammyPlayListAdapter(playListList);
        binding.recyclerView.setAdapter(adapter);
    }


}
