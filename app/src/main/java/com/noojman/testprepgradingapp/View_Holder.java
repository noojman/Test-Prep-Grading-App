package com.noojman.testprepgradingapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.noojman.testprepgradingapp.activities.BubbleSheetActivity;
import com.noojman.testprepgradingapp.activities.ResumeTestActivity;

public class View_Holder extends RecyclerView.ViewHolder {

    CardView cv;
    TextView title;
    TextView publisher;
    TextView testNumber;
    TextView numProblems;

    View_Holder(View itemView) {
        super(itemView);
        cv = itemView.findViewById(R.id.cardView);
        title = itemView.findViewById(R.id.title);
        publisher = itemView.findViewById(R.id.publisher);
        testNumber = itemView.findViewById(R.id.testNumber);
        numProblems = itemView.findViewById(R.id.numProblems);
    }
}