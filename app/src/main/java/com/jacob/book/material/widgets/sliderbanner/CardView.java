package com.jacob.book.material.widgets.sliderbanner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jacob.book.material.R;

public class CardView{
    private View rootView;
    private ImageView imageView;
    private Card card;


    public CardView(LayoutInflater layoutInflater, ViewGroup container) {
        rootView = layoutInflater.inflate(R.layout.slider_banner_holder, container, false);
        imageView = rootView.findViewById(R.id.image_view);
    }

    public View getRootView() {
        return rootView;
    }

    public void bind(Card card){
        this.card = card;
        this.imageView.setImageResource(card.getImageResId());
    }

}
