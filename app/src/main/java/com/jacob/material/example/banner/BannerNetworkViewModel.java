/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-3-9 下午7:16
 *
 */

package com.jacob.material.example.banner;


import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;

import com.jacob.material.BR;

public class BannerNetworkViewModel extends ViewModel implements Observable {

    private PropertyChangeRegistry callbacks = new PropertyChangeRegistry();

    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        callbacks.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        callbacks.remove(callback);
    }

    enum Action{
        BACK, SAVE, ALBUM, SHARE;
    }

    @Bindable
    private Action action;

    public void setAction(Action action) {
        this.action = action;
        callbacks.notifyCallbacks(this, BR.action, null);
    }

    public Action getAction(){
        return this.action;
    }


    enum NetworkState{
        AVAILABE,
        LOST
    }

    @Bindable
    private NetworkState networkState;

    public void setNetworkState(NetworkState networkState, boolean isNotifyCallbacks) {
        this.networkState = networkState;
        if(isNotifyCallbacks){
            callbacks.notifyCallbacks(this, BR.networkState, null);
        }
    }

    public NetworkState getNetworkState(){
        return networkState;
    }


    private NavController navController;

    public NavController getNavController() {
        return navController;
    }

    public void setNavController(NavController navController) {
        this.navController = navController;
    }

}
