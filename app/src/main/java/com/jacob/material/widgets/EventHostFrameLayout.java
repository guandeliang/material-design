/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-26 上午11:57
 *
 */

package com.jacob.material.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.jacob.temp.TempConstant;


public class EventHostFrameLayout extends FrameLayout {
    public interface OnMotionEventListener{
        public void onEventHostMotion(EventHostFrameLayout view, MotionEvent ev);
    }

    private OnMotionEventListener listener;

    public EventHostFrameLayout(@NonNull Context context) {
        this(context, null);
    }

    public EventHostFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EventHostFrameLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnMotionEventListener(OnMotionEventListener listener){
        this.listener = listener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if(this.listener != null){
            this.listener.onEventHostMotion(this, ev);
            return false;
        }else{
            return true;
        }
    }
}
