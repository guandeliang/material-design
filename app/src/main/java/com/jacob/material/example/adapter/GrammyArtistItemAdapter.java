/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-5 下午1:34
 *
 */

package com.jacob.material.example.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jacob.material.R;
import com.jacob.material.example.model.AddressBook;
import com.jacob.material.example.model.GrammySinger;

import java.util.List;

public class GrammyArtistItemAdapter extends BaseQuickAdapter<GrammySinger, BaseViewHolder> {
    public GrammyArtistItemAdapter(List<GrammySinger> list){
        super(R.layout.grammy_artist_item_holder, list);
        this.addChildClickViewIds(R.id.delete_image_view);

    }

    @Override
    protected void convert(BaseViewHolder holder, GrammySinger data) {
        ImageView headerImageView = holder.getView(R.id.header_image_view);
        int imageResId = getContext().getResources().getIdentifier(data.getFileName(), "drawable", getContext().getPackageName());
        headerImageView.setImageResource(imageResId);
        holder.setText(R.id.title_text_view, data.getTitle());
        holder.setText(R.id.sub_title_text_view, data.getSubTitle());
    }


}
