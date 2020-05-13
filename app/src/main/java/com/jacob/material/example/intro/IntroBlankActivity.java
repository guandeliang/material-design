/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-19 上午9:42
 *
 */

package com.jacob.material.example.intro;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ContentFrameLayout;
import androidx.appcompat.widget.FitWindowsLinearLayout;

import com.jacob.temp.TempConstant;

public class IntroBlankActivity extends AppCompatActivity{



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View view = this.findViewById(android.R.id.content);
        Log.d(TempConstant.LOG_TAG, view.getClass().getName());

        View rootView  = view.getRootView();
        printViewTree(rootView, 0);
    }

    private void printViewTree(View view, int level){
        String hexColor = null;
        if(view.getBackground() != null){
            if(view.getBackground() instanceof ColorDrawable){
                ColorDrawable colorDrawable = (ColorDrawable)view.getBackground();
                int color = colorDrawable.getColor();
                hexColor = "#-" + Integer.toHexString(Color.red(color)) + "-" +  Integer.toHexString(Color.green(color)) + "-" + Integer.toHexString(Color.blue(color));
            }
        }


        if(view instanceof LinearLayout){
            LinearLayout linearLayout = (LinearLayout)view;
            linearLayout.setPadding(50,50,50,50);
        }



        Log.d(TempConstant.LOG_TAG, level + " " + view.getClass().getName() +  " " + hexColor);
        if(view instanceof ViewGroup){
            ViewGroup vg = (ViewGroup)view;
            for(int i=0; i<vg.getChildCount(); i++){
                View child = vg.getChildAt(i);
                printViewTree(child, level+1);
            }
        }
    }

    /*
    ContentLoadingProgressBar
com.facebook.shimmer:shimmer
    https://www.jianshu.com/p/6d059c858396
    https://www.jianshu.com/p/afa921d8ed24
    https://android.googlesource.com/platform/frameworks/base/+/refs/heads/master/core/res/res/layout/
    0 com.android.internal.policy.DecorView #37464f
    1 android.widget.LinearLayout null
    2 android.view.ViewStub null
    2 android.widget.FrameLayout null
    3 androidx.appcompat.widget.FitWindowsLinearLayout null
    4 androidx.appcompat.widget.ViewStubCompat null
    4 androidx.appcompat.widget.ContentFrameLayout null
     */
}
