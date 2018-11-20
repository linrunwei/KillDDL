package app.killddl.killddl;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


import site.gemus.openingstartanimation.OpeningStartAnimation;

public class MainActivity extends AppCompatActivity {

    LinearLayout loginLayout;
    AnimationDrawable animationDrawable;
    TextView errorMsg;
    static Db dbase = new Db();
    static private FirebaseAuth mAuth;
    EditText userEmail, userPassword;
    FirebaseDatabase database;
    DatabaseReference myRef;
    DocumentReference myDocRef;

    private CallbackManager mCallbackManager;

/*
    static void setUser(User u) {
        user = u;
        System.out.println(user.getTaskList().size());
    }

    static User getUser() {
        return user;
    }

    static void deleteUser() {
        user = null;
    }
    */

    static FirebaseAuth getAuth() {
        return mAuth;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        View view = this.getCurrentFocus();
        //close keyboard
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);

        //DATABASE
        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("message");
        //TODO if user didnt quit, automatically logged in


        //UI elements
        userEmail = (EditText) findViewById(R.id.login_username);
        userPassword = (EditText) findViewById(R.id.login_password);
        loginLayout = (LinearLayout) findViewById(R.id.layout_main);

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
            } else {
                Toast.makeText(this, "createNotificationChannel failed", Toast.LENGTH_SHORT).show();
            }
        }
/*
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });
        */


        //FB Login:
        // Initialize Facebook Login button
        mCallbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = findViewById(R.id.fb_login_button);
        loginButton.setReadPermissions("email", "public_profile");
        loginButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d("App log:", "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d("App log:", "facebook:onCancel");
                // ...
            }

            @Override
            public void onError(FacebookException error) {
                Log.d("App log:", "facebook:onError", error);
                // ...
            }
        });
