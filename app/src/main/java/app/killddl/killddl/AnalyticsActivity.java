package app.killddl.killddl;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.SimpleFormatter;

//import com.jjoe64.graphview.GraphView;
//import com.jjoe64.graphview.ValueDependentColor;
//import com.jjoe64.graphview.series.BarGraphSeries;
//import com.jjoe64.graphview.series.DataPoint;

public class AnalyticsActivity extends AppCompatActivity {
    HashMap<String, Integer> map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_analytics);
        map = MainActivity.getDatabase().getFinshedTasks();
        System.out.println(map.toString());
        displayFourDaysData();

        //bottom navigation bar
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_menu:
                        Intent menuIntent = new Intent(getApplicationContext(),MenuActivity.class);
                        menuIntent.putExtra("menuState","daily");
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
            }
        });

        BottomNavigationView topNavigationView = (BottomNavigationView) findViewById(R.id.analytics_navigation);
        topNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.analytics_fourday:
                        displayFourDaysData();
                        break;
                    case R.id.analytics_week:
                        displayWeekData();
                        System.out.println("HI");
                        break;
                    case R.id.analytics_month:
                        displayMonthData();
                        break;
                }
            }
        });
    }

    private Date toDate(String date){
        String pattern = "yyyy/MM/dd";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        try {
            System.out.println(format.parse(date));
            return format.parse(date);
        } catch(ParseException e){
            System.out.println("Error in Analytics: " + e.getMessage());
        }
        finally {

        }
        return new Date();

    }

    private String toTimeString(Date date){
        String pattern = "yyyy/MM/dd";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        System.out.println(format.format(date));
        return format.format(date);
    }

    private void displayFourDaysData(){
        GraphView graph = (GraphView) findViewById(R.id.analytics_graph);
        graph.removeAllSeries();
        DataPoint[] res = new DataPoint[4];
        Calendar calendar = Calendar.getInstance();
        Date maxDate = new Date();
        Date minDate = new Date();
        calendar.add(Calendar.DATE, -3);
        for (int i = 0; i <= 3; i++){
            if(i == 0) minDate = calendar.getTime();
            if(i == 3) maxDate = calendar.getTime();
            if(map.get(toTimeString(calendar.getTime())) == null)
                res[i] = new DataPoint(calendar.getTime(),0);
            else
                res[i] = new DataPoint(calendar.getTime(),map.get(toTimeString(calendar.getTime()))+1);
            calendar.add(Calendar.DATE, 1);
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(res);
        series.setDrawBackground(true);
        series.setDrawDataPoints(true);
        series.setAnimated(true);
        series.setTitle("Tasks Finished in past 4 day");
        graph.addSeries(series);

        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getApplicationContext()));
        graph.getGridLabelRenderer().setNumHorizontalLabels(3); // only 4 because of the space

        graph.getViewport().setMinX(minDate.getTime());
        graph.getViewport().setMaxX(maxDate.getTime());
        graph.getViewport().setXAxisBoundsManual(true);

    }

    private void displayWeekData(){

        GraphView graph = (GraphView) findViewById(R.id.analytics_graph);
        graph.removeAllSeries();
        DataPoint[] res = new DataPoint[7];
        Calendar calendar = Calendar.getInstance();
        Date maxDate = new Date();
        Date minDate = new Date();
        calendar.add(Calendar.DATE, -6);
        for (int i = 0; i < 7; i++){
            if(i == 0) minDate = calendar.getTime();
            if(i == 6) maxDate = calendar.getTime();
            if(map.get(toTimeString(calendar.getTime())) == null)
                res[i] = new DataPoint(calendar.getTime(),0);
            else
                res[i] = new DataPoint(calendar.getTime(),map.get(toTimeString(calendar.getTime()))+1);
            calendar.add(Calendar.DATE, 1);
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(res);
        series.setDrawBackground(true);
        series.setDrawDataPoints(true);
        series.setAnimated(true);
        series.setTitle("Tasks Finished in this week");
        graph.addSeries(series);

        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getApplicationContext()));
        graph.getGridLabelRenderer().setNumHorizontalLabels(3); // only 4 because of the space

        graph.getViewport().setMinX(minDate.getTime());
        graph.getViewport().setMaxX(maxDate.getTime());

        graph.getViewport().setXAxisBoundsManual(true);

    }

    private void displayMonthData(){
        GraphView graph = (GraphView) findViewById(R.id.analytics_graph);
        graph.removeAllSeries();
        DataPoint[] res = new DataPoint[30];
        Calendar calendar = Calendar.getInstance();
        Date maxDate = new Date();
        Date minDate = new Date();
        calendar.add(Calendar.DATE, -29);
        for (int i = 0; i < 30; i++){
            if(i == 0) minDate = calendar.getTime();
            if(i == 29) maxDate = calendar.getTime();
            if(map.get(toTimeString(calendar.getTime())) == null)
                res[i] = new DataPoint(calendar.getTime(),0);
            else
                res[i] = new DataPoint(calendar.getTime(),map.get(toTimeString(calendar.getTime()))+1);
            calendar.add(Calendar.DATE, 1);
        }
        LineGraphSeries<DataPoint> series = new LineGraphSeries<>(res);
        series.setDrawBackground(true);
        series.setDrawDataPoints(true);
        series.setAnimated(true);
        series.setTitle("Tasks Finished in past 30 days");
        graph.addSeries(series);

        graph.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(getApplicationContext()));
        graph.getGridLabelRenderer().setNumHorizontalLabels(3); // only 4 because of the space

        graph.getViewport().setMinX(minDate.getTime());
        graph.getViewport().setMaxX(maxDate.getTime());

        graph.getViewport().setXAxisBoundsManual(true);

    }
    public void goBackProfile(View v){
        Intent Profile = new Intent(getApplicationContext(),ProfileActivity.class);
        startActivity(Profile);
    }
}

