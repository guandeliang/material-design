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
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.jacob.material.R;
import com.jacob.material.databinding.CardRecycleViewMoveActivityBinding;
import com.jacob.material.example.adapter.CardRecycleViewMoveAdapter;
import com.jacob.material.example.model.AddressBook;
import com.jacob.material.widgets.GridLayoutVertialItemDecoration;
import com.jacob.utils.JsonUtils;
import com.jacob.utils.WidgetsUtils;

import java.util.List;

public class CardRecycleViewMoveActivity extends AppCompatActivity {
    private CardRecycleViewMoveActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.card_recycle_view_move_activity);

        List<AddressBook> addressBookList = JsonUtils.loadAddressBooks(getResources());

        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, RecyclerView.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);

        int px_24 = WidgetsUtils.dpToPx(this, 24);
        GridLayoutVertialItemDecoration decoration = new GridLayoutVertialItemDecoration(px_24, px_24, px_24, px_24);
        binding.recyclerView.addItemDecoration(decoration);

        CardRecycleViewMoveAdapter adapter = new CardRecycleViewMoveAdapter(addressBookList);

        binding.recyclerView.setAdapter(adapter);

        CardRecycleViewMoveCallback callback = new CardRecycleViewMoveCallback();
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(binding.recyclerView);
        callback.setAdapter(adapter);
    }

    //解决不可移动条目问题，比如头部条目
    //解决没有放手就开始移动问题，增加touch事件监听，
    //https://medium.com/@ipaulpro/drag-and-swipe-with-recyclerview-6a6f0c422efd
    //https://stackoverflow.com/questions/37425494/recyclerview-itemtouchhelper-action-drag-ended
    //https://stackoverflow.com/questions/35920584/android-how-to-catch-drop-action-of-itemtouchhelper-which-is-used-with-recycle
}
