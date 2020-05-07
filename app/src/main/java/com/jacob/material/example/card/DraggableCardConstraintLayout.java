package com.jacob.material.example.card;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.customview.widget.ViewDragHelper;
import androidx.customview.widget.ViewDragHelper.Callback;

import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

public class DraggableCardConstraintLayout extends ConstraintLayout {
    public interface ViewDragListener {
        public void onViewCaptured(@NonNull MaterialCardView cardView, int left, int top);
        public void onViewMove(@NonNull MaterialCardView cardView, int newLeft, int newTop);
        public void onViewReleased(@NonNull MaterialCardView cardView, int left, int top, float xVel, float yVel);
    }

    private final ViewDragHelper viewDragHelper;
    private final List<MaterialCardView> cardViewList;
    private ViewDragListener viewDragListener;

    public DraggableCardConstraintLayout(Context context) {
        this(context, null);
    }

    public DraggableCardConstraintLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.cardViewList = new ArrayList<>();
        this.viewDragHelper = ViewDragHelper.create(this, dragCallback);
    }

    public void addDraggableCardView(MaterialCardView cardView) {
        if (cardView.getParent() != this) {
            throw new IllegalArgumentException();
        }
        cardViewList.add(cardView);
    }

    public void removeDraggableCardView(MaterialCardView cardView) {
        if (cardView.getParent() != this) {
            throw new IllegalArgumentException();
        }
        cardViewList.remove(cardView);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return viewDragHelper.shouldInterceptTouchEvent(ev) || super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        viewDragHelper.processTouchEvent(ev);
        return super.onTouchEvent(ev);
    }

    private final Callback dragCallback = new Callback() {
        @Override
        public boolean tryCaptureView(View view, int pointerId) {
            for(MaterialCardView cardView:cardViewList){
                if(cardView.isDragged()){
                    return false;
                }
            }

            return cardViewList.contains(view) && view.getVisibility() == View.VISIBLE;
        }

        @Override
        public void onViewCaptured(@NonNull View view, int activePointerId) {
            if (viewDragListener != null) {
                viewDragListener.onViewCaptured((MaterialCardView)view, view.getLeft(), view.getTop());
            }
        }

        @Override
        public void onViewPositionChanged(@NonNull View view, int left, int top, int dx, int dy) {
            if (viewDragListener != null) {
                viewDragListener.onViewMove((MaterialCardView)view, left, top);
            }
        }

        @Override
        public void onViewReleased(@NonNull View view, float xVel, float yVel) {
            if (viewDragListener != null) {
                viewDragListener.onViewReleased((MaterialCardView)view, view.getLeft(), view.getTop(), xVel, yVel);
            }

        }

        @Override
        public int getViewHorizontalDragRange(View view) {
            return view.getWidth();
        }

        @Override
        public int getViewVerticalDragRange(View view) {
            return view.getHeight();
        }

        @Override
        public int clampViewPositionHorizontal(View view, int left, int dx) {
            return left;
        }

        @Override
        public int clampViewPositionVertical(View view, int top, int dy) {
            return top;
        }
    };



    public void setViewDragListener(ViewDragListener viewDragListener) {
        this.viewDragListener = viewDragListener;
    }



}
