package app.killddl.killddl;

import com.google.firebase.Timestamp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable{
    protected String name;
    protected String password;
    protected List<Object> taskList = new ArrayList<Object>();

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

    public List<Object> getTaskList(){
        return this.taskList;
    }
    public List<Tasks> getTaskListByTime(Timestamp tsp){
        List<Tasks> selected = new ArrayList<>();
        for(Object t : this.taskList){
            Timestamp curr = ((Tasks) t).getDeadline();

            if(curr.toDate() == tsp.toDate()){
                selected.add((Tasks) t);
            }
        }
        return selected;
    }

}
