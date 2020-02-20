/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-9 下午9:12
 *
 */

package com.jacob.book.material.example.adapter;

import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.material.card.MaterialCardView;
import com.jacob.book.material.R;
import com.jacob.book.material.example.model.GrammyMultiItemEntity;

import java.util.List;

public class GrammyIndexThreeAdapter extends BaseMultiItemQuickAdapter<GrammyMultiItemEntity, BaseViewHolder> {
    private RecyclerView recyclerView;


    public GrammyIndexThreeAdapter(List<GrammyMultiItemEntity> data) {
        super(data);
        addItemType(GrammyMultiItemEntity.ITEM_TYPE_A, R.layout.grammy_index_item_three_a_holder);
        addItemType(GrammyMultiItemEntity.ITEM_TYPE_B, R.layout.grammy_index_item_three_b_holder);
        addItemType(GrammyMultiItemEntity.ITEM_TYPE_C, R.layout.grammy_index_item_three_c_holder);
    }


    @Override
    protected void convert(BaseViewHolder helper, GrammyMultiItemEntity data) {
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        this.recyclerView = recyclerView;

    }

    @Override
    protected void onItemViewHolderCreated(BaseViewHolder viewHolder, int viewType) {
        super.onItemViewHolderCreated(viewHolder, viewType);

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

        params.width = containerWidth;
        rootView.setLayoutParams(params);

    }
}
