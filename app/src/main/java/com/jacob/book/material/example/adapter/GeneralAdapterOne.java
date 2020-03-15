/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-15 下午6:07
 *
 */

package com.jacob.book.material.example.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jacob.book.material.R;
import com.jacob.book.material.example.model.GeneralData;
import com.jacob.book.material.example.model.Mail;

import java.util.List;

public class GeneralAdapterOne extends BaseQuickAdapter<GeneralData, BaseViewHolder> {
    public GeneralAdapterOne(List<GeneralData> dataList){
        super(R.layout.general_holder_one, dataList);

    }

    @Override
    protected void convert(BaseViewHolder holder, GeneralData data) {
        int fileNameResId = getContext().getResources().getIdentifier(data.getFileName(), "drawable", getContext().getPackageName());
        holder.setImageResource(R.id.image_view, fileNameResId);
        holder.setText(R.id.title_text_view, data.getTitle());
        holder.setText(R.id.sub_title_text_view, data.getSubTitle());
    }


}
