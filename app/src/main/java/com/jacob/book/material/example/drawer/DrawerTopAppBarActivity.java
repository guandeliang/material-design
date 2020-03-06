/*
 * Create by Jacob G(GuanDeLiang) on 2020.
 * Copyright (c) 2020 . All rights reserved.
 * Last modified 20-3-4 下午11:31
 *
 */

package com.jacob.book.material.example.drawer;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.jacob.book.material.R;
import com.jacob.book.material.databinding.DrawerTopAppBarActivityBinding;

import java.util.ArrayList;
import java.util.List;

public class DrawerTopAppBarActivity extends AppCompatActivity {
    private DrawerTopAppBarActivityBinding binding;
    private NavController navController;
    private List<MenuItemInfo> newMenus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.drawer_top_app_bar_activity);
        binding.materialToolbar.setTitle("全部照片");

        newMenus = new ArrayList<>();
        newMenus.add(new MenuItemInfo(0, 301, "春季出游(2020)"));
        newMenus.add(new MenuItemInfo(0, 302, "弹冠相庆(0305)"));
        newMenus.add(new MenuItemInfo(0, 303, "各种美女照片"));
        newMenus.add(new MenuItemInfo(0, 304, "成长的烦恼"));
        newMenus.add(new MenuItemInfo(0, 305, "室内设计ShowRoom"));
        newMenus.add(new MenuItemInfo(0, 306, "剩下的果实"));



        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                binding.drawerLayout,
                binding.materialToolbar,
                R.string.nav_app_bar_open_drawer_description,
                R.string.nav_app_bar_navigate_up_description);
        binding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        initNavigationMenu();

        //建立FragmentContainerView 与 drawer_photp_graph之间关联关系
        int fragmentContainerId = binding.fragmentContainerView.getId();
        NavHostFragment navHostFragment = NavHostFragment.create(R.navigation.drawer_photp_graph);
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().add(fragmentContainerId, navHostFragment, "fragmentTag");
        fragmentTransaction.commitNow();

        navController = navHostFragment.getNavController();

        //以下只能二选一，如果把navController与navigationView关联，就无法解决其他菜单点击问题。
        //NavigationUI.setupWithNavController(binding.navigationView, navController);
        binding.navigationView.setNavigationItemSelectedListener(new NavItemSelectedListener());


        navController.addOnDestinationChangedListener(new NavDestinationChangedListener());

    }

    private void initNavigationMenu(){
        Menu menu = binding.navigationView.getMenu();
        menu.findItem(R.id.all).setChecked(true);

        MenuItem camera = menu.findItem(R.id.camera);
        camera.getActionView().findViewById(R.id.button).setVisibility(View.INVISIBLE);

        MenuItem shareMe = menu.findItem(R.id.share_me);
        TextView shareBadgeTextView = shareMe.getActionView().findViewById(R.id.text_view);
        shareBadgeTextView.setText("289");

        MenuItem rectentMe = menu.findItem(R.id.recent);
        TextView rectentBadgeTextView = rectentMe.getActionView().findViewById(R.id.text_view);
        rectentBadgeTextView.setText("57");

        MenuItem collection = menu.findItem(R.id.collection);
        TextView titleTextView = collection.getActionView().findViewById(R.id.title_text_view);
        titleTextView.setText("分类收藏");

        Button addButtom = collection.getActionView().findViewById(R.id.button);
        addButtom.setOnClickListener(new AddButtonClickListener());


    }

    private class AddButtonClickListener implements View.OnClickListener{
        @Override
        public void onClick(View view) {
            Menu menu = binding.navigationView.getMenu();
            MenuItemInfo menuItemInfo = null;
            for(MenuItemInfo info : newMenus){
                if(info.isCreate == false){
                    if(menu.findItem(info.id) == null){
                        menuItemInfo = info;
                    }
                }
                if(menuItemInfo != null){
                    break;
                }
            }
            if(menuItemInfo != null){
                menuItemInfo.id = View.generateViewId();
                MenuItem menuItem = menu.add(0, menuItemInfo.id, menuItemInfo.order, menuItemInfo.title);
                menuItem.setIcon(R.drawable.icon_image);
                //menuItem.setActionView(R.layout.menu_action_layout_badge);
                menuItem.setCheckable(true);
            }
        }
    }

    private class NavItemSelectedListener implements NavigationView.OnNavigationItemSelectedListener{
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            if(item.getOrder() == 101){//search

            }else if(item.getOrder() > 200 && item.getOrder() < 300){//固定的Fragment
                navController.navigate(item.getItemId());
            }else if(item.getOrder() > 300){//动态创建的链接

            }
            return true;
        }
    }

    private class NavDestinationChangedListener implements NavController.OnDestinationChangedListener{
        @Override
        public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {


        }
    }

    private class MenuItemInfo{
        private int id;
        private int order;
        private String title;
        boolean isCreate;
        public MenuItemInfo(int id, int order, String title){
            this.id = id;
            this.order = order;
            this.title = title;
            this.isCreate = false;
        }
    }


}
