package com.noojman.testprepgradingapp.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.noojman.testprepgradingapp.R;

import java.util.ArrayList;

public class BubbleSheetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bubble_sheet);

        /*LinearLayout answersLayout = findViewById(R.id.answer_buttons);
        for (int i = 0; i < answersLayout.getChildCount(); i++)
        {
            View v = answersLayout.getChildAt(i);
            if (v instanceof RadioGroup)
            {
                ((TextView)(((RadioGroup)v).getChildAt(0))).setText(i + 1);
            }
        }*/

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
                                LinearLayout answersLayout = findViewById(R.id.answer_buttons);
                                ArrayList<Integer> answers = new ArrayList<>();
                                for (int i = 0; i < answersLayout.getChildCount(); i++)
                                {
                                    View v = answersLayout.getChildAt(i);
                                    if (v instanceof RadioGroup)
                                    {
                                        answers.add(((RadioGroup)v).indexOfChild(findViewById(((RadioGroup)v).getCheckedRadioButtonId())));
                                    }
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