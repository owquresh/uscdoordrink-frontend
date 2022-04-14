package com.example.uscdoordrink;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isDialog;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import com.example.uscdoordrink.LoginActivity;
import com.example.uscdoordrink.DataActivity;
import com.example.uscdoordrink.R;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EspressoSignInTest {

    public ActivityScenarioRule<LoginActivity> loginActivityRule =
            new ActivityScenarioRule<LoginActivity>(LoginActivity.class);
//    public ActivityScenarioRule<DataActivity> dataActivityRule =
//            new ActivityScenarioRule<DataActivity>(DataActivity.class);

    @Test
    public void testLoginNoEmail(){
        onView(ViewMatchers.withId(R.id.getPassword)).perform(ViewActions.typeText("123"));
        onView(withId(R.id.buttonSignIn)).perform(ViewActions.click());

        String expected = "Login not possible, try again";

        onView(withText(expected)).inRoot(isDialog()).check(matches(isDisplayed()));

    }

}
