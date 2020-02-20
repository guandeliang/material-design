/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-17 上午9:59
 *
 */

package com.jacob.book.material.example.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jacob.book.material.R;
import com.jacob.book.material.example.model.Thrones;

import java.util.List;

public class GrammyLibraryLiveAdapter extends BaseQuickAdapter<Thrones, BaseViewHolder> {
    public GrammyLibraryLiveAdapter(List<Thrones> data){
        super(R.layout.grammy_library_live_holder, data);

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


}
