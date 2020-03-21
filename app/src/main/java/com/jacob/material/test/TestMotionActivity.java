/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-19 上午9:37
 *
 */

package com.jacob.material.test;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jacob.utils.JsonUtils;
import com.jacob.utils.WidgetsUtils;
import com.jacob.material.R;
import com.jacob.material.databinding.TestMotionActivityBinding;
import com.jacob.material.example.adapter.GrammyLibraryAlbumAdapter;
import com.jacob.material.example.model.Grammy;
import com.jacob.material.widgets.LinearLayoutVertialItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class TestMotionActivity extends AppCompatActivity {

    private TestMotionActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.test_motion_activity);
        initLibraryList();
    }

    private void initLibraryList(){
        List<Grammy> allList = JsonUtils.loadGrammy(getResources());
        List<Grammy> librarylList = new ArrayList<>();
        for(Grammy grammy:allList){
            if("album".equals(grammy.getCategory())){
                librarylList.add(grammy);
            }
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);

        int px_08 = WidgetsUtils.dpToPx(this, 8);
        int px_16 = WidgetsUtils.dpToPx(this, 16);

        LinearLayoutVertialItemDecoration decoration = new LinearLayoutVertialItemDecoration(px_16, px_16, px_08);
        binding.recyclerView.addItemDecoration(decoration);

        GrammyLibraryAlbumAdapter adapter = new GrammyLibraryAlbumAdapter(librarylList);

        View header = View.inflate(this, R.layout.grammy_library_header, null);
        adapter.setHeaderView(header);



        binding.recyclerView.setAdapter(adapter);
    }


}
