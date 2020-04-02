/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-20 下午9:41
 *
 */

package com.jacob.material.example.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jacob.material.R;
import com.jacob.material.example.model.Thrones;

import java.util.List;

public class GotMenuAdapter extends RecyclerView.Adapter<GotMenuAdapter.GotMenuViewHolder> {
    private List<Thrones> dataList;

    public GotMenuAdapter(List<Thrones> dataList){
        this.dataList = dataList;
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public Thrones getItem(int position){
        return dataList.get(position);
    }

    @NonNull
    @Override
    public GotMenuAdapter.GotMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.menu_context_item_holder, parent, false);
        GotMenuAdapter.GotMenuViewHolder holder = new GotMenuViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GotMenuAdapter.GotMenuViewHolder holder, int position) {
        Thrones data = dataList.get(position);
        holder.bind(data);
    }

    class GotMenuViewHolder extends RecyclerView.ViewHolder{
        public GotMenuViewHolder(View view){
            super(view);
        }

        public void bind(Thrones data){
            ImageView imageView = itemView.findViewById(R.id.image_view);
            int imageResId = itemView.getContext().getResources().getIdentifier(data.getFileName(), "drawable", itemView.getContext().getPackageName());
            imageView.setImageResource(imageResId);
        }
    }

}
