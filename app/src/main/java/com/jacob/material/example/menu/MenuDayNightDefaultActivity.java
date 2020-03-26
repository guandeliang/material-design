/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-1-31 下午5:05
 *
 */

package com.jacob.material.example.menu;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.media.session.MediaSessionCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuCompat;
import androidx.databinding.DataBindingUtil;

import com.google.android.material.snackbar.Snackbar;
import com.jacob.material.R;
import com.jacob.material.databinding.MenuDayNightDefaultActivityBinding;
import com.jacob.utils.WidgetsUtils;

import static android.text.Spanned.SPAN_EXCLUSIVE_EXCLUSIVE;

public class MenuDayNightDefaultActivity extends AppCompatActivity {
    private MenuDayNightDefaultActivityBinding binding;
    private ListPopupWindow listPopupWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.menu_day_night_default_activity);
        intiToolBarMenu();
        binding.bottomAppBar.setNavigationOnClickListener(new OnBottomNavigationClickListener());


        binding.popupMenuButton.setOnClickListener(new OnPopupButtonClickListener());
        binding.popupIconMenuButton.setOnClickListener(new OnPopupWithIconButtonButtonClickListener());

        listPopupWindow = initializeListPopupMenu(binding.listPopupButton);
        binding.listPopupButton.setOnClickListener(new OnPopupListButtonClickListener());

        initAutoCompleteTextView();

        registerForContextMenu(binding.contextTextView);

    }


    @SuppressLint("RestrictedApi")
    private void intiToolBarMenu(){
        //binding.toolBar.setTitleTextColor(WidgetsUtils.getColorValue(this, R.color.gray_200));
        //WidgetsUtils.setDrawableColor(this, binding.toolBar.getNavigationIcon(), R.color.gray_200);
        binding.toolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Configuration configuration = getResources().getConfiguration();
        if (configuration == null){
            binding.toolBar.getMenu().clear();
            return;
        }

        int uiMode = configuration.uiMode & Configuration.UI_MODE_NIGHT_MASK;
        if(uiMode != Configuration.UI_MODE_NIGHT_YES && uiMode != Configuration.UI_MODE_NIGHT_NO){
            binding.toolBar.getMenu().clear();
            return;
        }

        Menu menu = binding.toolBar.getMenu();
        MenuCompat.setGroupDividerEnabled(menu, true);

        if(menu instanceof MenuBuilder){
            MenuBuilder menuBuilder = (MenuBuilder) menu;
            menuBuilder.setOptionalIconsVisible(true);
        }

        for(int i = 0; i < menu.size(); i++){
            MenuItem menuItem = menu.getItem(i);
            /*
            //Menu Icon is share in more then one menu
            //if set it color, wei effect it in other menus in save activity
            Drawable drawable = menuItem.getIcon();
            if(drawable != null) {
                if(menuItem.getOrder() < 200){
                    WidgetsUtils.setDrawableColor(this, drawable, R.color.gray_200);
                }else{
                    if(uiMode == Configuration.UI_MODE_NIGHT_YES){//夜间模式
                        WidgetsUtils.setDrawableColor(this, drawable, R.color.gray_200);
                    }else{//日间模式
                        WidgetsUtils.setDrawableColor(this, drawable, R.color.gray_1000);
                    }
                }

            }
             */
            if(uiMode == Configuration.UI_MODE_NIGHT_YES && menuItem.getItemId() == R.id.action_night){//夜间模式
                menuItem.setChecked(true);

                SpannableString s = new SpannableString(menuItem.getTitle());
                s.setSpan(new ForegroundColorSpan(WidgetsUtils.getColorValue(this, R.attr.colorPrimary)), 0, s.length(), 0);
                menuItem.setTitle(s);

            }
            if(uiMode == Configuration.UI_MODE_NIGHT_NO && menuItem.getItemId() == R.id.action_day){//夜间模式
                menuItem.setChecked(true);

                SpannableString s = new SpannableString(menuItem.getTitle());
                s.setSpan(new ForegroundColorSpan(WidgetsUtils.getColorValue(this, R.attr.colorPrimary)), 0, s.length(), 0);
                menuItem.setTitle(s);
            }
        }

        binding.toolBar.setOnMenuItemClickListener(new OnToolBarMenuClickListener());
    }

    private class OnToolBarMenuClickListener implements Toolbar.OnMenuItemClickListener {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if(item.getItemId() == R.id.action_day){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            }else if(item.getItemId() == R.id.action_night){
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            }
            item.setChecked(true);
            return true;
        }
    }

    private class OnBottomNavigationClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            PopupMenu popup = new PopupMenu(MenuDayNightDefaultActivity.this, binding.bottomAppBar);
            popup.getMenuInflater().inflate(R.menu.regular_menu, popup.getMenu());
            popup.show();
        }
    }


    private class OnPopupButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            PopupMenu popup = new PopupMenu(MenuDayNightDefaultActivity.this, binding.popupMenuButton);
            popup.getMenuInflater().inflate(R.menu.regular_menu, popup.getMenu());
            popup.show();
        }
    }

    private class OnPopupWithIconButtonButtonClickListener implements View.OnClickListener{
        @SuppressLint("RestrictedApi")
        @Override
        public void onClick(View view) {
            PopupMenu popup = new PopupMenu(MenuDayNightDefaultActivity.this, binding.popupIconMenuButton);
            popup.getMenuInflater().inflate(R.menu.regular_menu, popup.getMenu());
            Menu menu = popup.getMenu();
            if(menu instanceof MenuBuilder){
                MenuBuilder menuBuilder = (MenuBuilder) menu;
                menuBuilder.setOptionalIconsVisible(true);

                for (MenuItem item : menuBuilder.getVisibleItems()) {
                    int iconLeftMargin = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 16, getResources().getDisplayMetrics());
                    int iconRightMargin = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());

                    if (item.getIcon() != null) {
                        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
                            item.setIcon(new InsetDrawable(item.getIcon(), iconLeftMargin, 0, iconRightMargin, 0));
                        } else {
                            item.setIcon(
                                    new InsetDrawable(item.getIcon(), iconLeftMargin, 0, iconRightMargin, 0) {
                                        @Override
                                        public int getIntrinsicWidth() {
                                            return getIntrinsicHeight() + iconLeftMargin + iconRightMargin;
                                        }
                                    });
                        }
                    }
                }

            }



            popup.show();
        }
    }


    private ListPopupWindow initializeListPopupMenu(View v) {
        ListPopupWindow listPopupWindow = new ListPopupWindow(this, null, R.attr.listPopupWindowStyle);
        String[] items = new String[5];
        items[0] = "点赞";
        items[1] = "查找";
        items[2] = "分享";
        items[3] = "帮助";
        items[4] = "设置";
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_popup_item, items);
        listPopupWindow.setAdapter(adapter);
        listPopupWindow.setAnchorView(v);
        listPopupWindow.setOnItemClickListener(
                (parent, view, position, id) -> {
                    Snackbar.make(this.findViewById(android.R.id.content), adapter.getItem(position), Snackbar.LENGTH_LONG)
                            .show();
                    listPopupWindow.dismiss();
                });
        return listPopupWindow;
    }


    private class OnPopupListButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            listPopupWindow.show();
        }
    }


    private void initAutoCompleteTextView() {
        String[] items = new String[11];
        items[0] = "黑龙江";
        items[1] = "吉林";
        items[2] = "辽宁";
        items[3] = "河南";
        items[4] = "河北";
        items[5] = "山东";
        items[6] = "山西";
        items[7] = "湖南";
        items[8] = "湖北";
        items[9] = "广东";
        items[10] = "广西";
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.list_popup_item, items);
        binding.autoCompleteTextView.setAdapter(adapter);
    }



    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        TextView contextMenuTextView = (TextView) v;
        menu.add("复制")
                .setOnMenuItemClickListener(
                        item -> {
                            ClipboardManager clipboard = (ClipboardManager) this.getSystemService(CLIPBOARD_SERVICE);
                            clipboard.setPrimaryClip(ClipData.newPlainText("ANDROID MENU", contextMenuTextView.getText()));
                            return true;
                        });

        menu.add("高亮")
                .setOnMenuItemClickListener(
                        item -> {
                            highlightText(contextMenuTextView);
                            return true;
                        });
    }


    private void highlightText(TextView textView) {
        CharSequence text = textView.getText();
        TypedValue value = new TypedValue();
        this.getTheme().resolveAttribute(R.attr.colorPrimary, value, true);
        Spannable spanText = Spannable.Factory.getInstance().newSpannable(text);
        spanText.setSpan(new BackgroundColorSpan(value.data), 0, text.length(), SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spanText);
    }

}
