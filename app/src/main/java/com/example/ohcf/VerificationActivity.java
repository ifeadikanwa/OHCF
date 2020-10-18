package com.example.ohcf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class VerificationActivity extends AppCompatActivity {

    ImageButton signin_forward_button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verified);
        signin_forward_button2 = findViewById(R.id.signin_forward_button2);

        signin_forward_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerificationActivity.this, VerifiedActivity.class);
                startActivity(intent);
            }
        });
    }
}