// ...


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d("App log:", "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("App log:", "signInWithCredential:success");
                            completeLogin(mAuth.getCurrentUser());
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("App log:", "signInWithCredential:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onStart() {
        super.onStart();

        OpeningStartAnimation openAnime = new OpeningStartAnimation.Builder(this).
                setAppIcon(ContextCompat.getDrawable(getApplicationContext(),R.drawable.open_logo)).create();
        openAnime.show(this);
        // Check if user is signed in (non-null) and update UI accordingly.
        completeLogin(mAuth.getCurrentUser());
    }

    public void SignUp(View v){
        RegisterUser();
    }

    private void RegisterUser(){

        //getting email and password from edit texts
        final String email = userEmail.getText().toString().trim();
        final String password  = userPassword.getText().toString().trim();
//        System.out.println("Email: " + email + " password:" + password);

        //checking if email and passwords are empty
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog


        //creating a new user
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //checking if success
                        if (task.isSuccessful()) {
                            //display some message here
                            Toast.makeText(MainActivity.this, "Successfully registered", Toast.LENGTH_LONG).show();
                            FirebaseUser currUser = mAuth.getCurrentUser();
                            String uid = "";
                            if (currUser != null) {
                                uid = currUser.getUid();
                            }
                            dbase.setID(uid);
                            dbase.setTaskList(new ArrayList<Tasks>());
                            User user = new User(email);
                            myDocRef = dbase.getDB().collection("User").document(currUser.getUid());
                            myDocRef.set(user);
                            System.out.println("succesfully saved");

                        } else {
                            //display some message here

                        }
                    }
                });
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof FirebaseAuthException) {
                            ((FirebaseAuthException) e).getErrorCode();
                            //System.out.println(((FirebaseAuthException) e).getErrorCode());
                            Toast.makeText(MainActivity.this, ((FirebaseAuthException) e).getErrorCode(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void UserLogin() {
        final String email = userEmail.getText().toString().trim();
        final String password = userPassword.getText().toString().trim();

        //checking if email and passwords are empty
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            return;
        }


        //logging in the user
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //if the task is successfull
                        if (task.isSuccessful()) {
                            //start the profile activity
                            final FirebaseUser currUser = mAuth.getCurrentUser();
                            String uid = "";
                            if (currUser != null) {
                                uid = currUser.getUid();
                            }
                            dbase.setID(uid);
//                            final User user = new User(email);


                            dbase.getDB().collection("User").document(currUser.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                @Override
                                public void onSuccess(DocumentSnapshot documentSnapshot) {
                                    final User loginUser = documentSnapshot.toObject(User.class);
                                    dbase.getDB().collection("User").document(currUser.getUid()).collection("taskList")
                                            .get()
                                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                                @Override
                                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                                    if (task.isSuccessful()) {
                                                        List<Tasks> tasksList = new ArrayList<>();
                                                        HashMap<String, Integer> finishedTasks = new HashMap<>();
                                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                                            Tasks t = document.toObject(Tasks.class);
                                                            if(t.isFinished){
                                                                String date = timestampToString(t.finishTime);
                                                                if(finishedTasks.get(date) == null)
                                                                    finishedTasks.put(date, 1);
                                                                else
                                                                    finishedTasks.put(date, finishedTasks.get(date)+1);

                                                            }
                                                            tasksList.add(t);
                                                        }
                                                        User user = new User(email, loginUser.getAvatar());
                                                        dbase.setUser(user);
                                                        dbase.setTaskList(tasksList);
                                                        dbase.setFinishedTasks(finishedTasks);
                                                        finish();
                                                        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                                                        intent.putExtra("menuState", "daily");
                                                        startActivity(intent);
                                                    }

                                                }
                                            });
                                }
                            });

                        }
                    }
                });

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof FirebaseAuthException) {
                            ((FirebaseAuthException) e).getErrorCode();
                            Toast.makeText(MainActivity.this, ((FirebaseAuthException) e).getErrorCode(), Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

    public void ForgetPassword(View v) {
        Intent intent = new Intent(getApplicationContext(), ForgetPasswordActivity.class);
        startActivity(intent);
    }

    public void SignIn(View v) {
        UserLogin();
    }

    static Db getDatabase() {
        return dbase;
    }

    static void quit() {
        mAuth.signOut();
    }

    static String timestampToString(Timestamp t) {
        Date date = t.toDate();
        String pattern = "yyyy/MM/dd";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        System.out.println(format.format(date));
        return format.format(date);
    }

    private void completeLogin(final FirebaseUser currUser) {
        String uid = "";
        if (currUser != null) {
            uid = currUser.getUid();

            dbase.setID(uid);

            dbase.getDB().collection("User").document(currUser.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    final User loginUser = documentSnapshot.toObject(User.class);
                    dbase.getDB().collection("User").document(currUser.getUid()).collection("taskList")
                            .get()
                            .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                @Override
                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                    if (task.isSuccessful()) {
                                        List<Tasks> tasksList = new ArrayList<>();
                                        HashMap<String, Integer> finishedTasks = new HashMap<>();
                                        for (QueryDocumentSnapshot document : task.getResult()) {
                                            Tasks t = document.toObject(Tasks.class);
                                            if (t.isFinished) {
                                                String date = timestampToString(t.finishTime);
                                                if (finishedTasks.get(date) == null)
                                                    finishedTasks.put(date, 1);
                                                else
                                                    finishedTasks.put(date, finishedTasks.get(date) + 1);

                                            }
                                            tasksList.add(t);
                                        }
                                        User user = new User(loginUser.getEmail(), loginUser.getAvatar());
                                        dbase.setUser(user);
                                        dbase.setTaskList(tasksList);
                                        dbase.setFinishedTasks(finishedTasks);
                                        finish();
                                        Intent intent = new Intent(getApplicationContext(), MenuActivity.class);
                                        intent.putExtra("menuState", "daily");
                                        startActivity(intent);
                                    }

                                }
                            });
                }
            });
        }

    }
}
