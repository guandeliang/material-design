/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-10 下午7:06
 *
 */

package com.jacob.book.material.example.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jacob.book.material.R;
import com.jacob.book.material.example.model.Grammy;
import com.jacob.book.material.example.model.GrammySinger;

import java.util.List;

public class GrammyLibraryArtistAdapter extends BaseQuickAdapter<GrammySinger, BaseViewHolder> {
    public GrammyLibraryArtistAdapter(List<GrammySinger> data){
        super(R.layout.grammy_library_artist_holder, data);

    }

    @Override
    protected void convert(BaseViewHolder holder, GrammySinger singer) {
        int imageResId = getContext().getResources().getIdentifier(singer.getFileName(), "drawable", getContext().getPackageName());
        holder.setImageResource(R.id.image_view, imageResId);
        holder.setText(R.id.title_text_view, singer.getTitle());
        holder.setText(R.id.sub_title_text_view, singer.getSubTitle());
        holder.setText(R.id.summary_text_view, singer.getSummary());
    }


}
