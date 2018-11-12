package app.killddl.killddl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    User user;
    ImageView avatar;
    int count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.AppThemeDark);
        }else{
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        user = MainActivity.getDatabase().getUser();
        avatar = (ImageView) findViewById(R.id.avatar);
        final int image[] = new int[]{
                R.drawable.ic_person_white_24dp,
                R.drawable.dog,
                R.drawable.cat,
                R.drawable.turtle,
                R.drawable.student,
                R.drawable.basketball,
                R.drawable.baseball,
                R.drawable.americanfootball,
                R.drawable.soccer_ball,
                R.drawable.goal
        };
        avatar.setImageResource(image[user.getAvatar()%image.length]);
        count = 0;
        avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AvatarChangeActivity.class);
                startActivity(intent);
            }
        });

        Switch toggle = (Switch) findViewById(R.id.switch1);
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            toggle.setChecked(true);
        }
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            /*
            public void onCheckedChanged(CompoundButton view, boolean isChecked) {
                toggleTheme(isChecked);
            }*/
            public void onCheckedChanged(CompoundButton view, boolean isChecked){
                if(isChecked){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    restartApp();
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    restartApp();
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
                        Intent menuIntent = new Intent(getApplicationContext(),MenuActivity.class);
                        menuIntent.putExtra("menuState","daily");
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

        user = MainActivity.getDatabase().getUser();
        TextView headUserName = findViewById(R.id.profile_head_username);
        TextView username = findViewById(R.id.profile_username);
        TextView tasksRemaining = findViewById(R.id.profile_tasks_number);
        headUserName.setText(user.email);
        username.setText(user.email); 
        tasksRemaining.setText((MainActivity.getDatabase().getUnfinishedTask()+ ""));

    }

    public void Logout(View v){
        MainActivity.quit();
        Intent login = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(login);
    }

    public void restartApp(){
        Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(i);
        finish();
    }

    public void EditPassword(View v){
        Intent login = new Intent(getApplicationContext(),EditPasswordActivity.class);
        startActivity(login);
    }

    public void openAnalytics(View v){
        Intent Analytics = new Intent(getApplicationContext(),AnalyticsActivity.class);
        startActivity(Analytics);
    }
}
