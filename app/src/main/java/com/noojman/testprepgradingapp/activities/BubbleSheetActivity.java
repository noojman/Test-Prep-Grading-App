package com.noojman.testprepgradingapp.activities;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.noojman.testprepgradingapp.MainActivity;
import com.noojman.testprepgradingapp.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class BubbleSheetActivity extends AppCompatActivity {

    ArrayList<ViewGroup> answerGroups;
    int numProblems;
    int timerSeconds;
    TextView titleText;
    TextView timerText;
    CountDownTimer timer;
    boolean testStarted = false;

    @Override
    public void onBackPressed() {
        if (!testStarted) {
            super.onBackPressed();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble_sheet);

        titleText = findViewById(R.id.exam_title);
        timerText = findViewById(R.id.timer_text);
        titleText.setText(getIntent().getSerializableExtra("publisherName").toString().concat(" - ").concat(getIntent().getSerializableExtra("bookTitle").toString()));
        timerSeconds = (int) getIntent().getSerializableExtra("timerSeconds");
        numProblems = (int) getIntent().getSerializableExtra("numProblems");

        answerGroups = new ArrayList<>();
        LinearLayout answerLayout = findViewById(R.id.answer_layout);
        for (int i = 1; i <= numProblems; i++) {
            View child = getLayoutInflater().inflate(R.layout.layout_bubbleline, null);
            String newTag = "problem_" + i;
            child.setTag(newTag);
            answerLayout.addView(child);
            answerGroups.add((ViewGroup) child);
        }

        for (int i = 1; i <= answerGroups.size(); i++) {
            ((TextView) answerGroups.get(i - 1).getChildAt(0)).setText(String.valueOf(i));
        }

        if ((boolean) getIntent().getSerializableExtra("answerKeySelectionMode")) {
            timerText.setEnabled(false);

            Button submitButton = findViewById(R.id.button_submit);
            submitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(BubbleSheetActivity.this)
                            .setTitle("Finish and Create Test")
                            .setMessage("Are you sure you're finished?")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {
                                    ArrayList<Integer> answers = new ArrayList<>();

                                    for (int i = 0; i < answerGroups.size(); i++) {
                                        answers.add(((RadioGroup) answerGroups.get(i)).getCheckedRadioButtonId());
                                    }

                                    //TODO grab fields from intent extras and create a new test in database


                                    Intent intent = new Intent(BubbleSheetActivity.this, MainActivity.class);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .setNegativeButton(android.R.string.no, null).show();
                }
            });
        } else {
            timerSeconds = (int) getIntent().getSerializableExtra("timerSeconds");
            timer = new CountDownTimer(timerSeconds * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timerText.setText(new SimpleDateFormat("mm:ss").format(new Date(millisUntilFinished)));
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

                                    for (int i = 0; i < answerGroups.size(); i++) {
                                        answers.add(((RadioGroup) answerGroups.get(i)).getCheckedRadioButtonId());
                                    }

                                    Intent intent = new Intent(BubbleSheetActivity.this, TestReportActivity.class);
                                    intent.putExtra("answers", answers);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .setNegativeButton(null, null).show();
                }
            };

            new AlertDialog.Builder(BubbleSheetActivity.this)
                    .setTitle("Ready?")
                    .setMessage("Press OK to begin the test.")
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setOnKeyListener(new Dialog.OnKeyListener() {
                        @Override
                        public boolean onKey(DialogInterface arg0, int keyCode,
                                             KeyEvent event) {
                            if (keyCode == KeyEvent.KEYCODE_BACK) {
                                finish();
                            }
                            return true;
                        }
                    })
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            timer.start();
                            testStarted = true;
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            finish();
                        }
                    }).show();

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

                                    for (int i = 0; i < answerGroups.size(); i++) {
                                        answers.add(((RadioGroup) answerGroups.get(i)).getCheckedRadioButtonId());
                                    }

                                    Intent intent = new Intent(BubbleSheetActivity.this, TestReportActivity.class);
                                    intent.putExtra("answers", answers);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .setNegativeButton(android.R.string.no, null).show();
                }
            });
        }
    }
}