package app.killddl.killddl;

import android.content.Intent;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;


import java.util.Date;

public class EditPasswordActivity extends AppCompatActivity{

    User user;
    FirebaseAuth mAuth;
    private EditText mOldPassword;
    private EditText mNewPassword;
    private Button mChangePassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editpassword);
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

        mOldPassword = findViewById(R.id.editpassword_oldpassword);
        mNewPassword = findViewById(R.id.editpassword_newpassword);
        mOldPassword.setEnabled(true);
        mNewPassword.setEnabled(true);

        final FirebaseUser cuser = FirebaseAuth.getInstance().getCurrentUser();

        mChangePassword = (Button) findViewById(R.id.editpassword_changepasswordBtn);
        mChangePassword.setOnClickListener(new View.OnClickListener() {
            //save
            @Override
            public void onClick(View view) {
                if (mOldPassword == null || mNewPassword == null) {
                    TextView err = findViewById(R.id.editpassword_errormsg);
                    err.setText("Some contents are empty!");
                    return;
                }
                String OldPassword = mOldPassword.getText().toString();
                String NewPassword = mNewPassword.getText().toString();

                //add password in Database

                cuser.updatePassword(NewPassword)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    //Toast.makeText(EditPasswordActivity.this,"Password Changed.",Toast.LENGTH_LONG).show();
                                    Toast.makeText(getApplicationContext(), "Password Changed.", Toast.LENGTH_LONG).show();
                                    Intent Profile = new Intent(getApplicationContext(),ProfileActivity.class);
                                    startActivity(Profile);
                                } else {
                                    //Toast.makeText(getApplicationContext(), "Password Could Not Be Changed.", Toast.LENGTH_LONG).show();
                                }
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof FirebaseAuthException) {
                            ((FirebaseAuthException) e).getErrorCode();
                            Toast.makeText(EditPasswordActivity.this,((FirebaseAuthException) e).getErrorCode(),Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "Password Could Not Be Changed.", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }


//    public void Logout(View v){
//        MainActivity.quit();
//        Intent login = new Intent(getApplicationContext(),MainActivity.class);
//        startActivity(login);
//    }
}
