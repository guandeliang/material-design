/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-17 下午1:06
 *
 */

package com.jacob.material.example.fab;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.transition.MaterialContainerTransform;
import com.jacob.material.R;
import com.jacob.material.databinding.FabPhoneDialFragmentBinding;
import com.jacob.material.example.adapter.SortableAddressBookAdapter;
import com.jacob.material.example.model.AddressBook;
import com.jacob.material.widgets.LinearLayoutVertialItemDecoration;
import com.jacob.utils.JsonUtils;
import com.jacob.utils.WidgetsUtils;

import java.util.ArrayList;
import java.util.List;

public class FabPhoneDialFragment extends Fragment implements LifecycleObserver {
    private FabPhoneDialFragmentBinding binding;
    private FabPhoneViewModel viewModel;
    private List<AddressBook> list;
    private SortableAddressBookAdapter adapter;
    private BottomSheetBehavior sheetBehavior;

    public FabPhoneDialFragment(){
        this.getLifecycle().addObserver(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.startPostponedEnterTransition();

        binding = DataBindingUtil.inflate(inflater, R.layout.fab_phone_dial_fragment, container, false);
        binding.keyConstraintLayout.setTransitionName(FabPhoneViewModel.TRANSITION_FAB_TO_VIEW);
        binding.fabButton.setTransitionName(FabPhoneViewModel.TRANSITION_FAB_TO_FULL);

        MaterialContainerTransform transform = new MaterialContainerTransform();
        //transform.addTarget(binding.keyConstraintLayout);//这句不能加，加了就出问题，
        transform.setDuration(500);
        transform.setScrimColor(WidgetsUtils.getColorValue(getContext(), R.attr.scrimBackground));
        this.setSharedElementEnterTransition(transform);
        this.setSharedElementReturnTransition(transform);


        viewModel = new ViewModelProvider(this.getActivity()).get(FabPhoneViewModel.class);
        binding.setFragment(this);

        binding.numberEditText.setText("");
        binding.numberEditText.setShowSoftInputOnFocus(false);
        binding.numberEditText.requestFocus();

        sheetBehavior = BottomSheetBehavior.from(binding.keyConstraintLayout);
        sheetBehavior.setFitToContents(true);
        sheetBehavior.setHideable(true);
        sheetBehavior.setPeekHeight(WidgetsUtils.dpToPx(this.getContext(), 0));
        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        sheetBehavior.addBottomSheetCallback(new SheetStateChangeCallback());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);

        int px_24 = WidgetsUtils.dpToPx(getContext(), 24);
        LinearLayoutVertialItemDecoration decoration = new LinearLayoutVertialItemDecoration(0, px_24, 0);
        binding.recyclerView.addItemDecoration(decoration);

        list = JsonUtils.loadAddressBooks(getResources());
        adapter = new SortableAddressBookAdapter(list, new OnAddressBookItemClickListener());
        binding.recyclerView.setAdapter(adapter);

        return binding.getRoot();
    }

    private class OnAddressBookItemClickListener implements SortableAddressBookAdapter.OnItemClickListener{
        @Override
        public void onItemClick(SortableAddressBookAdapter.AddressBookViewHolder holder, AddressBook addressBook) {
            binding.numberEditText.setText(addressBook.getMobile());
            binding.numberEditText.setSelection(addressBook.getMobile().length());
        }
    }


    private AddressBook findAddressBook(String mobile){
        AddressBook addressBook = null;
        for(AddressBook ab:list){
            if(ab.getMobile().equals(mobile)){
                addressBook = ab;
                break;
            }
        }
        return addressBook;
    }

    private void call(){
        String mobile = binding.numberEditText.getText().toString();
        AddressBook addressBook = findAddressBook(mobile);
        if(addressBook == null){
            addressBook = new AddressBook();
            addressBook.setId(-1);
            addressBook.setTitle("不在通讯录中");
            addressBook.setMobile(mobile);
            addressBook.setFileName("joker");
        }

        Bundle bundle = new Bundle();
        bundle.putSerializable(FabPhoneCallFragment.PARAM_ADDRESS_BOOK, addressBook);
        bundle.putString(FabPhoneCallFragment.PARAM_TRANSITION_TO_FULL, FabPhoneViewModel.TRANSITION_FAB_TO_FULL);

        FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                .addSharedElement(binding.fabButton, FabPhoneViewModel.TRANSITION_FAB_TO_FULL)
                .build();

        viewModel.getNavController().navigate(R.id.show_call, bundle, null, extras);
    }



