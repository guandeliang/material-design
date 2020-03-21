/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-15 下午6:07
 *
 */

package com.jacob.material.example.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jacob.material.R;
import com.jacob.material.example.model.Mail;

import java.util.List;

public class MailInBoxAdapter extends BaseQuickAdapter<Mail, BaseViewHolder> {
    public MailInBoxAdapter(List<Mail> data){
        super(R.layout.mail_in_box_holder, data);

    }

    @Override
    protected void convert(BaseViewHolder holder, Mail mail) {
        int headerResId = getContext().getResources().getIdentifier(mail.getHeaderFile(), "drawable", getContext().getPackageName());
        holder.setImageResource(R.id.header_image_view, headerResId);
        holder.setText(R.id.title_text_view, mail.getTitle());
        holder.setText(R.id.names_text_view, mail.getNames());
        holder.setText(R.id.summary_text_view, mail.getSummary());
    }


}
