/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-31 下午8:08
 *
 */

package com.jacob.material.example.topappbar;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.snackbar.Snackbar;
import com.jacob.material.R;
import com.jacob.material.databinding.TopAppBarCustomLayoutActivityBinding;

public class TopAppBarCustomLayoutActivity extends AppCompatActivity {
    private TopAppBarCustomLayoutActivityBinding binding;
    private TopAppBarCustomLayoutViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.top_app_bar_custom_layout_activity);
        viewModel = new ViewModelProvider(this).get(TopAppBarCustomLayoutViewModel.class);
        viewModel.operator.observe(this, new OperatorObserver());


        binding.backImageView.setOnClickListener(new OnBackClickListener());
        binding.shareImageView.setOnClickListener(new OnShareClickListener());
        binding.favoriteImageView.setOnClickListener(new OnFavoriteClickListener());
        binding.moreImageView.setOnClickListener(new OnMoreClickListener());
    }

    private class OnBackClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            viewModel.setOperator(TopAppBarCustomLayoutViewModel.Operator.BACK);
        }
    }

    private class OnShareClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            viewModel.setOperator(TopAppBarCustomLayoutViewModel.Operator.SHARE);
        }
    }

    private class OnFavoriteClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            viewModel.setOperator(TopAppBarCustomLayoutViewModel.Operator.FAVORITE);
        }
    }

    private class OnMoreClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            TopAppBarCustomLayoutMenuDialog dialog = new TopAppBarCustomLayoutMenuDialog();
            dialog.show(TopAppBarCustomLayoutActivity.this.getSupportFragmentManager() , "TopAppBarCustomLayoutMenuDialog");
        }
    }

    private class OperatorObserver implements Observer<TopAppBarCustomLayoutViewModel.Operator>{
        @Override
        public void onChanged(TopAppBarCustomLayoutViewModel.Operator operator) {
            if(TopAppBarCustomLayoutViewModel.Operator.BACK.equals(operator)){
                Snackbar snackbar = Snackbar
                        .make(binding.linearLayout, "返回上一个界面吗？", Snackbar.LENGTH_LONG)
                        .setAction("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                TopAppBarCustomLayoutActivity.this.finish();
                            }
                        });
                snackbar.show();
            }
            if(TopAppBarCustomLayoutViewModel.Operator.SHARE.equals(operator)){
                Snackbar snackbar = Snackbar
                        .make(binding.linearLayout, "分享是一种美德", Snackbar.LENGTH_LONG)
                        .setAction("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        });
                snackbar.show();
            }
            if(TopAppBarCustomLayoutViewModel.Operator.FAVORITE.equals(operator)){
                Snackbar snackbar = Snackbar
                        .make(binding.linearLayout, "点赞可以锻炼手指头", Snackbar.LENGTH_LONG)
                        .setAction("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        });
                snackbar.show();
            }
            if(TopAppBarCustomLayoutViewModel.Operator.SEARCH.equals(operator)){
                Snackbar snackbar = Snackbar
                        .make(binding.linearLayout, "查找一般都找不到真正想要的东西", Snackbar.LENGTH_LONG)
                        .setAction("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        });
                snackbar.show();
            }
            if(TopAppBarCustomLayoutViewModel.Operator.USER.equals(operator)){
                Snackbar snackbar = Snackbar
                        .make(binding.linearLayout, "用户信息就是一张名片", Snackbar.LENGTH_LONG)
                        .setAction("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        });
                snackbar.show();
            }
        }
    }
}
