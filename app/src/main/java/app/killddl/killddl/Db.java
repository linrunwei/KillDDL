package app.killddl.killddl;

import android.support.annotation.NonNull;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Vector;
public class Db {
    //save current user in DB class
    private User user;
    private Vector<String> userlist = new Vector<>();
    private FirebaseFirestore db;
    TextView errorMsg;
    public Db(){
        db = FirebaseFirestore.getInstance();
    }
    public void setErrorMsgMain(TextView em){
        this.errorMsg = em;
    }

    public User getUser(){
        return this.user;
    }
    public void setUser(User u){
        this.user = u;
    }
    public Vector<String> getAllUserName(){
        CollectionReference userRef = db.collection("User");

        userRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    System.out.println("ok");
                    for(DocumentSnapshot document : task.getResult()){
                        User curr = document.toObject(User.class);
                        System.out.println(curr.getName());
                        userlist.add(curr.getName());
                        System.out.println(userlist.size());
                    }

                }
//                isComplete = true;
            }
        });
        try {
            Thread.sleep(1000);
        }
        catch (Exception e){

        }
        while(userlist.isEmpty()){
            System.out.println("wtf");
        }
        return userlist;
    }
    public FirebaseFirestore getDB(){
        return this.db;
    }
    public boolean addUser(User user){
//        Vector<String> allusers = this.getAllUserName();
//        for(String name: allusers){
//            if(user.getName().equals(name))
//                return false;
//        }
        this.db.collection("User").document(user.getName()).set(user);
        return true;
    }
    public void addTask(Tasks task){
        user = MainActivity.getUser();
        user.getTaskList().add(task);
        addUser(user);
    }

    public void removeTask(Tasks task){
        this.user.getTaskList().remove(task);
        addUser(user);
    }
}
