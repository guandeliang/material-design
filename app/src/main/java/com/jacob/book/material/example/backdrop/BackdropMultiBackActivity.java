/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-3-10 下午10:07
 *
 */

package com.jacob.book.material.example.backdrop;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
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
import com.jacob.book.temp.TempConstant;

import java.util.ArrayList;
import java.util.List;

public class BackdropMultiBackActivity extends AppCompatActivity {
    private BackdropMultiBackActivityBinding binding;
    private BackdropMulitBackViewModel viewModel;

    private NavController backNavController;
    private NavController dropNavController;
    private boolean isFirstLoadBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.backdrop_multi_back_activity);
        viewModel = new ViewModelProvider(this).get(BackdropMulitBackViewModel.class);
        viewModel.addOnPropertyChangedCallback(new ViewModelPropertyChangedCallback());


        binding.setLifecycleOwner(this);
        binding.setActivity(this);


        isFirstLoadBack = true;
        viewModel.setDropDown(false);

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        //drop fragment
        int dropFragmentContainerId = binding.dropFragmentContainerView.getId();
        NavHostFragment dropNavHostFragment = NavHostFragment.create(R.navigation.backdrop_multi_back_drop_graph);
        FragmentTransaction dropFragmentTransaction = fragmentManager.beginTransaction().add(dropFragmentContainerId, dropNavHostFragment, "drop_nav_host_fragment");
        dropFragmentTransaction.commitNow();
        dropNavController = dropNavHostFragment.getNavController();

        //back fragment
        int backFragmentContainerId = binding.backFragmentContainerView.getId();
        NavHostFragment backNavHostFragment = NavHostFragment.create(R.navigation.backdrop_multi_back_graph);
        FragmentTransaction backFragmentTransaction = fragmentManager.beginTransaction().add(backFragmentContainerId, backNavHostFragment, "back_nav_host_fragment");
        backFragmentTransaction.commitNow();
        backNavController = backNavHostFragment.getNavController();
        backNavController.addOnDestinationChangedListener(new BackDestinationChangedListener());
    }


    private class BackDestinationChangedListener implements NavController.OnDestinationChangedListener{
        @Override
        public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
            if(isFirstLoadBack){
                isFirstLoadBack = false;
            }else{
                dropDown(destination.getId());
            }
        }
    }



    public void onBackSwitchButtonClick(View view){
        int viesId = view.getId();
        int currDesId = backNavController.getCurrentDestination().getId();

        if(viesId == binding.categoryImageView.getId()){
            if(viewModel.isDropDown()){
                dropUp();
            }else{
                if(currDesId != R.id.category){
                    backNavController.navigate(R.id.category);
                }else{
                    dropDown(R.id.category);
                }
            }
        }

        if(viesId == binding.filterImageView.getId()){
            if(currDesId == R.id.filter){
                dropDown(R.id.filter);
            }else{
                backNavController.navigate(R.id.filter);
            }
        }

        if(viesId == binding.searchImageView.getId()){
            if(currDesId == R.id.search){
                dropDown(R.id.search);
            }else{
                backNavController.navigate(R.id.search);
            }
        }

    }

    private class ViewModelPropertyChangedCallback extends Observable.OnPropertyChangedCallback {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
            if(propertyId == BR.backAction){
                dropUp();
            }
        }
    }


    private void dropUp(){
        viewModel.setDropDown(false);
        binding.bottomNavigationView.setVisibility(View.VISIBLE);
        binding.fab.setVisibility(View.VISIBLE);
        binding.titleTextView.setText("杉杉奥特莱斯");
        binding.categoryImageView.setImageResource(R.drawable.backdrop_branded_menu);
        binding.dropFragmentContainerView.animate().translationY(0).setDuration(300);
    }

    private void dropDown(int backId){
        viewModel.setDropDown(true);
        binding.bottomNavigationView.setVisibility(View.GONE);
        binding.fab.setVisibility(View.GONE);
        binding.categoryImageView.setImageResource(R.drawable.icon_close);

        if(backId == R.id.category){
            binding.titleTextView.setText("选择品类");
        }else if(backId == R.id.filter){
            binding.titleTextView.setText("筛选");
        }else if(backId == R.id.search){
            binding.titleTextView.setText("查找");
        }

        int dropDownDistance = viewModel.calcDropDownDistance(this, backId);
        binding.dropFragmentContainerView.animate().translationY(dropDownDistance).setDuration(300);
    }

}
