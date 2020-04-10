/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-31 下午5:05
 *
 */

package com.jacob.material.example.button;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.snackbar.Snackbar;
import com.jacob.material.R;
import com.jacob.material.databinding.ButtonToggleActivityBinding;

public class ButtonToggleActivity extends AppCompatActivity {
    private ButtonToggleActivityBinding binding;
    private ListPopupWindow sortListPopupWindow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.button_toggle_activity);
        initSortListPopupWindow();
        binding.sortLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortListPopupWindow.show();
            }
        });


    }


    private void initSortListPopupWindow() {
        sortListPopupWindow = new ListPopupWindow(this, null, R.attr.listPopupWindowStyle);
        String[] items = new String[4];
        items[0] = "按照推荐程度排序";
        items[1] = "按照距离排序";
        items[2] = "按收费情况排序";
        items[3] = "按交通情况排序";
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_popup_item, items);
        sortListPopupWindow.setAdapter(adapter);
        sortListPopupWindow.setAnchorView(binding.sortLinearLayout);
        sortListPopupWindow.setOnItemClickListener(
                (parent, view, position, id) -> {
                    binding.sortTextView.setText(adapter.getItem(position));
                    sortListPopupWindow.dismiss();
                });
    }

}
