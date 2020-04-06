/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-8 下午8:53
 *
 */

package com.jacob.material.example.bottomsheet;

import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.jacob.material.R;
import com.jacob.material.databinding.BottomSheetDialogActivityBinding;
import com.jacob.material.databinding.BottomSheetFullActivityBinding;
import com.jacob.temp.TempConstant;
import com.jacob.utils.WidgetsUtils;

public class BottomSheetDialogActivity extends AppCompatActivity {

    private BottomSheetDialogActivityBinding binding;
    private BottomSheetBehavior sheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.bottom_sheet_dialog_activity);
        WidgetsUtils.setSystemBarColor(BottomSheetDialogActivity.this,  android.R.attr.colorBackground);
        WidgetsUtils.setSystemBarLight(BottomSheetDialogActivity.this);

        binding.nestedScrollView.setOnScrollChangeListener(new OnContentScrollChangeListener());

        binding.moreImageView.setOnClickListener(new OnMoreClickListener());


    }

    private class OnContentScrollChangeListener implements NestedScrollView.OnScrollChangeListener{
        @Override
        public void onScrollChange(NestedScrollView nestedScrollView, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
            Rect displayFrameRect = new Rect();
            nestedScrollView.getWindowVisibleDisplayFrame(displayFrameRect);
            int statusBarHeight = displayFrameRect.top;

            int[] headerLocationOnScreen = new int[2];
            binding.authorHeaderImageView.getLocationOnScreen(headerLocationOnScreen);

            int headerTop = headerLocationOnScreen[1] - statusBarHeight;

            int appBarHeight = binding.appBarLayout.getHeight();
            int headerHeight = binding.authorHeaderImageView.getHeight();



            if(headerTop <= appBarHeight && headerTop >= (appBarHeight - headerHeight)/2){//过渡过程
                int y = headerTop - appBarHeight;
                binding.titleHeaderImageView.setTranslationY(y);
                binding.titleNameTextView.setTranslationY(y);
                binding.tilteJobTextView.setTranslationY(y);

                binding.backImageView.setTranslationY(y);
                binding.appTitleTextView.setTranslationY(y);
            }else if(headerTop < (appBarHeight - headerHeight)/2){//完成过渡
                int y = 0 - headerHeight -(appBarHeight - headerHeight)/2;
                binding.titleHeaderImageView.setTranslationY(y);
                binding.titleNameTextView.setTranslationY(y);
                binding.tilteJobTextView.setTranslationY(y);

                binding.backImageView.setTranslationY(y);
                binding.appTitleTextView.setTranslationY(y);
            }else{//完成过渡
                binding.titleHeaderImageView.setTranslationY(0);
                binding.titleNameTextView.setTranslationY(0);
                binding.tilteJobTextView.setTranslationY(0);

                binding.backImageView.setTranslationY(0);
                binding.appTitleTextView.setTranslationY(0);
            }
        }
    }

    private class OnMoreClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            BottomSheetDialog dialog = BottomSheetDialog.getInstance();
            dialog.show(getSupportFragmentManager(), "BottomSheetDialog");
        }
    }


}
