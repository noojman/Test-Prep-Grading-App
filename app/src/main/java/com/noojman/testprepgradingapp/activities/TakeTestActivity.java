package com.noojman.testprepgradingapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.noojman.testprepgradingapp.R;

public class TakeTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_test);

        Button findTestButton = findViewById(R.id.button_find_test);
        findTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO find test page
                /*Intent intent = new Intent(this, .class);
                startActivity(intent);*/
            }
        });

        Button resumeTestButton = findViewById(R.id.button_resume_test);
        resumeTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO resume test page
                /*Intent intent = new Intent(this, .class);
                startActivity(intent);*/
            }
        });
    }
}
