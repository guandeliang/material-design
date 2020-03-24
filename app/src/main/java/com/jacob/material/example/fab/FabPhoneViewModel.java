/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-3-9 下午7:16
 *
 */

package com.jacob.material.example.fab;


import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;

import com.jacob.material.BR;

public class FabPhoneViewModel extends ViewModel implements Observable {
    public static String TRANSITION_FAB_TO_VIEW = "TRANSITION_FAB_TO_VIEW";
    public static String TRANSITION_FAB_TO_FULL = "TRANSITION_FAB_TO_FULL";
    public static String TRANSITION_ITEM_TO_FULL = "TRANSITION_ITEM_TO_FULL";
    public static String TRANSITION_IMAGE_TO_IMAGE = "TRANSITION_IMAGE_TO_IMAGE";


    enum Action{
        BACK
    }

    private PropertyChangeRegistry callbacks = new PropertyChangeRegistry();
    @Override
    public void addOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        callbacks.add(callback);
    }

    @Override
    public void removeOnPropertyChangedCallback(OnPropertyChangedCallback callback) {
        callbacks.remove(callback);
    }


    private NavController navController;
    public NavController getNavController() {
        return navController;
    }

    public void setNavController(NavController navController) {
        this.navController = navController;
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


}
