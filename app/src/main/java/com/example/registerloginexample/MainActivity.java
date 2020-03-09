package com.example.registerloginexample;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView TextView_Main_UserID, TextView_Main_UserPassword;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView_Main_UserID = findViewById(R.id.TextView_Main_UserID);
        TextView_Main_UserPassword = findViewById(R.id.TextView_Main_UserPassword);

        Intent intent = getIntent();
        String userID = intent.getStringExtra("userID");
        String userPassword = intent.getStringExtra("userPassword");

        TextView_Main_UserID.setText(userID);
        TextView_Main_UserPassword.setText(userPassword);

    }
}
