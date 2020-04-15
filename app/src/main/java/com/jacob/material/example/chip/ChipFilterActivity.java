/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-31 下午5:05
 *
 */

package com.jacob.material.example.chip;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.jacob.material.R;
import com.jacob.material.databinding.ChipFilterActivityBinding;
import com.jacob.material.example.adapter.LivelyShopAdapter;
import com.jacob.material.example.model.Commodit;
import com.jacob.material.widgets.LinearLayoutVertialItemDecoration;
import com.jacob.temp.TempConstant;
import com.jacob.utils.JsonUtils;
import com.jacob.utils.WidgetsUtils;

import java.util.ArrayList;
import java.util.List;

public class ChipFilterActivity extends AppCompatActivity {
    public enum CommoditCategory{
        ALL(100, R.id.all_chip),
        CAR(101, R.id.car_chip),
        CLTHES(102, R.id.clthes_chip),
        COVERALL(103, R.id.coverall_chip),
        DLING(104, R.id.dling_chip),
        FOOD(105, R.id.food_chip),
        GUN(106, R.id.gun_chip),
        USED(107, R.id.used_chip),
        WATCH(108, R.id.watch_chip),
        WHEEL(109, R.id.wheel_chip),
        YACHT(111, R.id.yacht_chip);

        private int category;
        private int chipId;
        private CommoditCategory(int category, int chipId){
            this.category = category;
            this.chipId = chipId;
        }

        public int getCategory(){
            return category;
        }

        public int getChipId() {
            return chipId;
        }

        static public int getCategoryByChipId(int chipId){
            int result = -1;
            for(CommoditCategory cc:CommoditCategory.values()){
                if(chipId == cc.getChipId()){
                    result = cc.getCategory();
                }
            }
            return result;
        }
    }


    private ChipFilterActivityBinding binding;
    private LivelyShopAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.chip_filter_activity);
        binding.setActivity(this);

        setCheckedIconColor();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);

        int px_16 = WidgetsUtils.dpToPx(this, 16);

        LinearLayoutVertialItemDecoration decoration = new LinearLayoutVertialItemDecoration(0, px_16, px_16);
        binding.recyclerView.addItemDecoration(decoration);

        List<Integer> categoryList = new ArrayList<>();
        categoryList.add(CommoditCategory.ALL.getCategory());
        List<Commodit> commoditList = getCommoditList(categoryList);
        adapter = new LivelyShopAdapter(this, commoditList);

        binding.recyclerView.setAdapter(adapter);
    }

    private void setCheckedIconColor(){
        for(int i = 0; i < binding.filterChipGroup.getChildCount(); i++){
            View view = binding.filterChipGroup.getChildAt(i);
            if(view instanceof Chip){
                Chip chip = (Chip)view;
                Drawable checkedDrawable = AppCompatResources.getDrawable(this, R.drawable.icon_check_circle_outline);
                WidgetsUtils.setDrawableColor(this, checkedDrawable, R.attr.colorSecondary);
                chip.setCheckedIcon(checkedDrawable);
            }
        }
    }

    private List<Commodit> getCommoditList(List<Integer> categoryList){
        List<Commodit> commoditList = JsonUtils.loadCommodits(getResources());
        if(categoryList.contains(CommoditCategory.ALL.getCategory())){
            return commoditList;
        }

        List<Commodit> result = new ArrayList<>();
        for(Commodit c:commoditList){
            if(categoryList.contains(c.getCategory())){
                result.add(c);
            }
        }
        return result;
    }


    public void onFitlerCheckedChanged(CompoundButton compoundButton, boolean b){
        List<Integer> checkedCategoryList = new ArrayList<>();
        for(int i = 0; i < binding.filterChipGroup.getChildCount(); i++){
            View view = binding.filterChipGroup.getChildAt(i);
            if(view instanceof Chip){
                Chip chip = (Chip)view;
                if(chip.isChecked()){
                    int category = CommoditCategory.getCategoryByChipId(chip.getId());
                    if(category > 0){
                        checkedCategoryList.add(category);
                    }
                }
            }
        }

        List<Commodit> commoditList = getCommoditList(checkedCategoryList);
        adapter.setDataList(commoditList);
    }


}
