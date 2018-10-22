package app.killddl.killddl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CalendarActivity extends AppCompatActivity {
    private static final String TAG = "CalendarActivity";
    private CalendarView mCalendarView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        mCalendarView = (CalendarView) findViewById(R.id.calendarView);

        mCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                String date = (month+1) + "/" + (day) + "/" + year;
                System.out.println(date);
                LinearLayout ll = (LinearLayout) findViewById(R.id.calendar_tasks);
                ll.removeAllViews();
                //example tasks TODO need adding vector of tasks
                //java.sql.Timestamp timestamp = java.sql.Timestamp.valueOf("2007-09-23 10:10:10.0");
                //Tasks example = new Tasks(1,timestamp);
                //example.EditName("Hello");


                //Dynamically display task in that day
                //ll.addView(addTaskView(example));

            }
        });
        //Bottom Navigation Bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_menu:
                        Intent menuIntent = new Intent(getApplicationContext(),MenuActivity.class);
                        startActivity(menuIntent);
                        break;
                    case R.id.action_calendar:
                        break;
                    case R.id.action_profile:
                        Intent profileIntent = new Intent(getApplicationContext(),ProfileActivity.class);
                        startActivity(profileIntent);
                        break;
                }
            }
        });


    }

    LinearLayout addTaskView(Tasks task) {
        LinearLayout ll = new LinearLayout(this);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

        ImageView circle = new ImageView(this);
        circle.setMaxWidth(30);
        circle.setMaxHeight(30);
        circle.setImageResource(R.drawable.ic_person_black_24dp);
        ll.addView(circle);

        TextView taskName = new TextView(this);
        taskName.setText(task.name);
        ll.addView(taskName);


        return ll;
    }

    public void AddTask(View v){
        Intent addTask = new Intent(getApplicationContext(),AddTaskActivity.class);
        //TODO add extra info
        startActivity(addTask);
    }
}

//TODO finish add task 3h
//TODO finish profile page 3h
//TODO fix calendar page 1h
//TODO finish menu page 3h



