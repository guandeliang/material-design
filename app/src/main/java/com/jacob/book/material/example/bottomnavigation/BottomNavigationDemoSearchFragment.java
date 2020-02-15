package com.jacob.book.material.example.bottomnavigation;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.navigation.Navigation;

import com.jacob.book.WidgetsUtils;
import com.jacob.book.material.R;
import com.jacob.book.material.databinding.BottomNavigationDemoSearchFragmentBinding;
import com.jacob.book.temp.TempConstant;

public class BottomNavigationDemoSearchFragment extends Fragment implements LifecycleObserver {
    private BottomNavigationDemoSearchFragmentBinding binding;

    public BottomNavigationDemoSearchFragment(){
        this.getLifecycle().addObserver(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_navigation_demo_search_fragment, container, false);

        WidgetsUtils.setSystemBarColor(this.getActivity(), R.color.gray_50);
        WidgetsUtils.setSystemBarLight(this.getActivity());

        binding.backImageView.setOnClickListener(new OnBackClickListener());


        return binding.getRoot();
    }


    private class OnBackClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            Navigation.findNavController(getActivity(), R.id.nav_host_fragment).popBackStack();
        }
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void onLifecycleDistory(){
        Log.d(TempConstant.LOG_TAG, "1 Lifecycle.Event.ON_RESUME");

        if(binding.searchEditText.requestFocus()){
            InputMethodManager imm =(InputMethodManager)this.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(binding.searchEditText, InputMethodManager.SHOW_IMPLICIT);
        }
    }


    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    private void onLifecyclePause(){
        Log.d(TempConstant.LOG_TAG, "2 Lifecycle.Event.ON_PAUSE");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    private void onLifecycleStop(){
        Log.d(TempConstant.LOG_TAG, "3 Lifecycle.Event.ON_STOP");
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private void onLifecycleDestory(){
        Log.d(TempConstant.LOG_TAG, "4 Lifecycle.Event.ON_DESTROY");
    }

}
