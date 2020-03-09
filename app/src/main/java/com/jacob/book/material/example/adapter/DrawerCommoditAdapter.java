/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-19 上午9:41
 *
 */

package com.jacob.book.material.example.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseDelegateMultiAdapter;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.material.card.MaterialCardView;
import com.jacob.book.material.R;
import com.jacob.book.material.example.model.Commodit;
import com.jacob.book.material.example.model.Grammy;
import com.jacob.book.temp.TempConstant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawerCommoditAdapter extends RecyclerView.Adapter<DrawerCommoditAdapter.CommoditViewHolder> {

    private List<Commodit> dataList;
    private Context context;


    public DrawerCommoditAdapter(Context context, List<Commodit> dataList){
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
    public CommoditViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        int layoutId = R.layout.drawer_shop_item_one_holder;
        if (viewType == 1) {
            layoutId = R.layout.drawer_shop_item_two_holder;
        } else if (viewType == 2) {
            layoutId = R.layout.drawer_shop_item_three_holder;
        }
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
        return new CommoditViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull CommoditViewHolder holder, int position) {
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


    class CommoditViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView titleTextView;
        public TextView priceTextView;

        CommoditViewHolder(@NonNull View itemView) {
            super(itemView);
            Random random = new Random();
            float rValue = random.nextFloat();
            int dy = 0;
            if(rValue >= 0.5f){
                dy = context.getResources().getDimensionPixelSize(R.dimen.dp_4)*8;
                itemView.setRotation(2);
            }else{
                dy = 0 - context.getResources().getDimensionPixelSize(R.dimen.dp_4)*8;
                itemView.setRotation(-2);
            }

            imageView = itemView.findViewById(R.id.image_view);
            titleTextView = itemView.findViewById(R.id.title_text_view);
            priceTextView = itemView.findViewById(R.id.price_text_view);
        }
    }

}
