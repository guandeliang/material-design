package com.jacob.book.material.example.topappbar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.jacob.book.material.R;
import com.jacob.book.material.databinding.TopAppBarDayNightDefaultActivityBinding;

public class TopAppBarDayNightDefaultActivity extends AppCompatActivity {
    private TopAppBarDayNightDefaultActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.top_app_bar_day_night_default_activity);
    }

}
