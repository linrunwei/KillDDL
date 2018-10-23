package app.killddl.killddl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import com.google.firebase.Timestamp;

public class User implements Serializable{
    protected String name;
    protected String password;
    protected Vector<Object> taskList;

    public User(){
    }
    public User(String name, String password){
        this.name = name;
        this.password = password;
    }

    public String getName(){
        return this.name;
    }
    public void setPassword(String ps){
        this.password = ps;
    }
    public void addTask(Tasks task){
        this.taskList.add(task);
    }

    public Vector<Object> getTaskList(){
        return this.taskList;
    }
    public Vector<Tasks> getTaskListByTime(Timestamp tsp){
        Vector<Tasks> selected = new Vector<>();
        for(Object t : this.taskList){
            Timestamp curr = ((Tasks) t).getDeadline();

            if(curr.toDate() == tsp.toDate()){
                selected.add((Tasks) t);
            }
        }
        return selected;
    }

}
