/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-19 上午10:43
 *
 */

package com.jacob.book.material.test;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.material.shape.EdgeTreatment;
import com.google.android.material.shape.ShapePath;
import com.jacob.book.temp.TempConstant;

public class OutsideTriangleEdgeTreatment extends EdgeTreatment {
    public static int Edge_POSITION_LEFT = 1;
    public static int Edge_POSITION_BOTTOM = 2;
    public static int Edge_POSITION_RIGHT = 3;

    private int edgePosition;
    private double angle;

    public OutsideTriangleEdgeTreatment(int edgePosition, float angle){
        this.edgePosition = edgePosition;
        this.angle = (angle/360)*Math.PI;
    }


    @Override
    public void getEdgePath(float length, float center, float interpolation, @NonNull ShapePath shapePath) {
        if(edgePosition == Edge_POSITION_LEFT){
            getLeftEdgePath(length, shapePath);
        }else if(edgePosition == Edge_POSITION_BOTTOM){
            getBottomEdgePath(length, shapePath);
        }else if(edgePosition == Edge_POSITION_RIGHT){
            getRightEdgePath(length, shapePath);
        }
    }

    private void getLeftEdgePath(float length, @NonNull ShapePath shapePath){
        float startY  = (float)(Math.tan(angle)*length);
        shapePath.reset(0, startY);
        shapePath.lineTo(length, 0);
        Log.d(TempConstant.LOG_TAG, "getLeftEdgePath startY = " + startY);
    }

    private void getBottomEdgePath(float length, @NonNull ShapePath shapePath){

        float dx  = 80.38449f;//(float)(Math.tan(angle)*length);
        float startX = dx;
        float endX = length- startX;
        shapePath.reset(startX, 0);
        shapePath.lineTo(endX, 0);

        Log.d(TempConstant.LOG_TAG, "getBottomEdgePath startX = " + startX + " shapePath.startX " + shapePath.startX);
        Log.d(TempConstant.LOG_TAG, "getBottomEdgePath endX = " + endX + " shapePath.endX " + shapePath.endX);
    }


    private void getRightEdgePath(float length, @NonNull ShapePath shapePath){
        float endY  = (float)(Math.tan(angle)*length);
        shapePath.lineTo(length, endY);
        Log.d(TempConstant.LOG_TAG, "getRightEdgePath endY = " + endY);
    }

}
