package com.jacob.book.material.example.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jacob.book.material.R;
import com.jacob.book.material.example.model.Grammy;

import java.util.List;

public class GrammyLibraryDetailAdapter extends BaseQuickAdapter<Grammy, BaseViewHolder> {
    public GrammyLibraryDetailAdapter(List<Grammy> data){
        super(R.layout.grammy_library_detail_holder, data);

    }

    @Override
    protected void convert(BaseViewHolder holder, Grammy grammy) {
        holder.setText(R.id.title_text_view, grammy.getTitle());
        holder.setText(R.id.sub_title_text_view, grammy.getSubTitle());
        holder.setText(R.id.duration_title_text_view, grammy.getDuration());
    }


}
