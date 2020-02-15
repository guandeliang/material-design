package com.jacob.book.material.example.bottomnavigation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.CutCornerTreatment;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.jacob.book.JsonUtils;
import com.jacob.book.WidgetsUtils;
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

        WidgetsUtils.setSystemBarColor(this.getActivity(), R.color.gray_1000);
        WidgetsUtils.setSystemBarDark(this.getActivity());

        initPlayListList();

        sheetBehavior = BottomSheetBehavior.from(binding.bottomsheetLinearView);
        sheetBehavior.setFitToContents(true);
        sheetBehavior.setHideable(false);
        sheetBehavior.setPeekHeight(WidgetsUtils.dpToPx(this.getContext(), 112));
        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        binding.bottomsheetDownImageView.setVisibility(View.GONE);
        sheetBehavior.addBottomSheetCallback(new SheetStateChangeCallback());

        ShapeAppearanceModel.Builder builder = ShapeAppearanceModel.builder();
        builder.setTopLeftCorner(CornerFamily.ROUNDED, WidgetsUtils.dpToPx(this.getContext(), 24));
        builder.setTopRightCorner(CornerFamily.ROUNDED, WidgetsUtils.dpToPx(this.getContext(), 24));

        MaterialShapeDrawable drawable = new MaterialShapeDrawable(builder.build());
        drawable.setTint(WidgetsUtils.getColorValue(this.getActivity(), android.R.attr.colorPrimary));

        binding.bottomsheetTitleConstraintLayout.setBackground(drawable);



        return binding.getRoot();
    }


    private class SheetStateChangeCallback extends BottomSheetBehavior.BottomSheetCallback{
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if(BottomSheetBehavior.STATE_EXPANDED == newState){
                binding.bottomsheetUpImageView.setVisibility(View.GONE);
                binding.bottomsheetDownImageView.setVisibility(View.VISIBLE);
            }else{
                binding.bottomsheetUpImageView.setVisibility(View.VISIBLE);
                binding.bottomsheetDownImageView.setVisibility(View.GONE);
            }

        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {

        }
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