    private class SheetStateChangeCallback extends BottomSheetBehavior.BottomSheetCallback{
        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if(BottomSheetBehavior.STATE_EXPANDED == newState | BottomSheetBehavior.STATE_HALF_EXPANDED == newState){
                binding.fabButton.setEnabled(true);
                binding.fabButton.show();
                binding.fabButton.setBackgroundTintList(ColorStateList.valueOf(WidgetsUtils.getColorValue(getContext(), R.color.material_color_green)));
                binding.fabButton.setImageResource(R.drawable.icon_call);
            }else if(BottomSheetBehavior.STATE_COLLAPSED == newState | BottomSheetBehavior.STATE_HIDDEN == newState){
                binding.fabButton.setEnabled(true);
                binding.fabButton.show();
                binding.fabButton.setBackgroundTintList(ColorStateList.valueOf(WidgetsUtils.getColorValue(getContext(), R.attr.colorSecondary)));
                binding.fabButton.setImageResource(R.drawable.icon_dialpad);
            }else{
                binding.fabButton.setEnabled(false);
                binding.fabButton.hide();
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    }


    public void onDialButtonClick(View view){
        if(sheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED){
            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }else{
            if(binding.numberEditText.getText().length() == 0){
                binding.numberEditText.setText(list.get(0).getMobile());
            }else{
                call();
            }
        }
    }

    public void onKeyButtonClick(View view, CharSequence value){

        int selectionStart = binding.numberEditText.getSelectionStart();
        int selectionEnd = binding.numberEditText.getSelectionEnd();
        String oldText = binding.numberEditText.getText().toString();

        if("-".equals(value)){
            if(selectionStart == selectionEnd && selectionStart == 0){
                return;
            }

            String newText = oldText.substring(0, selectionStart);
            if(selectionStart == selectionEnd){
                newText = oldText.substring(0, selectionStart-1);
            }
            int newCursorPosition = newText.length();

            if(selectionEnd < oldText.length()){
                newText = newText + oldText.substring(selectionEnd);
            }
            binding.numberEditText.setText(newText);
            binding.numberEditText.requestFocus();
            binding.numberEditText.setSelection(newCursorPosition);
        }else{
            if(oldText.length() > 15){
                return;
            }
            if(selectionStart == selectionEnd){
                if(selectionStart == oldText.length()){
                    String newText = oldText + value;
                    binding.numberEditText.setText(newText);
                    binding.numberEditText.requestFocus();
                    binding.numberEditText.setSelection(binding.numberEditText.getText().length());
                }else{
                    String newText = oldText.substring(0, selectionStart) + value + oldText.substring(selectionStart);
                    binding.numberEditText.setText(newText);
                    binding.numberEditText.requestFocus();
                    binding.numberEditText.setSelection(selectionStart + 1);
                }
            }else{
                String newText = oldText.substring(0, selectionStart) + value + oldText.substring(selectionEnd);
                binding.numberEditText.setText(newText);
                binding.numberEditText.requestFocus();
                binding.numberEditText.setSelection(selectionStart + 1);
            }
        }
    }


    public void onNumberTextChanged(CharSequence text) {
        List<AddressBook> addList = new ArrayList<>();
        List<AddressBook> removeList = new ArrayList<>();

        String searchText = text.toString();
        if(searchText == null || searchText.length() == 0){
            addList.addAll(list);
        }else{
            for(AddressBook ab:list){
                if(ab.getTitle().indexOf(searchText) >=0 || ab.getMobile().indexOf(searchText) >=0){
                    addList.add(ab);
                }else{
                    removeList.add(ab);
                }
            }
        }
        adapter.setData(addList, removeList);
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    private void onLifecycleResume(){
        this.getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        onNumberTextChanged(binding.numberEditText.getText());
    }


}
