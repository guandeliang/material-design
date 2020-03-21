/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-6 下午2:00
 *
 */

package com.jacob.material.widgets.sliderbanner;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.CardViewHolder> {
    private List<Card> cardList;

    public CardViewAdapter(List<Card> cardList){
        if(cardList == null){
            cardList = new ArrayList<>();
        }
        this.cardList = cardList;
    }

    public void setCardList(List<Card> cardList){
        this.cardList = cardList;
        this.notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CardViewHolder(new CardView(LayoutInflater.from(parent.getContext()), parent));
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        Card card = cardList.get(position);
        holder.bind(card);
    }

    @Override
    public int getItemCount() {
        return cardList.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder{
        private CardView cardView;
        private Card card;

        public CardViewHolder(CardView cardView) {
            super(cardView.getRootView());
            this.cardView = cardView;
        }

        public void bind(Card card){
            this.card = card;
            cardView.bind(card);
        }
    }
}
