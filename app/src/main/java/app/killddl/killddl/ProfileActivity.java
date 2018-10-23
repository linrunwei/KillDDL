package app.killddl.killddl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        user = MainActivity.getUser();

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
                        Intent calendarIntent = new Intent(getApplicationContext(),CalendarActivity.class);
                        startActivity(calendarIntent);
                        break;
                    case R.id.action_profile:
                        break;
                }
            }
        });

        //generate username and tasks they have
        //TODO fixed tasks remaining after get User
        user = MainActivity.getUser();
        TextView headUserName = findViewById(R.id.profile_head_username);
        TextView username = findViewById(R.id.profile_username);
        TextView tasksRemaining = findViewById(R.id.profile_tasks_number);
        headUserName.setText(user.name);
        username.setText(user.name);
        tasksRemaining.setText(user.getTaskList().size()+ "");

    }

    void Logout(View v){
        MainActivity.deleteUser();
        Intent login = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(login);
    }
}
