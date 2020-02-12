package com.jacob.book.material.widgets;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class HorizontalLinearLayoutItemDecoration extends RecyclerView.ItemDecoration {
    private int lSpace;
    private int tSpace;
    private int rSpace;
    private int bSpace;
    private int hSpace;


    public HorizontalLinearLayoutItemDecoration(int lSpace, int tSpace, int rSpace, int bSpace, int hSpace) {
        this.lSpace = lSpace;
        this.tSpace = tSpace;
        this.rSpace = rSpace;
        this.bSpace = bSpace;
        this.hSpace = hSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        if(!(layoutManager instanceof LinearLayoutManager)){
            return;
        }

        LinearLayoutManager hLinearLayoutManager = (LinearLayoutManager)layoutManager;
        if(hLinearLayoutManager.getOrientation() != RecyclerView.HORIZONTAL){
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
            outRect.right = hSpace/2;
            outRect.bottom = bSpace;
        }else if(viewPosition == viewCount - 1){
            outRect.left = hSpace/2;
            outRect.top = tSpace;
            outRect.right = rSpace;
            outRect.bottom = bSpace;
        }else {
            outRect.left = hSpace/2;
            outRect.top = tSpace;
            outRect.right = hSpace/2;
            outRect.bottom = bSpace;
        }
    }
}
