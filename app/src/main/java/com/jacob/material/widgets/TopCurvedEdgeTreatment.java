/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-2 下午1:16
 *
 */

package com.jacob.material.widgets;

import androidx.annotation.NonNull;

import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.shape.ShapePath;

public class TopCurvedEdgeTreatment extends EdgeTreatment {
    private float fabCradleMargin;
    private float fabCradleRoundedCornerRadius;
    private float cradleVerticalOffset;

    private float fabDiameter;
    private float horizontalOffset;

    public TopCurvedEdgeTreatment(float fabCradleMargin, float fabCradleRoundedCornerRadius, float cradleVerticalOffset){
        if (cradleVerticalOffset < 0){
            throw new IllegalArgumentException("cradleVerticalOffset must be positive.");
        }

        this.fabCradleMargin = fabCradleMargin;
        this.fabCradleRoundedCornerRadius = fabCradleRoundedCornerRadius;
        this.cradleVerticalOffset = cradleVerticalOffset;
        this.fabDiameter = 0f;
        this.horizontalOffset = 0f;
    }

    public void setFabDiameter(float fabDiameter) {
        this.fabDiameter = fabDiameter;
    }

    public void setHorizontalOffset(float horizontalOffset) {
        this.horizontalOffset = horizontalOffset;
    }

    @Override
    public void getEdgePath(float length, float center, float interpolation, @NonNull ShapePath shapePath) {
        if (this.fabDiameter == 0.0f) {
            shapePath.lineTo(length, 0.0f);
        } else {
            float cradleDiameter = this.fabCradleMargin * 2.0f + this.fabDiameter;
            float cradleRadius = cradleDiameter / 2.0f;
            float roundedCornerOffset = interpolation * this.fabCradleRoundedCornerRadius;
            float middle = length / 2.0f + this.horizontalOffset;
            float verticalOffset = interpolation * this.cradleVerticalOffset + (1.0f - interpolation) * cradleRadius;
            float verticalOffsetRatio = verticalOffset / cradleRadius;
            if (verticalOffsetRatio >= 1.0f) {
                shapePath.lineTo(length, 0.0f);
            } else {
                float distanceBetweenCenters = cradleRadius + roundedCornerOffset;
                float distanceBetweenCentersSquared = distanceBetweenCenters * distanceBetweenCenters;
                float distanceY = verticalOffset + roundedCornerOffset;
                float distanceX = (float)Math.sqrt((double)(distanceBetweenCentersSquared - distanceY * distanceY));
                float leftRoundedCornerCircleX = middle - distanceX;
                float rightRoundedCornerCircleX = middle + distanceX;
                float cornerRadiusArcLength = (float) Math.toDegrees((double) Math.atan((distanceX / distanceY)));
                float cutoutArcOffset = 90.0f - cornerRadiusArcLength;
                shapePath.lineTo(leftRoundedCornerCircleX - roundedCornerOffset, 0.0f);
                shapePath.addArc(
                        leftRoundedCornerCircleX - roundedCornerOffset,
                        0.0f,
                        leftRoundedCornerCircleX + roundedCornerOffset,
                        roundedCornerOffset * 2.0f,
                        270.0f,
                        cornerRadiusArcLength
                );
                shapePath.addArc(
                        middle - cradleRadius,
                        -cradleRadius - verticalOffset,
                        middle + cradleRadius,
                        cradleRadius - verticalOffset,
                        180.0f - cutoutArcOffset,
                        cutoutArcOffset * 2.0f - 180.0f
                );
                shapePath.addArc(
                        rightRoundedCornerCircleX - roundedCornerOffset,
                        0.0f,
                        rightRoundedCornerCircleX + roundedCornerOffset,
                        roundedCornerOffset * 2.0f,
                        270.0f - cornerRadiusArcLength,
                        cornerRadiusArcLength
                );
                shapePath.lineTo(length, 0.0f);
            }
        }
    }
}
