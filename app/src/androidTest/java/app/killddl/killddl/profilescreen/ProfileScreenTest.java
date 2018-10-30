package app.killddl.killddl.profilescreen;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import app.killddl.killddl.CalendarActivity;
import app.killddl.killddl.MainActivity;
import app.killddl.killddl.ProfileActivity;
import app.killddl.killddl.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.doubleClick;
import static android.support.test.espresso.action.ViewActions.typeText;
import android.support.test.espresso.contrib.PickerActions;

import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.core.AllOf.allOf;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ProfileScreenTest {
    @Rule
    public ActivityTestRule<ProfileActivity> mProfileActivityTestRule = new
            ActivityTestRule<ProfileActivity>(ProfileActivity.class);

    @Test
    public void clickLogOutButton_LogOut(){
        //try{ Thread.sleep(5000); }catch (Exception _){}
        try{ Thread.sleep(3000); }catch (Exception _){}
        //click on logout button
        onView(withId(R.id.profile_logoutBtn)).perform(click());

        try{ Thread.sleep(3000); }catch (Exception _){}

        //check
        onView(withId(R.id.profile_logoutBtn)).check(doesNotExist());
    }


    @Test
    public void clickMenuButton(){
        onView(withId(R.id.action_menu)).perform(doubleClick());
        onView(withId(R.id.menu_scrolllist)).check(matches(allOf(isDescendantOfA(withId(R.id.layout_menu)),isDisplayed())));
    }

    @Test
    public void clickCalendarButton(){
        onView(withId(R.id.action_calendar)).perform(doubleClick());
        onView(withId(R.id.calendar_tasks)).check(matches(allOf(isDescendantOfA(withId(R.id.layout_menu)),isDisplayed())));
    }
}
