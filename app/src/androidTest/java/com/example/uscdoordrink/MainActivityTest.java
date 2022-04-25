package com.example.uscdoordrink;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;


import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityScenarioRule<MainActivity> mRegisterActivityTest =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void loginButtonExists() {
        ViewInteraction button = onView(
                allOf(withId(R.id.buttonSignIn), withText("SIGN IN"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class))),
                        isDisplayed()));
        button.check(matches(isDisplayed()));
    }

    @Test
    public void signInButtonExists(){
        ViewInteraction button2 = onView(
                allOf(withId(R.id.buttonRegister), withText("REGISTER"),
                        withParent(withParent(IsInstanceOf.<View>instanceOf(android.widget.RelativeLayout.class))),
                        isDisplayed()));
        button2.check(matches(isDisplayed()));
    }

    @Test
    public void clickLoginButton_opensLoginPage() throws Exception {
        Espresso.onView(ViewMatchers.withId(R.id.buttonSignIn)).perform(ViewActions.click());
        onView(ViewMatchers.withId(R.id.login)).check(matches(isDisplayed()));
    }


    @Test
    public void clickRegisterButton_opensRegisterPage() throws Exception {
        Espresso.onView(ViewMatchers.withId(R.id.buttonRegister)).perform(ViewActions.click());
        onView(ViewMatchers.withId(R.id.background)).check(matches(isDisplayed()));
    }



}
