package app.killddl.killddl.menuscreen;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import app.killddl.killddl.MenuActivity;
import app.killddl.killddl.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.doubleClick;
import static android.support.test.espresso.action.ViewActions.typeText;
import android.support.test.espresso.contrib.PickerActions;
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
public class MenuScreenTest {
    @Rule
    public ActivityTestRule<MenuActivity> mMenuActivityTestRule = new
            ActivityTestRule<MenuActivity>(MenuActivity.class);

    @Test
    public void clickDailyButton_showDailyScreen(){
        //click on daily button
        onView(withId(R.id.action_daily)).perform(click(),closeSoftKeyboard());

        try{ Thread.sleep(3000); }catch (Exception _){}

        //check that we can see dailyTask screen
        onView(withId(R.id.menu_daily)).check(matches(allOf(isDescendantOfA(withId(R.id.menu_scrolllist)),isDisplayed())));
        //onView(withId(2131296440)).check();
    }

    @Test
    public void clickWeeklyButton_showWeeklyScreen(){
        //click on weekly button
        onView(withId(R.id.action_weekly)).perform(click(),closeSoftKeyboard());

        try{ Thread.sleep(3000); }catch (Exception _){}

        //check that we can see weeklyTask screen
        onView(withId(R.id.menu_weekly)).check(matches(allOf(isDescendantOfA(withId(R.id.menu_scrolllist)),isDisplayed())));
    }

    @Test
    public void clickMonthlyButton_showMonthlyScreen(){
        //click on monthly button
        onView(withId(R.id.action_monthly)).perform(click(),closeSoftKeyboard());

        try{ Thread.sleep(3000); }catch (Exception _){}

        //check that we can see monthlyTask screen
        onView(withId(R.id.menu_monthly)).check(matches(allOf(isDescendantOfA(withId(R.id.menu_scrolllist)),isDisplayed())));
    }

    @Test
    public void clickProfileButton(){
        onView(withId(R.id.action_profile)).perform(doubleClick());
        onView(withId(R.id.profile_logoutBtn)).check(matches(allOf(isDescendantOfA(withId(R.id.layout_profile)),isDisplayed())));
    }

    @Test
    public void clickCalendarButton(){
        onView(withId(R.id.action_calendar)).perform(doubleClick());
        onView(withId(R.id.calendar_tasks)).check(matches(allOf(isDescendantOfA(withId(R.id.layout_menu)),isDisplayed())));
    }
}
