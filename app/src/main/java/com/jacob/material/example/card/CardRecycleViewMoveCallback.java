package com.jacob.material.example.card;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;


public class CardRecycleViewMoveCallback extends ItemTouchHelper.Callback{
    private int lastActionState;
    private RecyclerView.ViewHolder lastSelectedViewHolder;
    private RecyclerView.ViewHolder lastTargetViewHolder;

    public interface ItemTouchHelperAdapter {
        void onItemMove(int fromPosition, int toPosition);
        void onItemDismiss(int position);
    }

    private ItemTouchHelperAdapter adapter;


    public CardRecycleViewMoveCallback() {
        this.lastActionState = ItemTouchHelper.ACTION_STATE_IDLE;
        this.lastSelectedViewHolder = null;
        this.lastTargetViewHolder = null;
    }

    public void setAdapter(ItemTouchHelperAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
        int dragFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.DOWN | ItemTouchHelper.UP;
        int swipeFlags = ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT;
        return makeMovementFlags(dragFlags, swipeFlags);
    }


    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView,
                          @NonNull RecyclerView.ViewHolder viewHolder,
                          @NonNull RecyclerView.ViewHolder target) {
        if(this.lastTargetViewHolder != null){
            this.lastTargetViewHolder.itemView.setActivated(false);
        }
        this.lastTargetViewHolder = target;
        this.lastTargetViewHolder.itemView.setActivated(true);
        return false;//false避免自动提交移动状态，否则会跟onSelectedChanged冲突
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        if(adapter != null){
            adapter.onItemDismiss(viewHolder.getAdapterPosition());
        }
    }

    @Override
    public void onSelectedChanged(@Nullable RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if(actionState == ItemTouchHelper.ACTION_STATE_IDLE){
            boolean isDragOver = this.lastActionState == ItemTouchHelper.ACTION_STATE_DRAG &&
                                 this.lastSelectedViewHolder != null &&
                                 this.lastTargetViewHolder != null &&
                                !this.lastSelectedViewHolder.equals(this.lastTargetViewHolder);

            if(isDragOver && adapter != null){
                adapter.onItemMove(this.lastSelectedViewHolder.getAdapterPosition(), this.lastTargetViewHolder.getAdapterPosition());
            }

            this.lastActionState = ItemTouchHelper.ACTION_STATE_IDLE;
            this.lastSelectedViewHolder.itemView.setSelected(false);
            this.lastSelectedViewHolder.itemView.animate().scaleX(1f).scaleY(1f);
            this.lastSelectedViewHolder = null;

            if(this.lastTargetViewHolder != null && this.lastTargetViewHolder.itemView != null){
                this.lastTargetViewHolder.itemView.setActivated(false);
            }
            this.lastTargetViewHolder = null;
        }else{
            lastActionState = actionState;
            lastSelectedViewHolder = viewHolder;
            lastSelectedViewHolder.itemView.setSelected(true);
            lastSelectedViewHolder.itemView.animate().scaleX(0.75f).scaleY(0.75f);
        }
    }
}
