package app.killddl.killddl.mainscreen;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import app.killddl.killddl.MainActivity;
import app.killddl.killddl.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginSetup {
    @Rule
    public ActivityTestRule<MainActivity> myActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void login(){
        String email = "liyanxi63@gmail.com";
        String password = "yanxili";

        //find the email edit text and type in the email address
        onView(withId(R.id.login_username)).perform(typeText(email),closeSoftKeyboard());

        //find the password edit text and type in the password
        onView(withId(R.id.login_password)).perform(typeText(password),closeSoftKeyboard());

        //click the login button
        onView(withId(R.id.loginBtn)).perform(click(),closeSoftKeyboard());

        //check that we can see the Calendar screen
        //onView(withId(R.id.calendarView)).check(matches(allOf(isDescendantOfA(withId(R.id.layout_calendar)),isDisplayed())));
        //intended(toPackage("app.killddl.killddl"));
        try{ Thread.sleep(3000); }catch (Exception _){}
        onView(withId(R.id.loginBtn)).check(doesNotExist());
    }
}
