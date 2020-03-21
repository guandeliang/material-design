/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-19 上午9:36
 *
 */

package com.jacob.material.test;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.jacob.utils.WidgetsUtils;
import com.jacob.material.R;
import com.jacob.material.databinding.TestShapeActivityBinding;

public class TestShapeActivity extends AppCompatActivity {

    private TestShapeActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.test_shape_activity);

        ShapeAppearanceModel.Builder builder = ShapeAppearanceModel.builder();

        //builder.setBottomLeftCorner(CornerFamily.CUT, WidgetsUtils.dpToPx(this, 100));

        OutsideTriangleEdgeTreatment leftEdgeTreatment = new OutsideTriangleEdgeTreatment(OutsideTriangleEdgeTreatment.Edge_POSITION_LEFT, 30);
        OutsideTriangleEdgeTreatment bottomEdgeTreatment = new OutsideTriangleEdgeTreatment(OutsideTriangleEdgeTreatment.Edge_POSITION_BOTTOM, 30);
        OutsideTriangleEdgeTreatment rightEdgeTreatment = new OutsideTriangleEdgeTreatment(OutsideTriangleEdgeTreatment.Edge_POSITION_RIGHT, 30);
        builder.setLeftEdge(leftEdgeTreatment);
        builder.setBottomEdge(bottomEdgeTreatment);
        builder.setRightEdge(rightEdgeTreatment);



        //builder.setBottomRightCorner(CornerFamily.ROUNDED, WidgetsUtils.dpToPx(this, 20));

        //RoundedCornerTreatment cornerTreatment = new RoundedCornerTreatment(30);
        //builder.setAllCorners(cornerTreatment);

        /*
        builder.setTopLeftCorner(CornerFamily.ROUNDED, WidgetsUtils.dpToPx(this, 20));
        builder.setTopRightCorner(CornerFamily.CUT, WidgetsUtils.dpToPx(this, 20));

        TriangleEdgeTreatment triangleEdgeTreatment = new TriangleEdgeTreatment(WidgetsUtils.dpToPx(this, 20),true);
        builder.setLeftEdge(triangleEdgeTreatment);


         */


        MaterialShapeDrawable drawable = new MaterialShapeDrawable(builder.build());
        drawable.setStroke(10, WidgetsUtils.getColorValue(this, R.color.material_color_blue));

//        drawable.setTint(WidgetsUtils.getColorValue(this, R.color.black));

        binding.textView.setBackground(drawable);


    }



}
