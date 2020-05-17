/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-19 上午9:42
 *
 */

package com.jacob.material.example.intro;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.jacob.temp.TempConstant;

public class IntroBlankActivity extends AppCompatActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        logInfo();
    }

    private void logInfo(){
        View view = this.findViewById(android.R.id.content);
        View rootView  = view.getRootView();
        printViewTree(rootView, 0);
    }

    private void printViewTree(View view, int level){
        String hexColor = null;
        if(view.getBackground() != null){
            if(view.getBackground() instanceof ColorDrawable){
                ColorDrawable colorDrawable = (ColorDrawable)view.getBackground();
                int color = colorDrawable.getColor();
                hexColor = "#" + Integer.toHexString(color).toUpperCase();
            }
        }

        Log.d(TempConstant.LOG_TAG, level + " " + view.getClass().getName() +  " " + hexColor);
        if(view instanceof ViewGroup){
            ViewGroup viewGroup = (ViewGroup)view;
            for(int i=0; i<viewGroup.getChildCount(); i++){
                View child = viewGroup.getChildAt(i);
                printViewTree(child, level+1);
            }
        }
    }

}
