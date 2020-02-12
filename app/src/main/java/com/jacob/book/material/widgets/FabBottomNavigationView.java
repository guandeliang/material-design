package com.jacob.book.material.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapePathModel;
import com.jacob.book.material.R;
import com.jacob.book.temp.TempConstant;

public class FabBottomNavigationView extends BottomNavigationView {
    private TopCurvedEdgeTreatment topEdge;
    private float fabSize;
    private float fabCradleMargin;
    private float fabCradleRoundedCornerRadius;
    private float fabCradleVerticalOffset;


    public FabBottomNavigationView(@NonNull Context context) {
        this(context, null);
    }

    public FabBottomNavigationView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, R.attr.bottomNavigationStyle);
    }

    public FabBottomNavigationView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.fab_bottom_navigation_view, 0, 0);
        fabSize = typedArray.getDimension(R.styleable.fab_bottom_navigation_view_fab_size, 0f);
        fabCradleMargin = typedArray.getDimension(R.styleable.fab_bottom_navigation_view_fab_cradle_margin, 0f);
        fabCradleRoundedCornerRadius = typedArray.getDimension(R.styleable.fab_bottom_navigation_view_fab_cradle_rounded_corner_radius, 0f);
        fabCradleVerticalOffset = typedArray.getDimension(R.styleable.fab_bottom_navigation_view_fab_cradle_vertical_offset, 0f);
        typedArray.recycle();

        topEdge = new TopCurvedEdgeTreatment(fabCradleMargin, fabCradleRoundedCornerRadius, fabCradleVerticalOffset);
        topEdge.setFabDiameter(fabSize);

        ShapeAppearanceModel.Builder builder = ShapeAppearanceModel.builder();
        builder.setTopEdge(topEdge);


        Drawable background = getBackground();
        if(background != null && background instanceof MaterialShapeDrawable){
            MaterialShapeDrawable shapeBackground = (MaterialShapeDrawable)background;
            shapeBackground.setShapeAppearanceModel(builder.build());
        }

/*

        MaterialShapeDrawable drawable = new MaterialShapeDrawable(builder.build());
        drawable.setTint(ContextCompat.getColor(context, R.color.colored_color_secondary_variant));
        drawable.setElevation(4);
        drawable.setShadowCompatibilityMode(MaterialShapeDrawable.SHADOW_COMPAT_MODE_DEFAULT);
        drawable.setPaintStyle(Paint.Style.FILL_AND_STROKE);
        setBackground(drawable);
*/

    }



}
