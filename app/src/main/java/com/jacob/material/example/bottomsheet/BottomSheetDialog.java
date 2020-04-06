package com.jacob.material.example.bottomsheet;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.jacob.material.R;
import com.jacob.material.databinding.BottomSheetDialogBinding;
import com.jacob.temp.TempConstant;

public class BottomSheetDialog extends BottomSheetDialogFragment {
    private BottomSheetDialogBinding binding;

    private static BottomSheetDialog instance;

    public static BottomSheetDialog getInstance(){
        if (instance == null){
            instance = new BottomSheetDialog();
        }
        return instance;
    }

    private BottomSheetDialog(){
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.bottom_sheet_dialog, container, false);
        binding.cancelCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        //First to call super onActivityCreated to Create Parent View
        super.onActivityCreated(savedInstanceState);
        View view = this.getView();
        ViewParent viewParent = view.getParent();

        if(viewParent != null){
            Log.d(TempConstant.LOG_TAG, "------------------------------");
            View vp = (View)viewParent;
            vp.setBackgroundTintMode(PorterDuff.Mode.CLEAR);
            vp.setBackgroundTintList(ColorStateList.valueOf(Color.TRANSPARENT));
            vp.setBackgroundColor(Color.TRANSPARENT);
            vp.setElevation(0);
        }
    }
}
