/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-28 下午10:44
 *
 */

package com.jacob.book.material.widgets;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;

import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment;
import com.google.android.material.shape.ShapePath;

import static androidx.annotation.RestrictTo.Scope.LIBRARY_GROUP;

public class BottomAppBarCutCornersTopEdge extends BottomAppBarTopEdgeTreatment {
    private final float fabMargin;
    private final float cradleVerticalOffset;

    public BottomAppBarCutCornersTopEdge(float fabMargin, float roundedCornerRadius, float cradleVerticalOffset) {
        super(fabMargin, roundedCornerRadius, cradleVerticalOffset);
        this.fabMargin = fabMargin;
        this.cradleVerticalOffset = cradleVerticalOffset;
    }


    @Override
    @SuppressLint("RestrictedApi")
    public void getEdgePath(float length, float center, float interpolation, @NonNull ShapePath shapePath) {
        float fabDiameter = getFabDiameter();
        if (fabDiameter == 0) {
            shapePath.lineTo(length, 0);
            return;
        }

        float diamondSize = fabDiameter / 2f;
        float middle = center + getHorizontalOffset();

        float verticalOffsetRatio = cradleVerticalOffset / diamondSize;
        if (verticalOffsetRatio >= 1.0f) {
            shapePath.lineTo(length, 0);
            return;
        }

        shapePath.lineTo(middle - (fabMargin + diamondSize - cradleVerticalOffset), 0);

        shapePath.lineTo(middle, (diamondSize - cradleVerticalOffset + fabMargin) * interpolation);

        shapePath.lineTo(middle + (fabMargin + diamondSize - cradleVerticalOffset), 0);

        shapePath.lineTo(length, 0);
    }
}
