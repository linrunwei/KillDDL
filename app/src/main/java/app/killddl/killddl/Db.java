package app.killddl.killddl;

import com.google.firebase.firestore.FirebaseFirestore;

public class Db {
    private FirebaseFirestore db;
    public Db(){
        db = FirebaseFirestore.getInstance();
    }
    public FirebaseFirestore getDB(){
        return this.db;
    }
    public void addUser(User user){
        this.db.collection("User").document(user.getName()).set(user);
    }
    public void addTask(Tasks task){
        this.db.collection("Tasks").document(task.getName()+task.getId()).set(task);
    }
    
}
