package com.example.uscdoordrink;
import android.content.Context;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class RegisterActivityTest {

    @Rule
    public ActivityScenarioRule<RegisterActivity> mRegisterActivityTest =
            new ActivityScenarioRule<>(RegisterActivity.class);




}
