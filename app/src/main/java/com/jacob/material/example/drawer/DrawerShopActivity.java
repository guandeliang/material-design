/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-3-7 上午10:29
 *
 */

package com.jacob.material.example.drawer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.chad.library.BR;
import com.jacob.utils.ViewAnimation;
import com.jacob.material.R;
import com.jacob.material.base.LivelyShopViewModel;
import com.jacob.material.databinding.DrawerShopActivityBinding;

public class DrawerShopActivity extends AppCompatActivity {
    private DrawerShopActivityBinding binding;
    private NavController navController;
    private LivelyShopViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.drawer_shop_activity);
        binding.setLifecycleOwner(this);
        viewModel = new ViewModelProvider(this).get(LivelyShopViewModel.class);
        viewModel.addOnPropertyChangedCallback(new ViewModelPropertyChangedCallback());
        binding.setViewModel(viewModel);
        binding.setActivity(this);


        //建立FragmentContainerView 与 drawer_photp_graph之间关联关系
        int fragmentContainerId = binding.fragmentContainerView.getId();
        NavHostFragment navHostFragment = NavHostFragment.create(R.navigation.drawer_shop_graph);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().add(fragmentContainerId, navHostFragment, "nav_host_fragment");
        fragmentTransaction.commitNow();

        navController = navHostFragment.getNavController();

    }

    private class ViewModelPropertyChangedCallback extends Observable.OnPropertyChangedCallback {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
            if(propertyId != BR.currCategory){
                return;
            }
            closeDrawer();
            LivelyShopViewModel.Category currCategory = viewModel.getCurrCategory();
            binding.categoryTitleTextView.setText(currCategory.getTitle());
            ViewAnimation.fadeIn(binding.fragmentContainerView);
        }
    }

    public void openDrawer(){
        binding.drawerLayout.openDrawer(binding.navigationView);
    }
    public void closeDrawer(){
        binding.drawerLayout.closeDrawer(binding.navigationView);
    }



}
