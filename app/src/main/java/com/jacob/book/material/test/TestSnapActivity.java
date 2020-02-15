package com.jacob.book.material.test;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.jacob.book.JsonUtils;
import com.jacob.book.WidgetsUtils;
import com.jacob.book.material.R;
import com.jacob.book.material.databinding.TestSnapActivityBinding;
import com.jacob.book.material.example.model.ExampleImage;
import com.jacob.book.material.widgets.HorizontalEdgeSnapHelper;
import com.jacob.book.material.widgets.LinearLayoutHorizontalItemDecoration;

import java.util.List;

public class TestSnapActivity extends AppCompatActivity {

    private TestSnapActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.test_snap_activity);




        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);
        LinearLayoutHorizontalItemDecoration decoration = new LinearLayoutHorizontalItemDecoration(0, 0, 0, 0, WidgetsUtils.dpToPx(this, 16));
        binding.recyclerView.addItemDecoration(decoration);

        HorizontalEdgeSnapHelper snapHelper = new HorizontalEdgeSnapHelper();
        snapHelper.attachToRecyclerView(binding.recyclerView);

        List<ExampleImage> imageList = JsonUtils.loadExampleImage(getResources());

        TestSnapAdapter adapter = new TestSnapAdapter(imageList, 3, WidgetsUtils.dpToPx(this, 16));


        binding.recyclerView.setAdapter(adapter);
    }


}
