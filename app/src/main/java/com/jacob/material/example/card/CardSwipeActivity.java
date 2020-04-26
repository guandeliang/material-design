/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-31 下午5:05
 *
 */

package com.jacob.material.example.card;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jacob.material.R;
import com.jacob.material.databinding.CardSwipeActivityBinding;
import com.jacob.material.example.adapter.CardSwipeAdapter;
import com.jacob.material.example.model.Thrones;
import com.jacob.material.widgets.LinearLayoutVertialItemDecoration;
import com.jacob.utils.JsonUtils;
import com.jacob.utils.WidgetsUtils;

import java.util.List;

public class CardSwipeActivity extends AppCompatActivity {
    private CardSwipeActivityBinding binding;
    private List<Thrones> thronesList;
    private CardSwipeAdapter adapter;
    private int px_8;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.card_swipe_activity);

        thronesList = JsonUtils.loadThrones(getResources());

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);

        px_8 = WidgetsUtils.dpToPx(this, 8);

        LinearLayoutVertialItemDecoration decoration = new LinearLayoutVertialItemDecoration(px_8*3, px_8*3, px_8*2);
        binding.recyclerView.addItemDecoration(decoration);

        adapter = new CardSwipeAdapter(thronesList);

        binding.recyclerView.setAdapter(adapter);

        CardSwipeCallback callback = new CardSwipeCallback(this, R.id.card_view, px_8*10, px_8*10);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(binding.recyclerView);
    }


}
