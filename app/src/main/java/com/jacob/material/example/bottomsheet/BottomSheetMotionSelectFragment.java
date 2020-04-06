/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-28 下午10:04
 *
 */

package com.jacob.material.example.bottomsheet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.selection.StorageStrategy;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jacob.material.R;
import com.jacob.material.databinding.BottomSheetMotionSelectFragmentBinding;
import com.jacob.material.example.adapter.GrammyArtistTrackerAdapter;
import com.jacob.material.example.model.GrammySinger;
import com.jacob.material.widgets.GridLayoutVertialItemDecoration;
import com.jacob.utils.JsonUtils;
import com.jacob.utils.WidgetsUtils;

import java.util.List;

public class BottomSheetMotionSelectFragment extends Fragment implements LifecycleObserver {
    private static int STATE_COLLAPSED = 0;
    private static int STATE_HALF_EXPANDED = 1;
    private static int STATE_EXPANDED = 2;
    private BottomSheetMotionSelectFragmentBinding binding;
    private BottomSheetMotionViewModel viewModel;

    private SelectionTracker<Long> tracker;
    private GrammyArtistTrackerAdapter adapter;
    private int selectedCardViewState;

    public BottomSheetMotionSelectFragment(){
        this.getLifecycle().addObserver(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_motion_select_fragment, container, false);
        viewModel = new ViewModelProvider(this.getActivity()).get(BottomSheetMotionViewModel.class);

        this.startPostponedEnterTransition();
        binding.selectedCardViewWrapper.setTransitionName(BottomSheetMotionViewModel.TRANSITION_WRAPPER_TO_FRAGMENT);
        binding.selectedCardView.setTransitionName(BottomSheetMotionViewModel.TRANSITION_CARD_TO_TOOLBAR);
        if(viewModel.getSelectedList().size() > 0){
            binding.selectedCardViewWrapper.setTranslationX(0);
        }



        WidgetsUtils.setSystemBarColor(this.getActivity(), R.color.gray_300);
        WidgetsUtils.setSystemBarLight(this.getActivity());

        selectedCardViewState = STATE_COLLAPSED;

        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);

        int px_16 = WidgetsUtils.dpToPx(getContext(), 16);
        GridLayoutVertialItemDecoration decoration = new GridLayoutVertialItemDecoration(px_16, px_16, px_16, px_16);
        binding.recyclerView.addItemDecoration(decoration);

        adapter = new GrammyArtistTrackerAdapter(this.getContext());
        binding.recyclerView.setAdapter(adapter);

        tracker = new SelectionTracker.Builder<>(
                "ExampleImageSelectionTrackerId",
                binding.recyclerView,
                new GrammyArtistTrackerAdapter.KeyProvider(adapter),
                new GrammyArtistTrackerAdapter.DetailsLookup(binding.recyclerView),
                StorageStrategy.createLongStorage()).withSelectionPredicate(new GrammyArtistTrackerAdapter.SelectionPredicate(adapter))
                .build();
        tracker.addObserver(new SelectionObserver());
        adapter.setTracker(tracker);

        List<GrammySinger> grammySingerList = JsonUtils.loadGrammySinger(getResources());
        adapter.setItems(grammySingerList);
        binding.recyclerView.addOnScrollListener(new RecyclerViewScrollListener());

        adapter.initTracker(viewModel.getSelectedList());

        binding.selectedCardView.setOnClickListener(new OnSelectedCardViewClickListener());

        return binding.getRoot();
    }


    private class SelectionObserver extends SelectionTracker.SelectionObserver<Long> {

        @Override
        public void onItemStateChanged(@NonNull Long key, boolean selected) {
            GrammySinger data = adapter.getGrammySinger(key.intValue());

            if(selected){
                viewModel.addSelected(data);
            }else{
                viewModel.removeSelected(data);
            }

            if(viewModel.getSelectedList().isEmpty()){
                collapsedSelectedCardView();
            }else{
                expandSelectedCardView();
            }
        }
    }

    private class RecyclerViewScrollListener extends RecyclerView.OnScrollListener{
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if(newState != RecyclerView.SCROLL_STATE_IDLE){
                halfExpandSelectedCardView();
            }
        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    }

    private void halfExpandSelectedCardView(){
        if(selectedCardViewState == STATE_EXPANDED){
            selectedCardViewState = STATE_HALF_EXPANDED;
            binding.selectedCardViewWrapper.animate().translationX(WidgetsUtils.dpToPx(this.getContext(), 56)).setDuration(300);
        }
    }

    private void expandSelectedCardView(){
        if(selectedCardViewState != STATE_EXPANDED){
            selectedCardViewState = STATE_EXPANDED;
            binding.selectedCardViewWrapper.animate().translationX(0).setDuration(300);
        }

        if(!viewModel.getSelectedList().isEmpty()){
            binding.selectedCountTextView.setText(Integer.toString(viewModel.getSelectedList().size()));
            GrammySinger grammySinger = viewModel.getSelectedList().get(viewModel.getSelectedList().size() - 1);
            if(grammySinger != null){
                int iconResId = this.getResources().getIdentifier(grammySinger.getFileName(), "drawable", this.getContext().getPackageName());
                binding.selectedImageView.setImageResource(iconResId);
            }
        }
    }

    private void collapsedSelectedCardView(){
        if(selectedCardViewState != STATE_COLLAPSED){
            selectedCardViewState = STATE_COLLAPSED;
            binding.selectedCardViewWrapper.animate().translationX(WidgetsUtils.dpToPx(this.getContext(), 144)).setDuration(300);
        }
    }


    private class OnSelectedCardViewClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {

            FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                    .addSharedElement(binding.selectedCardViewWrapper, BottomSheetMotionViewModel.TRANSITION_WRAPPER_TO_FRAGMENT)
                    .addSharedElement(binding.selectedCardView, BottomSheetMotionViewModel.TRANSITION_CARD_TO_TOOLBAR)
                    .build();

            viewModel.getNavController().navigate(R.id.show_setting, null, null, extras);
        }
    }

}
