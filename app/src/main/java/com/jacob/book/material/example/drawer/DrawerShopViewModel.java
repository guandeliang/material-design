/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-3-3 下午8:57
 *
 */

package com.jacob.book.material.example.drawer;


import android.util.Log;

import androidx.databinding.Bindable;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.ViewModel;

import com.jacob.book.material.BR;
import com.jacob.book.temp.TempConstant;

public class DrawerShopViewModel extends ViewModel implements Observable {
    public enum Category{
        CAR(101, "草原名车"),
        CLTHES(102, "品质正装"),
        COVERALL(103, "职业工装"),
        DLING(104, "闪闪一族"),
        FOOD(105, "福建美食"),
        GUN(106, "防身必备"),
        USED(107, "二手精品"),
        WATCH(108, "广东名表"),
        WHEEL(109, "拍照利器"),
        YACHT(111, "沙漠游艇");

        private int id;
        private String title;
        private Category(int id, String title){
            this.id = id;
            this.title = title;
        }

        public int getId(){
            return id;
        }

        public String getTitle(){
            return title;
        }
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

    @Bindable
    private Category currCategory;

    public Category getCurrCategory() {
        return currCategory;
    }

    public void setCurrCategory(Category currCategory) {
        Log.d(TempConstant.LOG_TAG, "category = " + currCategory.getId());
        this.currCategory = currCategory;
        callbacks.notifyCallbacks(this, BR.currCategory, null);
    }

}
