/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-24 下午8:54
 *
 */

package com.jacob.material.test;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

import com.jacob.temp.TempConstant;


public class EventTextView extends AppCompatTextView {
    public EventTextView(@NonNull Context context) {
        super(context);
    }

    public EventTextView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public EventTextView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
/*

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        String tag = (String)this.getTag();
        Log.d(TempConstant.LOG_TAG, tag + " dispatchTouchEvent before call super " + MotionEvent.actionToString(event.getAction()));
        boolean result = super.dispatchTouchEvent(event);
        Log.d(TempConstant.LOG_TAG, tag + " dispatchTouchEvent after call super " + MotionEvent.actionToString(event.getAction()) + " " +result);
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        String tag = (String)this.getTag();
        Log.d(TempConstant.LOG_TAG, tag + " onTouchEvent before call super " + MotionEvent.actionToString(event.getAction()));
        boolean result = super.onTouchEvent(event);
        Log.d(TempConstant.LOG_TAG, tag + " onTouchEvent after call super " + MotionEvent.actionToString(event.getAction()) + " " +result);
        return result;
    }

 */

}
