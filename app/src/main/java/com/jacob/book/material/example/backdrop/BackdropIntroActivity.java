/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-3-7 上午10:29
 *
 */

package com.jacob.book.material.example.backdrop;

import android.os.Bundle;
import android.view.ViewTreeObserver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import com.jacob.book.JsonUtils;
import com.jacob.book.ViewAnimation;
import com.jacob.book.material.BR;
import com.jacob.book.material.R;
import com.jacob.book.material.base.LivelyShopViewModel;
import com.jacob.book.material.databinding.BackdropIntroActivityBinding;
import com.jacob.book.material.example.adapter.LivelyCommoditAdapter;
import com.jacob.book.material.example.drawer.DrawerShopItemDecoration;
import com.jacob.book.material.example.model.Commodit;

import java.util.ArrayList;
import java.util.List;

public class BackdropIntroActivity extends AppCompatActivity {
    private BackdropIntroActivityBinding binding;
    private LivelyShopViewModel viewModel;
    private LivelyCommoditAdapter adapter;
    private boolean isShowBack;
    private int backHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.backdrop_intro_activity);
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this).get(LivelyShopViewModel.class);
        viewModel.addOnPropertyChangedCallback(new ViewModelPropertyChangedCallback());
        binding.setViewModel(viewModel);
        binding.setActivity(this);


        isShowBack = false;
        backHeight = 0;

        ViewTreeObserver viewTreeObserver = binding.backConstrainLayout.getViewTreeObserver();
        if(viewTreeObserver.isAlive()){
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    binding.backConstrainLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    backHeight = binding.backConstrainLayout.getHeight();
                }
            });
        }

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.HORIZONTAL, false);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position % 3 == 2 ? 2 : 1;
            }
        });

        binding.recyclerView.setLayoutManager(gridLayoutManager);
        int largePadding = getResources().getDimensionPixelSize(R.dimen.dp_4)*6;
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.dp_4)*4;
        DrawerShopItemDecoration decoration = new DrawerShopItemDecoration(largePadding, smallPadding);
        binding.recyclerView.addItemDecoration(decoration);
        adapter = new LivelyCommoditAdapter(this, new ArrayList<>());
        binding.recyclerView.setAdapter(adapter);

        viewModel.setCurrCategory(LivelyShopViewModel.Category.CLTHES);

    }

    private class ViewModelPropertyChangedCallback extends Observable.OnPropertyChangedCallback {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
            if(propertyId != BR.currCategory){
                return;
            }

            LivelyShopViewModel.Category currCategory = viewModel.getCurrCategory();
            binding.categoryTitleTextView.setText(currCategory.getTitle());
            loadCommoditList(currCategory.getId());
            ViewAnimation.fadeIn(binding.recyclerView);

        }
    }


    private void loadCommoditList(int category){
        List<Commodit> commoditList = JsonUtils.loadCommodits(getResources());
        List<Commodit> result = new ArrayList<>();
        for(Commodit c:commoditList){
            if(category == c.getCategory()){
                result.add(c);
            }
        }
        adapter.setDataList(result);
    }


    public void onMenuClick(){
        if(!isShowBack){
            isShowBack = true;
            binding.menuImageView.setImageResource(R.drawable.icon_close);
            binding.titleTextView.setText("选择品类");
            binding.dropLinearLayout.animate().translationY(backHeight).setDuration(300);
        }else{
            isShowBack = false;
            binding.menuImageView.setImageResource(R.drawable.backdrop_branded_menu);
            binding.titleTextView.setText("杉杉奥特莱斯");
            binding.dropLinearLayout.animate().translationY(0).setDuration(300);
        }
    }

}
