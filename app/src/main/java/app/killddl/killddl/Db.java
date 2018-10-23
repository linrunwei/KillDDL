package app.killddl.killddl;

import com.google.firebase.firestore.FirebaseFirestore;
import java.util.Vector;
public class Db {
    //save current user in DB class
    private User user;
    private FirebaseFirestore db;
    public Db(){
        db = FirebaseFirestore.getInstance();
    }
    public User getUser(){
        return this.user;
    }
    public void setUser(User u){
        this.user = u;
    }
    public FirebaseFirestore getDB(){
        return this.db;
    }
    public void addUser(User user){
        this.db.collection("User").document(user.getName()).set(user);
    }
    public void addTask(Tasks task){
        this.user.getTaskList().add(task);
        addUser(user);
    }
}
