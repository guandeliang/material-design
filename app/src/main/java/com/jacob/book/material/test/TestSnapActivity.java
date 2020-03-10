/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-15 下午6:42
 *
 */

package com.jacob.book.material.test;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jacob.book.JsonUtils;
import com.jacob.book.WidgetsUtils;
import com.jacob.book.material.R;
import com.jacob.book.material.databinding.TestSnapActivityBinding;
import com.jacob.book.material.example.model.ExampleImage;
import com.jacob.book.material.widgets.HorizontalEdgeSnapHelper;
import com.jacob.book.material.widgets.LinearLayoutHorizontalItemDecoration;
import com.jacob.book.temp.TempConstant;

import java.util.List;

public class TestSnapActivity extends AppCompatActivity {

    private TestSnapActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.test_snap_activity);
        /*
        binding.rootButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TempConstant.LOG_TAG, "000000000000000000000000000000000000000000000000");
            }
        });

        binding.oneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TempConstant.LOG_TAG, "11111111111111111111111111111111111111111111111");
            }
        });

         */
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(TempConstant.LOG_TAG, "dispatchTouchEvent    action:" + MotionEvent.actionToString(ev.getAction()));
        boolean superReturn = super.dispatchTouchEvent(ev);
        //Log.d(TempConstant.LOG_TAG, "dispatchTouchEvent    action:" + MotionEvent.actionToString(ev.getAction()) + " " + superReturn);
        return superReturn;
    }
/*
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        Log.i(TempConstant.LOG_TAG, "onTouchEvent          action:" + MotionEvent.actionToString(ev.getAction()));
        boolean superReturn = super.onTouchEvent(ev);
        Log.d(TempConstant.LOG_TAG, "onTouchEvent          action:" + MotionEvent.actionToString(ev.getAction()) + " " + superReturn);
        return superReturn;
    }
*/
}
