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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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
        User u = new User("Yanxi Li", "123456");
        TextView headUserName = findViewById(R.id.profile_head_username);
        TextView username = findViewById(R.id.profile_username);
        TextView tasksRemaining = findViewById(R.id.profile_tasks_number);
        headUserName.setText(u.name);
        username.setText(u.name);
        tasksRemaining.setText("12");

    }

    void Logout(View v){
        //TODO user logout


        Intent login = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(login);
    }
}
