/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-3-9 下午7:16
 *
 */

package com.jacob.material.example.bottomsheet;


import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;

import com.jacob.material.BR;
import com.jacob.material.example.fab.FabPhoneViewModel;
import com.jacob.material.example.model.GrammySinger;

import java.util.ArrayList;
import java.util.List;

public class BottomSheetMotionViewModel extends ViewModel implements Observable {

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



    public static String TRANSITION_WRAPPER_TO_FRAGMENT = "TRANSITION_WRAPPER_TO_FRAGMENT";
    public static String TRANSITION_CARD_TO_TOOLBAR = "TRANSITION_CARD_TO_TOOLBAR";

    private NavController navController;
    private List<GrammySinger> selectedList;

    public NavController getNavController() {
        return navController;
    }

    public void setNavController(NavController navController) {
        this.navController = navController;
    }


    public void removeSelected(GrammySinger data){
        if(this.selectedList == null){
            this.selectedList = new ArrayList<>();
        }
        this.selectedList.remove(data);
    }

    public void addSelected(GrammySinger data) {
        if(this.selectedList == null){
            this.selectedList = new ArrayList<>();
        }
        if(!this.selectedList.contains(data)){
            this.selectedList.add(data);
        }
    }

    public List<GrammySinger> getSelectedList() {
        if(this.selectedList == null){
            this.selectedList = new ArrayList<>();
        }
        return this.selectedList;
    }

    public void clearSelectedList() {
        if(this.selectedList == null){
            this.selectedList = new ArrayList<>();
        }
        this.selectedList.clear();
    }
}
