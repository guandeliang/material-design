package com.jacob.book.material.test;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.jacob.book.material.R;
import com.jacob.book.material.databinding.TestShareElementActivityBinding;

public class TestShareElementActivity extends AppCompatActivity {

    private TestShareElementActivityBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.test_share_element_activity);
    }



}
