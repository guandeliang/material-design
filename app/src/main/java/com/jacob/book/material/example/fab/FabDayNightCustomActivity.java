/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-19 上午9:42
 *
 */

package com.jacob.book.material.example.fab;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.shape.CornerFamily;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.Shapeable;
import com.jacob.book.WidgetsUtils;
import com.jacob.book.material.R;
import com.jacob.book.material.databinding.FabDayNightCustomActivityBinding;
import com.jacob.book.material.databinding.FabDayNightDefaultActivityBinding;
import com.jacob.book.temp.TempConstant;

public class FabDayNightCustomActivity extends AppCompatActivity {
    private FabDayNightCustomActivityBinding binding;
    private boolean isVisibility;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.fab_day_night_custom_activity);
        binding.setActivity(this);
        this.isVisibility = true;
    }

    public void onVisibilityButtonClick(){
        if(isVisibility){
            isVisibility = false;
            binding.visibilityButton.setText("显示");
            binding.miniFab.hide();
            binding.fab.hide();
            binding.extendedFab.hide();
        }else{
            isVisibility = true;
            binding.visibilityButton.setText("隐藏");
            binding.miniFab.show();
            binding.fab.show();
            binding.extendedFab.show();
        };

    }

    public void onRotateButtonClick(){
        if(!isVisibility){
            return;
        }
        binding.miniFab.setRotation(0);
        binding.fab.setRotation(0);
        binding.miniFab.animate().rotation(360).setDuration(1000);
        binding.fab.animate().rotation(360).setDuration(1000);
        if(!binding.extendedFab.isExtended()){
            binding.extendedFab.extend();
        }else{
            binding.extendedFab.shrink();
        }

    }

    public void onShapeChanged(RadioGroup radioGroup, int id) {
        if(R.id.round_radio_button == id){
            setRoundShape(binding.miniFab, binding.miniFab.getCustomSize());
            setRoundShape(binding.fab, binding.fab.getHeight());
            setRoundShape(binding.extendedFab, binding.extendedFab.getHeight());
        }else if(R.id.diamond_radio_button == id){
            setDiamondShape(binding.miniFab);
            setDiamondShape(binding.fab);
            setDiamondShape(binding.extendedFab);
        }else if(R.id.rectangle_radio_button == id){
            setRectangleShape(binding.miniFab);
            setRectangleShape(binding.fab);
            setRectangleShape(binding.extendedFab);
        }

    }

    private void setRoundShape(Shapeable shapeable, float size){
        ShapeAppearanceModel.Builder builder = ShapeAppearanceModel.builder();
        builder.setAllCorners(CornerFamily.ROUNDED, size/2.0f);
        shapeable.setShapeAppearanceModel(builder.build());
    }

    private void setDiamondShape(Shapeable shapeable){
        ShapeAppearanceModel.Builder builder = ShapeAppearanceModel.builder();
        builder.setAllCorners(CornerFamily.CUT, WidgetsUtils.dpToPx(this, 16));
        shapeable.setShapeAppearanceModel(builder.build());
    }

    private void setRectangleShape(Shapeable shapeable){
        ShapeAppearanceModel.Builder builder = ShapeAppearanceModel.builder();
        builder.setAllCorners(CornerFamily.CUT, 0f);
        shapeable.setShapeAppearanceModel(builder.build());
    }
}
