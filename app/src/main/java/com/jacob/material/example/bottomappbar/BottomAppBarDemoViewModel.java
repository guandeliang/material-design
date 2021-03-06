/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-29 下午6:35
 *
 */

package com.jacob.material.example.bottomappbar;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

public class BottomAppBarDemoViewModel extends AndroidViewModel {
    public enum FragmentEnum{
        IN_BOX,MAIL_CONTENT;
    }

    private MutableLiveData<FragmentEnum> fragmentLiveData;
    private MutableLiveData<String> actionMsgLiveData;

    public BottomAppBarDemoViewModel(@NonNull Application application) {
        super(application);
        fragmentLiveData = new MutableLiveData<>();
        actionMsgLiveData = new MutableLiveData<>();

    }

    public MutableLiveData<FragmentEnum> getFragmentLiveData() {
        return fragmentLiveData;
    }

    public MutableLiveData<String> getActionMsgLiveData() {
        return actionMsgLiveData;
    }
}
