package com.noojman.testprepgradingapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.noojman.testprepgradingapp.MainActivity;
import com.noojman.testprepgradingapp.R;

import java.util.ArrayList;

public class TestReportActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_report);

        // TODO show progression and prediction statistics

        // TODO reference with answer key for grading

        int numCorrect = 0;

        @SuppressWarnings("unchecked")
        ArrayList<Integer> answers = (ArrayList<Integer>) getIntent().getSerializableExtra("answers");

        for (int i = 0; i < answers.size(); i++) {
            if (answers.get(i) == R.id.answer_a) {
                numCorrect++;
            }
        }

        TextView scoreText = findViewById(R.id.score_text);
        String yourScoreText = "Your score: ";
        scoreText.setText(yourScoreText.concat(String.valueOf(numCorrect)).concat("/").concat(String.valueOf(answers.size())));

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
