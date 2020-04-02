/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-15 下午6:42
 *
 */

package com.jacob.material.test;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import androidx.databinding.DataBindingUtil;

import com.jacob.material.R;
import com.jacob.material.databinding.TestEventActivityBinding;
import com.jacob.material.example.model.Thrones;
import com.jacob.temp.TempConstant;
import com.jacob.utils.JsonUtils;

import java.util.List;

public class EventTestActivity extends AppCompatActivity {

    private TestEventActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.test_event_activity);

        List<Thrones> thronesList = JsonUtils.loadThrones(getResources());
        EventTestAdapter adapter = new EventTestAdapter(thronesList);
        binding.viewPager.setAdapter(adapter);

        GestureDetectorCompat seatViewDetector = new GestureDetectorCompat(this, new SeatViewDetector("Seat View"));
        GestureDetectorCompat subImagetViewDetector = new GestureDetectorCompat(this, new SeatViewDetector("Sub Image View"));
        GestureDetectorCompat subSubImagetViewDetector = new GestureDetectorCompat(this, new SeatViewDetector("Sub Sub Image View"));

        binding.motionEventPublishLayout.addSubscriber(binding.imageSeatView, seatViewDetector);
        binding.motionEventPublishLayout.addSubscriber(binding.subImageView, subImagetViewDetector);
        binding.motionEventPublishLayout.addSubscriber(binding.subSubImageView, subSubImagetViewDetector);


    }

    private class SeatViewDetector extends GestureDetector.SimpleOnGestureListener{
        String tag;
        public SeatViewDetector(String tag){
            this.tag = tag;
        }

        @Override
        public boolean onSingleTapUp(MotionEvent e) {
            Log.d(TempConstant.LOG_TAG, tag + " ========================================================onSingleTapUp");
            return super.onSingleTapUp(e);
        }

        @Override
        public void onLongPress(MotionEvent e) {
            Log.d(TempConstant.LOG_TAG, tag + " ========================================================onLongPress");
            super.onLongPress(e);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            Log.d(TempConstant.LOG_TAG, tag + " ========================================================onScroll");
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d(TempConstant.LOG_TAG, tag + " ========================================================onFling");
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public void onShowPress(MotionEvent e) {
            Log.d(TempConstant.LOG_TAG, tag + " ========================================================onShowPress");
            super.onShowPress(e);
        }

        @Override
        public boolean onDown(MotionEvent e) {
            Log.d(TempConstant.LOG_TAG, tag + " ========================================================onDown");
            /*

            Rect r1 = new Rect();
            binding.imageSeatView.getHitRect(r1);

            Rect r2 = new Rect();
            binding.subImageView.getHitRect(r2);

            Log.d(TempConstant.LOG_TAG, " event = [" + e.getX() + ", " + e.getY() + "]");
            Log.d(TempConstant.LOG_TAG, " imageSeatView = " + r1.toShortString());
            Log.d(TempConstant.LOG_TAG, " subImageView = " + r2.toShortString());

             */


            return super.onDown(e);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            Log.d(TempConstant.LOG_TAG, tag + " ========================================================onDoubleTap");
            return super.onDoubleTap(e);
        }

        @Override
        public boolean onDoubleTapEvent(MotionEvent e) {
            Log.d(TempConstant.LOG_TAG, tag + " ========================================================onDoubleTapEvent");
            return super.onDoubleTapEvent(e);
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            Log.d(TempConstant.LOG_TAG, tag + " ========================================================onSingleTapConfirmed");
            return super.onSingleTapConfirmed(e);
        }

        @Override
        public boolean onContextClick(MotionEvent e) {
            Log.d(TempConstant.LOG_TAG, tag + " ========================================================onContextClick");
            return super.onContextClick(e);
        }
    }

    /*

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TempConstant.LOG_TAG, "EventTestActivity dispatchTouchEvent before call super " + MotionEvent.actionToString(event.getAction()));
        boolean result = super.dispatchTouchEvent(event);
        Log.d(TempConstant.LOG_TAG, "EventTestActivity dispatchTouchEvent after call super " + MotionEvent.actionToString(event.getAction()) + " " +result);
        getRect(event);
        Log.d(TempConstant.LOG_TAG, "------------------------------------------------------------------");
        return result;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TempConstant.LOG_TAG, "EventTestActivity onTouchEvent before call super " + MotionEvent.actionToString(event.getAction()));
        boolean result = super.onTouchEvent(event);
        Log.d(TempConstant.LOG_TAG, "EventTestActivity onTouchEvent after call super " + MotionEvent.actionToString(event.getAction()) + " " +result);
        return result;
    }

    private void getRect(MotionEvent ev){
        Rect r1 = new Rect();
        binding.imageSeatView.getHitRect(r1);

        Rect r2 = new Rect();
        binding.dragonImageView.getHitRect(r2);


        Rect r3 = new Rect();
        binding.contentTextView.getHitRect(r3);

        Log.d(TempConstant.LOG_TAG, " event = [" + ev.getX() + ", " + ev.getY() + "]");
        Log.d(TempConstant.LOG_TAG, " imageSeatView = " + r1.toShortString());
        Log.d(TempConstant.LOG_TAG, " dragonImageView = " + r2.toShortString());
        Log.d(TempConstant.LOG_TAG, " contentTextView = " + r3.toShortString());
    }

*/

    /*
找到解决办法了
    1、在根View中，修改事件分发机制
    2、对于注册的View,单独分发事件
    3、为了不影响，需要走单独的实践处理方法
    5、分发的时候，每个视图根据情况决定是否对事件进行处理
    分发方法
            1、维护一个单独的分发别表
    2、DOWN不再区内的，绝对不分发
    2、DOWN在区域内的，分发MOV 事件
            3、如果UP超区区域分发CANCEL事件
    如果没有超出，分发UP事件
    这样在既不干扰其他元件的情况下，解决了需要单独处理事件的元件

     */
}
