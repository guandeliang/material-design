/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-8 下午8:49
 *
 */

package com.jacob.material.widgets;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.OrientationHelper;
import androidx.recyclerview.widget.RecyclerView;

public class HorizontalEdgeSnapHelper extends LinearSnapHelper {
    // Orientation helpers are lazily created per LayoutManager.
    @Nullable
    private OrientationHelper mHorizontalHelper;

    @Nullable
    @Override
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View targetView) {
        int[] out = new int[2];//out[0] 为水平方向距离，out[1] 为垂直方向距离
        if (layoutManager.canScrollHorizontally()) {
            out[0] = distanceToEdge(targetView, getHorizontalHelper(layoutManager));
        } else {
            out[0] = 0;
        }
        out[1] = 0;

        return out;
    }

    @Nullable
    @Override
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        if (layoutManager.canScrollHorizontally()) {
            return findClosedView(layoutManager, getHorizontalHelper(layoutManager));
        } else {
            return null;
        }
    }


    @Nullable
    private View findClosedView(RecyclerView.LayoutManager layoutManager, OrientationHelper helper) {
        int childCount = layoutManager.getChildCount();
        if (childCount == 0) {
            return null;
        }

        View closestChild = null;
        final int containerStart = helper.getStartAfterPadding();
        final int containerEnd = helper.getEndAfterPadding();

        int absClosest = Integer.MAX_VALUE;

        for (int i = 0; i < childCount; i++) {
            final View child = layoutManager.getChildAt(i);

            int childStart = child.getLeft();
            int childEnd = child.getRight();

            int startDistance = Math.abs(childStart - containerStart);
            int endDistance = Math.abs(childEnd - containerEnd);
            int distance = Math.min(startDistance, endDistance);

            if (distance < absClosest) {
                absClosest = distance;
                closestChild = child;
            }
        }
        return closestChild;
    }


    private int distanceToEdge(@NonNull View targetView, OrientationHelper helper) {
        final int childStart = targetView.getLeft();
        final int childEnd = targetView.getRight();

        final int containerStart = helper.getStartAfterPadding();
        final int containerEnd = helper.getEndAfterPadding();

        int startDistance = childStart - containerStart;
        int endDistance = childEnd - containerEnd;

        if(Math.abs(startDistance) <= Math.abs(endDistance)){
            return startDistance;
        }else{
            return endDistance;
        }
    }


    @NonNull
    private OrientationHelper getHorizontalHelper(@NonNull RecyclerView.LayoutManager layoutManager) {
        if (mHorizontalHelper == null || mHorizontalHelper.getLayoutManager() != layoutManager) {
            mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return mHorizontalHelper;
    }
}
