package app.killddl.killddl;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.google.firebase.Timestamp;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    private static final String TAG = "MenuActivity";
    private User user = MainActivity.getDatabase().getUser();
    //    List<Tasks> tasksList = new ArrayList<Tasks>();
//    Timestamp tsp;
    private String menustate;
    private boolean init;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
        Intent intent = getIntent();
        if (intent.hasExtra("menuState")) {
            menustate = intent.getStringExtra("menuState");
        }
        else {
//            menustate = "daily";
            System.err.println("!!! No extra in menu");
        }
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.AppThemeDark);
        }else{
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        /*
        Switch toggle = (Switch) findViewById(R.id.switch1);
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            toggle.setChecked(true);
        }
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override*/
            /*
            public void onCheckedChanged(CompoundButton view, boolean isChecked) {
                toggleTheme(isChecked);
            }*/
            /*
            public void onCheckedChanged(CompoundButton view, boolean isChecked){
                if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    restartApp();
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    restartApp();
                }
            }
        });*/

//        tsp = Timestamp.now();
//
//        switch(menustate){
//            case "daily":
//                tasksList = MainActivity.getDatabase().getTaskListByTime(tsp);
//                break;
//            case "weekly":
//                tasksList = weeklyTaskView(tsp);
//                break;
//            case "monthly":
//                tasksList = monthlyTaskView(tsp);
//        }


//        final ScrollView menuScroll = (ScrollView) findViewById(R.id.menu_scrolllist);
//        menuScroll.addView(displayTaskList(tasksList,1));

        // Recycler View
//        final RecyclerView recyclerView = findViewById(R.id.recycler_view);
//        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        initRecyclerView();

        // Top Navigation Bar
        BottomNavigationView topNavigationView = findViewById(R.id.top_navigation);
        BottomNavigationViewHelper.removeShiftMode(topNavigationView);
//        topNavigationView.setItemBackgroundResource(R.drawable.menubackground);

        topNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_daily:
                        menustate="daily";
                        Intent daily = new Intent(getApplicationContext(), MenuActivity.class);
                        daily.putExtra("menuState",menustate);
//                        daily.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(daily);
                        break;
                    case R.id.action_weekly:
                        menustate="weekly";
                        Intent weekly = new Intent(getApplicationContext(), MenuActivity.class);
                        weekly.putExtra("menuState",menustate);
//                        weekly.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(weekly);
                        break;
                    case R.id.action_monthly:
                        menustate="monthly";
                        Intent monthly = new Intent(getApplicationContext(), MenuActivity.class);
                        monthly.putExtra("menuState",menustate);
//                        monthly.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(monthly);
                        break;
                    case R.id.action_past:
                        menustate="past";
                        Intent past = new Intent(getApplicationContext(), MenuActivity.class);
                        past.putExtra("menuState",menustate);
//                        monthly.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        startActivity(past);
                        break;
                }
//                initRecyclerView();
                return true;
            }
        });

//        topNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
//            @Override
//            public void onNavigationItemReselected(@NonNull MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.action_daily:
//                        menustate="daily";
//                        break;
//                    case R.id.action_weekly:
//                        menustate="weekly";
//                        break;
//                    case R.id.action_monthly:
//                        menustate="monthly";
//                        break;
//                }
////                initRecyclerView();
//            }
//        });

//        topNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
//            @Override
//            public void onNavigationItemReselected(@NonNull MenuItem item) {
//                switch (item.getItemId()){
//                    case R.id.action_daily:
//                        tsp = Timestamp.now();
////                        menuScroll.removeAllViews();
//                        tasksList = MainActivity.getDatabase().getTaskListByTime(tsp);
////                        menuScroll.addView(displayTaskList(tasksList,1));
//                        initRecyclerView(recyclerView);
//                        menustate="daily";
//                        break;
//                    case R.id.action_weekly:
//                        tsp = Timestamp.now();
////                        menuScroll.removeAllViews();
//                        tasksList = weeklyTaskView(tsp);
////                        menuScroll.addView(displayTaskList(tasksList,2));
//                        initRecyclerView(recyclerView);
//                        menustate="weekly";
//                        break;
//                    case R.id.action_monthly:
//                        tsp = Timestamp.now();
////                        menuScroll.removeAllViews();
//                        tasksList = monthlyTaskView(tsp);
////                        menuScroll.addView(displayTaskList(tasksList,3));
//                        initRecyclerView(recyclerView);
//                        menustate="monthly";
//                        break;
//                }
//            }
//        });

        // Bottom Navigation Bar
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_menu:
                        Intent menuIntent = new Intent(getApplicationContext(), MenuActivity.class);
                        menuIntent.putExtra("menuState", menustate);
                        startActivity(menuIntent);
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
                return true;
            }
        });

