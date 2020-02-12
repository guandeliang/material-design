package com.jacob.book.material.example.bottomnavigation;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.jacob.book.material.R;
import com.jacob.book.material.databinding.BottomNavigationDayNightDefaultActivityBinding;

public class BottomNavigationDayNightDefaultActivity extends AppCompatActivity {
    private BottomNavigationDayNightDefaultActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.bottom_navigation_day_night_default_activity);
    }

}
