/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-5 下午1:34
 *
 */

package com.jacob.material.example.adapter;

import android.util.Log;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jacob.material.R;
import com.jacob.material.example.card.CardRecycleViewMoveCallback;
import com.jacob.material.example.fab.FabPhoneViewModel;
import com.jacob.material.example.model.AddressBook;
import com.jacob.temp.TempConstant;

import java.util.Collections;
import java.util.List;

public class CardRecycleViewMoveAdapter extends BaseQuickAdapter<AddressBook, BaseViewHolder> implements CardRecycleViewMoveCallback.ItemTouchHelperAdapter {
    public CardRecycleViewMoveAdapter(List<AddressBook> list){
        super(R.layout.card_recycle_view_move_holder, list);
        this.setHasStableIds(true);

    }

    @Override
    public long getItemId(int position) {
        return getData().get(position).getId();
    }


    @Override
    protected void convert(BaseViewHolder holder, AddressBook data) {
        ImageView headerImageView = holder.getView(R.id.image_view);
        int imageResId = getContext().getResources().getIdentifier(data.getFileName(), "drawable", getContext().getPackageName());
        headerImageView.setImageResource(imageResId);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if(fromPosition == toPosition || fromPosition < 0 || toPosition < 0 || fromPosition >= getItemCount() || toPosition >= getItemCount()){
            return;
        }

        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(getData(), i, i + 1);
            }
        }else{
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(getData(), i, i - 1);
            }
        }

        if(!this.getWeakRecyclerView().get().isComputingLayout()){//避免线程冲突
            this.notifyItemRangeChanged(Math.min(fromPosition, toPosition), Math.abs(fromPosition - toPosition));
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        Log.d(TempConstant.LOG_TAG, "position = " + position);
    }


    @Override
    public void onItemDismiss(int position) {
        remove(position);
    }
}
