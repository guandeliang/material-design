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

import java.util.List;

public class AddressBookCardAdapter extends BaseQuickAdapter<AddressBook, BaseViewHolder> {
    public AddressBookCardAdapter(List<AddressBook> list){
        super(R.layout.address_book_card_holder, list);
        this.addChildClickViewIds(R.id.video_image_view, R.id.call_image_view, R.id.more_image_view);

    }

    @Override
    protected void convert(BaseViewHolder holder, AddressBook data) {
        ImageView headerImageView = holder.getView(R.id.header_image_view);
        int imageResId = getContext().getResources().getIdentifier(data.getFileName(), "drawable", getContext().getPackageName());
        headerImageView.setImageResource(imageResId);
        holder.setText(R.id.title_text_view, data.getTitle());
        holder.setText(R.id.mobile_text_view, data.getMobile());
    }


}
