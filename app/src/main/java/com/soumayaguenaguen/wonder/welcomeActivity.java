package com.soumayaguenaguen.wonder;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class welcomeActivity extends AppCompatActivity {
    Button buttonSUP,buttonSIN;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        //btn
        buttonSUP = findViewById(R.id.signUp);
        buttonSUP.setOnClickListener(view -> {
            new Thread(() -> {
                Intent intent = new Intent(welcomeActivity.this, SignupActivity.class);
                startActivity(intent);
            }).start();
        });

        buttonSIN = findViewById(R.id.signIn);
        buttonSIN.setOnClickListener(view -> {
            new Thread(() -> {
                Intent intent = new Intent(welcomeActivity.this, SigninActivity.class);
                startActivity(intent);
            }).start();

        });

    }}