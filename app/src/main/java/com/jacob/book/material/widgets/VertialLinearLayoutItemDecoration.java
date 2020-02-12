package com.jacob.book.material.widgets;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class VertialLinearLayoutItemDecoration extends RecyclerView.ItemDecoration {
    private int lSpace;
    private int tSpace;
    private int rSpace;
    private int bSpace;
    private int vSpace;


    public VertialLinearLayoutItemDecoration(int lSpace, int tSpace, int rSpace, int bSpace, int vSpace) {
        this.lSpace = lSpace;
        this.tSpace = tSpace;
        this.rSpace = rSpace;
        this.bSpace = bSpace;
        this.vSpace = vSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        if(!(layoutManager instanceof LinearLayoutManager)){
            return;
        }

        LinearLayoutManager vLinearLayoutManager = (LinearLayoutManager)layoutManager;
        if(vLinearLayoutManager.getOrientation() != RecyclerView.VERTICAL){
            return;
        }

        int viewCount = parent.getAdapter().getItemCount();
        int viewPosition = ((RecyclerView.LayoutParams) view.getLayoutParams()).getViewLayoutPosition();
        if(viewCount == 0){
            return;
        }

        if(viewCount == 1){
            outRect.left = lSpace;
            outRect.top = tSpace;
            outRect.right = rSpace;
            outRect.bottom = bSpace;
            return;
        }


        if(viewPosition == 0){
            outRect.left = lSpace;
            outRect.top = tSpace;
            outRect.right = rSpace;
            outRect.bottom = vSpace/2;
        }else if(viewPosition == viewCount - 1){
            outRect.left = lSpace;
            outRect.top = vSpace/2;
            outRect.right = rSpace;
            outRect.bottom = bSpace;
        }else {
            outRect.left = lSpace;
            outRect.top = vSpace/2;
            outRect.right = rSpace;
            outRect.bottom = vSpace/2;
        }
    }
}
