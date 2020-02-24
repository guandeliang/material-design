/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-24 下午8:54
 *
 */

package com.jacob.book.material.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class NestedScrollableHost extends FrameLayout {
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;

    private int touchSlop = 0;
    private float initialX = 0f;
    private float initialY = 0f;
    private int orientation;

    public NestedScrollableHost(@NonNull Context context) {
        this(context, null);
    }

    public NestedScrollableHost(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NestedScrollableHost(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        this.orientation = -1;
    }

    public void setOrientation(int orientation){
        this.orientation = orientation;
    }

    private View getChild(){
        if(this.getChildCount() > 0){
            return this.getChildAt(0);
        }else{
            return null;
        }
    }

    private boolean canChildScroll(int orientation, float delta){
        View child = this.getChild();
        if(child == null){
            return false;
        }
        int direction = (int) (0 - Math.signum(delta));

        if(orientation == HORIZONTAL){
            return child.canScrollHorizontally(direction);
        }else if(orientation == VERTICAL){
            return child.canScrollVertically(direction);
        }else{
            throw new IllegalArgumentException();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        handleInterceptTouchEvent(event);
        return super.onInterceptTouchEvent(event);
    }

    //本方法用于解决元件嵌套在可以横向滑动的其他ViewPager2中时，无法滑动问题。
    private void handleInterceptTouchEvent(MotionEvent event){
        if(orientation < 0 || orientation > 1 ){
            return;
        }

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            initialX = event.getX();
            initialY = event.getY();
            this.getParent().requestDisallowInterceptTouchEvent(true);
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            float dx = event.getX() - initialX;
            float dy = event.getY() - initialY;

            float scaledDx = Math.abs(dx * 0.5f);
            float scaledDy = Math.abs(dy * 1f);
            boolean isHorizontal = orientation == HORIZONTAL;
            if(scaledDx > touchSlop || scaledDy > touchSlop){
                if(isHorizontal == (scaledDy > touchSlop)){
                    this.getParent().requestDisallowInterceptTouchEvent(false);
                }else{
                    float delta = 0;
                    if(orientation == HORIZONTAL){
                        delta = dx;
                    }else{
                        delta = dy;
                    }
                    if(canChildScroll(orientation, delta)){
                        this.getParent().requestDisallowInterceptTouchEvent(true);
                    }else{
                        this.getParent().requestDisallowInterceptTouchEvent(false);
                    }
                }
            }
        }

    }


}
