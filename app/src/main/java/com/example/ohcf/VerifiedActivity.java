package com.example.ohcf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class VerifiedActivity extends AppCompatActivity {

    ImageButton signin_forward_button3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verified);
        signin_forward_button3 = findViewById(R.id.signin_forward_button3);

        signin_forward_button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerifiedActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}