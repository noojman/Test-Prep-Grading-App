package com.noojman.testprepgradingapp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.noojman.testprepgradingapp.R;

import java.util.ArrayList;

public class BubbleSheetActivity extends AppCompatActivity {

    ArrayList<ViewGroup> answerGroups;
    int numProblems = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble_sheet);

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

        Button submitButton = findViewById(R.id.button_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(BubbleSheetActivity.this)
                        .setTitle("Complete and Submit")
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
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        });
    }
}