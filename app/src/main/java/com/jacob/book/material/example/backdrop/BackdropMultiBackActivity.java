/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-3-10 下午10:07
 *
 */

package com.jacob.book.material.example.backdrop;

import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;

import com.jacob.book.JsonUtils;
import com.jacob.book.ViewAnimation;
import com.jacob.book.material.BR;
import com.jacob.book.material.R;
import com.jacob.book.material.base.LivelyShopViewModel;
import com.jacob.book.material.databinding.BackdropIntroActivityBinding;
import com.jacob.book.material.databinding.BackdropMultiBackActivityBinding;
import com.jacob.book.material.example.adapter.LivelyCommoditAdapter;
import com.jacob.book.material.example.drawer.DrawerShopItemDecoration;
import com.jacob.book.material.example.model.Commodit;

import java.util.ArrayList;
import java.util.List;

public class BackdropMultiBackActivity extends AppCompatActivity {
    private BackdropMultiBackActivityBinding binding;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.backdrop_multi_back_activity);
        binding.setLifecycleOwner(this);
        binding.setActivity(this);

        int fragmentContainerId = binding.fragmentContainerView.getId();
        NavHostFragment navHostFragment = NavHostFragment.create(R.navigation.backdrop_multi_back_graph);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().add(fragmentContainerId, navHostFragment, "nav_host_fragment");
        fragmentTransaction.commitNow();


        navController = navHostFragment.getNavController();

    }

    public void onBackSwitchButtonClick(View view){
        String tag = (String)view.getTag();
        if(tag == null){
            return;
        }

        String label =  navController.getCurrentDestination().getLabel().toString();
        if(tag.equals(label)){
            return;
        }

        if(tag.equals(navController.getGraph().findNode(R.id.category).getLabel())){
            navController.navigate(R.id.category);
            return;
        }

        if(tag.equals(navController.getGraph().findNode(R.id.filter).getLabel())){
            navController.navigate(R.id.filter);
            return;
        }

    }


}
