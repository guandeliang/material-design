package com.jacob.book.material.example.topappbar;

import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;

import com.jacob.book.JsonUtils;
import com.jacob.book.material.R;
import com.jacob.book.material.databinding.TopAppBarBigActivityBinding;
import com.jacob.book.material.example.adapter.MarvelNewsAdapter;
import com.jacob.book.material.example.model.MarvelNews;

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
