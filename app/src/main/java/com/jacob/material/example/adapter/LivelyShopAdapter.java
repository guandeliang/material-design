/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-19 上午9:41
 *
 */

package com.jacob.material.example.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jacob.material.R;
import com.jacob.material.example.model.Commodit;

import java.util.List;
import java.util.Random;

public class LivelyShopAdapter extends RecyclerView.Adapter<LivelyShopAdapter.ShopViewHolder> {

    private List<Commodit> dataList;
    private Context context;


    public LivelyShopAdapter(Context context, List<Commodit> dataList){
        this.context = context;
        this.dataList = dataList;
    }

    public List<Commodit> getDataList() {
        return dataList;
    }

    public void setDataList(List<Commodit> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.lively_shop_item_holder, parent, false);
        return new ShopViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ShopViewHolder holder, int position) {
        if(dataList != null && position <dataList.size()){
            Commodit data = dataList.get(position);
            int imageResId =  context.getResources().getIdentifier(data.getImageFile(), "drawable", context.getPackageName());
            holder.imageView.setImageResource(imageResId);
            holder.titleTextView.setText(data.getTitle());
            holder.priceTextView.setText(String.format("%.2f", data.getPrice()));
        }

    }

    @Override
    public int getItemCount() {
        if(dataList != null){
            return dataList.size();
        }else{
            return 0;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position % 3;
    }


    class ShopViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView titleTextView;
        public TextView priceTextView;

        ShopViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view);
            titleTextView = itemView.findViewById(R.id.title_text_view);
            priceTextView = itemView.findViewById(R.id.price_text_view);
        }
    }

}
