package app.killddl.killddl;

import java.util.ArrayList;
import java.util.List;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.*;
import com.google.android.gms.tasks.*;
import android.support.annotation.*;

public class User {
    protected String name;
    //protected String password;
    protected List<Tasks> taskList = new ArrayList<Tasks>();

    public User(String name, String password){
        this.name = name;
        //this.password = password;
    }
    public String getName(){
        return this.name;
    }
    public void addTask(Tasks task){
        this.taskList.add(task);
    }
    public List<Tasks> getTaskList(Timestamp ts){
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference taskRef = db.collection("Task");
        taskRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    taskList = task.getResult().toObjects(Tasks.class);
                }
            }
        });

    public List<Tasks> getTaskList(){
        return this.taskList;
    }

}
