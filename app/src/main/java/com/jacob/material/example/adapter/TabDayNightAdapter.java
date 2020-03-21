/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-19 上午9:41
 *
 */

package com.jacob.material.example.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jacob.material.R;

public class TabDayNightAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String[] dataList;

    public TabDayNightAdapter(){
        dataList = new String[4];
        dataList[0] = "主题";
        dataList[1] = "图标";
        dataList[2] = "文字";
        dataList[3] = "指示";
    }

    @Override
    public int getItemCount() {
        return dataList.length;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public String getItem(int position){
        return dataList[position];
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = null;
        if(viewType == 0){
            view = inflater.inflate(R.layout.tab_day_night_theme_holder, parent, false);
        }else if(viewType == 1){
            view = inflater.inflate(R.layout.tab_day_night_icon_holder, parent, false);
        }else if(viewType == 2){
            view = inflater.inflate(R.layout.tab_day_night_text_holder, parent, false);
        }else if(viewType == 3){
            view = inflater.inflate(R.layout.tab_day_night_indicator_holder, parent, false);
        }

        RecyclerView.ViewHolder holder = new TabDayNightViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    class TabDayNightViewHolder extends RecyclerView.ViewHolder{
        public TabDayNightViewHolder(View view){
            super(view);
        }
    }

}
