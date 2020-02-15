package com.jacob.book.material.example.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jacob.book.material.R;
import com.jacob.book.material.example.model.GrammySinger;
import com.jacob.book.material.example.model.Thrones;

import java.util.List;

public class GrammyLibraryLiveAdapter extends BaseQuickAdapter<Thrones, BaseViewHolder> {
    public GrammyLibraryLiveAdapter(List<Thrones> data){
        super(R.layout.grammy_library_live_holder, data);

    }

    @Override
    protected void convert(BaseViewHolder holder, Thrones thrones) {
        int imageResId = getContext().getResources().getIdentifier(thrones.getFileName(), "drawable", getContext().getPackageName());
        holder.setImageResource(R.id.image_view, imageResId);
        holder.setText(R.id.title_text_view, thrones.getTitle());
        holder.setText(R.id.summary_text_view, thrones.getSummary());
    }


}
