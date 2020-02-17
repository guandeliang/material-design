package com.jacob.book.material.example.bottomnavigation;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import com.jacob.book.material.R;
import com.jacob.book.material.databinding.BottomNavigationDemoActivityBinding;
import com.jacob.book.temp.TempConstant;

public class BottomNavigationDemoActivity extends AppCompatActivity {
    private static String SEARCH_DESTINATION_LABEL = "search";


    private BottomNavigationDemoActivityBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.bottom_navigation_demo_activity);
        FragmentManager fragmentManager = this.getSupportFragmentManager();

        NavHostFragment navHostFragment = (NavHostFragment)fragmentManager.findFragmentById(R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(binding.bottomNavigationView, navHostFragment.getNavController());
        navHostFragment.getNavController().addOnDestinationChangedListener(new NavigationDestinationChangedListener());

    }


    private class NavigationDestinationChangedListener implements NavController.OnDestinationChangedListener{
        @Override
        public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
            String label = destination.getLabel().toString();
            if(SEARCH_DESTINATION_LABEL.equals(label)){
                binding.bottomNavigationView.setVisibility(View.GONE);
            }else{
                binding.bottomNavigationView.setVisibility(View.VISIBLE);
            }
        }
    }


}
