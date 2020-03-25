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
import com.jacob.material.example.fab.FabPhoneViewModel;
import com.jacob.material.example.model.AddressBook;
import com.jacob.material.example.model.ExampleImage;

import java.util.List;

public class AddressBookAdapter extends BaseQuickAdapter<AddressBook, BaseViewHolder> {
    public AddressBookAdapter(List<AddressBook> list){
        super(R.layout.square_image_holder, list);

    }

    @Override
    protected void convert(BaseViewHolder holder, AddressBook data) {
        ImageView headerImageView = holder.getView(R.id.image_view);
        int imageResId = getContext().getResources().getIdentifier(data.getFileName(), "drawable", getContext().getPackageName());
        headerImageView.setImageResource(imageResId);
        headerImageView.setTransitionName(FabPhoneViewModel.TRANSITION_IMAGE_TO_IMAGE + "_" + data.getId());
    }


}
