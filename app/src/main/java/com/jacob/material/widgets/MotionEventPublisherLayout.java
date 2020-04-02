/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-24 下午8:54
 *
 */

package com.jacob.material.widgets;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowInsets;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GestureDetectorCompat;

import com.jacob.temp.TempConstant;

import java.util.HashMap;
import java.util.Map;


public class MotionEventPublisherLayout extends FrameLayout {

    private Map<View, GestureDetectorCompat> subscriberMap;
    private int statusBarHeight;

    public MotionEventPublisherLayout(@NonNull Context context) {
        this(context, null);
    }

    public MotionEventPublisherLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MotionEventPublisherLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        subscriberMap = new HashMap<>();
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Rect displayFrameRect = new Rect();
        this.getWindowVisibleDisplayFrame(displayFrameRect);
        statusBarHeight = displayFrameRect.top;


        for(Map.Entry<View, GestureDetectorCompat> entry : subscriberMap.entrySet()){
            View view = entry.getKey();
            GestureDetectorCompat detector = entry.getValue();

            int[] viewLocationOnScreen = new int[2];
            view.getLocationOnScreen(viewLocationOnScreen);

            int left = viewLocationOnScreen[0];
            int top = viewLocationOnScreen[1] - statusBarHeight;
            int right = viewLocationOnScreen[0] + view.getWidth();
            int bottom = viewLocationOnScreen[1] + view.getHeight() - statusBarHeight;

            Rect r = new Rect();
            r.set(left, top, right, bottom);

//            Log.d(TempConstant.LOG_TAG, view.getTag() +  " = " + r.toShortString() + "   " + event.getY() + ", " + event.getY() + "  " + statusBarHeight);

            if(r.contains((int)event.getX(), (int)event.getY())){
                detector.onTouchEvent(event);
            }
        }
        return super.dispatchTouchEvent(event);
    }

    public void addSubscriber(View view, GestureDetectorCompat detector){
        subscriberMap.put(view, detector);
    }


}
