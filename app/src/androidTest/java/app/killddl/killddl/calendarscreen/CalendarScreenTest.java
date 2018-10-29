//package app.killddl.killddl.calendarscreen;
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
//import app.killddl.killddl.CalendarActivity;
//import app.killddl.killddl.MainActivity;
//import app.killddl.killddl.R;
//
//import static android.support.test.espresso.Espresso.onView;
//import static android.support.test.espresso.action.ViewActions.click;
//import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
//import static android.support.test.espresso.action.ViewActions.typeText;
//import android.support.test.espresso.contrib.PickerActions;
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
//public class CalendarScreenTest {
//
//    @Rule
//    public ActivityTestRule<CalendarActivity> mCalendarActivityTestRule = new
//            ActivityTestRule<CalendarActivity>(CalendarActivity.class);
//
//    @Test
//    public void clickDateOnCalendar_showUpdatedCalendarScreen(){
//        //click on specific date on calendar
//        onView(withId(R.id.calendarView)).perform(PickerActions.setDate(2018, 10, 31));
//
//        //check for Id name
//    }
//
//    @Test
//    public void clickAddTaskButton_showAddTaskScreen(){
//        //click on addtask button
//        onView(withId(R.id.calendar_addTaskBtn)).perform(click(),closeSoftKeyboard());
//
//        //check that we can see the AddTask screen
//        onView(withId(R.id.addtask_finishBtn)).check(matches(allOf(isDescendantOfA(withId(R.id.layout_addtask)),isDisplayed())));
//    }
//
//}
