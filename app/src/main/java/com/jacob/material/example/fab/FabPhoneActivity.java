/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-2 下午3:03
 *
 */

package com.jacob.material.example.fab;

import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.Snackbar;
import com.jacob.material.BR;
import com.jacob.material.R;
import com.jacob.material.databinding.FabPhoneActivityBinding;

public class FabPhoneActivity extends AppCompatActivity {
    private FabPhoneActivityBinding binding;
    private FabPhoneViewModel viewModel;
    private long lastPressBackTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_ACTIVITY_TRANSITIONS);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.fab_phone_activity);
        viewModel = new ViewModelProvider(this).get(FabPhoneViewModel.class);
        viewModel.addOnPropertyChangedCallback(new ViewModelPropertyChangedCallback());
        lastPressBackTime = System.currentTimeMillis();

        this.getOnBackPressedDispatcher().addCallback(new BackPressedCallback());


        //建立FragmentContainerView 与 drawer_photp_graph之间关联关系
        int fragmentContainerId = binding.fragmentContainerView.getId();
        NavHostFragment navHostFragment = NavHostFragment.create(R.navigation.fab_phone_graph);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().add(fragmentContainerId, navHostFragment, "nav_host_fragment");
        fragmentTransaction.commitNow();
        viewModel.setNavController(navHostFragment.getNavController());

    }



    private class ViewModelPropertyChangedCallback extends Observable.OnPropertyChangedCallback {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
            if(propertyId == BR.action){
                FabPhoneViewModel.Action action = viewModel.getAction();

                if(action == FabPhoneViewModel.Action.BACK){
                    if(viewModel.getNavController().getCurrentDestination().getId() == R.id.address){
                        long now = System.currentTimeMillis();
                        if(now - lastPressBackTime < 2000){
                            finish();
                        }else{
                            lastPressBackTime = now;
                            Toast toast = Toast.makeText(FabPhoneActivity.this,  "再按一次退出", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER,0,0);
                            toast.show();
                        }
                    }else{
                        viewModel.getNavController().popBackStack();
                    }

                    return;
                }
            }
        }
    }


    private class BackPressedCallback extends OnBackPressedCallback{
        private BackPressedCallback(){
            super(true);
        }

        @Override
        public void handleOnBackPressed() {
            viewModel.setAction(FabPhoneViewModel.Action.BACK);
        }
    }


}
