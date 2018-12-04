package app.killddl.killddl;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class UpdateEmailActivity extends AppCompatActivity {

    User user;
    ImageView avatar;
    FirebaseAuth mAuth;
    private EditText mNewEmail;
    private Button mChangeEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_updateemail);
        mAuth = MainActivity.getAuth();

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

        user = MainActivity.getDatabase().getUser();
        TextView headUserName = findViewById(R.id.profile_head_username);
        headUserName.setText(user.email);
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
        mNewEmail = findViewById(R.id.newemail);

        mNewEmail.setEnabled(true);

        final FirebaseUser currUser = FirebaseAuth.getInstance().getCurrentUser();

        mChangeEmail = (Button) findViewById(R.id.changeemailBtn);
        mChangeEmail.setOnClickListener(new View.OnClickListener() {
            //save
            @Override
            public void onClick(View view) {
                if (mNewEmail == null || mNewEmail.length() == 0 ) {
                    TextView err = findViewById(R.id.updateemail_errormsg);
                    //err.setText("Some contents are empty!");
                    Toast.makeText(getApplicationContext(), "New email is empty!", Toast.LENGTH_LONG).show();
                    return;
                }
                final String newEmail = mNewEmail.getText().toString();

                if (newEmail.length() == 0 ) {
                    TextView err = findViewById(R.id.updateemail_errormsg);
                    //err.setText("Some contents are empty!");
                    Toast.makeText(getApplicationContext(), "New email is empty!", Toast.LENGTH_LONG).show();
                    return;
                }
                if(newEmail.equals(user.getEmail())){
                    Toast.makeText(getApplicationContext(), "There is your current email!", Toast.LENGTH_LONG).show();
                    return;
                }


                //add password in Database
                if(currUser == null){
                    Toast.makeText(getApplicationContext(), "An error occurred for user.", Toast.LENGTH_LONG).show();
                    return;
                }
                currUser.updateEmail(newEmail)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Email Updated.", Toast.LENGTH_LONG).show();
                                    user.setEmail(newEmail);
                                    MainActivity.getDatabase().updateUser(user);
                                    FirebaseUser cuser = mAuth.getCurrentUser();

                                    cuser.sendEmailVerification()
                                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                @Override
                                                public void onComplete(@NonNull Task<Void> task) {
                                                    if (task.isSuccessful()) {
                                                        System.out.print("success!!!!!!");
                                                    }
                                                }
                                            });
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof FirebaseAuthException) {
                            ((FirebaseAuthException) e).getErrorCode();
                            Toast.makeText(UpdateEmailActivity.this,((FirebaseAuthException) e).getErrorCode(),Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Email Could Not Be Changed.", Toast.LENGTH_LONG).show();
                        }
                    }
                });


            }
        });
    }

    public void goBackProfile(View v){
        Intent Profile = new Intent(getApplicationContext(),ProfileActivity.class);
        startActivity(Profile);
    }
}
