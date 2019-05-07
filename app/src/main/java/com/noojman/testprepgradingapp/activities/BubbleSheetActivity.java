package com.noojman.testprepgradingapp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.noojman.testprepgradingapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BubbleSheetActivity extends AppCompatActivity {

    ArrayList<ViewGroup> answerGroups;
    int numProblems = 20;
    int timerSeconds = 10;
    TextView timerText;
    CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble_sheet);

        timerText = findViewById(R.id.timer_text);

        timer = new CountDownTimer(timerSeconds * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerText.setText(new SimpleDateFormat("mm:ss").format(new Date( millisUntilFinished)));
            }

            @Override
            public void onFinish() {
                new AlertDialog.Builder(BubbleSheetActivity.this)
                        .setTitle("Times up!")
                        .setMessage("Press OK to submit and continue.")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setCancelable(false)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ArrayList<Integer> answers = new ArrayList<>();

                                for (int i = 0; i < answerGroups.size(); i++)
                                {
                                    answers.add(((RadioGroup)answerGroups.get(i)).getCheckedRadioButtonId());
                                }

                                Intent intent = new Intent(BubbleSheetActivity.this, TestReportActivity.class);
                                intent.putExtra("answers", answers);
                                startActivity(intent);
                                finish();
                            }})
                        .setNegativeButton(null, null).show();
            }
        };

        answerGroups = new ArrayList<>();

        LinearLayout answerLayout = findViewById(R.id.answer_layout);
        for (int i = 1; i <= numProblems; i++)
        {
            View child = getLayoutInflater().inflate(R.layout.layout_bubbleline, null);
            String newTag = "problem_" + i;
            child.setTag(newTag);
            answerLayout.addView(child);
            answerGroups.add((ViewGroup)child);
        }

        for (int i = 1; i <= answerGroups.size(); i++)
        {
            ((TextView)answerGroups.get(i - 1).getChildAt(0)).setText(i + "");
        }

        //TODO begin test button
        new AlertDialog.Builder(BubbleSheetActivity.this)
                .setTitle("Ready?")
                .setMessage("Press OK to begin the test.")
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setCancelable(false)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        timer.start();
                    }})
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        finish();
                    }}).show();

        Button submitButton = findViewById(R.id.button_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(BubbleSheetActivity.this)
                        .setTitle("Finish and Submit")
                        .setMessage("Are you sure you want to submit?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ArrayList<Integer> answers = new ArrayList<>();

                                for (int i = 0; i < answerGroups.size(); i++)
                                {
                                    answers.add(((RadioGroup)answerGroups.get(i)).getCheckedRadioButtonId());
                                }

                                Intent intent = new Intent(BubbleSheetActivity.this, TestReportActivity.class);
                                intent.putExtra("answers", answers);
                                startActivity(intent);
                                finish();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });
    }
}