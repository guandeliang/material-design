/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-7 下午2:53
 *
 */

package com.jacob.material.example.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.selection.ItemDetailsLookup;
import androidx.recyclerview.selection.ItemKeyProvider;
import androidx.recyclerview.selection.SelectionTracker;
import androidx.recyclerview.widget.RecyclerView;

import com.jacob.material.R;
import com.jacob.material.example.model.ExampleImage;
import com.jacob.material.example.model.GrammySinger;
import com.jacob.temp.TempConstant;

import java.util.ArrayList;
import java.util.List;

public class GrammyArtistTrackerAdapter extends RecyclerView.Adapter<GrammyArtistTrackerAdapter.ItemViewHolder> {

    private List<GrammySinger> items;
    private Context context;
    private SelectionTracker<Long> tracker;

    public GrammyArtistTrackerAdapter(Context context){
        this.context = context;
        this.items = new ArrayList<>();
    }

    public void setTracker(SelectionTracker<Long> tracker){
        this.tracker = tracker;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<GrammySinger> items){
        if(items != null){
            this.items = items;
        }
    }

    public GrammySinger getGrammySinger(int position){
        if(position <0 || position >= this.getItemCount()){
            return null;
        }else{
            return items.get(position);
        }
    }

    public void initTracker(List<GrammySinger> selectedList){
        Log.d(TempConstant.LOG_TAG, "items = " + items.size());
        Log.d(TempConstant.LOG_TAG, "selectedList = " + selectedList.size());
        for(GrammySinger data:selectedList){
            Log.d(TempConstant.LOG_TAG, "data = " + data.getId());
            int position = items.indexOf(data);
            boolean result = tracker.select(Long.valueOf(position));
            Log.d(TempConstant.LOG_TAG, "result = " + result + " position = " + position);
        }
    }




    @NonNull
    @Override
    public GrammyArtistTrackerAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.grammy_artist_selection_holder, parent, false);
        GrammyArtistTrackerAdapter.ItemViewHolder holder = new ItemViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GrammyArtistTrackerAdapter.ItemViewHolder holder, int position) {
        holder.bind(position);
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private View maskView;
        private ImageView unCheckImageView;
        private ImageView checkImageView;

        private final ItemDetails details;

        public ItemViewHolder(View view){
            super(view);
            this.imageView = view.findViewById(R.id.image_view);
            this.maskView = view.findViewById(R.id.mask_view);
            this.unCheckImageView = view.findViewById(R.id.unchecked_image_view);
            this.checkImageView = view.findViewById(R.id.checked_image_view);
            details = new ItemDetails();
        }

        private void bind(int position) {
            GrammySinger item  = getGrammySinger(position);
            details.position = position;
            String fileName = item.getFileName();
            int iconResId = context.getResources().getIdentifier(fileName, "drawable", context.getPackageName());
            imageView.setImageResource(iconResId);

            if (tracker != null) {
                bindSelectedState();
            }
        }

        private void bindSelectedState() {
            if(tracker.isSelected(details.getSelectionKey())){
                this.maskView.setVisibility(View.VISIBLE);
                this.unCheckImageView.setVisibility(View.INVISIBLE);
                this.checkImageView.setVisibility(View.VISIBLE);
            }else{
                this.maskView.setVisibility(View.INVISIBLE);
                this.unCheckImageView.setVisibility(View.VISIBLE);
                this.checkImageView.setVisibility(View.INVISIBLE);
            }
        }

        ItemDetailsLookup.ItemDetails<Long> getItemDetails() {
            return details;
        }

    }

    public static class SelectionPredicate extends SelectionTracker.SelectionPredicate<Long> {
        private GrammyArtistTrackerAdapter adapter;
        public SelectionPredicate(GrammyArtistTrackerAdapter adapter){
            this.adapter = adapter;
        }

        @Override
        public boolean canSetStateForKey(@NonNull Long key, boolean nextState) {
            if(key < 0 || key >= adapter.getItemCount()){
                return false;
            }else{
                return true;
            }
        }

        @Override
        public boolean canSetStateAtPosition(int position, boolean nextState) {
            if(position < 0 || position >= adapter.getItemCount()){
                return false;
            }else{
                return true;
            }
        }

        @Override
        public boolean canSelectMultiple() {
            return true;
        }
    }

    public static class DetailsLookup extends ItemDetailsLookup<Long> {

        private final RecyclerView recyclerView;

        public DetailsLookup(RecyclerView recyclerView) {
            this.recyclerView = recyclerView;
        }

        @Nullable
        @Override
        public ItemDetails<Long> getItemDetails(@NonNull MotionEvent e) {
            View view = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (view != null) {
                RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(view);
                if (viewHolder instanceof ItemViewHolder) {
                    return ((ItemViewHolder) viewHolder).getItemDetails();
                }
            }
            return null;
        }
    }

    public static class KeyProvider extends ItemKeyProvider<Long> {

        public KeyProvider(GrammyArtistTrackerAdapter adapter) {
            super(ItemKeyProvider.SCOPE_MAPPED);
        }

        @Nullable
        @Override
        public Long getKey(int position) {
            return (long) position;
        }

        @Override
        public int getPosition(@NonNull Long key) {
            long value = key;
            return (int) value;
        }
    }

    public static class ItemDetails extends ItemDetailsLookup.ItemDetails<Long> {
        private long position;
        public ItemDetails() {
        }

        @Override
        public int getPosition() {
            return (int) position;
        }

        @Nullable
        @Override
        public Long getSelectionKey() {
            return position;
        }

        @Override
        public boolean inSelectionHotspot(@NonNull MotionEvent e) {
            return false;
        }

        @Override
        public boolean inDragRegion(@NonNull MotionEvent e) {
            return true;
        }
    }


}
