package com.jacob.book.material.main.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jacob.book.JsonUtils;
import com.jacob.book.WidgetsUtils;
import com.jacob.book.material.R;
import com.jacob.book.material.databinding.SnapTestActivityBinding;
import com.jacob.book.material.example.model.ExampleImage;
import com.jacob.book.material.widgets.HorizontalEdgeSnapHelper;
import com.jacob.book.material.widgets.HorizontalLinearLayoutItemDecoration;

import java.util.List;

public class SnapTestActivity extends AppCompatActivity {

    private SnapTestActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.snap_test_activity);




        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);
        HorizontalLinearLayoutItemDecoration decoration = new HorizontalLinearLayoutItemDecoration(0, 0, 0, 0, WidgetsUtils.dpToPx(this, 16));
        binding.recyclerView.addItemDecoration(decoration);

        HorizontalEdgeSnapHelper snapHelper = new HorizontalEdgeSnapHelper();
        snapHelper.attachToRecyclerView(binding.recyclerView);

        List<ExampleImage> imageList = JsonUtils.loadExampleImage(getResources());

        SnapTestAdapter adapter = new SnapTestAdapter(imageList, 3, WidgetsUtils.dpToPx(this, 16));


        binding.recyclerView.setAdapter(adapter);
    }


}
