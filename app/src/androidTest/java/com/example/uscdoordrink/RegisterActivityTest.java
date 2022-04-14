package com.example.uscdoordrink;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import android.content.Context;
import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.*;

import java.nio.charset.Charset;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@RunWith(AndroidJUnit4.class)
public class RegisterActivityTest {

    @Rule
    public ActivityScenarioRule<RegisterActivity> mRegisterActivityTest =
            new ActivityScenarioRule<>(RegisterActivity.class);

    @Test
    public void registerBackButtonExists() {
        ViewInteraction button = onView(
                allOf(withId(R.id.buttonRegisterBack), withText("Back"),
                        isDisplayed()));
        button.check(matches(isDisplayed()));
    }

    @Test
    public void nameFieldExists() {
        ViewInteraction field = onView(
                allOf(withId(R.id.inputName),
                        isDisplayed()));
        field.check(matches(isDisplayed()));
    }

    @Test
    public void spinnerCustomerExists() {
        ViewInteraction field = onView(
                allOf(withId(R.id.spinnerCustomer),
                        isDisplayed()));
        field.check(matches(isDisplayed()));
    }

    @Test
    public void emailFieldExists() {
        ViewInteraction field = onView(
                allOf(withId(R.id.inputEmail),
                        isDisplayed()));
        field.check(matches(isDisplayed()));
    }

    @Test
    public void passwordFieldExists() {
        ViewInteraction field = onView(
                allOf(withId(R.id.inputPassword),
                        isDisplayed()));
        field.check(matches(isDisplayed()));
    }

    @Test
    public void addressFieldExists() {
        ViewInteraction field = onView(
                allOf(withId(R.id.inputAddress),
                        isDisplayed()));
        field.check(matches(isDisplayed()));
    }

    @Test
    public void cityFieldExists() {
        ViewInteraction field = onView(
                allOf(withId(R.id.inputCity),
                        isDisplayed()));
        field.check(matches(isDisplayed()));
    }

    @Test
    public void stateFieldExists() {
        ViewInteraction field = onView(
                allOf(withId(R.id.inputState),
                        isDisplayed()));
        field.check(matches(isDisplayed()));
    }

    @Test
    public void postalCodeFieldExists() {
        ViewInteraction field = onView(
                allOf(withId(R.id.inputPostalCode),
                        isDisplayed()));
        field.check(matches(isDisplayed()));
    }

    @Test
    public void buttonRegisterExists() {
        ViewInteraction field = onView(
                allOf(withId(R.id.buttonRegister),
                        isDisplayed()));
        field.check(matches(isDisplayed()));
    }

    @Test
    public void clickBackButton_opensMainActivity() throws Exception {
        Espresso.onView(ViewMatchers.withId(R.id.buttonRegisterBack)).perform(click());
        onView(ViewMatchers.withId(R.id.main)).check(matches(isDisplayed()));
    }

    @Test
    public void correctCustomerRegisterDetails_DirectToLoginPage() throws Exception {
        Espresso.onView(ViewMatchers.withId(R.id.inputName))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("New User"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.spinnerCustomer)).perform(click());
        onView(withText("Customer")).perform(click());
        Espresso.closeSoftKeyboard();

