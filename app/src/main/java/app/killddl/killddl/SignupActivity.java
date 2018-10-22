package app.killddl.killddl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        //sign up
        Button signupBtn = (Button) findViewById(R.id.signupBtn);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText usernameEditText = (EditText) findViewById(R.id.signup_username);
                EditText passwordEditText = (EditText) findViewById(R.id.signup_password);
                TextView errorMsg = (TextView) findViewById(R.id.signup_errorMsg);
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                System.out.println("username: " + username + " password: " + password); //TODO remove this after connection
                if(password.length() == 0){errorMsg.setText("Password cannot be empty!");}
                else if(username.length() == 0){errorMsg.setText("Username cannot be empty!");}
                else if(false){errorMsg.setText("Username already in use!");} //TODO check database
                else if(false){errorMsg.setText("Password already in use!");} //TODO check database
                else{
                    Intent mainPage = new Intent(getApplicationContext(),CalendarActivity.class);
                    startActivity(mainPage);
                }
            }
        });


    }

}
