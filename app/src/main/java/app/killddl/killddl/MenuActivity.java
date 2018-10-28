package app.killddl.killddl;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.Timestamp;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

public class MenuActivity extends AppCompatActivity {
    User user = MainActivity.getDatabase().getUser();
    List<Tasks> tasksList = new ArrayList<Tasks>();
    Timestamp tsp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        tsp = Timestamp.now();

        tasksList = MainActivity.getDatabase().getTaskListByTime(tsp);
        final ScrollView menuScroll = (ScrollView) findViewById(R.id.menu_scrolllist);
        menuScroll.addView(displayTaskList(tasksList));
        //Top Navigation Bar
        BottomNavigationView topNavigationView = (BottomNavigationView) findViewById(R.id.top_navigation);
        topNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_daily:
                        tsp = Timestamp.now();
                        menuScroll.removeAllViews();
                        tasksList = MainActivity.getDatabase().getTaskListByTime(tsp);
                        menuScroll.addView(displayTaskList(tasksList));
                        break;
                    case R.id.action_weekly:
                        tsp = Timestamp.now();
                        menuScroll.removeAllViews();
                        tasksList = weeklyTaskView(tsp);
                        menuScroll.addView(displayTaskList(tasksList));

                        break;
                    case R.id.action_monthly:
                        tsp = Timestamp.now();
                        menuScroll.removeAllViews();
                        tasksList = monthlyTaskView(tsp);
                        menuScroll.addView(displayTaskList(tasksList));
                        break;
                }
            }
        });

        //Bottom Navigation Bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_menu:
                        break;
                    case R.id.action_calendar:
                        Intent calendarIntent = new Intent(getApplicationContext(),CalendarActivity.class);
                        startActivity(calendarIntent);
                        break;
                    case R.id.action_profile:
                        Intent profileIntent = new Intent(getApplicationContext(),ProfileActivity.class);
                        startActivity(profileIntent);
                        break;
                }
            }
        });
    }
    public static float convertDpToPixel(float dp, Context context){
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }
    public static int spToPx(float sp, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
    }

    LinearLayout displayTaskList(List<Tasks> tasksList){


        LinearLayout rl = new LinearLayout(this);
        rl.setOrientation(LinearLayout.VERTICAL);
        for(int i=0; i<tasksList.size(); i++){
            //create new View
            if(!tasksList.get(i).isFinished) {
                LinearLayout ll = new LinearLayout(this);
                ll.setOrientation(LinearLayout.VERTICAL);
                float density = this.getResources().getDisplayMetrics().density;
                int paddingPixel = (int) (30 * density);
                ll.setPadding(paddingPixel, 0, 0, 0);
                //add color

                final int id = tasksList.get(i).getId();
                TextView tx = new TextView(this);
                tx.setText(tasksList.get(i).getName());
                tx.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        clickTask(view, id);
                    }
                });
                ll.addView(tx);
                rl.addView(ll);
            }

            //add days_remaining
        }
        return rl;

    }
    List<Tasks> weeklyTaskView(Timestamp tsp){
        List<Tasks> selected = new ArrayList<Tasks>();
        selected.addAll(MainActivity.getDatabase().getTaskListByTime(tsp));
        for (int i = 0; i < 6; i++) {
            Calendar c = Calendar.getInstance();
            c.setTime(tsp.toDate());
            c.add(Calendar.DATE, 1);
            tsp = new Timestamp(c.getTime());
            selected.addAll(MainActivity.getDatabase().getTaskListByTime(tsp));
        }
        return selected;
    }
    List<Tasks> monthlyTaskView(Timestamp tsp){
        List<Tasks> selected = new ArrayList<Tasks>();
        selected.addAll(MainActivity.getDatabase().getTaskListByTime(tsp));
        for (int i = 0; i < 30; i++) {
            Calendar c = Calendar.getInstance();
            c.setTime(tsp.toDate());
            c.add(Calendar.DATE, 1);
            tsp = new Timestamp(c.getTime());
            selected.addAll(MainActivity.getDatabase().getTaskListByTime(tsp));
        }
        return selected;

    }
    public void clickTask(View v, int id){
        Intent newIntent = new Intent(getApplicationContext(), EditTaskActivity.class);
        newIntent.putExtra("edit_taskId",id);
        startActivity(newIntent);
    }
    public void AddTask(View v){
        Intent addTask = new Intent(getApplicationContext(),AddTaskActivity.class);
        //TODO add extra info
        startActivity(addTask);
    }
}