        //https://www.baeldung.com/java-random-string
        Random random = new Random();
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = ThreadLocalRandom.current().nextInt(3, 10 + 1);
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        generatedString += "@gmail.com";
        Espresso.onView(ViewMatchers.withId(R.id.inputEmail))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText(generatedString));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.inputPassword))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("password123"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.inputAddress))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("3131 McClintock Avenue"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.inputCity))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("Los Angeles"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.inputState))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("CA"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.inputPostalCode))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("90007"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.buttonRegister)).perform(click());

        onView(ViewMatchers.withId(R.id.login)).check(matches(isDisplayed()));
    }

    @Test
    public void correctShopRegisterDetails_DirectToLoginPage() throws Exception {
        Espresso.onView(ViewMatchers.withId(R.id.inputName))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("New User"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.spinnerCustomer)).perform(click());
        onView(withText("Shop")).perform(click());
        Espresso.closeSoftKeyboard();

        //https://www.baeldung.com/java-random-string
        Random random = new Random();
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = ThreadLocalRandom.current().nextInt(3, 10 + 1);
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        generatedString += "@gmail.com";
        Espresso.onView(ViewMatchers.withId(R.id.inputEmail))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText(generatedString));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.inputPassword))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("password123"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.inputAddress))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("3131 McClintock Avenue"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.inputCity))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("Los Angeles"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.inputState))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("CA"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.inputPostalCode))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("90007"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.buttonRegister)).perform(click());

        onView(ViewMatchers.withId(R.id.login)).check(matches(isDisplayed()));


    }

    @Test
    public void alreadyRegistedEmailInRegisterDetails_ShowAlertDialog() throws Exception {
        Espresso.onView(ViewMatchers.withId(R.id.inputName))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("New User"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.spinnerCustomer)).perform(click());
        onView(withText("Shop")).perform(click());
        Espresso.closeSoftKeyboard();

        String generatedString = "shop1@gmail.com";
        Espresso.onView(ViewMatchers.withId(R.id.inputEmail))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText(generatedString));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.inputPassword))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("password123"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.inputAddress))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("3131 McClintock Avenue"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.inputCity))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("Los Angeles"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.inputState))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("CA"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.inputPostalCode))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("90007"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.buttonRegister)).perform(click());

        ViewInteraction frameLayout = onView(
                allOf(withId(R.id.action_bar_root),
                        isDisplayed()));
        frameLayout.check(matches(isDisplayed()));
    }

    @Test
    public void emptyEmailInRegisterDetails_ShowAlertDialog() throws Exception {
        Espresso.onView(ViewMatchers.withId(R.id.inputName))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("New User"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.spinnerCustomer)).perform(click());
        onView(withText("Shop")).perform(click());
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.inputPassword))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("password123"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.inputAddress))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("3131 McClintock Avenue"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.inputCity))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("Los Angeles"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.inputState))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("CA"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.inputPostalCode))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("90007"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.buttonRegister)).perform(click());

        ViewInteraction frameLayout = onView(
                allOf(withId(R.id.action_bar_root),
                        isDisplayed()));
        frameLayout.check(matches(isDisplayed()));
    }

    @Test
    public void wrongStateFormatInRegisterDetails_ShowAlertDialog() throws Exception {
        Espresso.onView(ViewMatchers.withId(R.id.inputName))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("New User"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.spinnerCustomer)).perform(click());
        onView(withText("Shop")).perform(click());
        Espresso.closeSoftKeyboard();

        //https://www.baeldung.com/java-random-string
        Random random = new Random();
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = ThreadLocalRandom.current().nextInt(3, 10 + 1);
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        generatedString += "@gmail.com";
        Espresso.onView(ViewMatchers.withId(R.id.inputEmail))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText(generatedString));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.inputPassword))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("password123"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.inputAddress))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("3131 McClintock Avenue"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.inputCity))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("Los Angeles"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.inputState))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("USA"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.inputPostalCode))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("90007"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.buttonRegister)).perform(click());

        ViewInteraction frameLayout = onView(
                allOf(withId(R.id.action_bar_root),
                        isDisplayed()));
        frameLayout.check(matches(isDisplayed()));
    }

    @Test
    public void invalidEmailInRegisterDetails_ShowAlertDialog() throws Exception {
        Espresso.onView(ViewMatchers.withId(R.id.inputName))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("New User"));
        Espresso.closeSoftKeyboard();

        onView(withId(R.id.spinnerCustomer)).perform(click());
        onView(withText("Shop")).perform(click());
        Espresso.closeSoftKeyboard();

        //https://www.baeldung.com/java-random-string
        Random random = new Random();
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = ThreadLocalRandom.current().nextInt(3, 10 + 1);
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();

        Espresso.onView(ViewMatchers.withId(R.id.inputEmail))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText(generatedString));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.inputPassword))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("password123"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.inputAddress))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("3131 McClintock Avenue"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.inputCity))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("Los Angeles"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.inputState))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("CA"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.inputPostalCode))
                .perform(ViewActions.clearText())
                .perform(ViewActions.typeText("90007"));
        Espresso.closeSoftKeyboard();

        Espresso.onView(ViewMatchers.withId(R.id.buttonRegister)).perform(click());

        ViewInteraction frameLayout = onView(
                allOf(withId(R.id.action_bar_root),
                        isDisplayed()));
        frameLayout.check(matches(isDisplayed()));
    }
}
