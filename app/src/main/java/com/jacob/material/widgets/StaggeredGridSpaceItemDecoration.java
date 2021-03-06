/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-3 下午10:02
 *
 */

package com.jacob.material.widgets;

import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

public class StaggeredGridSpaceItemDecoration extends RecyclerView.ItemDecoration {
    private int space;
    private int columnCount;

    public StaggeredGridSpaceItemDecoration(int space, int columnCount) {
        this.space = space;
        this.columnCount = columnCount;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        outRect.top = space;
        StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) view.getLayoutParams();

        if(params.isFullSpan()){
            outRect.left = space;
            outRect.right = space;
        }else if (params.getSpanIndex() % columnCount == 0) {
            outRect.left = space;
            outRect.right = space / columnCount;
        } else {
            outRect.left = space / columnCount;
            outRect.right = space;
        }
    }
}
