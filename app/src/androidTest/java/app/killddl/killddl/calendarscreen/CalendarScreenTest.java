package app.killddl.killddl.calendarscreen;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import app.killddl.killddl.CalendarActivity;
import app.killddl.killddl.MainActivity;
import app.killddl.killddl.MenuActivity;
import app.killddl.killddl.R;
import app.killddl.killddl.matcher.IdMatchers;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.doubleClick;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import android.support.test.espresso.contrib.PickerActions;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.google.firebase.auth.FirebaseAuth;
import com.wdullaer.materialdatetimepicker.date.DayPickerView;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
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
public class CalendarScreenTest {
    @Rule
    public ActivityTestRule<CalendarActivity> mCalendarActivityTestRule = new
            ActivityTestRule<CalendarActivity>(CalendarActivity.class);


    @Test
    @Ignore
    public void clickDateOnCalendar_showUpdatedCalendarScreen(){
        //click on specific date on calendar
        //onView(withClassName(Matchers.equalTo(Day.class.getName()))).perform(PickerActions.setDate(2013, 3, 2));

        try{
            Thread.sleep(3000);
            ScrollView tasksView = (ScrollView) withId(R.id.calendar_tasks);
            LinearLayout ll = (LinearLayout) tasksView.getChildAt(0);
            assertEquals(ll.getChildCount(),0);
        }catch (Exception _){

        }
    }


    @Test
    public void clickAddTaskButton_showAddTaskScreen(){
        //click on addtask button
        onView(withId(R.id.calendar_addTaskBtn)).perform(click());

        //check that we can see the AddTask screen
        onView(withId(R.id.addtask_finishBtn)).check(matches(allOf(isDescendantOfA(withId(R.id.layout_addtask)),isDisplayed())));
    }

    @Test
    public void clickCalendarButton(){
        onView(withId(R.id.action_calendar)).perform(doubleClick());
        onView(withId(R.id.calendar_calendarview)).check(matches(allOf(isDescendantOfA(withId(R.id.layout_calendar)),isDisplayed())));
    }

    @Test
    public void clickMenuButton(){
        onView(withId(R.id.action_menu)).perform(doubleClick());
        onView(withId(R.id.menu_scrolllist)).check(matches(allOf(isDescendantOfA(withId(R.id.layout_menu)),isDisplayed())));
    }

    @Test
    public void clickProfileButton(){
        onView(withId(R.id.action_profile)).perform(doubleClick());
        onView(withId(R.id.profile_logoutBtn)).check(matches(allOf(isDescendantOfA(withId(R.id.layout_profile)),isDisplayed())));
    }

}
