/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-2-19 上午9:42
 *
 */

package com.jacob.material.example.menu;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.view.MenuCompat;
import androidx.databinding.DataBindingUtil;

import com.jacob.material.R;
import com.jacob.material.databinding.MenuDayNightCustomActivityBinding;

public class MenuDayNightCustomActivity extends AppCompatActivity {
    private MenuDayNightCustomActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.menu_day_night_custom_activity);
        binding.groupMenuButton.setOnClickListener(new OnPopupButtonClickListener());
        binding.subMenuButton.setOnClickListener(new OnSubMenuButtonClickListener());
        binding.chedkedButton.setOnClickListener(new OnCheckedButtonClickListener());
    }

    private class OnPopupButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            PopupMenu popup = new PopupMenu(MenuDayNightCustomActivity.this, binding.groupMenuButton);
            popup.getMenuInflater().inflate(R.menu.menu_group, popup.getMenu());
            MenuCompat.setGroupDividerEnabled(popup.getMenu(), true);
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    return true;
                }
            });
            popup.show();
        }
    }

    private class OnSubMenuButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            PopupMenu popup = new PopupMenu(MenuDayNightCustomActivity.this, binding.subMenuButton);
            popup.getMenuInflater().inflate(R.menu.menu_with_sub, popup.getMenu());
            popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    return true;
                }
            });
            popup.show();
        }
    }

    private PopupMenu checkablePopupMenu;

    private class OnCheckedButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            if(checkablePopupMenu == null){
                checkablePopupMenu = new PopupMenu(MenuDayNightCustomActivity.this, binding.chedkedButton);
                checkablePopupMenu.getMenuInflater().inflate(R.menu.menu_checked, checkablePopupMenu.getMenu());
                MenuCompat.setGroupDividerEnabled(checkablePopupMenu.getMenu(), true);

                checkablePopupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getOrder() < 200){
                            item.setChecked(true);
                        }else{
                            item.setChecked(!item.isChecked());
                        }

                        return true;
                    }
                });

            }
            checkablePopupMenu.show();
        }
    }


}
