package com.jacob.book.material.example.bottomappbar;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.jacob.book.material.R;
import com.jacob.book.material.databinding.BottomAppBarDayNightDefaultActivityBinding;

public class BottomAppBarDayNightDefaultActivity extends AppCompatActivity {
    private BottomAppBarDayNightDefaultActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.bottom_app_bar_day_night_default_activity);
    }

}
