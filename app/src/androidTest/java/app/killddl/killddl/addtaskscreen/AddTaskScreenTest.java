package app.killddl.killddl.addtaskscreen;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.filters.LargeTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;


import app.killddl.killddl.AddActivity;
import app.killddl.killddl.CalendarActivity;
import app.killddl.killddl.MainActivity;
import app.killddl.killddl.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
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
public class AddTaskScreenTest {
    private String taskName = "Implementation";
    private String Description = "CSCI310 Implementation Due";
    private int year = 2018;
    private int month = 10;
    private int day = 31;

    @Rule
    public ActivityTestRule<AddActivity> mAddActivityTestRule = new
            ActivityTestRule<AddActivity>(AddActivity.class);

    @Test
    public void clickFinishButtonAfterFillingCorrectInfo_showCalendarScreen(){
        //find the task name edit text and type in the task name

        //find the description edit text and type in the description

        //
    }

    @Test
    public void clickFinishButtonAfterLeavingTaskNameEntryEmpty_showAddTaskScreen(){

    }

    @Test
    public void clickFinishButtonAfterLeavingDescriptionEntryEmpty_showAddTaskScreen(){

    }

    @Test
    public void clickFinishButtonAfterLeavingColorEntryEmpty_showAddTaskScreen(){

    }

    @Test
    public void clickFinishButtonAfterLeavingDateEntryEmpty_showAddTaskScreen(){

    }

    @Test
    public void clickFinishButtonAfterLeavingTimeEntryEmpty_showAddTaskScreen(){

    }
}
