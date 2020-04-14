/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-31 下午5:05
 *
 */

package com.jacob.material.example.chip;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.jacob.material.R;
import com.jacob.material.databinding.ChipDayNightDefaultActivityBinding;
import com.jacob.material.databinding.ChipInputActivityBinding;
import com.jacob.material.example.adapter.SortableAddressBookAdapter;
import com.jacob.material.example.adapter.SortableAddressMailAdapter;
import com.jacob.material.example.fab.FabPhoneSearchFragment;
import com.jacob.material.example.model.AddressBook;
import com.jacob.material.widgets.LinearLayoutVertialItemDecoration;
import com.jacob.temp.TempConstant;
import com.jacob.utils.JsonUtils;
import com.jacob.utils.WidgetsUtils;

import java.util.ArrayList;
import java.util.List;

public class ChipInputActivity extends AppCompatActivity {
    private ChipInputActivityBinding binding;
    private List<AddressBook> list;
    private SortableAddressMailAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        binding = DataBindingUtil.setContentView(this, R.layout.chip_input_activity);
        binding.toEditText.setOnEditorActionListener(new ChipEditTextActionListener());
        binding.toEditText.setOnKeyListener(new ChipEditTextKeyEventListener());
        binding.toEditText.addTextChangedListener(new ChipEditTextWatcher());

        ViewTreeObserver viewTreeObserver = binding.constraintLayout.getViewTreeObserver();
        if(viewTreeObserver.isAlive()){
            viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    binding.constraintLayout.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    int scrollViewHeight = binding.nestedScrollView.getHeight();
                    int contentHeight = binding.contentConstraintLayout.getHeight();
                    int oriHeight = binding.messageEditText.getHeight();
                    int newHeight = oriHeight + scrollViewHeight - contentHeight;
                    ViewGroup.LayoutParams params = binding.messageEditText.getLayoutParams();
                    params.height = newHeight;
                    binding.messageEditText.setLayoutParams(params);
                }
            });
        }

        binding.toEditText.setOnFocusChangeListener((view, b) -> {
            if(b){
                scrollToView(binding.toEditText);
            }
        });

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);

        int px_8 = WidgetsUtils.dpToPx(this, 8);
        LinearLayoutVertialItemDecoration decoration = new LinearLayoutVertialItemDecoration(0, px_8, 0);
        binding.recyclerView.addItemDecoration(decoration);

        list = JsonUtils.loadAddressBooks(getResources());
        adapter = new SortableAddressMailAdapter(list, new OnMailItemClickListener());

        binding.recyclerView.setAdapter(adapter);


    }

    private void scrollToView(View view){
        int distance = view.getTop();
        boolean isChildView = false;
        ViewParent parent = view.getParent();
        while (parent != null){
            View parentView = (View)parent;
            if(parentView instanceof NestedScrollView && parentView.getId() == binding.nestedScrollView.getId()){
                isChildView = true;
                break;
            }else{
                distance = distance + parentView.getTop();
            }
            parent = parentView.getParent();
        }
        if(isChildView){
            binding.nestedScrollView.smoothScrollTo(0, distance);
        }
    }

    private class ChipEditTextWatcher implements TextWatcher{
        @Override
        public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
            List<AddressBook> addList = new ArrayList<>();
            List<AddressBook> removeList = new ArrayList<>();

            String searchText = charSequence.toString();
            if(searchText == null || searchText.length() == 0){
                binding.listCareView.setVisibility(View.GONE);
                addList.addAll(list);
                adapter.setData(addList, removeList);
            }else{
                for(AddressBook ab:list){
                    if(ab.getTitle().indexOf(searchText) >=0 || ab.getMail().indexOf(searchText) >=0){
                        addList.add(ab);
                    }else{
                        removeList.add(ab);
                    }
                }
                if(addList.size() == 0){
                    binding.listCareView.setVisibility(View.GONE);
                }else{
                    adapter.setData(addList, removeList);
                    binding.listCareView.setVisibility(View.VISIBLE);
                }

            }
        }

        @Override
        public void afterTextChanged(Editable editable) {}
    }


    private class ChipEditTextKeyEventListener implements View.OnKeyListener{
        @Override
        public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
            int action = -1;
            if(keyEvent != null){
                action = keyEvent.getAction();
            }
            if(keyCode == KeyEvent.KEYCODE_DEL && action == 0){
                int selectionStart = binding.toEditText.getSelectionStart();
                int selectionEnd = binding.toEditText.getSelectionEnd();
                if(selectionStart == selectionEnd && selectionStart == 0){
                    removeLastChipFromGroup(binding.toChipGroup);
                    return true;
                }
            }
            return false;
        }
    }

    private class ChipEditTextActionListener implements TextView.OnEditorActionListener{
        @Override
        public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
            if(actionId == EditorInfo.IME_ACTION_DONE){
                String text= binding.toEditText.getText().toString();
                if(!text.isEmpty()){
                    addChipToGroup(text, -1, binding.toChipGroup);
                    binding.toEditText.setText("");
                    scrollToView(binding.toEditText);
                    return true;
                }else{
                    return false;
                }
            }else{
                return false;
            }
        }
    }


    private class OnMailItemClickListener implements SortableAddressMailAdapter.OnItemClickListener{
        @Override
        public void onItemClick(SortableAddressMailAdapter.AddressBookViewHolder holder, AddressBook addressBook) {
            String title = addressBook.getTitle();
            int imageResId = getResources().getIdentifier(addressBook.getFileName(), "drawable", getPackageName());
            addChipToGroup(title, imageResId, binding.toChipGroup);

            binding.toEditText.setText("");
            scrollToView(binding.toEditText);
            binding.listCareView.setVisibility(View.GONE);


        }
    }


    private void addChipToGroup(String text, int resId,  ChipGroup chipGroup) {
        if(resId < 0){
            resId = R.drawable.person_none;
        }
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),resId);
        RoundedBitmapDrawable rnd = (RoundedBitmapDrawable) RoundedBitmapDrawableFactory.create(getResources(), bitmap);
        rnd.setCircular(true);
        Chip chip = new Chip(this);
        chip.setChipIcon(rnd);
        chip.setText(text);
        chip.setCloseIconVisible(true);
        chip.setCloseIconResource(R.drawable.icon_remove_circle_outline);
        chip.setCheckable(false);
        chip.setClickable(false);
        chipGroup.addView(chip);
        chip.setOnCloseIconClickListener(view -> {chipGroup.removeView(chip);});
    }

    private void removeLastChipFromGroup(ChipGroup chipGroup) {
        int childCount = chipGroup.getChildCount();
        if(childCount > 0){
            chipGroup.removeViewAt(childCount - 1);
        }
    }
}
