/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-23 上午10:37
 *
 */

package com.jacob.book.material.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

import com.jacob.book.WidgetsUtils;
import com.jacob.book.material.R;
import com.jacob.book.temp.TempConstant;

public class MaterialRatingBar extends View {
    private float width;
    private float height;
    private Paint bgPaint;
    private Paint lightPaint;
    private Paint darkPaint;
    private float space;

    public MaterialRatingBar(Context context) {
        this(context, null);
    }
    public MaterialRatingBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }
    public MaterialRatingBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.width = -1;
        this.height = -1;

        bgPaint = new Paint();
        bgPaint.setStyle(Paint.Style.FILL);
        bgPaint.setColor(WidgetsUtils.getColorValue(this.getContext(), R.color.transparent_black_16_24));

        lightPaint = new Paint();
        lightPaint.setStyle(Paint.Style.FILL);
        lightPaint.setAntiAlias(true);
        lightPaint.setColor(WidgetsUtils.getColorValue(this.getContext(), R.color.gray_500));

        darkPaint = new Paint();
        darkPaint.setStyle(Paint.Style.FILL);
        darkPaint.setAntiAlias(true);
        darkPaint.setColor(WidgetsUtils.getColorValue(this.getContext(), R.color.material_color_orange));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        this.width = right - left;
        this.height = bottom - top;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, this.width, this.height, bgPaint);

        if(this.width <=0 || this.height <=0 ){
            return;
        }

        float colWidth = this.width/5;
        float radius = (Math.min(this.height, colWidth) - WidgetsUtils.dpToPx(getContext(), 2))/2f;


        space = WidgetsUtils.dpToPx(getContext(), 2);
        drawDarkStars(canvas, colWidth, radius);
        drawLightStars(canvas, colWidth, radius);
    }

    private void drawDarkStars(Canvas canvas, float colWidth, float radius){
        float centerY = this.height/2f;
        for(int col = 0; col < 5; col++){
            FloatPoint center = new FloatPoint();
            center.x =  (col + 0.5f)*colWidth;
            center.y = centerY;
            FloatPoint[] points = calcStarPoints(center, radius);

            Path path = new Path();
            path.moveTo(points[0].x, points[0].y);
            for(int i=1; i<points.length; i++){
                path.lineTo(points[i].x, points[i].y);
            }
            path.lineTo(points[0].x, points[0].y);

            canvas.drawPath(path, darkPaint);
        }
    }

    private void drawLightStars(Canvas canvas, float colWidth, float radius){
        float rate = 0.7f;
        float centerY = this.height/2f;
        for(int col = 0; col < 5; col++){
            if(rate >= 0.2f){//绘制完整的五角星
                FloatPoint center = new FloatPoint();
                center.x =  (col + 0.5f)*colWidth;
                center.y = centerY;
                FloatPoint[] points = calcStarPoints(center, radius);

                Path path = new Path();
                path.moveTo(points[0].x, points[0].y);
                for(int i=1; i<points.length; i++){
                    path.lineTo(points[i].x, points[i].y);
                }
                path.lineTo(points[0].x, points[0].y);
                canvas.drawPath(path, lightPaint);
            }else if(rate > 0 && rate < 0.2f){
                float starWidth = (float)(2f * radius * Math.cos(Math.PI * 18f/180f));
                float clipWidth = col * colWidth + (colWidth - starWidth)/2 + starWidth*rate/0.2f;
                canvas.clipRect(0, 0, clipWidth, this.height);

                Log.d(TempConstant.LOG_TAG, "radius = " + radius);
                Log.d(TempConstant.LOG_TAG, "colWidth = " + colWidth);
                Log.d(TempConstant.LOG_TAG, "starWidth = " + starWidth);
                Log.d(TempConstant.LOG_TAG, "clipWidth = " + clipWidth);

                FloatPoint center = new FloatPoint();
                center.x =  (col + 0.5f)*colWidth;
                center.y = centerY;
                FloatPoint[] points = calcStarPoints(center, radius);

                Path path = new Path();
                path.moveTo(points[0].x, points[0].y);
                for(int i=1; i<points.length; i++){
                    path.lineTo(points[i].x, points[i].y);
                }
                path.lineTo(points[0].x, points[0].y);
                canvas.drawPath(path, lightPaint);


            }
            rate = rate - 0.2f;
        }
    }


    private FloatPoint[] calcStarPoints(FloatPoint center, float radius){
        FloatPoint[] result = new FloatPoint[10];

        double[] outterPointRadians = new double[5];
        outterPointRadians[0] = Math.PI * 18f/180f;
        outterPointRadians[1] = Math.PI * 90f/180f;
        outterPointRadians[2] = Math.PI * 162f/180f;
        outterPointRadians[3] = Math.PI * 234f/180f;
        outterPointRadians[4] = Math.PI * 306f/180f;

        double[] innerPointRadians = new double[5];
        innerPointRadians[0] = Math.PI * 54f/180f;
        innerPointRadians[1] = Math.PI * 126f/180f;
        innerPointRadians[2] = Math.PI * 198f/180f;
        innerPointRadians[3] = Math.PI * 270f/180f;
        innerPointRadians[4] = Math.PI * 342f/180f;



        float longRadius = radius;
        float shortRadius = (float)(longRadius * Math.sin(Math.PI*18f/180f) / Math.sin(Math.PI*126f/180f));

        for(int i=0; i<5; i++){
            FloatPoint outterPint  = new FloatPoint();
            outterPint.x = center.x + (float)(longRadius * Math.cos(outterPointRadians[i]));
            outterPint.y = center.y - (float)(longRadius * Math.sin(outterPointRadians[i]));

            FloatPoint innerPint  = new FloatPoint();
            innerPint.x = center.x + (float)(shortRadius * Math.cos(innerPointRadians[i]));
            innerPint.y = center.y - (float)(shortRadius * Math.sin(innerPointRadians[i]));
            result[i*2] = outterPint;
            result[i*2 + 1] = innerPint;
        }

        return result;
    }

    private class FloatPoint{
        float x;
        float y;
    }


}
