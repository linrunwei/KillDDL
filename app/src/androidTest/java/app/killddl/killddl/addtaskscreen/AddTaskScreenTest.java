package app.killddl.killddl.addtaskscreen;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import app.killddl.killddl.AddTaskActivity;
import app.killddl.killddl.CalendarActivity;
import app.killddl.killddl.MainActivity;
import app.killddl.killddl.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import android.support.test.espresso.contrib.PickerActions;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.sql.Time;

import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;

import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class AddTaskScreenTest {
    private String taskName = "CSCI310-Testing";
    private String description = "CSCI310 Implementation Due";
    private int year = 2018;
    private int month = 11;
    private int day = 30;
    private int hour = 11;
    private int time = 23;

    @Rule
    public ActivityTestRule<AddTaskActivity> mAddTaskActivityTestRule = new
            ActivityTestRule<AddTaskActivity>(AddTaskActivity.class);

    @Test
    public void clickFinishButtonAfterFillingCorrectInfo_showCalendarScreen(){
        mAddTaskActivityTestRule.getActivity();
        //find the task name edit text and type in the task name
        onView(withId(R.id.addtask_taskname)).perform(typeText(taskName),closeSoftKeyboard());

        //find the description edit text and type in the description
        onView(withId(R.id.addtask_description)).perform(typeText(description),closeSoftKeyboard());

        //find the color radiobutton and choose one
        onView(withId(R.id.addtask_purple)).perform(click());

        //choose date
        onView(withId(R.id.addtask_date)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020,3,20));

        onView(withText("OK")).perform(click());

        onView(withId(R.id.addtask_time)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(20,3));
        onView(withText("OK")).perform(click());
        //choose time

        //click on finish button
        onView(withId(R.id.addtask_finishBtn)).perform(click());

        try{ Thread.sleep(3000); }catch (Exception _){}

        //check that we don't stay on AddTask page anymore
        onView(withId(R.id.addtask_finishBtn)).check(doesNotExist());

    }

    @Test
    public void clickFinishButtonAfterLeavingTaskNameEntryEmpty_showAddTaskScreen(){
        //find the description edit text and type in the description
        onView(withId(R.id.addtask_description)).perform(typeText(description),closeSoftKeyboard());

        //find the color radiobutton and choose one
        onView(withId(R.id.addtask_purple)).perform(click());

        //choose date
        onView(withId(R.id.addtask_date)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020,3,20));

        onView(withText("OK")).perform(click());

        onView(withId(R.id.addtask_time)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(20,3));
        onView(withText("OK")).perform(click());

        //choose time

        //click on finish button
        onView(withId(R.id.addtask_finishBtn)).perform(click());

        try{ Thread.sleep(3000); }catch (Exception _){}

        //check that we stay on AddTask page anymore
        onView(withId(R.id.calendar_tasks)).check(matches(allOf(isDescendantOfA(withId(R.id.layout_calendar)),isDisplayed())));

    }

    @Test
    public void clickFinishButtonAfterLeavingDescriptionEntryEmpty_showAddTaskScreen(){
        //find the task name edit text and type in the task name
        onView(withId(R.id.addtask_taskname)).perform(typeText(taskName),closeSoftKeyboard());

        //find the color radiobutton and choose one
        onView(withId(R.id.addtask_purple)).perform(click());

        //choose date
        onView(withId(R.id.addtask_date)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020,3,20));
        onView(withText("OK")).perform(click());

        //choose time
        onView(withId(R.id.addtask_time)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(20,3));
        onView(withText("OK")).perform(click());

        //click on finish button
        onView(withId(R.id.addtask_finishBtn)).perform(click());

        try{ Thread.sleep(3000); }catch (Exception _){}

        //check that we stay on AddTask page anymore
        onView(withId(R.id.calendar_tasks)).check(matches(allOf(isDescendantOfA(withId(R.id.layout_calendar)),isDisplayed())));

    }

    @Test
    public void clickFinishButtonAfterLeavingColorEntryEmpty_showAddTaskScreen(){
        //find the task name edit text and type in the task name
        onView(withId(R.id.addtask_taskname)).perform(typeText(taskName),closeSoftKeyboard());

        //find the description edit text and type in the description
        onView(withId(R.id.addtask_description)).perform(typeText(description),closeSoftKeyboard());

        //choose date
        onView(withId(R.id.addtask_date)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020,3,20));
        onView(withText("OK")).perform(click());

        //choose time
        onView(withId(R.id.addtask_time)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(20,3));
        onView(withText("OK")).perform(click());

        //click on finish button
        onView(withId(R.id.addtask_finishBtn)).perform(click());

        try{ Thread.sleep(3000); }catch (Exception _){}

        //check that we stay on AddTask page anymore
        onView(withId(R.id.addtask_finishBtn)).check(matches(allOf(isDescendantOfA(withId(R.id.layout_addtask)),isDisplayed())));

    }

    @Test
    public void clickFinishButtonAfterLeavingDateEntryEmpty_showAddTaskScreen(){
        //find the task name edit text and type in the task name
        onView(withId(R.id.addtask_taskname)).perform(typeText(taskName),closeSoftKeyboard());

        //find the description edit text and type in the description
        onView(withId(R.id.addtask_description)).perform(typeText(description),closeSoftKeyboard());

        //find the color radiobutton and choose one
        onView(withId(R.id.addtask_purple)).perform(click());

        onView(withId(R.id.addtask_date)).perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(PickerActions.setDate(2020,3,20));

        onView(withText("OK")).perform(click());



        //choose time

        //click on finish button
        onView(withId(R.id.addtask_finishBtn)).perform(click());

        try{ Thread.sleep(3000); }catch (Exception _){}

        //check that we stay on AddTask page anymore
        onView(withId(R.id.addtask_finishBtn)).check(matches(allOf(isDescendantOfA(withId(R.id.layout_addtask)),isDisplayed())));
    }

    @Test
    public void clickFinishButtonAfterLeavingTimeEntryEmpty_showAddTaskScreen(){
        //find the task name edit text and type in the task name
        onView(withId(R.id.addtask_taskname)).perform(typeText(taskName),closeSoftKeyboard());

        //find the description edit text and type in the description
        onView(withId(R.id.addtask_description)).perform(typeText(description),closeSoftKeyboard());

        //find the color radiobutton and choose one
        onView(withId(R.id.addtask_purple)).perform(click());

        //choose date


        onView(withId(R.id.addtask_time)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(20,3));
        onView(withText("OK")).perform(click());

        //click on finish button
        onView(withId(R.id.addtask_finishBtn)).perform(click());

        try{ Thread.sleep(3000); }catch (Exception _){}

        //check that we stay on AddTask page anymore
        onView(withId(R.id.addtask_finishBtn)).check(matches(allOf(isDescendantOfA(withId(R.id.layout_addtask)),isDisplayed())));
    }

    @Test
    public void clickCloseButton(){
        onView(withId(R.id.addtask_close)).perform(click());
        //check  if go back to calendar
        onView(withId(R.id.calendar_tasks)).check(matches(allOf(isDescendantOfA(withId(R.id.layout_calendar)),isDisplayed())));

    }
}
