package com.noojman.testprepgradingapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.noojman.testprepgradingapp.MainActivity;
import com.noojman.testprepgradingapp.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button signInButton = findViewById(R.id.button_sign_in);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO sign in
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        TextView registerButton = findViewById(R.id.button_register);
        registerButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                //TODO register
            }
        });
    }
}
