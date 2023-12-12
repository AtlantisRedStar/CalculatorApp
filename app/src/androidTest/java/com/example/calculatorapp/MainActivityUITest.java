package com.example.calculatorapp;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

public class MainActivityUITest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testButtonClicks() {
        // Wait for the activity to be launched
        MainActivity mainActivity = activityRule.getActivity();

        Espresso.onView(ViewMatchers.withId(R.id.button1)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.buttonAdd)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.button2)).perform(ViewActions.click());
        Espresso.onView(ViewMatchers.withId(R.id.buttonEquals)).perform(ViewActions.click());

        // Add assertions based on the expected state of the UI or MainActivity
    }
}
