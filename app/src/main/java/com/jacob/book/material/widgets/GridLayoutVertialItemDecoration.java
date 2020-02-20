/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-14 下午9:57
 *
 */

package com.jacob.book.material.widgets;

import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.jacob.book.temp.TempConstant;

public class GridLayoutVertialItemDecoration extends RecyclerView.ItemDecoration {
    private int tSpace;
    private int bSpace;
    private int colSpace;
    private int rowSpace;


    public GridLayoutVertialItemDecoration(int tSpace, int bSpace, int colSpace, int rowSpace) {
        this.tSpace = tSpace;
        this.bSpace = bSpace;
        this.colSpace = colSpace;
        this.rowSpace = rowSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();

        if(!(layoutManager instanceof GridLayoutManager)){
            return;
        }

        GridLayoutManager gridLayoutManager = (GridLayoutManager)layoutManager;
        if(gridLayoutManager.getOrientation() != RecyclerView.VERTICAL){
            return;
        }

        int spanCount = gridLayoutManager.getSpanCount();
        int viewCount = parent.getAdapter().getItemCount();
        int viewPosition = parent.getChildLayoutPosition (view);
        if(viewCount == 0){
            return;
        }




        GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();
        int spanGroupIndex = spanSizeLookup.getSpanGroupIndex(viewPosition, spanCount);//row index
        int spanIndex = spanSizeLookup.getSpanIndex(viewPosition, spanCount);//column index
        int spanSize = spanSizeLookup.getSpanSize(viewPosition);//column span
        int spanGroupCount = spanSizeLookup.getSpanGroupIndex(viewCount - 1, spanCount) + 1;//row count


        float fSpanCount = (float)spanCount;
        float fStartSpanIndex = (float)spanIndex;
        float fEndSpanIndex = (float)(spanIndex + spanSize - 1);
        float fColSpace = (float)colSpace;

        outRect.left = Math.round((fStartSpanIndex/fSpanCount)*fColSpace);
        outRect.right = Math.round(((fSpanCount - fEndSpanIndex - 1)/fSpanCount)*fColSpace);


        if(spanGroupIndex == 0){
            outRect.top = tSpace;
        }else{
            outRect.top = rowSpace/2;
        }

        if(spanGroupIndex == spanGroupCount - 1){
            outRect.bottom = bSpace;
        }else{
            outRect.bottom = rowSpace/2;
        }

    }
}
