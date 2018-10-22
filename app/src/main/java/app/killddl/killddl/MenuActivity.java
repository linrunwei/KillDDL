package app.killddl.killddl;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        /*
        final ScrollView dailyView = (ScrollView) findViewById(R.id.daily);
        final ScrollView weeklyView = (ScrollView) findViewById(R.id.weekly);
        final ScrollView monthlyView = (ScrollView) findViewById(R.id.monthly);
        dailyView.setVisibility(View.VISIBLE);
        weeklyView.setVisibility(View.INVISIBLE);
        monthlyView.setVisibility(View.INVISIBLE);*/
        final CheckBox checkBox = (CheckBox) findViewById(R.id.menu_content_chekcbox);
        checkBox.setVisibility(View.INVISIBLE);
        dailyTaskView();

        //Top Navigation Bar
        BottomNavigationView topNavigationView = (BottomNavigationView) findViewById(R.id.top_navigation);
        topNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_daily:
                        //dailyView.setVisibility(View.VISIBLE);
                        //weeklyView.setVisibility(View.INVISIBLE);
                        //monthlyView.setVisibility(View.INVISIBLE);
                        dailyTaskView();
                        break;
                    case R.id.action_weekly:
                        //dailyView.setVisibility(View.INVISIBLE);
                        //weeklyView.setVisibility(View.VISIBLE);
                        //monthlyView.setVisibility(View.INVISIBLE);
                        weeklyTaskView();
                        break;
                    case R.id.action_monthly:
                        //dailyView.setVisibility(View.INVISIBLE);
                        //weeklyView.setVisibility(View.INVISIBLE);
                        //monthlyView.setVisibility(View.VISIBLE);
                        monthlyTaskView();
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

    LinearLayout dailyTaskView() {
        LinearLayout rl = (LinearLayout) findViewById(R.id.menu_content);

        while(rl.getChildCount() > 0){
            rl.removeAllViews();
        }
        /*
        LinearLayout l1 = (LinearLayout) rl.getChildAt(0);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) l1.getLayoutParams();
        l1.setLayoutParams(params);*/

        LayoutInflater inflater = (LayoutInflater)getBaseContext() .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.task_view, null);
        view.setId(0);
        rl.addView(view);

        /*
        LinearLayout l1 = new LinearLayout(this);
        int menu_task_width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, R.dimen.menu_task_width, getResources().getDisplayMetrics());
        int menu_task_height = (int) spToPx(R.dimen.menu_task_height, this.getApplicationContext());
        l1.setLayoutParams(new LinearLayout.LayoutParams(menu_task_width, menu_task_height));

        CheckBox isTaskFinished = new CheckBox(this);
        l1.addView(isTaskFinished);

        TextView taskName = new TextView(this);
        taskName.setText("@string/string_name");
        l1.addView(taskName);

        TextView taskTime = new TextView(this);
        taskName.setText("12 pm");
        l1.addView(taskTime);

        rl.addView(l1);*/
        return rl;
    }
    LinearLayout weeklyTaskView(){
        LinearLayout rl = (LinearLayout) findViewById(R.id.menu_content);

        while(rl.getChildCount() > 0){
            rl.removeAllViews();
        }

        LayoutInflater inflater = (LayoutInflater)getBaseContext() .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for(int i=0; i<2; i++){
            View view = inflater.inflate(R.layout.task_view, null);
            view.setId(i);
            rl.addView(view);
        }
        /*
        LinearLayout l1 = new LinearLayout(this);
        int menu_task_width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, R.dimen.menu_task_width, getResources().getDisplayMetrics());
        int menu_task_height = (int) spToPx(R.dimen.menu_task_height, this.getApplicationContext());
        l1.setLayoutParams(new LinearLayout.LayoutParams(menu_task_width, menu_task_height));

        CheckBox isTaskFinished = new CheckBox(this);
        l1.addView(isTaskFinished);

        TextView taskName = new TextView(this);
        taskName.setText("@string/string_name");
        rl.addView(taskName);

        TextView taskTime = new TextView(this);
        taskName.setText("1 pm");
        rl.addView(taskTime);*/

        //rl.addView(l1);
        return rl;
    }
    LinearLayout monthlyTaskView(){
        LinearLayout rl = (LinearLayout) findViewById(R.id.menu_content);
        if(rl.getChildCount() > 0){
            rl.removeAllViews();
        }
        LayoutInflater inflater = (LayoutInflater)getBaseContext() .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        for(int i=0; i<3; i++){
            View view = inflater.inflate(R.layout.task_view, null);
            view.setId(i);
            rl.addView(view);
        }

        /*
        LinearLayout l1 = new LinearLayout(this);
        int menu_task_width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, R.dimen.menu_task_width, getResources().getDisplayMetrics());
        int menu_task_height = (int) spToPx(R.dimen.menu_task_height, this.getApplicationContext());
        l1.setLayoutParams(new LinearLayout.LayoutParams(menu_task_width, menu_task_height));

        CheckBox isTaskFinished = new CheckBox(this);
        l1.addView(isTaskFinished);

        TextView taskName = new TextView(this);
        taskName.setText("@string/string_name");
        l1.addView(taskName);

        TextView taskTime = new TextView(this);
        taskName.setText("2 pm");
        l1.addView(taskTime);

        rl.addView(l1);*/
        return rl;
    }
    public void clickTask(View View){
        Intent editTaskIntent = new Intent(getApplicationContext(),EditTaskActivity.class);
        startActivity(editTaskIntent);
    }
    public void AddTask(View v){
        Intent addTask = new Intent(getApplicationContext(),AddTaskActivity.class);
        //TODO add extra info
        startActivity(addTask);
    }
}
