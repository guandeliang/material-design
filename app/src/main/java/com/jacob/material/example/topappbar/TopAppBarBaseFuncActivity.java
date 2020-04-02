/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-8 下午8:53
 *
 */

package com.jacob.material.example.topappbar;

import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.MenuCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.color.MaterialColors;
import com.google.android.material.snackbar.Snackbar;
import com.jacob.utils.JsonUtils;
import com.jacob.utils.WidgetsUtils;
import com.jacob.material.R;
import com.jacob.material.databinding.TopAppBarBaseFuncActivityBinding;
import com.jacob.material.example.adapter.ExampleImageAdapter;
import com.jacob.material.example.model.ExampleImage;
import com.jacob.material.widgets.StaggeredGridSpaceItemDecoration;

import java.util.List;

public class TopAppBarBaseFuncActivity extends AppCompatActivity {

    private TopAppBarBaseFuncActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.top_app_bar_base_func_activity);

        setSupportActionBar(binding.materialToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.icon_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        binding.recyclerView.setLayoutManager(layoutManager);

        StaggeredGridSpaceItemDecoration decoration = new StaggeredGridSpaceItemDecoration(WidgetsUtils.dpToPx(this, 8), 2);
        binding.recyclerView.addItemDecoration(decoration);

        List<ExampleImage> imageList = JsonUtils.loadExampleImage(getResources());
        ExampleImageAdapter adapter = new ExampleImageAdapter(imageList);

        View header = View.inflate(this, R.layout.example_image_header, null);
        TextView headerTextView = header.findViewById(R.id.text_view);
        headerTextView.setText("共" + imageList.size() + "张图片");
        adapter.setHeaderView(header);

        View footer = View.inflate(this, R.layout.example_image_footer, null);
        adapter.setFooterView(footer);

        binding.recyclerView.setAdapter(adapter);
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_favorite: {
                Snackbar snackbar = Snackbar
                        .make(binding.constraintLayout, "点赞是最廉价的驱动方式", Snackbar.LENGTH_LONG)
                        .setAction("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        });
                snackbar.show();
                return true;
            }
            case R.id.action_search: {
                Snackbar snackbar = Snackbar
                        .make(binding.constraintLayout, "查找一般都找不到想要的东西", Snackbar.LENGTH_LONG)
                        .setAction("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        });
                snackbar.show();
                return true;
            }

            case R.id.action_share: {
                Snackbar snackbar = Snackbar
                        .make(binding.constraintLayout, "好东西值得分享，不好的东西分享就是打扰", Snackbar.LENGTH_LONG)
                        .setAction("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        });
                snackbar.show();
                return true;
            }
            case R.id.action_help: {
                Snackbar snackbar = Snackbar
                        .make(binding.constraintLayout, "想看帮助信息吗", Snackbar.LENGTH_LONG)
                        .setAction("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        });
                snackbar.show();
                return true;
            }
            case R.id.action_setting: {
                Snackbar snackbar = Snackbar
                        .make(binding.constraintLayout, "其实现在大部分APP都没有设置功能了", Snackbar.LENGTH_LONG)
                        .setAction("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                            }
                        });
                snackbar.show();
                return true;
            }
            default: {//处理导航按钮
                Snackbar snackbar = Snackbar
                        .make(binding.constraintLayout, "返回上一个界面吗？", Snackbar.LENGTH_LONG)
                        .setAction("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                TopAppBarBaseFuncActivity.this.finish();
                            }
                        });
                snackbar.show();
                return true;
            }
        }
    }



}
