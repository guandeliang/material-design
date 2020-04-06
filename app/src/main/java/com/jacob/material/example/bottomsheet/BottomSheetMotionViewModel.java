/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-3-9 下午7:16
 *
 */

package com.jacob.material.example.bottomsheet;


import androidx.lifecycle.ViewModel;
import androidx.navigation.NavController;

import com.jacob.material.example.model.GrammySinger;

import java.util.ArrayList;
import java.util.List;

public class BottomSheetMotionViewModel extends ViewModel{
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

}
