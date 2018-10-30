package app.killddl.killddl.edittaskscreen;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import app.killddl.killddl.EditTaskActivity;
import app.killddl.killddl.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import android.support.test.espresso.contrib.PickerActions;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isClickable;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.core.AllOf.allOf;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class EditTaskScreenTest {
    private int year = 2018;
    private int month = 11;
    private int day = 20;
    private int hour = 11;
    private int time = 23;

    @Rule
    public ActivityTestRule<EditTaskActivity> mEditTaskActivityTestRule = new
            ActivityTestRule<EditTaskActivity>(EditTaskActivity.class);

    @Test
    public void clickEntryWithoutClickingEditButton_cannotEdit(){
        onView(withId(R.id.edittask_taskname)).check(matches(not(isEnabled())));
    }

    @Test
    public void clickSaveAfterEditingDateAndTime_showCalendarScreen(){
        //click on edit button
        onView(withId(R.id.edittask_editBtn)).perform(click());

        //edit Date

        //edit Time

        //click on finish button
        onView(withId(R.id.edittask_editBtn)).perform(click());

        try{ Thread.sleep(3000); }catch (Exception _){}

        //check that we can see the Calendar screen
        onView(withId(R.id.calendarView)).check(matches(allOf(isDescendantOfA(withId(R.id.layout_calendar)),isDisplayed())));
    }

    @Test
    public void clickSaveAfterEditingOnlyDate_showEditTaskScreen(){
        //click on edit button
        onView(withId(R.id.edittask_editBtn)).perform(click());

        //edit Date

        //click on finish button
        onView(withId(R.id.edittask_editBtn)).perform(click());

        try{ Thread.sleep(3000); }catch (Exception _){}

        //check that we stay on edit task screen
        onView(withId(R.id.edittask_editBtn)).check(matches(allOf(isDescendantOfA(withId(R.id.layout_edittask)),isDisplayed())));
    }

    @Test
    public void clickSaveAfterEditingOnlyTime_showEditTaskScreen(){
        //click on edit button
        onView(withId(R.id.edittask_editBtn)).perform(click());

        //edit Time

        //click on finish button
        onView(withId(R.id.edittask_editBtn)).perform(click());

        try{ Thread.sleep(3000); }catch (Exception _){}

        //check that we stay on edit task screen
        onView(withId(R.id.edittask_editBtn)).check(matches(allOf(isDescendantOfA(withId(R.id.layout_edittask)),isDisplayed())));

    }

    @Test
    public void clickFinish_showCalendarScreen(){
        //click on finish button
        onView(withId(R.id.edittask_finishBtn)).perform(click(),closeSoftKeyboard());

        try{ Thread.sleep(3000); }catch (Exception _){}

        //check that we can see the Calendar screen
        onView(withId(R.id.calendarView)).check(matches(allOf(isDescendantOfA(withId(R.id.layout_calendar)),isDisplayed())));
    }
}
