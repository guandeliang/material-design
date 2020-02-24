/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-9 下午1:23
 *
 */

package com.jacob.book.material.example.adapter;

import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.material.card.MaterialCardView;
import com.jacob.book.material.R;
import com.jacob.book.material.example.model.Grammy;

import java.util.List;

public class GrammyIndexOneAdapter extends BaseQuickAdapter<Grammy, BaseViewHolder> {
    private RecyclerView recyclerView;
    private float itemCountPerContainer;
    private float itemSpace;


    public GrammyIndexOneAdapter(List<Grammy> data, float itemCountPerContainer, float itemSpace) {
        super(R.layout.grammy_index_item_one_holder, data);
        this.itemCountPerContainer = itemCountPerContainer;
        this.itemSpace = itemSpace;
    }


    @Override
    protected void convert(BaseViewHolder holder, Grammy data) {
        int imageResId = getContext().getResources().getIdentifier(data.getFileName(), "drawable", getContext().getPackageName());
        holder.setImageResource(R.id.image_view, imageResId);
        holder.setText(R.id.title_text_view, data.getTitle());

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;

    }

    @Override
    protected void onItemViewHolderCreated(BaseViewHolder viewHolder, int viewType) {
        super.onItemViewHolderCreated(viewHolder, viewType);

        if(itemCountPerContainer < 1){
            return;
        }

        OrientationHelper helper  = OrientationHelper.createHorizontalHelper(recyclerView.getLayoutManager());
        if(helper == null){
            return;
        }

        final int containerStart = helper.getStartAfterPadding();
        final int containerEnd = helper.getEndAfterPadding();
        final int containerWidth = containerEnd - containerStart;
        if(containerWidth <=0){
            return;
        }

        MaterialCardView rootView = viewHolder.getView(R.id.card_view);
        RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) rootView.getLayoutParams();

        if(itemCountPerContainer == 1f){
            params.width = containerWidth;
            rootView.setLayoutParams(params);
            return;
        }


        float spaceCount = (float)(Math.ceil(itemCountPerContainer) - 1);
        float spaceTotal = spaceCount*itemSpace;
        int itemWidth = (int)((containerWidth - spaceTotal)/itemCountPerContainer);
        if(itemWidth > 0){
            params.width = itemWidth;
            rootView.setLayoutParams(params);
        }

    }
}
