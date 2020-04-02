/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-31 下午5:05
 *
 */

package com.jacob.material.example.menu;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.jacob.material.R;
import com.jacob.material.databinding.MenuAutoCompleteActivityBinding;
import com.jacob.material.example.model.District;
import com.jacob.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;

public class MenuAutoCompleteActivity extends AppCompatActivity {
    private MenuAutoCompleteActivityBinding binding;
    private List<District> districtList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.menu_auto_complete_activity);
        districtList = JsonUtils.loadDistricts(this.getResources());

        initLabelList();
        initStateList();
    }

    private void initLabelList() {
        String[] items = new String[5];
        items[0] = "亲属";
        items[1] = "同学";
        items[2] = "同事";
        items[3] = "好友";
        items[4] = "其他";
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_popup_item, items);
        binding.labelCompleteTextView.setInputType(InputType.TYPE_NULL);//只能选择
        binding.labelCompleteTextView.setAdapter(adapter);
        binding.labelCompleteTextView.setListSelection(0);
        binding.labelCompleteTextView.setText(adapter.getItem(0));
        adapter.getFilter().filter(null);//清空过滤条件，否则点击的之后，只能显示部分数据
    }



    private void initStateList() {
        List<District> stateList = new ArrayList<>();
        for(District d:districtList){
            if(d.getCode().endsWith("0000")){
                stateList.add(d);
            }
        }

        ArrayAdapter<District> stateAdapter = new ArrayAdapter<>(this, R.layout.list_popup_item, stateList);
        binding.stateCompleteTextView.setInputType(InputType.TYPE_NULL);//只能选择
        binding.stateCompleteTextView.setAdapter(stateAdapter);
        binding.stateCompleteTextView.setListSelection(0);
        binding.stateCompleteTextView.setText(stateAdapter.getItem(0).toString());
        stateAdapter.getFilter().filter(null);//清空过滤条件，否则点击的之后，只能显示部分数据
        setCityList(stateAdapter.getItem(0));

        binding.stateCompleteTextView.setOnItemClickListener(new OnStateItemClickListener(stateAdapter));
    }

    private class OnStateItemClickListener implements AdapterView.OnItemClickListener{
        private ArrayAdapter<District> stateAdapter;
        public OnStateItemClickListener(ArrayAdapter<District> stateAdapter){
            this.stateAdapter = stateAdapter;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            binding.stateCompleteTextView.setListSelection(position);
            binding.stateCompleteTextView.setText(stateAdapter.getItem(position).toString());
            stateAdapter.getFilter().filter(null);//清空过滤条件，否则点击的之后，只能显示部分数据
            setCityList(stateAdapter.getItem(position));
        }
    }


    private void setCityList(District state) {
        String stateCode = state.getCode();
        List<District> cityList = new ArrayList<>();
        for(District d:districtList){
            if(d.getCode().startsWith(stateCode.substring(0,2)) && d.getCode().endsWith("00") && !d.getCode().endsWith("0000")){
                cityList.add(d);
            }
        }

        //必须每次都创建新的cityAdapter，否则会收到历史数据干扰，
        //因为在ArrayAdapter里维持两个数据列表，下次使用的时候，会访问上次的数据列表，这个很讨厌
        ArrayAdapter<District> cityAdapter = new ArrayAdapter<>(this, R.layout.list_popup_item, cityList);
        binding.cityCompleteTextView.setAdapter(cityAdapter);
        binding.cityCompleteTextView.setInputType(InputType.TYPE_NULL);//只能选择


        if(cityAdapter.getCount() > 0){
            binding.cityCompleteTextView.setListSelection(0);
            binding.cityCompleteTextView.setText(cityAdapter.getItem(0).toString());
            setDictList(cityAdapter.getItem(0));
        }

        cityAdapter.getFilter().filter(null);//清空过滤条件，否则点击的之后，只能显示部分数据

        binding.cityCompleteTextView.setOnItemClickListener(new OnCityItemClickListener(cityAdapter));
    }


    private class OnCityItemClickListener implements AdapterView.OnItemClickListener{
        private ArrayAdapter<District> cityAdapter;
        public OnCityItemClickListener(ArrayAdapter<District> cityAdapter){
            this.cityAdapter = cityAdapter;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            binding.cityCompleteTextView.setListSelection(position);
            binding.cityCompleteTextView.setText(cityAdapter.getItem(position).toString());
            cityAdapter.getFilter().filter(null);//清空过滤条件，否则点击的之后，只能显示部分数据
            setDictList(cityAdapter.getItem(position));
        }
    }

    private void setDictList(District city) {
        String cityCode = city.getCode();
        List<District> dictList = new ArrayList<>();
        for(District d:districtList){
            if(d.getCode().startsWith(cityCode.substring(0,4)) && !d.getCode().endsWith("00")){
                dictList.add(d);
            }
        }

        //必须每次都创建新的cityAdapter，否则会收到历史数据干扰，
        //因为在ArrayAdapter里维持两个数据列表，下次使用的时候，会访问上次的数据列表，这个很讨厌
        ArrayAdapter<District> dictAdapter = new ArrayAdapter<>(this, R.layout.list_popup_item, dictList);
        binding.distCompleteTextView.setAdapter(dictAdapter);
        binding.distCompleteTextView.setInputType(InputType.TYPE_NULL);//只能选择


        if(dictAdapter.getCount() > 0){
            binding.distCompleteTextView.setListSelection(0);
            binding.distCompleteTextView.setText(dictAdapter.getItem(0).toString());
        }

        dictAdapter.getFilter().filter(null);//清空过滤条件，否则点击的之后，只能显示部分数据

    }



}
