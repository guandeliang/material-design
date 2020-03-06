/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-10 下午8:05
 *
 */

package com.jacob.book.material.example.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jacob.book.material.R;
import com.jacob.book.material.example.model.Grammy;

import java.util.List;

public class GrammyLibraryAlbumAdapter extends BaseQuickAdapter<Grammy, BaseViewHolder> {
    public GrammyLibraryAlbumAdapter(List<Grammy> data){
        super(R.layout.grammy_library_album_holder, data);

    }

    @Override
    protected void convert(BaseViewHolder holder, Grammy grammy) {
        int imageResId = getContext().getResources().getIdentifier(grammy.getFileName(), "drawable", getContext().getPackageName());
        holder.setImageResource(R.id.image_view, imageResId);
        holder.setText(R.id.title_text_view, grammy.getTitle());
        holder.setText(R.id.sub_title_text_view, grammy.getSubTitle());
        holder.setText(R.id.duration_title_text_view, grammy.getDuration());
    }


}