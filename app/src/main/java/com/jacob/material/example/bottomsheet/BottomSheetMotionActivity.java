/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-8 下午8:53
 *
 */

package com.jacob.material.example.bottomsheet;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.jacob.material.BR;
import com.jacob.material.R;
import com.jacob.material.databinding.BottomSheetMotionActivityBinding;

public class BottomSheetMotionActivity extends AppCompatActivity {
    private BottomSheetMotionActivityBinding binding;
    private BottomSheetMotionViewModel viewModel;
    private long lastPressBackTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.bottom_sheet_motion_activity);
        viewModel = new ViewModelProvider(this).get(BottomSheetMotionViewModel.class);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        viewModel.setNavController(navController);

        lastPressBackTime = 0;
        this.getOnBackPressedDispatcher().addCallback(new BackPressedCallback());
        viewModel.addOnPropertyChangedCallback(new ViewModelPropertyChangedCallback());

    }


    private class ViewModelPropertyChangedCallback extends Observable.OnPropertyChangedCallback {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
            if(propertyId == BR.action){
                BottomSheetMotionViewModel.Action action = viewModel.getAction();

                if(action == BottomSheetMotionViewModel.Action.BACK){
                    if(viewModel.getNavController().getCurrentDestination().getId() == R.id.select){
                        long now = System.currentTimeMillis();
                        if(now - lastPressBackTime < 2000){
                            finish();
                        }else{
                            lastPressBackTime = now;
                            Toast toast = Toast.makeText(BottomSheetMotionActivity.this,  "再按一次退出", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER,0,0);
                            toast.show();
                        }
                    }else{
                        viewModel.getNavController().popBackStack();
                    }
                }else{
                    if(viewModel.getNavController().getCurrentDestination().getId() != R.id.select){
                        viewModel.getNavController().popBackStack();
                    }
                }
            }
        }
    }



    private class BackPressedCallback extends OnBackPressedCallback {
        private BackPressedCallback(){
            super(true);
        }

        @Override
        public void handleOnBackPressed() {
            viewModel.setAction(BottomSheetMotionViewModel.Action.BACK);
        }
    }


}
