package com.noojman.testprepgradingapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;

import com.noojman.testprepgradingapp.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);

        // line below not necessary if splash screen is an image (instead modify styles.xml)
        setContentView(R.layout.activity_splash_screen);

        new CountDownTimer(1500, 1000) {

            public void onTick(long millisUntilFinished) { }

            public void onFinish() {
                Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        }.start();
    }
}
