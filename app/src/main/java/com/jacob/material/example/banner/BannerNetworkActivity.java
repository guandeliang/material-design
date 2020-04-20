/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-2 下午3:03
 *
 */

package com.jacob.material.example.banner;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import com.google.android.material.snackbar.Snackbar;
import com.jacob.material.BR;
import com.jacob.material.R;
import com.jacob.material.databinding.BannerNetworkActivityBinding;
import com.jacob.temp.TempConstant;

public class BannerNetworkActivity extends AppCompatActivity implements LifecycleObserver {

    private BannerNetworkActivityBinding binding;
    private ConnectivityManager connectivityManager;
    private NetworkCallback networkCallback;
    private BannerNetworkViewModel viewModel;
    private long lastPressBackTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getLifecycle().addObserver(this);
        binding = DataBindingUtil.setContentView(this, R.layout.banner_network_activity);
        viewModel = new ViewModelProvider(this).get(BannerNetworkViewModel.class);
        viewModel.setNetworkState(BannerNetworkViewModel.NetworkState.LOST, false);


        int fragmentContainerId = binding.fragmentContainerView.getId();
        NavHostFragment navHostFragment = NavHostFragment.create(R.navigation.banner_network_graph);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().add(fragmentContainerId, navHostFragment, "nav_host_fragment");
        fragmentTransaction.commitNow();
        viewModel.setNavController(navHostFragment.getNavController());

        connectivityManager = (ConnectivityManager)this.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkRequest networkRequest = new NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .build();
        networkCallback = new NetworkCallback();

        connectivityManager.registerNetworkCallback(networkRequest, networkCallback);

        lastPressBackTime = 0;
        this.getOnBackPressedDispatcher().addCallback(new BackPressedCallback());
        viewModel.addOnPropertyChangedCallback(new ViewModelPropertyChangedCallback());


    }

    private class NetworkCallback extends ConnectivityManager.NetworkCallback{
        @Override
        public void onAvailable(@NonNull Network network) {
            super.onAvailable(network);
            viewModel.setNetworkState(BannerNetworkViewModel.NetworkState.AVAILABE, true);
        }


        @Override
        public void onLost(@NonNull Network network) {
            super.onLost(network);
            viewModel.setNetworkState(BannerNetworkViewModel.NetworkState.LOST, true);
        }
    }

    private class ViewModelPropertyChangedCallback extends Observable.OnPropertyChangedCallback {
        @Override
        public void onPropertyChanged(Observable sender, int propertyId) {
            if(propertyId == BR.action){
                BannerNetworkViewModel.Action action = viewModel.getAction();
                if(action == BannerNetworkViewModel.Action.BACK){
                    long now = System.currentTimeMillis();
                    if(now - lastPressBackTime < 2000){
                        finish();
                    }else{
                        lastPressBackTime = now;
                        Snackbar snackbar = Snackbar.make(binding.fragmentContainerView, "再按一次退出", Snackbar.LENGTH_LONG);
                        snackbar.show();
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
            Log.d(TempConstant.LOG_TAG, "handleOnBackPressed");
            viewModel.setAction(BannerNetworkViewModel.Action.BACK);
        }
    }



    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    public void onDestory() {
        if(connectivityManager != null){
            connectivityManager.unregisterNetworkCallback(networkCallback);
        }
    }
}
