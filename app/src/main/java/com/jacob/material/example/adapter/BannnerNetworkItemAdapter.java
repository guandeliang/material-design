/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-17 上午9:59
 *
 */

package com.jacob.material.example.adapter;

import android.util.Log;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jacob.http.MdGlide;
import com.jacob.http.MdRetrofitFactory;
import com.jacob.material.R;
import com.jacob.material.example.model.Thrones;
import com.jacob.temp.TempConstant;

import java.util.List;

public class BannnerNetworkItemAdapter extends BaseQuickAdapter<Thrones, BaseViewHolder> {
    public BannnerNetworkItemAdapter(List<Thrones> data){
        super(R.layout.banner_network_item_holder, data);

    }

    @Override
    protected void convert(BaseViewHolder holder, Thrones thrones) {
        ImageView imageView = holder.getView(R.id.image_view);
        String url = MdRetrofitFactory.BASE_URL + "images/" + thrones.getFileName();
        Log.d(TempConstant.LOG_TAG, "URL = " + url);
        MdGlide.with(getContext()).load(url).into(imageView);

        holder.setText(R.id.title_text_view, thrones.getTitle());
        holder.setText(R.id.summary_text_view, thrones.getSummary());
    }


}
