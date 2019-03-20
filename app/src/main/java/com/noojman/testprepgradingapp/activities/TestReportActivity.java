package com.noojman.testprepgradingapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.noojman.testprepgradingapp.MainActivity;
import com.noojman.testprepgradingapp.R;

import java.util.ArrayList;

public class TestReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_report);

        int numCorrect = 0;
        ArrayList<Integer> answers = (ArrayList<Integer>)getIntent().getSerializableExtra("answers");
        for (int i = 0; i < answers.size(); i++)
        {
            if (answers.get(i) == 1)
            {
                numCorrect++;
            }
        }

        TextView scoreText = findViewById(R.id.score_text);
        scoreText.setText("Your score: " + numCorrect + "/" + answers.size());

        Button mainMenuButton = findViewById(R.id.button_main_menu);
        mainMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TestReportActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
