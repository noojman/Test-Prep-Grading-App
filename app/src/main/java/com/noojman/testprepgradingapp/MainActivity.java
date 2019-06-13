package com.noojman.testprepgradingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.noojman.testprepgradingapp.activities.BubbleSheetActivity;
import com.noojman.testprepgradingapp.activities.CreateTestActivity;
import com.noojman.testprepgradingapp.activities.SettingsActivity;
import com.noojman.testprepgradingapp.activities.TakeTestActivity;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TODO main menu progress report

        Button takeTestButton = findViewById(R.id.button_take_test);
        takeTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TakeTestActivity.class);
                startActivity(intent);
            }
        });

        Button myReportButton = findViewById(R.id.button_my_report);
        myReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO report page
                /*Intent intent = new Intent(MainActivity.this, .class);
                startActivity(intent);*/
            }
        });

        Button createTestButton = findViewById(R.id.button_create_test);
        createTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateTestActivity.class);
                startActivity(intent);
            }
        });

        Button settingsButton = findViewById(R.id.button_settings);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
            }
        });
    }
}
