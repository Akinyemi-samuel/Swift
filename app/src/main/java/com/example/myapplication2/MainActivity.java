package com.example.myapplication2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;


import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    First_Fragment first_fragment = new First_Fragment();
    Settings_Fragment settings_fragment = new Settings_Fragment();
    rides_Fragment rides_fragment = new rides_Fragment();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView  bottomnavigationView = findViewById(R.id.bottomNavigationView);
        bottomnavigationView.setOnItemSelectedListener(this);
        loadFragment(first_fragment);


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.first_Fragment:
                loadFragment(first_fragment);

                return true;
            case R.id.settings_Fragment:
                loadFragment(settings_fragment);
                return true;
            case R.id.rides_Fragment:
                loadFragment(rides_fragment);
                return true;
        }
        return false;
    }



    private void loadFragment(Fragment fragment) {
        FragmentTransaction fn = getSupportFragmentManager().beginTransaction();
        fn.replace(R.id.fragmentContainerView,fragment,"main_fragment").commit();
    }


}