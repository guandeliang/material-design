/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-28 下午10:04
 *
 */

package com.jacob.material.example.bottomsheet;

import android.os.Bundle;
import android.transition.ArcMotion;
import android.transition.PatternPathMotion;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.transition.MaterialContainerTransform;
import com.jacob.material.R;
import com.jacob.material.databinding.BottomSheetMotionSettingFragmentBinding;
import com.jacob.material.example.adapter.GeneralAdapterOne;
import com.jacob.material.example.adapter.GrammyArtistItemAdapter;
import com.jacob.material.widgets.LinearLayoutVertialItemDecoration;
import com.jacob.utils.WidgetsUtils;

public class BottomSheetMotionSettingFragment extends Fragment implements LifecycleObserver {
    private BottomSheetMotionSettingFragmentBinding binding;
    private BottomSheetMotionViewModel viewModel;

    public BottomSheetMotionSettingFragment(){
        this.getLifecycle().addObserver(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        WidgetsUtils.setSystemBarColor(this.getActivity(), R.attr.colorPrimaryDark);
        WidgetsUtils.setSystemBarDark(this.getActivity());

        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_motion_setting_fragment, container, false);
        viewModel = new ViewModelProvider(this.getActivity()).get(BottomSheetMotionViewModel.class);


        binding.constraintLayout.setTransitionName(BottomSheetMotionViewModel.TRANSITION_WRAPPER_TO_FRAGMENT);
        binding.appBarLayout.setTransitionName(BottomSheetMotionViewModel.TRANSITION_CARD_TO_TOOLBAR);
        MaterialContainerTransform transform = new MaterialContainerTransform(getContext());
        //transform.setDuration(300);
        //transform.setPathMotion(new ArcMotion());
        transform.setScrimColor(WidgetsUtils.getColorValue(getContext(), R.color.transparent));
        this.setSharedElementEnterTransition(transform);
        this.setSharedElementReturnTransition(transform);

        binding.toolbar.setTitle(viewModel.getSelectedList().size() + "张图片");


        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);

        int px_16 = WidgetsUtils.dpToPx(getContext(), 16);
        LinearLayoutVertialItemDecoration decoration = new LinearLayoutVertialItemDecoration(px_16, px_16, px_16);
        binding.recyclerView.addItemDecoration(decoration);

        GrammyArtistItemAdapter adapter = new GrammyArtistItemAdapter(viewModel.getSelectedList());
        binding.recyclerView.setAdapter(adapter);

        binding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.getNavController().popBackStack();
            }
        });


        return binding.getRoot();
    }


}
