package com.example.ohcf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.getMenu().findItem(R.id.home_page).setChecked(true);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationListener);

        Fragment theDefault = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, theDefault).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navigationListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selected = null;
            switch(item.getItemId()){
                case R.id.home_page:
                    selected = new HomeFragment();
                    break;

                case R.id.notifications:
                    selected = new NotificationFragment();
                    break;

                case R.id.profile:
                    selected = new ProfileFragment();
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selected).commit();
            return true;
        }
    };
}