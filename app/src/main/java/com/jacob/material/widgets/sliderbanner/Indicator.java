/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-8 下午8:53
 *
 */

package com.jacob.material.widgets.sliderbanner;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.jacob.utils.WidgetsUtils;

public class Indicator extends View {
    private float width;
    private float height;
    private Paint bgPaint;
    private Paint currItemPaint;
    private Paint otherItemPaint;
    private float pointRadius;
    private float pointSpace;
    private int pointCount;
    private int currPointIdx;

    public Indicator(Context context) {
        this(context, null);
    }
    public Indicator(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }
    public Indicator(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.width = -1;
        this.height = -1;
        this.pointCount = 0;
        this.currPointIdx = -1;
        this.pointRadius = WidgetsUtils.dpToPx(context, 3);
        this.pointSpace = pointRadius*3;

        bgPaint = new Paint();
        bgPaint.setStyle(Paint.Style.FILL);
        bgPaint.setColor(Color.WHITE);
        bgPaint.setAlpha(60);

        currItemPaint = new Paint();
        currItemPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        currItemPaint.setAntiAlias(true);
        currItemPaint.setColor(Color.WHITE);

        otherItemPaint = new Paint();
        otherItemPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        otherItemPaint.setAntiAlias(true);
        otherItemPaint.setColor(Color.DKGRAY);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        this.width = right - left;
        this.height = bottom - top;
    }

    public void setPointCount(int pointCount){
        this.pointCount = pointCount;
        this.invalidate();
    }

    public void setCurrPointIdx(int currPointIdx){
        this.currPointIdx = currPointIdx;
        this.invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, this.width, this.height, bgPaint);

        if(this.width <=0 || this.height <=0){
            return;
        }
        if(this.pointCount <=0 || this.currPointIdx <0 || this.currPointIdx >= this.pointCount){
            return;
        }

        float allPointWidht = pointCount * pointRadius * 2;
        float allSpaceWidth = (pointCount - 1) * pointSpace;
        float pointAndSpaceWidht = allPointWidht + allSpaceWidth;
        float centerX = (this.width - pointAndSpaceWidht)/2 + pointRadius;
        float centerY = height/2;

        for(int i=0; i<pointCount; i++){
            if(i == currPointIdx){
                canvas.drawCircle(centerX, centerY, pointRadius, currItemPaint);
            }else{
                canvas.drawCircle(centerX, centerY, pointRadius, otherItemPaint);
            }
            centerX = centerX + pointRadius*2 + pointSpace;
        }




    }



}
