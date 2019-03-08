package com.noojman.testprepgradingapp.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.noojman.testprepgradingapp.FragmentChangeListener;
import com.noojman.testprepgradingapp.R;

public class MainMenuFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

    View view =  inflater.inflate(R.layout.fragment_mainmenu, container, false);

        Button takeTestButton = view.findViewById(R.id.take_test_button);
        takeTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Fragment fr = new BubbleSheetFragment();
                FragmentChangeListener fc = (FragmentChangeListener)getActivity();
                fc.replaceFragment(fr);*/
            }
        });

        Button myReportButton = view.findViewById(R.id.my_report_button);
        myReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button createTestButton = view.findViewById(R.id.create_test_button);
        createTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        Button settingsButton = view.findViewById(R.id.settings_button);
        settingsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return inflater.inflate(R.layout.fragment_bubblesheet, container, false);
    }
}