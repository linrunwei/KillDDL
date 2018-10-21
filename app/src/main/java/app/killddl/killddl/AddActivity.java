package app.killddl.killddl;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.google.firebase.firestore.*;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
public class AddActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference userRef = db.collection("User");
        userRef.whereEqualTo("name", "runwei").whereEqualTo("password", "12345");

        Button loginBtn = (Button) findViewById(R.id.loginBtn);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText usernameEditText = (EditText) findViewById(R.id.login_username);
                //TODO add task
            }
        });

    }
}
