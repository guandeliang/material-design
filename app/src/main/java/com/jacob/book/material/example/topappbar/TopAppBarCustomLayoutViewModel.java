package com.jacob.book.material.example.topappbar;

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
