/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-5 下午1:34
 *
 */

package com.jacob.book.material.example.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jacob.book.material.R;
import com.jacob.book.material.example.model.ExampleImage;

import java.util.List;

public class ExampleImageAdapter extends BaseQuickAdapter<ExampleImage, BaseViewHolder> {
    public ExampleImageAdapter(List<ExampleImage> data){
        super(R.layout.example_image_holder, data);

    }

    @Override
    protected void convert(BaseViewHolder holder, ExampleImage exampleImage) {
        int iconResId = getContext().getResources().getIdentifier(exampleImage.getFileName(), "drawable", getContext().getPackageName());
        holder.setImageResource(R.id.image_view, iconResId);
    }


}
