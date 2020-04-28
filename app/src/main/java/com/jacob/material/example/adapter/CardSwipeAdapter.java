/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-17 上午9:59
 *
 */

package com.jacob.material.example.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jacob.material.R;
import com.jacob.material.example.card.CardSwipeCallback;
import com.jacob.material.example.model.Thrones;

import java.util.List;

public class CardSwipeAdapter extends BaseQuickAdapter<Thrones, BaseViewHolder> {
    private CardSwipeCallback swipeCallback;


    public CardSwipeAdapter(List<Thrones> data){
        super(R.layout.card_swipe_holder, data);
        this.addChildClickViewIds(R.id.delete_card_view, R.id.edit_card_view);
        this.setHasStableIds(true);//为每个Holder分配唯一ItemId
    }

    public void setSwipeCallback(CardSwipeCallback swipeCallback) {
        this.swipeCallback = swipeCallback;
    }

    @Override
    protected void convert(BaseViewHolder holder, Thrones thrones) {
        ImageView imageView = holder.getView(R.id.image_view);
        int imageResId = getContext().getResources().getIdentifier(thrones.getFileName(), "drawable", getContext().getPackageName());
        imageView.setImageResource(imageResId);
        imageView.setTransitionName(thrones.getFileName());
        holder.setText(R.id.title_text_view, thrones.getTitle());
        holder.setText(R.id.summary_text_view, thrones.getSummary());
    }

    @Override
    public long getItemId(int position) {
        return getData().get(position).getId();
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        if(swipeCallback != null){
            swipeCallback.onBindViewHolder(holder);
        }
    }




}
