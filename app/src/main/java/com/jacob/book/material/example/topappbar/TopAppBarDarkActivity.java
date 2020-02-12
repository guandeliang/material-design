package com.jacob.book.material.example.topappbar;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.jacob.book.WidgetsUtils;
import com.jacob.book.material.R;
import com.jacob.book.material.base.TabBaseAdapter;
import com.jacob.book.material.base.TabBaseFragment;
import com.jacob.book.material.databinding.TopAppBarDarkActivityBinding;

import java.util.ArrayList;
import java.util.List;

public class TopAppBarDarkActivity extends AppCompatActivity {

    private TopAppBarDarkActivityBinding binding;
    private List<TabBaseFragment> fragmentList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.top_app_bar_dark_activity);
        setSupportActionBar(binding.toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.icon_menu);
        ab.setDisplayHomeAsUpEnabled(true);
        binding.toolbar.getNavigationIcon().setTint(WidgetsUtils.getColorValue(this, android.R.attr.textColorPrimaryInverse));
        WidgetsUtils.setSystemBarColor(this, R.color.gray_1000);

        fragmentList = new ArrayList<>();
        fragmentList.add(new TopAppBarDarkOneFragment());
        fragmentList.add(new TopAppBarDarkTwoFragment());
        fragmentList.add(new TopAppBarDarkThreeFragment());

        TabBaseAdapter adapter = new TabBaseAdapter(this, fragmentList);
        binding.viewPager.setAdapter(adapter);

        TabLayoutMediator mediator = new TabLayoutMediator(
                binding.tabLayout,
                binding.viewPager,
                new TabBaseFragment.ConfigurationStrategy(fragmentList)
        );
        mediator.attach();

    }



}
