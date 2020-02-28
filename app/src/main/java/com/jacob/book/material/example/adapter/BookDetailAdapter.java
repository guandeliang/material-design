/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-20 下午9:41
 *
 */

package com.jacob.book.material.example.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jacob.book.material.R;

public class BookDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private String[] dataList;
    private String[] numberList;

    public BookDetailAdapter(){
        dataList = new String[3];
        dataList[0] = "书评";
        dataList[1] = "讨论";
        dataList[2] = "读书笔记";

        numberList = new String[3];
        numberList[0] = "175";
        numberList[1] = "268";
        numberList[2] = "1137";
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

    public String getNumber(int position){
        return numberList[position];
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = null;
        if(viewType == 0){
            view = inflater.inflate(R.layout.tab_base_detail_001_holder, parent, false);
        }else if(viewType == 1){
            view = inflater.inflate(R.layout.tab_base_detail_002_holder, parent, false);
        }else if(viewType == 2){
            view = inflater.inflate(R.layout.tab_base_detail_003_holder, parent, false);
        }

        RecyclerView.ViewHolder holder = new BookDetailViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    class BookDetailViewHolder extends RecyclerView.ViewHolder{
        public BookDetailViewHolder(View view){
            super(view);
        }
    }

}
