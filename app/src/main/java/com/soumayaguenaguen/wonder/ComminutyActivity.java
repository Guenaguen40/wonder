package com.soumayaguenaguen.wonder;




import android.annotation.SuppressLint;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;


import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



import de.hdodenhof.circleimageview.CircleImageView;


public class ComminutyActivity extends AppCompatActivity {


    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comminuty);

        FloatingActionButton add_post = (FloatingActionButton) findViewById(R.id.add_post);
        add_post.setOnClickListener(view -> {
            Intent posting = new Intent(this, PostingActivity.class);
            startActivity(posting);
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.nav_item_3);
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
                    startActivity(new Intent(this, ProfileActivity.class));
                    break;
            }
            return true;

        });


        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            TextView usernameTextView = findViewById(R.id.username);
            usernameTextView.setText(name);
            Uri photoUrl = user.getPhotoUrl();
            CircleImageView menu_button = findViewById(R.id.menu_button);
            Glide.with(this).load(photoUrl).into(menu_button);
        }



    }

    public void onMenuButtonClick(View view) {
        PopupMenu popupMenu = new PopupMenu(this, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menu_items, popupMenu.getMenu());
        popupMenu.show();
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_item_1:
                startActivity(new Intent(this, ProfileActivity.class));
            case R.id.menu_item_2:
                FirebaseAuth.getInstance().signOut();
                Intent loginActivity = new Intent(getApplicationContext(), SigninActivity.class);
                startActivity(loginActivity);
                finish();
        }
        return true;

    }
    }




