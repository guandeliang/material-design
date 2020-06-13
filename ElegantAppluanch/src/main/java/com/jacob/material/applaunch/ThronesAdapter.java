/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-20 下午9:41
 *
 */

package com.jacob.material.applaunch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.jacob.http.MdGlide;
import com.jacob.http.MdRetrofitFactory;

import java.util.ArrayList;
import java.util.List;

public class ThronesAdapter extends RecyclerView.Adapter<ThronesAdapter.ThronesHolder> {
    private List<Thrones> dataList;
    private Context context;

    public ThronesAdapter(Context context){
        this.context = context;
        this.dataList = new ArrayList<>();
    }

    public void setDataList(List<Thrones> dataList){
        this.dataList.clear();
        this.dataList.addAll(dataList);
        this.notifyDataSetChanged();
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
    public ThronesAdapter.ThronesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_holder, parent, false);
        ThronesAdapter.ThronesHolder holder = new ThronesAdapter.ThronesHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ThronesAdapter.ThronesHolder holder, int position) {
        Thrones data = dataList.get(position);
        holder.bind(data);
    }

    class ThronesHolder extends RecyclerView.ViewHolder{
        public ThronesHolder(View view){
            super(view);
        }

        public void bind(Thrones thrones){
            ImageView imageView = itemView.findViewById(R.id.image_view);
            TextView titleTextView = itemView.findViewById(R.id.title_text_view);
            TextView summaryTextView = itemView.findViewById(R.id.summary_text_view);

            String url = MdRetrofitFactory.BASE_URL + "images/" + thrones.getFileName();
            MdGlide.with(context).load(url).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(imageView);
            titleTextView.setText(thrones.getTitle());
            summaryTextView.setText(thrones.getSummary());
        }
    }

}
