/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-5 下午1:34
 *
 */

package com.jacob.material.example.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SortedList;
import androidx.recyclerview.widget.SortedListAdapterCallback;

import com.jacob.material.R;
import com.jacob.material.example.fab.FabPhoneViewModel;
import com.jacob.material.example.model.AddressBook;

import java.util.List;

public class SortableAddressMailAdapter extends RecyclerView.Adapter<SortableAddressMailAdapter.AddressBookViewHolder> {
    public interface OnItemClickListener{
        public void onItemClick(SortableAddressMailAdapter.AddressBookViewHolder holder, AddressBook addressBook);
    }


    private SortedList<AddressBook> dataList = null;
    private OnItemClickListener listener;


    public SortableAddressMailAdapter(List<AddressBook> list, OnItemClickListener listener){
        this.listener = listener;
        this.dataList = new SortedList<>(AddressBook.class, new AddressBookSortedListCallback());
        this.dataList.addAll(list);
    }

    public void setData(List<AddressBook> addList, List<AddressBook> removeList){
        for(AddressBook ab:removeList){
            dataList.remove(ab);
        }
        for(AddressBook ab:addList){
            dataList.add(ab);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull SortableAddressMailAdapter.AddressBookViewHolder holder, int position) {
        if(dataList != null && position <dataList.size()){
            AddressBook data = dataList.get(position);
            holder.bind(data);
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @NonNull
    @Override
    public SortableAddressMailAdapter.AddressBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.address_mail_holder, parent, false);
        SortableAddressMailAdapter.AddressBookViewHolder holder = new AddressBookViewHolder(parent.getContext(), view);
        return holder;
    }

    public class AddressBookViewHolder extends RecyclerView.ViewHolder{
        private Context context;
        private ConstraintLayout root;
        private ImageView headerImageView;
        private TextView titleTextView;
        private TextView mailTextView;

        public AddressBookViewHolder(Context context, View view){
            super(view);
            this.context = context;
            this.root = view.findViewById(R.id.constraint_layout);
            this.headerImageView = view.findViewById(R.id.header_image_view);
            this.titleTextView = view.findViewById(R.id.title_text_view);
            this.mailTextView = view.findViewById(R.id.mail_text_view);
        }

        public void bind(AddressBook data){
            int imageResId = context.getResources().getIdentifier(data.getFileName(), "drawable", context.getPackageName());
            this.headerImageView.setImageResource(imageResId);
            this.titleTextView.setText(data.getTitle());
            this.mailTextView.setText(data.getMail());
            this.root.setTransitionName(FabPhoneViewModel.TRANSITION_ITEM_TO_FULL + "_" + data.getId());
            this.headerImageView.setTransitionName(FabPhoneViewModel.TRANSITION_IMAGE_TO_IMAGE + "_" + data.getId());
            if(listener != null){
                root.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(listener != null){
                            listener.onItemClick(AddressBookViewHolder.this, data);
                        }
                    }
                });
            }
        }

        public ConstraintLayout getRoot() {
            return root;
        }

        public ImageView getHeaderImageView() {
            return headerImageView;
        }

        public TextView getTitleTextView() {
            return titleTextView;
        }

        public TextView getMailTextView() {
            return mailTextView;
        }
    }

    private class AddressBookSortedListCallback extends SortedListAdapterCallback<AddressBook> {
        public AddressBookSortedListCallback(){
            super(SortableAddressMailAdapter.this);
        }

        @Override
        public int compare(AddressBook ab_1, AddressBook ab_2) {
            if(ab_1.getId() > ab_2.getId()){
                return 1;
            }else if(ab_1.getId() < ab_2.getId()){
                return -1;
            }else{
                return 0;
            }
        }

        @Override
        public boolean areContentsTheSame(AddressBook ab_1, AddressBook ab_2) {
            if(ab_1.getId() == ab_2.getId()){
                return true;
            }else{
                return false;
            }
        }

        @Override
        public boolean areItemsTheSame(AddressBook ab_1, AddressBook ab_2) {
            if(ab_1.getId() == ab_2.getId()){
                return true;
            }else{
                return false;
            }
        }
    }


}