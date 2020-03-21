/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-1 上午8:56
 *
 */

package com.jacob.material.example.topappbar;

import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import com.jacob.utils.JsonUtils;
import com.jacob.material.R;
import com.jacob.material.databinding.TopAppBarBigActivityBinding;
import com.jacob.material.example.adapter.MarvelNewsAdapter;
import com.jacob.material.example.model.MarvelNews;

import java.util.List;

public class TopAppBarBigActivity extends AppCompatActivity {

    private TopAppBarBigActivityBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.top_app_bar_big_activity);
        binding.appBarLayout.setOnApplyWindowInsetsListener(new ApplyWindowInsetsListener());

        GridLayoutManager layoutManager = new GridLayoutManager(this, GridLayoutManager.VERTICAL);
        binding.recyclerView.setLayoutManager(layoutManager);

        List<MarvelNews> newList = JsonUtils.loadMarvelNews(getResources());
        MarvelNewsAdapter adapter = new MarvelNewsAdapter(newList);

        binding.recyclerView.setAdapter(adapter);


    }

    private class ApplyWindowInsetsListener implements View.OnApplyWindowInsetsListener {
        @Override
        public WindowInsets onApplyWindowInsets(View view, WindowInsets windowInsets) {
            int statusBarHeight = windowInsets.getSystemWindowInsetTop();
            binding.statusBarBackgroundView.setLayoutParams(new ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, statusBarHeight));
            return windowInsets;
        }
    }


}
