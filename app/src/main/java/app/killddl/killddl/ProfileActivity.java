package app.killddl.killddl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserInfo;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import android.util.Log;


public class ProfileActivity extends AppCompatActivity {

    User user;
    ImageView avatar;
    int count;
    int ifFacebook = 0;
    FirebaseUser cuser = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            setTheme(R.style.AppThemeDark);
        }else{
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        cuser = FirebaseAuth.getInstance().getCurrentUser();
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
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        startActivity(login);
    }

    public void restartApp(){
        Intent i = new Intent(getApplicationContext(), ProfileActivity.class);
        startActivity(i);
        finish();
    }

    public void UpdateEmail(View v){
        if (cuser == null) return;
        for (UserInfo userInfo : cuser.getProviderData()) {
            if (userInfo.getProviderId().equals("facebook.com")) {
                ifFacebook = 1;
                Toast.makeText(getApplicationContext(), "You logged in with Facebook.\nEmail cannot be changed.", Toast.LENGTH_LONG).show();
                return;
            }
        }
        Intent login = new Intent(getApplicationContext(),UpdateEmailActivity.class);
        startActivity(login);
    }

    public void EditPassword(View v){
        if (cuser == null) return;
        for (UserInfo userInfo : cuser.getProviderData()) {
            if (userInfo.getProviderId().equals("facebook.com")) {
                ifFacebook = 1;
                Toast.makeText(getApplicationContext(), "You logged in with Facebook.\nPassword cannot be changed.", Toast.LENGTH_LONG).show();
                return;
            }
        }
        Intent login = new Intent(getApplicationContext(),EditPasswordActivity.class);
        startActivity(login);
    }

    public void openAnalytics(View v){
        Intent Analytics = new Intent(getApplicationContext(),AnalyticsActivity.class);
        startActivity(Analytics);
    }
    public void deleteAccount(View v){
//        MainActivity.quit();
        final Intent login = new Intent(getApplicationContext(),MainActivity.class);
        cuser.delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getApplicationContext(), "Delete Successful!", Toast.LENGTH_LONG).show();
                    startActivity(login);
                }
            }
        });



    }
}


