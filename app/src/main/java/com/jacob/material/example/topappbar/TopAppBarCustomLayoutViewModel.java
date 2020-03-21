/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-31 下午8:08
 *
 */

package com.jacob.material.example.topappbar;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TopAppBarCustomLayoutViewModel extends ViewModel {
    public enum Operator{
        BACK, SHARE, FAVORITE, SEARCH, USER;
    }

    public MutableLiveData<Operator> operator = new MutableLiveData<>();

    public void setOperator(Operator oper){
        operator.setValue(oper);
    }

}
