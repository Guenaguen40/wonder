package com.soumayaguenaguen.wonder;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;


import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DestinationActivity extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.nav_item_2);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_item_1:
                    // Start the HomeActivity
                    startActivity(new Intent(this, MainActivity.class));
                    break;

                case R.id.nav_item_2:
                    // Start the ShortsActivity
                    startActivity(new Intent(this, DestinationActivity.class));
                    break;

                case R.id.nav_item_3:
                    // Start the SubscriptionActivity
                    startActivity(new Intent(this, ComminutyActivity.class));
                    break;

                case R.id.nav_item_4:
                    // Start the LibraryActivity
                    startActivity(new Intent(this, EventsActivity.class));
                    break;
            }
            return true;

        });


    }
}