/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-2 下午3:08
 *
 */

package com.jacob.book.material.example.bottomnavigation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomappbar.BottomAppBarTopEdgeTreatment;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.jacob.book.material.R;
import com.jacob.book.material.databinding.BottomNavigationDayNightCustomActivityBinding;
import com.jacob.book.material.databinding.BottomNavigationFabActivityBinding;
import com.jacob.book.material.widgets.BottomAppBarCutCornersTopEdge;

public class BottomNavigationFabActivity extends AppCompatActivity {
    private BottomNavigationFabActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.bottom_navigation_fab_activity);
    }

}
