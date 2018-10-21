package app.killddl.killddl;

import android.graphics.drawable.AnimationDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.LinearLayout;
import android.view.View;
import android.content.Intent;


public class MainActivity extends AppCompatActivity {

    LinearLayout loginLayout;
    AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginLayout = (LinearLayout) findViewById(R.id.loginLayout);
        animationDrawable = (AnimationDrawable) loginLayout.getBackground();
        animationDrawable.setEnterFadeDuration(4500);
        animationDrawable.setExitFadeDuration(4500);
        animationDrawable.start();

//        Button button = findViewById(R.id.test);
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(MainActivity.this, NotificationActivity.class));
//            }
//        });
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
}
