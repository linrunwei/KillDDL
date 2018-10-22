package app.killddl.killddl;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.*;
import com.google.firebase.Timestamp;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    LinearLayout loginLayout;
    AnimationDrawable animationDrawable;
    TextView errorMsg;
    Db database = new Db();
    FirebaseFirestore db = database.getDB();
    Vector<User> userlist = new Vector<>();
    public static boolean isComplete = false;
    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //animate background
        loginLayout = (LinearLayout) findViewById(R.id.loginLayout);
        //animationDrawable = (AnimationDrawable) loginLayout.getBackground();
        //animationDrawable.setEnterFadeDuration(2000);
        //animationDrawable.setExitFadeDuration(2000);
        //animationDrawable.start();

//        Button button = findViewById(R.id.test);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, NotificationActivity.class));
//            }
//        });
        errorMsg = (TextView) findViewById(R.id.login_errorMsg);
        // register notification channel
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager notificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
            String channelId = "due-now";
            CharSequence channelName = "Due Right Now";
            String description = "Notification for tasks that due right now.";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel notificationChannel = new NotificationChannel(channelId, channelName, importance);
            notificationChannel.setDescription(description);
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.WHITE);
            notificationChannel.enableVibration(true);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
            else {
                Toast.makeText(this, "createNotificationChannel failed", Toast.LENGTH_SHORT).show();
            }
        }

        //login button
        //TODO connect to database
        //FirebaseFirestore db = FirebaseFirestore.getInstance();
        //final CollectionReference userRef = db.collection("User");


//        final CollectionReference userRef = db.collection("User");
//        final List<User> current = new ArrayList<>();
        //Signin
        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText usernameEditText = (EditText) findViewById(R.id.login_username);
                EditText passwordEditText = (EditText) findViewById(R.id.login_password);

                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                System.out.println("username: " + username + " password: " + password); //TODO remove this after connection
                if(password.length() == 0){errorMsg.setText("Password cannot be empty!");}
                if(username.length() == 0){errorMsg.setText("Username cannot be empty!");}
                signinconnect(username, password);
                if(!userlist.isEmpty()){
                    user = userlist.get(0);
                }
                userlist.clear();

            }
        });
        //Signup
        Button signupBtn = (Button) findViewById(R.id.signupBtn);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText usernameEditText = (EditText) findViewById(R.id.login_username);
                EditText passwordEditText = (EditText) findViewById(R.id.login_password);
                String username = usernameEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                System.out.println("username: " + username + " password: " + password); //TODO remove this after connection
                if(password.length() == 0){errorMsg.setText("Password cannot be empty!");}
                if(username.length() == 0){errorMsg.setText("Username cannot be empty!");}

                User newUser = new User(username, password);
                database.addUser(newUser);
                signinconnect(username, password);
            }
        });

    }
    public void signinconnect(String un, String ps){
        SigninTask task = new SigninTask();
        task.execute(un, ps);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void SignUp(View view)
    {
        Intent intent = new Intent(MainActivity.this, SignupActivity.class);
        startActivity(intent);
    }

    public void JumpToNotification (View view) {
        startActivity(new Intent(MainActivity.this, NotificationActivity.class));
    }
    class SigninTask extends AsyncTask<String, Integer, Integer> {
        @Override
        protected Integer doInBackground(String... params) {

            try{
                String username = params[0];
                String ps = params[1];

                //Find user from db
                CollectionReference userRef = db.collection("User");
                Query user = userRef.whereEqualTo("name", username).whereEqualTo("password", ps);
                System.out.println("find finished");
                user.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            System.out.println("ok");
                            for(DocumentSnapshot document : task.getResult()){
                                User curr = document.toObject(User.class);
                                System.out.println(curr.getName());
                                userlist.add(curr);
                                System.out.println(userlist.size());
                            }

                        }
                        isComplete = true;
                    }
                });

                Thread.sleep(1000);
                while(!isComplete){

                }
                return userlist.size();

            } catch(Exception e) {
                e.printStackTrace();
                return 0;
            }
        }
        @Override
        protected void onPostExecute(Integer result) {
            System.out.println(result+"result");
            if(result == 1){
                errorMsg.setText("Successful!");
                //TODO jump to next page(menu page?)
                Intent newIntent = new Intent(getApplicationContext(), CalendarActivity.class);
                startActivity(newIntent);
            }

            else
                errorMsg.setText("Username/Password combination wrong!");
        }
        @Override
        protected void onPreExecute() {
            errorMsg.setText("waiting");
        }
    }
}