//        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
//            @Override
//            public void onNavigationItemReselected(@NonNull MenuItem item) {
//                switch (item.getItemId()) {
//                    case R.id.action_menu:
//                        break;
//                    case R.id.action_calendar:
//                        Intent calendarIntent = new Intent(getApplicationContext(),CalendarActivity.class);
//                        startActivity(calendarIntent);
//                        break;
//                    case R.id.action_profile:
//                        Intent profileIntent = new Intent(getApplicationContext(),ProfileActivity.class);
//                        startActivity(profileIntent);
//                        break;
//                }
//            }
//        });
    }

    private List<Tasks> weeklyTaskView(Timestamp tsp){
        List<Tasks> selected = new ArrayList<Tasks>();
        selected.addAll(MainActivity.getDatabase().getTaskListByTimeLaterToday(tsp));
        for (int i = 0; i < 6; i++) {
            Calendar c = Calendar.getInstance();
            c.setTime(tsp.toDate());
            c.add(Calendar.DATE, 1);
            tsp = new Timestamp(c.getTime());
            selected.addAll(MainActivity.getDatabase().getTaskListByTime(tsp));
        }
        return selected;
    }

    private List<Tasks> monthlyTaskView(Timestamp tsp){
        List<Tasks> selected = new ArrayList<Tasks>();
        selected.addAll(MainActivity.getDatabase().getTaskListByTimeLaterToday(tsp));
        for (int i = 0; i < 30; i++) {
            Calendar c = Calendar.getInstance();
            c.setTime(tsp.toDate());
            c.add(Calendar.DATE, 1);
            tsp = new Timestamp(c.getTime());
            selected.addAll(MainActivity.getDatabase().getTaskListByTime(tsp));
        }
        return selected;
    }

    private List<Tasks> pastTaskView(Timestamp tsp){
        List<Tasks> selected = new ArrayList<Tasks>();
        selected.addAll(MainActivity.getDatabase().getTaskListBeforeTime(tsp));
//        selected.addAll(MainActivity.getDatabase().getTaskListByTimeEarlyToday(tsp));
//        for (int i = 0; i < 300; i++) {
//            Calendar c = Calendar.getInstance();
//            c.setTime(tsp.toDate());
//            c.add(Calendar.DATE, -1);
//            tsp = new Timestamp(c.getTime());
//            selected.addAll(MainActivity.getDatabase().getTaskListByTime(tsp));
//        }
        return selected;
    }

    public void AddTask(View v){
        Intent addTask = new Intent(getApplicationContext(),AddTaskActivity.class);
        addTask.putExtra("page","menuPage");
        addTask.putExtra("menuState", menustate);
        startActivity(addTask);
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        if (!init) {
            recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            init = true;
        }
        recyclerView.removeAllViews();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        Timestamp tsp = Timestamp.now();
        List<Tasks> tasksList = new ArrayList<>();

        switch(menustate){
            case "daily":
                tasksList = MainActivity.getDatabase().getTaskListByTimeLaterToday(tsp);
                break;
            case "weekly":
                tasksList = weeklyTaskView(tsp);
                break;
            case "monthly":
                tasksList = monthlyTaskView(tsp);
                break;
            case "past":
                tasksList = pastTaskView(tsp);
                break;
        }

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, displayTaskList(tasksList), menustate);
        GestureHelper gestureHelper = new GestureHelper(adapter, MenuActivity.this);
        ItemTouchHelper touchHelper = new ItemTouchHelper(gestureHelper);
        adapter.setTouchHelper(touchHelper);
        recyclerView.setAdapter(adapter);
        touchHelper.attachToRecyclerView(recyclerView);
    }

    public void restartApp(){
        Intent i = new Intent(getApplicationContext(), MenuActivity.class);
        i.putExtra("menuState",menustate);
        startActivity(i);
        finish();
    }

    private List<Tasks> displayTaskList(List<Tasks> tasksList) {
        List<Tasks> displayTasks = new ArrayList<>();
        for(int i=0; i<tasksList.size(); i++) {
            if(!tasksList.get(i).isFinished) {
                displayTasks.add(tasksList.get(i));
            }
        }
        return displayTasks;
    }

//    public static float convertDpToPixel(float dp, Context context){
//        Resources resources = context.getResources();
//        DisplayMetrics metrics = resources.getDisplayMetrics();
//        float px = dp * ((float)metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
//        return px;
//    }
//
//    public void clickTask(View v, int id){
//        Intent newIntent = new Intent(getApplicationContext(), EditTaskActivity.class);
//        newIntent.putExtra("edit_taskId",id);
//        startActivity(newIntent);
//    }
//
//    public static int spToPx(float sp, Context context) {
//        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
//    }
//
//    private LinearLayout displayTaskList(List<Tasks> tasksList, int displayType){
//        LinearLayout rl = new LinearLayout(this);
//        if(displayType == 1){
//            //rl.setId(R.id.menu_daily);
//            rl.setTag("menu_daily");
//        }else if(displayType == 2){
//            //rl.setId(R.id.menu_weekly);
//            rl.setTag("menu_weekly");
//        }else if(displayType == 3){
//            //rl.setId(R.id.menu_monthly);
//            rl.setTag("menu_monthly");
//        }
//        rl.setOrientation(LinearLayout.VERTICAL);
//        for(int i=0; i<tasksList.size(); i++){
//            //create new View
//            if(!tasksList.get(i).isFinished) {
//                LinearLayout ll = new LinearLayout(this);
//                ll.setOrientation(LinearLayout.VERTICAL);
//                float density = this.getResources().getDisplayMetrics().density;
//                int paddingPixel = (int) (30 * density);
//                ll.setPadding(paddingPixel, 0, 0, 0);
//                //add color
//
//                final int id = tasksList.get(i).getId();
//                TextView tx = new TextView(this);
//                tx.setText(tasksList.get(i).getName());
//                tx.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        clickTask(view, id);
//                    }
//                });
//                ll.addView(tx);
//                rl.addView(ll);
//            }
//        }
//        return rl;
//    }
}
class BottomNavigationViewHelper {

    @SuppressLint("RestrictedApi")
    public static void removeShiftMode(BottomNavigationView view) {
        //this will remove shift mode for bottom navigation view
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }

        } catch (NoSuchFieldException e) {
            Log.e("ERROR NO SUCH FIELD", "Unable to get shift mode field");
        } catch (IllegalAccessException e) {
            Log.e("ERROR ILLEGAL ALG", "Unable to change value of shift mode");
        }
    }
}