package com.jacob.book.material.example.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.jacob.book.material.R;
import com.jacob.book.material.example.model.MarvelNews;

import java.util.List;

public class MarvelNewsAdapter extends BaseQuickAdapter<MarvelNews, BaseViewHolder> {
    public MarvelNewsAdapter(List<MarvelNews> data){
        super(R.layout.marvel_news_holder, data);

    }

    @Override
    protected void convert(BaseViewHolder holder, MarvelNews news) {
        int logoResId = getContext().getResources().getIdentifier(news.getLogoGroup(), "drawable", getContext().getPackageName());
        int fileResId = getContext().getResources().getIdentifier(news.getImageFile(), "drawable", getContext().getPackageName());
        holder.setImageResource(R.id.logo_group_image_view, logoResId);
        holder.setText(R.id.media_group_text_view, news.getMediaGroup());
        holder.setText(R.id.time_text_view, news.getGenTime());
        holder.setImageResource(R.id.content_image_view, fileResId);
        holder.setText(R.id.title_text_view, news.getTitle());
        holder.setText(R.id.desc_text_view, news.getDesc());
    }


}
