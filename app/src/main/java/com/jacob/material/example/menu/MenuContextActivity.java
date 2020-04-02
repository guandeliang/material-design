/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-31 下午5:05
 *
 */

package com.jacob.material.example.menu;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.view.GestureDetectorCompat;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.jacob.material.R;
import com.jacob.material.databinding.MenuContextActivityBinding;
import com.jacob.material.example.adapter.GotMenuAdapter;
import com.jacob.material.example.model.Thrones;
import com.jacob.temp.TempConstant;
import com.jacob.utils.JsonUtils;
import com.jacob.utils.WidgetsUtils;

import java.util.List;

public class MenuContextActivity extends AppCompatActivity {
    private static String TAG_SEAT_VIEW = "TAG_SEAT_VIEW";
    private static String TAG_TITLE_TEXT_VIEW = "TAG_TITLE_TEXT_VIEW";
    private static String TAG_CONTENT_TEXT_VIEW = "TAG_CONTENT_TEXT_VIEW";

    private MenuContextActivityBinding binding;
    private GotMenuAdapter adapter;
    private int leftPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.menu_context_activity);
        WidgetsUtils.setSystemBarColor(this, android.R.attr.colorBackground);
        leftPosition = -1;

        List<Thrones> thronesList = JsonUtils.loadThrones(getResources());
        adapter = new GotMenuAdapter(thronesList);
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.registerOnPageChangeCallback(new PageChangeCallBack());

        GestureDetectorCompat seatViewDetector = new GestureDetectorCompat(this, new ContextViewDetector(TAG_SEAT_VIEW));
        GestureDetectorCompat titleTextViewDetector = new GestureDetectorCompat(this, new ContextViewDetector(TAG_TITLE_TEXT_VIEW));
        GestureDetectorCompat contentTextViewDetector = new GestureDetectorCompat(this, new ContextViewDetector(TAG_CONTENT_TEXT_VIEW));


        binding.motionEventPublisherLayout.addSubscriber(binding.imageSeatView, seatViewDetector);
        binding.motionEventPublisherLayout.addSubscriber(binding.leftPageTitleTextView, titleTextViewDetector);
        binding.motionEventPublisherLayout.addSubscriber(binding.rightPageTitleTextView, titleTextViewDetector);
        binding.motionEventPublisherLayout.addSubscriber(binding.leftPageContentTextView, contentTextViewDetector);
        binding.motionEventPublisherLayout.addSubscriber(binding.rightPageContentTextView, contentTextViewDetector);

    }

    private class ContextViewDetector extends GestureDetector.SimpleOnGestureListener{
        String tag;
        public ContextViewDetector(String tag){
            this.tag = tag;
        }
        @Override
        public void onLongPress(MotionEvent e) {
            int position = binding.viewPager.getCurrentItem();
            String title = adapter.getItem(position).getTitle();
            if(TAG_SEAT_VIEW.equals(tag)){
                new MaterialAlertDialogBuilder(MenuContextActivity.this)
                        .setTitle(title)
                        .setMessage("确定要保存" + title + "的照片到您的相册吗？")
                        .setPositiveButton("确定", null)
                        .setNegativeButton("取消", null)
                        .show();
            }else{
                new MaterialAlertDialogBuilder(MenuContextActivity.this)
                        .setTitle(title)
                        .setMessage("确定要复制" + title + "的介绍到吗？")
                        .setPositiveButton("确定", null)
                        .setNegativeButton("取消", null)
                        .show();
            }

            super.onLongPress(e);
        }
    }




    private class PageChangeCallBack extends ViewPager2.OnPageChangeCallback {
        public PageChangeCallBack() {
            super();
        }

        /*
         * position and position + 1 is the visiable page
         * position page at left
         * position page at right
         * if move to left
         * position page (left page) positionOffset form 0 to 1
         * if move to right
         * position page (left page)positionOffset form 1 to 0
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            if(leftPosition < 0){
                leftPosition = position;
                String leftPageTitle = null;
                String leftPageContent = null;
                String rightPageTitle = null;
                String rightPageContent = null;
                if(adapter.getItemCount() > position){
                    leftPageTitle = adapter.getItem(position).getTitle();
                    leftPageContent = adapter.getItem(position).getSummary();
                }
                if(adapter.getItemCount() > position + 1){
                    rightPageTitle = adapter.getItem(position + 1).getTitle();
                    rightPageContent = adapter.getItem(position + 1).getSummary();
                }
                binding.leftPageTitleTextView.setText(leftPageTitle);
                binding.leftPageContentTextView.setText(leftPageContent);
                binding.rightPageTitleTextView.setText(rightPageTitle);
                binding.rightPageContentTextView.setText(rightPageContent);
            }
            //0 -> 1 消失
            //1 -> 0 出现
            if(position == leftPosition){
                float leftPageTitleAlpha = 1 - positionOffset;
                float rightPageTitleAlpha = positionOffset;
                binding.leftPageTitleTextView.setAlpha(leftPageTitleAlpha);
                binding.leftPageContentTextView.setAlpha(leftPageTitleAlpha);
                binding.rightPageTitleTextView.setAlpha(rightPageTitleAlpha);
                binding.rightPageContentTextView.setAlpha(rightPageTitleAlpha);
            }
        }

        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {
            super.onPageScrollStateChanged(state);
            if(state ==  ViewPager2.SCROLL_STATE_IDLE){
                leftPosition = -1;
                int position = binding.viewPager.getCurrentItem();
                if(binding.leftPageTitleTextView.getAlpha() > binding.rightPageTitleTextView.getAlpha()){
                    binding.leftPageTitleTextView.setText(adapter.getItem(position).getTitle());
                    binding.leftPageContentTextView.setText(adapter.getItem(position).getSummary());
                    binding.leftPageTitleTextView.setAlpha(1);
                    binding.rightPageContentTextView.setAlpha(1);

                    binding.rightPageTitleTextView.setText(null);
                    binding.rightPageContentTextView.setText(null);
                    binding.rightPageTitleTextView.setAlpha(0);
                    binding.rightPageContentTextView.setAlpha(0);
                }else{
                    binding.rightPageTitleTextView.setText(adapter.getItem(position).getTitle());
                    binding.rightPageContentTextView.setText(adapter.getItem(position).getSummary());
                    binding.rightPageTitleTextView.setAlpha(1);
                    binding.rightPageContentTextView.setAlpha(1);

                    binding.leftPageTitleTextView.setText(null);
                    binding.leftPageContentTextView.setText(null);
                    binding.leftPageTitleTextView.setAlpha(0);
                    binding.leftPageContentTextView.setAlpha(0);
                }
            }
        }
    }




}
