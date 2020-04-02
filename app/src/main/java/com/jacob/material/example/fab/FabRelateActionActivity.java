/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-8 下午8:53
 *
 */

package com.jacob.material.example.fab;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.color.MaterialColors;
import com.jacob.temp.TempConstant;
import com.jacob.utils.WidgetsUtils;
import com.jacob.material.R;
import com.jacob.material.databinding.FabRelateActionActivityBinding;

public class FabRelateActionActivity extends AppCompatActivity {

    private FabRelateActionActivityBinding binding;
    private boolean isShowRelate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.fab_relate_action_activity);
        binding.setActivity(this);
        isShowRelate = false;

        setSupportActionBar(binding.materialToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.icon_menu);
        ab.setDisplayHomeAsUpEnabled(true);

    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.regular_menu, menu);
        if(menu instanceof MenuBuilder){
            MenuBuilder menuBuilder = (MenuBuilder) menu;
            menuBuilder.setOptionalIconsVisible(true);
        }

        return true;
    }

    public void onMainFabClick(View view){
        View[] relateActionViews = new View[3];
        relateActionViews[0] = binding.subActionButton001;
        relateActionViews[1] = binding.subActionButton002;
        relateActionViews[2] = binding.subActionButton003;

        View[] relateTextViews = new View[3];
        relateTextViews[0] = binding.subActionText001;
        relateTextViews[1] = binding.subActionText002;
        relateTextViews[2] = binding.subActionText003;


        int distance = WidgetsUtils.dpToPx(this, 64);
        int duration = 300;


        binding.mainActionButton.animate().cancel();
        for(int i=0; i<relateActionViews.length; i++){
            relateActionViews[i].animate().cancel();
            relateTextViews[i].animate().cancel();
        }

        binding.mainActionButton.setRotation(0);


        if(isShowRelate){
            isShowRelate = false;
            binding.mainActionButton.animate().rotation(-360).setDuration(duration);
            binding.mainActionButton.setImageResource(R.drawable.icon_share);

            for(int i=0; i<relateActionViews.length; i++){
                relateActionViews[i].animate().translationY(0).setDuration(duration);
                relateActionViews[i].animate().alpha(0).setDuration(duration);

                relateTextViews[i].animate().translationY(0).setDuration(duration);
                relateTextViews[i].animate().alpha(0).setDuration(duration);
            }

        }else{
            isShowRelate = true;
            binding.mainActionButton.animate().rotation(360).setDuration(duration);
            binding.mainActionButton.setImageResource(R.drawable.icon_clear);

            for(int i=0; i<relateActionViews.length; i++){
                relateActionViews[i].animate().translationY(0 - (i+1)*distance).setDuration(duration);
                relateActionViews[i].animate().alpha(1).setDuration(duration);

                relateTextViews[i].animate().translationY(0 - (i+1)*distance).setDuration(duration);
                relateTextViews[i].animate().alpha(1).setDuration(duration);

            }

        }


    }


}
