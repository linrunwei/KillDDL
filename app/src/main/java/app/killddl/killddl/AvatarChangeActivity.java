package app.killddl.killddl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class AvatarChangeActivity extends AppCompatActivity {
    User user;
    ImageView curr_avatar;
    ImageView avatar;

    int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = MainActivity.getDatabase().getUser();
        setContentView(R.layout.activity_avatarchange);
        curr_avatar = (ImageView) findViewById(R.id.avatar_change);
//        avatar = (ImageView) findViewById(R.id.avatar);

        final int image[] = new int[]{
                R.drawable.ic_person_black_24dp,
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
        curr_avatar.setImageResource(image[user.getAvatar()%image.length]);
        count = 0;
        curr_avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
//                avatar.setImageResource(image[count%image.length]);
                curr_avatar.setImageResource(image[count%image.length]);
                user.setAvatar(count);

                System.out.println(user.getAvatar());
                MainActivity.getDatabase().setUser(user);
                MainActivity.getDatabase().updateUser(user);
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

        //generate username and tasks they have
        //TODO fixed tasks remaining after get User


//        TextView headUserName = findViewById(R.id.profile_head_username);
//        TextView username = findViewById(R.id.profile_username);
//        TextView tasksRemaining = findViewById(R.id.profile_tasks_number);
//        headUserName.setText(user.email);
//        username.setText(user.email);
//        tasksRemaining.setText((MainActivity.getDatabase().getUnfinishedTask()+ ""));

    }


}
