package com.jacob.material.appstart;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class IntroBlankActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            for(int i=0; i<10; i++){
                Thread.sleep(500);
                Log.d("App_Start_Time", "Activity onCreate is sleep 1");
            }
        }catch (Exception e){
        }

        this.setTheme(R.style.Theme_Orange);

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

        Log.d("App_Start_Time", level + " " + view.getClass().getName() +  " " + hexColor);
        if(view instanceof ViewGroup){
            ViewGroup viewGroup = (ViewGroup)view;
            for(int i=0; i<viewGroup.getChildCount(); i++){
                View child = viewGroup.getChildAt(i);
                printViewTree(child, level+1);
            }
        }
    }
}
