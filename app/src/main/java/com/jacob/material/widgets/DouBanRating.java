/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-19 上午9:42
 *
 */

package com.jacob.material.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.jacob.utils.WidgetsUtils;
import com.jacob.material.R;

public class DouBanRating extends View {
    private float width;
    private float height;
    private Paint bgPaint;
    private Paint barPaint;

    private Paint startPaint;
    private Paint ratingPaint;
    private float space;
    private float lineHeight;
    private float[] rates;

    public DouBanRating(Context context) {
        this(context, null);
    }
    public DouBanRating(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);

    }
    public DouBanRating(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.width = -1;
        this.height = -1;

        bgPaint = new Paint();
        bgPaint.setStyle(Paint.Style.FILL);
        bgPaint.setColor(WidgetsUtils.getColorValue(this.getContext(), R.color.transparent));

        barPaint = new Paint();
        barPaint.setStyle(Paint.Style.FILL);
        barPaint.setColor(WidgetsUtils.getColorValue(this.getContext(), R.color.transparent_white_05_01));

        startPaint = new Paint();
        startPaint.setStyle(Paint.Style.FILL);
        startPaint.setAntiAlias(true);
        startPaint.setColor(WidgetsUtils.getColorValue(this.getContext(), R.color.gray_500));

        ratingPaint = new Paint();
        ratingPaint.setStyle(Paint.Style.FILL);
        ratingPaint.setAntiAlias(true);
        ratingPaint.setColor(WidgetsUtils.getColorValue(this.getContext(), R.color.material_color_orange));

        rates = new float[5];
    }

    public void setRates(float[] rates){
        if(rates == null || rates.length != 5){
            this.rates = new float[5];
        }else{
            for(int i=0; i<5; i++){
                this.rates[i] = rates[i];
            }
        }

        this.invalidate();
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

        space = WidgetsUtils.dpToPx(getContext(), 2);
        lineHeight = (height - space*6)/5f;//上下各留2dp空白
        drawStars(canvas);
        drawBars(canvas);
    }

    private void drawStars(Canvas canvas){
        float radius = lineHeight/2;
        for(int row = 0; row<5; row++){
            for(int col = row; col < 5; col++){
                FloatPoint center = new FloatPoint();
                center.x = space + (col + 0.5f)*lineHeight;
                center.y = (row + 1)*space + (row + 0.5f)*lineHeight;
                FloatPoint[] points = calcStarPoints(center, radius);

                Path path = new Path();
                path.moveTo(points[0].x, points[0].y);
                for(int i=1; i<points.length; i++){
                    path.lineTo(points[i].x, points[i].y);
                }
                path.lineTo(points[0].x, points[0].y);

                canvas.drawPath(path, startPaint);
            }
        }
    }

    private void drawBars(Canvas canvas){
        float left = lineHeight*5 + space*3;
        float right = width - space;
        float barWidth = right - left;
        for(int row = 0; row<5; row++){
            float vCenter = (row + 1)*space + (row + 0.5f)*lineHeight;
            float top = vCenter - 0.35f*lineHeight;
            float bottom = vCenter + 0.35f*lineHeight;
            float rateRight = left + barWidth*rates[row];
            canvas.drawRoundRect(left, top, right, bottom, space, space, barPaint);
            canvas.drawRoundRect(left, top, rateRight, bottom, space, space, ratingPaint);
        }
    }


    private FloatPoint[] calcStarPoints(FloatPoint center, float radius){
        FloatPoint[] result = new FloatPoint[10];

        double[] outterPointRadians = new double[5];
        outterPointRadians[0] = Math.PI * 18.0/180.0;
        outterPointRadians[1] = Math.PI * 90.0/180.0;
        outterPointRadians[2] = Math.PI * 162.0/180.0;
        outterPointRadians[3] = Math.PI * 234.0/180.0;
        outterPointRadians[4] = Math.PI * 306.0/180.0;

        double[] innerPointRadians = new double[5];
        innerPointRadians[0] = Math.PI * 54.0/180.0;
        innerPointRadians[1] = Math.PI * 126.0/180.0;
        innerPointRadians[2] = Math.PI * 198.0/180.0;
        innerPointRadians[3] = Math.PI * 270.0/180.0;
        innerPointRadians[4] = Math.PI * 342.0/180.0;



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
