package com.noojman.testprepgradingapp;

import android.app.Activity;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.intent.Intents;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry;

import com.google.firebase.auth.FirebaseAuth;
import com.noojman.testprepgradingapp.activities.LoginActivity;
import com.noojman.testprepgradingapp.activities.SettingsActivity;
import com.noojman.testprepgradingapp.activities.SplashScreenActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;

import static androidx.lifecycle.Lifecycle.State.RESUMED;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.platform.app.InstrumentationRegistry.getInstrumentation;
import static org.junit.Assert.fail;

@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest
public class UserFlowEspressoTest {

    private String exampleEmail;
    private String examplePassword;

    @Rule
    public ActivityTestRule<SplashScreenActivity> activityRule =
            new ActivityTestRule<>(SplashScreenActivity.class);

    @Before
    public void init()
    {
        exampleEmail = "eric_joo@ymail.com";
        examplePassword = "Password1";
        FirebaseAuth.getInstance().signOut();
        Intents.init();
    }

    @Test
    public void signInTest()
    {
        try {
            Thread.sleep(3000);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        onView(withId(R.id.login_email)).perform(typeText(exampleEmail));
        onView(withId(R.id.login_password)).perform(typeText(examplePassword));
        Espresso.closeSoftKeyboard();
        onView(withId(R.id.button_sign_in)).perform(click());

        try {
            Thread.sleep(3000);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

        intended(hasComponent(MainActivity.class.getName()));

        onView(withId(R.id.button_settings)).perform(click());
        intended(hasComponent(SettingsActivity.class.getName()));

        onView(withId(R.id.button_sign_out)).perform(click());
    }
}
