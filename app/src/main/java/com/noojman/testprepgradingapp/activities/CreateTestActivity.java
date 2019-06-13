package com.noojman.testprepgradingapp.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.noojman.testprepgradingapp.MainActivity;
import com.noojman.testprepgradingapp.R;

public class CreateTestActivity extends AppCompatActivity {

    EditText bookTitleText;
    EditText publisherNameText;
    EditText testNumText;
    EditText numProblemsText;
    EditText timerLengthText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_test);

        bookTitleText = findViewById(R.id.bookTitleInput);
        publisherNameText = findViewById(R.id.publisherNameInput);
        testNumText = findViewById(R.id.testNumberInput);
        numProblemsText = findViewById(R.id.numProblemsInput);
        timerLengthText = findViewById(R.id.timerLengthInput);

        Button setAnswersButton = findViewById(R.id.button_set_answers);
        setAnswersButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validateInputs()) {
                    Intent intent = new Intent(CreateTestActivity.this, BubbleSheetActivity.class);
                    intent.putExtra("bookTitle", bookTitleText.getEditableText().toString().trim());
                    intent.putExtra("publisherName", publisherNameText.getEditableText().toString().trim());
                    intent.putExtra("testNum", Integer.parseInt(testNumText.getEditableText().toString().trim()));
                    intent.putExtra("numProblems", Integer.parseInt(numProblemsText.getEditableText().toString().trim()));
                    intent.putExtra("timerSeconds", Integer.parseInt(timerLengthText.getEditableText().toString().trim()));
                    intent.putExtra("answerKeySelectionMode", true);
                    startActivity(intent);
                }
            }
        });
    }

    private boolean validateInputs() {
        String bookTitleInput = bookTitleText.getEditableText().toString().trim();
        String publisherNameInput = publisherNameText.getEditableText().toString().trim();
        String testNumInput = testNumText.getEditableText().toString().trim();
        String numProblemsInput = numProblemsText.getEditableText().toString().trim();
        String timerLengthInput = timerLengthText.getEditableText().toString().trim();

        if (bookTitleInput.isEmpty()) {
            bookTitleText.setError("Field can't be empty");
            bookTitleText.requestFocus();
            return false;
        }
        else if (publisherNameInput.isEmpty()) {
            publisherNameText.setError("Field can't be empty. Enter 1 if it is the only test");
            publisherNameText.requestFocus();
            return false;
        }
        else if (testNumInput.isEmpty()) {
            testNumText.setError("Field can't be empty");
            testNumText.requestFocus();
            return false;
        }
        else if (numProblemsInput.isEmpty()) {
            numProblemsText.setError("Field can't be empty");
            numProblemsText.requestFocus();
            return false;
        }
        else if (timerLengthInput.isEmpty()) {
            timerLengthText.setError("Field can't be empty");
            timerLengthText.requestFocus();
            return false;
        }
        else
        {
            return true;
        }
    }
}
