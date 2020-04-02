/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-8 下午8:53
 *
 */

package com.jacob.material.example.menu;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.MenuCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.jacob.material.R;
import com.jacob.material.databinding.MenuTopAppBarActivityBinding;
import com.jacob.material.example.adapter.AddressBookCardAdapter;
import com.jacob.material.example.adapter.SortableAddressBookAdapter;
import com.jacob.material.example.fab.FabPhoneSearchFragment;
import com.jacob.material.example.model.AddressBook;
import com.jacob.material.widgets.LinearLayoutVertialItemDecoration;
import com.jacob.utils.JsonUtils;
import com.jacob.utils.WidgetsUtils;

import java.util.List;

public class MenuTopAppBarActivity extends AppCompatActivity {

    private MenuTopAppBarActivityBinding binding;
    private MenuBuilder menuBuilder;
    private int currSortMenuItemId;
    private int currFilterMenuItemId;
    private int uiMode;
    private List<AddressBook> list;
    private AddressBookCardAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.menu_top_app_bar_activity);

        //每次切换theme的时候，会重启Activity，所以需要恢复上次的菜单选择情况
        if(savedInstanceState != null){
            currSortMenuItemId = savedInstanceState.getInt("currSortMenuItemId", 0);
            currFilterMenuItemId = savedInstanceState.getInt("currFilterMenuItemId", 0);
        }else{
            currSortMenuItemId = 0;
            currFilterMenuItemId = 0;
        }


        setSupportActionBar(binding.materialToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.icon_close);
        ab.setDisplayHomeAsUpEnabled(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);

        int px_16 = WidgetsUtils.dpToPx(this, 16);
        LinearLayoutVertialItemDecoration decoration = new LinearLayoutVertialItemDecoration(px_16, px_16, px_16);
        binding.recyclerView.addItemDecoration(decoration);

        list = JsonUtils.loadAddressBooks(getResources());
        adapter = new AddressBookCardAdapter(list);

        binding.recyclerView.setAdapter(adapter);

        adapter.setOnItemChildClickListener(new OnAddressBookItemChildClickListener());


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        //每次切换theme的时候，会重启Activity，所以需要保存上次的菜单选择情况
        outState.putInt("currSortMenuItemId", currSortMenuItemId);
        outState.putInt("currFilterMenuItemId", currFilterMenuItemId);
        super.onSaveInstanceState(outState);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.menu_connect, menu);
        if(menu instanceof MenuBuilder){
            menuBuilder = (MenuBuilder) menu;
            menuBuilder.setOptionalIconsVisible(true);
        }
        MenuCompat.setGroupDividerEnabled(menu, true);
        intiMenuItem();
        setMenuItemColor();
        return true;
    }

    @SuppressLint("RestrictedApi")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getOrder() == 2){

        }else if(item.getOrder() > 100 && item.getOrder() < 200){
            changeSort(item);
        }else if(item.getOrder() > 200 && item.getOrder() < 300){
            changeFilter(item);
        }else if(item.getOrder() > 300){

        }
        return true;
    }

    @SuppressLint("RestrictedApi")
    private void changeSort(MenuItem item){
        if(item.isChecked() && item.getItemId() ==  currSortMenuItemId){
            return;
        }
        currSortMenuItemId = item.getItemId();
        item.setChecked(true);
        for (int i = 0; i < menuBuilder.size(); i++) {
            MenuItem mi = menuBuilder.getItem(i);
            if(mi.getOrder() > 100 && mi.getOrder() < 200){
                if(mi.getItemId() != currSortMenuItemId){
                    mi.setChecked(false);
                }
            }
        }
        setMenuItemColor();
    }

    @SuppressLint("RestrictedApi")
    private void changeFilter(MenuItem item){
        if(item.isChecked() && item.getItemId() ==  currFilterMenuItemId){
            return;
        }
        currFilterMenuItemId = item.getItemId();
        item.setChecked(true);
        for (int i = 0; i < menuBuilder.size(); i++) {
            MenuItem mi = menuBuilder.getItem(i);
            if(mi.getOrder() > 200 && mi.getOrder() < 300){
                if(mi.getItemId() != currFilterMenuItemId){
                    mi.setChecked(false);
                }
            }
        }
        setMenuItemColor();
    }


    @SuppressLint("RestrictedApi")
    private void intiMenuItem(){
        Configuration configuration = getResources().getConfiguration();
        if (configuration == null){
            menuBuilder.findItem(R.id.action_theme).setVisible(false);
            return;
        }

        uiMode = configuration.uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if(uiMode != Configuration.UI_MODE_NIGHT_YES && uiMode != Configuration.UI_MODE_NIGHT_NO){
            menuBuilder.findItem(R.id.action_theme).setVisible(false);
            return;
        }

        if(uiMode == Configuration.UI_MODE_NIGHT_YES){//夜间模式
            menuBuilder.findItem(R.id.action_theme).getActionView().setSelected(false);
        }else if(uiMode == Configuration.UI_MODE_NIGHT_NO){
            menuBuilder.findItem(R.id.action_theme).getActionView().setSelected(true);
        }

        menuBuilder.findItem(R.id.action_theme).getActionView().setOnClickListener(new OnThemeSwitchClickListener());

        if(currSortMenuItemId == 0){
            currSortMenuItemId = R.id.action_number;
        }
        if(currFilterMenuItemId == 0){
            currFilterMenuItemId = R.id.action_all;
        }

        for (int i = 0; i < menuBuilder.size(); i++) {
            MenuItem item = menuBuilder.getItem(i);
            if(item.getOrder() > 100 && item.getOrder() < 200){
                if(currSortMenuItemId == item.getItemId()){
                    item.setChecked(true);
                }else{
                    item.setChecked(false);
                }
            }
            if(item.getOrder() > 200 && item.getOrder() < 300){
                if(currFilterMenuItemId == item.getItemId()){
                    item.setChecked(true);
                }else{
                    item.setChecked(false);
                }
            }
        }
    }

    @SuppressLint("RestrictedApi")
    private void setMenuItemColor(){
        /*
         * 在 布局文件中，app:popupTheme="@null"
         * 是为了放弃Style中对popupTheme的设置，否则无法更改MenuItem的图标颜色
         */
        for (int i = 0; i < menuBuilder.size(); i++) {
            MenuItem item = menuBuilder.getItem(i);
            if(item.getOrder() > 100){
                if(item.isChecked()){
                    int selectColor = WidgetsUtils.getColorValue(this, R.attr.colorPrimary);
                    DrawableCompat.setTint(item.getIcon(), selectColor);
                    SpannableString s = new SpannableString(item.getTitle());
                    s.setSpan(new ForegroundColorSpan(selectColor), 0, s.length(), 0);
                    item.setTitle(s);
                }else{
                    int unSelectColor = WidgetsUtils.getColorValue(this, R.attr.colorOnSurface);
                    DrawableCompat.setTint(item.getIcon(), unSelectColor);
                    SpannableString s = new SpannableString(item.getTitle());
                    s.setSpan(new ForegroundColorSpan(unSelectColor), 0, s.length(), 0);
                    item.setTitle(s);
                }
            }
        }
    }

    private class OnAddressBookItemChildClickListener implements OnItemChildClickListener{
        @Override
        public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
            if(view.getId() == R.id.video_image_view){
                return;
            }
            if(view.getId() == R.id.call_image_view){
                return;
            }
            if(view.getId() == R.id.more_image_view){
                PopupMenu menu = new PopupMenu(MenuTopAppBarActivity.this, view);
                menu.getMenu().add("修改");
                menu.getMenu().add("删除");
                menu.getMenu().add("添加到常用联系人");
                menu.getMenu().add("清除听话记录");
                menu.show();
            }

        }
    }



    private class OnThemeSwitchClickListener implements View.OnClickListener{
        @SuppressLint("RestrictedApi")
        @Override
        public void onClick(View view) {
            if(uiMode == Configuration.UI_MODE_NIGHT_YES){//夜间模式
                menuBuilder.findItem(R.id.action_theme).getActionView().setSelected(true);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }else if(uiMode == Configuration.UI_MODE_NIGHT_NO){
                menuBuilder.findItem(R.id.action_theme).getActionView().setSelected(false);
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
        }
    }





}
