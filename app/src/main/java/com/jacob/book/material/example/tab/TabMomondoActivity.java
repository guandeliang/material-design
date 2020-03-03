/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-23 下午9:35
 *
 */

package com.jacob.book.material.example.tab;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;

import com.jacob.book.material.R;
import com.jacob.book.material.databinding.TabMomondoActivityBinding;

public class TabMomondoActivity extends AppCompatActivity {
    private static String SEARCH_DESTINATION_LABEL = "search";

    private TabMomondoActivityBinding binding;
    private TabMomondoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.tab_momondo_activity);
        viewModel = new ViewModelProvider(this).get(TabMomondoViewModel.class);
        viewModel.actionLiveData.observe(this, new ActionObserver());

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        NavHostFragment navHostFragment = (NavHostFragment)fragmentManager.findFragmentById(R.id.nav_host_fragment);
        navHostFragment.getNavController().addOnDestinationChangedListener(new NavigationDestinationChangedListener());

    }


    private class NavigationDestinationChangedListener implements NavController.OnDestinationChangedListener{
        @Override
        public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
            String label = destination.getLabel().toString();
            if(SEARCH_DESTINATION_LABEL.equals(label)){
                setDrawerSelected(binding.searchImageView, true);
                setDrawerSelected(binding.searchTextView, true);
            }
        }
    }

    private class ActionObserver implements Observer<Integer> {
        @Override
        public void onChanged(Integer action) {
            if(action == TabMomondoViewModel.ACTION_SHOW_DRAWER){
                binding.drawerLayout.openDrawer(Gravity.LEFT);
            }
        }
    }

    private void setDrawerSelected(View view, Boolean selected){
        view.setSelected(selected);
    }

}
