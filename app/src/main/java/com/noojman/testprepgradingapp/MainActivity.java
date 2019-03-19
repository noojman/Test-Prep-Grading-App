package com.noojman.testprepgradingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.noojman.testprepgradingapp.activities.BubbleSheetActivity;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button takeTestButton = findViewById(R.id.take_test_button);
        takeTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BubbleSheetActivity.class);
                startActivity(intent);
            }
        });

        Button myReportButton = findViewById(R.id.my_report_button);
        myReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(this, .class);
                startActivity(intent);*/
            }
        });

        Button createTestButton = findViewById(R.id.create_test_button);
        createTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(this, .class);
                startActivity(intent);*/
            }
        });

        Button settingsButton = findViewById(R.id.settings_button);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Intent intent = new Intent(this, .class);
                startActivity(intent);*/
            }
        });
    }
}
