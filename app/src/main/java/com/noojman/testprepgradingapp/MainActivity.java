package com.noojman.testprepgradingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.noojman.testprepgradingapp.fragments.BubbleSheetFragment;

public class MainActivity extends AppCompatActivity {

    BubbleSheetFragment bubbleSheetFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_bubblesheet);
    }
}
