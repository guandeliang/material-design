/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-3-3 下午8:17
 *
 */

package com.jacob.book.material.example.tab;


import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TabMomondoViewModel extends ViewModel {
    public static final Integer ACTION_SHOW_DRAWER = 0;

    public MutableLiveData<Integer> actionLiveData = new MutableLiveData<>();

    public void setAction(Integer action){
        actionLiveData.setValue(action);
    }

}
