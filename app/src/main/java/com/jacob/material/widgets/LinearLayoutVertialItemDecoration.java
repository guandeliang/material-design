/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-14 下午10:02
 *
 */

package com.jacob.material.widgets;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LinearLayoutVertialItemDecoration extends RecyclerView.ItemDecoration {
    private int tSpace;
    private int bSpace;
    private int vSpace;


    public LinearLayoutVertialItemDecoration(int tSpace, int bSpace, int vSpace) {
        this.tSpace = tSpace;
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

        outRect.left = 0;
        outRect.right = 0;

        if(viewCount == 1){
            outRect.top = tSpace;
            outRect.bottom = bSpace;
            return;
        }


        if(viewPosition == 0){
            outRect.top = tSpace;
            outRect.bottom = vSpace/2;
        }else if(viewPosition == viewCount - 1){
            outRect.top = vSpace/2;
            outRect.bottom = bSpace;
        }else {
            outRect.top = vSpace/2;
            outRect.bottom = vSpace/2;
        }
    }
}
