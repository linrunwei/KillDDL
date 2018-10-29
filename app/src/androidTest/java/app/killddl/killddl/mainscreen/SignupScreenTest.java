//package app.killddl.killddl.mainscreen;
//
//import android.content.Context;
//import android.support.test.InstrumentationRegistry;
//import android.support.test.rule.ActivityTestRule;
//import android.support.test.runner.AndroidJUnit4;
//import android.support.test.filters.LargeTest;
//import android.support.test.runner.AndroidJUnit4;
//
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//
//import app.killddl.killddl.MainActivity;
//import app.killddl.killddl.R;
//
//import static android.support.test.espresso.Espresso.onView;
//import static android.support.test.espresso.action.ViewActions.click;
//import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
//import static android.support.test.espresso.action.ViewActions.typeText;
//import static android.support.test.espresso.assertion.ViewAssertions.matches;
//import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
//import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static android.support.test.espresso.matcher.ViewMatchers.withId;
//import static android.support.test.espresso.matcher.ViewMatchers.withParent;
//import static org.hamcrest.core.AllOf.allOf;
//
//import static org.junit.Assert.*;
//
///**
// * Instrumented test, which will execute on an Android device.
// *
// * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
// */
//
//@RunWith(AndroidJUnit4.class)
//@LargeTest
//public class SignupScreenTest {
//    @Rule
//    public ActivityTestRule<MainActivity> mMainActivityTestRule = new
//            ActivityTestRule<MainActivity>(MainActivity.class);
//
//    @Test
//    public void clickSignUpButtonAfterFillingExistingEmail_showSignUpScreen(){
//        String email = "yanxili@usc.edu";
//        String password = "yanxili";
//
//        //find the email edit text and type in the email address
//        onView(withId(R.id.login_username)).perform(typeText(email),closeSoftKeyboard());
//
//        //find the password edit text and type in the password
//        onView(withId(R.id.login_password)).perform(typeText(password),closeSoftKeyboard());
//
//        //click the signup button
//        onView(withId(R.id.signupBtn)).perform(click(),closeSoftKeyboard());
//
//        //check that we can see the SignUp screen
//        onView(withId(R.id.login_username)).check(matches(allOf(isDescendantOfA(withId(R.id.mainLayout)),isDisplayed())));
//    }
//
//    @Test
//    public void clickSignUpButtonAfterFillingInvalidEmail_showSignUpScreen(){
//        String email = "123";
//        String password = "yanxili";
//
//        //find the email edit text and type in the email address
//        onView(withId(R.id.login_username)).perform(typeText(email),closeSoftKeyboard());
//
//        //find the password edit text and type in the password
//        onView(withId(R.id.login_password)).perform(typeText(password),closeSoftKeyboard());
//
//        //click the signup button
//        onView(withId(R.id.signupBtn)).perform(click(),closeSoftKeyboard());
//
//        //check that we can see the SignUp screen
//        onView(withId(R.id.login_username)).check(matches(allOf(isDescendantOfA(withId(R.id.mainLayout)),isDisplayed())));
//    }
//
//    @Test
//    public void clickSignUpButtonAfterFillingWeakPassword_showSignUpScreen(){
//        String email = "yanxili@gmail.com";
//        String password = "123";
//
//        //find the email edit text and type in the email address
//        onView(withId(R.id.login_username)).perform(typeText(email),closeSoftKeyboard());
//
//        //find the password edit text and type in the password
//        onView(withId(R.id.login_password)).perform(typeText(password),closeSoftKeyboard());
//
//        //click the signup button
//        onView(withId(R.id.signupBtn)).perform(click(),closeSoftKeyboard());
//
//        //check that we can see the SignUp screen
//        onView(withId(R.id.login_username)).check(matches(allOf(isDescendantOfA(withId(R.id.mainLayout)),isDisplayed())));
//    }
//
//    @Test
//    public void clickSignUpButtonAfterFillingCorrectInfo_showCalendarScreen(){
//        String email = "yanxili123@gmail.com";
//        String password = "yanxili";
//
//        //find the email edit text and type in the email address
//        onView(withId(R.id.login_username)).perform(typeText(email),closeSoftKeyboard());
//
//        //find the password edit text and type in the password
//        onView(withId(R.id.login_password)).perform(typeText(password),closeSoftKeyboard());
//
//        //click the signup button
//        onView(withId(R.id.signupBtn)).perform(click(),closeSoftKeyboard());
//
//        //click the login button
//        onView(withId(R.id.loginBtn)).perform(click(),closeSoftKeyboard());
//
//        //check that we can see the SignUp screen
//        onView(withId(R.id.calendarView)).check(matches(allOf(isDescendantOfA(withId(R.id.layout_calendar)),isDisplayed())));
//    }
//
//    @Test
//    public void clickSignUpButtonAfterLeavingEmailEntryEmpty_showSignUpScreen(){
//        String password = "yanxili";
//
//        //find the password edit text and type in the password
//        onView(withId(R.id.login_password)).perform(typeText(password),closeSoftKeyboard());
//
//        //click the login button
//        onView(withId(R.id.loginBtn)).perform(click(),closeSoftKeyboard());
//
//        //check that we can see the Login screen
//        onView(withId(R.id.login_username)).check(matches(allOf(isDescendantOfA(withId(R.id.mainLayout)),isDisplayed())));
//    }
//
//    @Test
//    public void clickSignUpButtonAfterLeavingPasswordEntryEmpty_showSignUpScreen(){
//        String email = "yanxili1@usc.edu";
//
//        //find the email edit text and type in the email address
//        onView(withId(R.id.login_username)).perform(typeText(email),closeSoftKeyboard());
//
//        //click the login button
//        onView(withId(R.id.loginBtn)).perform(click(),closeSoftKeyboard());
//
//        //check that we can see the Login screen
//        onView(withId(R.id.login_username)).check(matches(allOf(isDescendantOfA(withId(R.id.mainLayout)),isDisplayed())));
//    }
//}
