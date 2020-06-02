package com.ayman.searchengine;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavBackStackEntry;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
        NavBackStackEntry mBackStackEntry = navController.getBackStackEntry(R.id.mobile_navigation);
        mViewModel = new ViewModelProvider(mBackStackEntry).get(MainViewModel.class);
    }

    public String getCurrentQuery() {
        return mViewModel.getCurrentQuery();
    }

    public void setCurrentQuery(String currentQuery) {
        mViewModel.setCurrentQuery(currentQuery);
    }
}
