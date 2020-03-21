/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-3-9 下午7:16
 *
 */

package com.jacob.material.example.backdrop;


import android.content.Context;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.databinding.Bindable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.Observable;
import androidx.databinding.PropertyChangeRegistry;
import androidx.lifecycle.ViewModel;

import com.jacob.utils.WidgetsUtils;
import com.jacob.material.BR;
import com.jacob.material.R;

public class BackdropMulitBackViewModel extends ViewModel implements Observable {
    enum BackAction{
        CATEGORY_CLEAR,
        CATEGORY_OK,
        FILTER_CLEAR,
        FILTER_OK,
        SEARCH
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
    private boolean isDropDown;
    public boolean isDropDown() {
        return isDropDown;
    }

    public void setDropDown(boolean dropDown) {
        this.isDropDown = dropDown;
        callbacks.notifyCallbacks(this, BR.isDropDown, null);
    }

    @Bindable
    private BackAction backAction;

    public void setBackAction(BackAction backAction) {
        this.backAction = backAction;
        callbacks.notifyCallbacks(this, BR.backAction, null);
    }

    //if only Two-way data binding, will not call set data method when data not change
    //editor action is used to accept the event of search key press
    @Bindable
    private String searchText;

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
        setBackAction(BackAction.SEARCH);
    }



    public class  SearchTextEditorActionListener implements TextView.OnEditorActionListener{
        @Override
        public boolean onEditorAction(TextView textView, int actionId, KeyEvent event) {
            if(actionId == EditorInfo.IME_ACTION_SEARCH || actionId == EditorInfo.IME_ACTION_UNSPECIFIED){
                textView.clearFocus();
                InputMethodManager imm = (InputMethodManager)textView.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(textView.getWindowToken(),0);

                setSearchText(textView.getText().toString());

                return true;
            }else{
                return false;
            }
        }
    }

    public SearchTextEditorActionListener searchTextEditorActionListener = new SearchTextEditorActionListener();

    @BindingAdapter("onEditorActionListener")
    public static void bindOnEditorActionListener(EditText editText, TextView.OnEditorActionListener editorActionListener) {
        editText.setOnEditorActionListener(editorActionListener);
    }


    private int categoryBottomRestSpace;
    private int searchBottomRestSpace;
    private int filterBottomRestSpace;
    private int dropViewHeight;

    public void setCategoryBottomRestSpace(int categoryBottomRestSpace) {
        this.categoryBottomRestSpace = categoryBottomRestSpace;
    }

    public void setSearchBottomRestSpace(int searchBottomRestSpace) {
        this.searchBottomRestSpace = searchBottomRestSpace;
    }

    public void setFilterBottomRestSpace(int filterBottomRestSpace) {
        this.filterBottomRestSpace = filterBottomRestSpace;
    }

    public void setDropViewHeight(int dropViewHeight) {
        this.dropViewHeight = dropViewHeight;
    }

    public int calcDropDownDistance(Context context, int backId){
        int backBottomRestSpace = 0;
        if(backId == R.id.category){
            backBottomRestSpace = categoryBottomRestSpace;
        }
        if(backId == R.id.filter){
            backBottomRestSpace = filterBottomRestSpace;
        }
        if(backId == R.id.search){
            backBottomRestSpace = searchBottomRestSpace;
        }

        int dropRestHeight = WidgetsUtils.dpToPx(context, 56);
        if(dropRestHeight < backBottomRestSpace){
            dropRestHeight = backBottomRestSpace;
        }

        int dropDownDistance = dropViewHeight - dropRestHeight;
        return dropDownDistance;
    }

}
