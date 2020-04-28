package com.jacob.material.example.card;

import android.content.Context;
import android.graphics.Canvas;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.viewholder.BaseViewHolder;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


public class CardSwipeCallback extends ItemTouchHelper.Callback{
    private class SwipeInfo{
        public long itemId;
        public float translationX;

        public SwipeInfo(long itemId) {
            this.itemId = itemId;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SwipeInfo swipeInfo = (SwipeInfo) o;
            return itemId == swipeInfo.itemId;
        }

        @Override
        public int hashCode() {
            return Objects.hash(itemId);
        }
    }

    private Context context;
    private int swipeViewId;
    private int leftBackWidth;
    private int rightBackWidth;

    private boolean isSwipeBack;
    private float backDistance;

    private Set<SwipeInfo> swipeSet;

    public CardSwipeCallback(@NonNull Context context, @NonNull @IdRes int swipeViewId, int leftBackWidth, int rightBackWidth){
        this.context = context;
        this.swipeViewId = swipeViewId;
        this.leftBackWidth = leftBackWidth;
        this.rightBackWidth = rightBackWidth;

        this.isSwipeBack = false;
        this.backDistance = 0f;
        this.swipeSet = new HashSet<>();
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlags = 0;  //ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP;
        int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public float getSwipeEscapeVelocity(float defaultValue) {
        return Float.MAX_VALUE;
    }

    @Override
    public float getSwipeThreshold(@NonNull RecyclerView.ViewHolder viewHolder) {
        return 1.1f;
    }

    @Override
    public long getAnimationDuration(@NonNull RecyclerView recyclerView, int animationType, float animateDx, float animateDy) {
        this.backDistance = animateDx;
        long duration = super.getAnimationDuration(recyclerView, animationType, animateDx, animateDy)*2;
        return duration;
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView,
                          @NonNull RecyclerView.ViewHolder viewHolder,
                          @NonNull RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

    }

    @Override
    public void onChildDraw(@NonNull Canvas c,
                            @NonNull RecyclerView recyclerView,
                            @NonNull RecyclerView.ViewHolder viewHolder,
                            float dX, float dY,
                            int actionState,
                            boolean isCurrentlyActive) {
        float newDx = dX;
        boolean isShowLeftBack = backDistance < 0 && leftBackWidth > 0 && Math.abs(backDistance) >  leftBackWidth;
        boolean isShowRightBack = backDistance > 0 && rightBackWidth > 0 && backDistance >  leftBackWidth;

        SwipeInfo swipeInfo = new SwipeInfo(viewHolder.getItemId());

        if(isSwipeBack && (isShowLeftBack || isShowRightBack)){
            boolean isShowBack = false;
            if(isShowLeftBack && newDx < leftBackWidth){
                newDx = leftBackWidth;
                isShowBack = true;
            }
            if(isShowRightBack && Math.abs(newDx) < rightBackWidth){
                newDx = 0 - rightBackWidth;
                isShowBack = true;
            }
            if(isShowBack){
                swipeInfo.translationX = newDx;
                swipeSet.add(swipeInfo);
            }
        }else{
            swipeSet.remove(swipeInfo);
        }

        CardSwipeUIUtilImpl.INSTANCE.onDraw(c, recyclerView, getSwipeView(viewHolder), newDx, dY, actionState, isCurrentlyActive);
    }

    @Override
    public void onChildDrawOver(@NonNull Canvas c,
                                @NonNull RecyclerView recyclerView,
                                RecyclerView.ViewHolder viewHolder,
                                float dX, float dY,
                                int actionState,
                                boolean isCurrentlyActive) {
        CardSwipeUIUtilImpl.INSTANCE.onDrawOver(c, recyclerView, getSwipeView(viewHolder), dX, dY, actionState, isCurrentlyActive);
    }

    @Override
    public void clearView(@NonNull RecyclerView recyclerView,
                          @NonNull RecyclerView.ViewHolder viewHolder) {

        CardSwipeUIUtilImpl.INSTANCE.clearView(getSwipeView(viewHolder));
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        if(viewHolder == null){
            isSwipeBack = true;
        }else{
            isSwipeBack = false;
            CardSwipeUIUtilImpl.INSTANCE.onSelected(getSwipeView(viewHolder));
        }
    }

    private View getSwipeView(RecyclerView.ViewHolder viewHolder){
        View cardView = viewHolder.itemView.findViewById(this.swipeViewId);
        return cardView;
    }

    public void onBindViewHolder(BaseViewHolder holder) {
        SwipeInfo swipeInfo = null;
        for(SwipeInfo si:swipeSet){
            if(holder.getItemId() ==  si.itemId){
                swipeInfo = si;
                break;
            }
        }
        if(swipeInfo != null){
            getSwipeView(holder).setTranslationX(swipeInfo.translationX);

        }else{
            getSwipeView(holder).setTranslationX(0);
        }
    }

    public void onItemRemove(long itemId) {
        SwipeInfo swipeInfo = null;
        for(SwipeInfo si:swipeSet){
            if(si.itemId == itemId){
                swipeInfo = si;
                break;
            }
        }
        if(swipeInfo != null){
            swipeSet.remove(swipeInfo);
        }
    }
}
