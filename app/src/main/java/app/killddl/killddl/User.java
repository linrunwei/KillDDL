package app.killddl.killddl;

import java.util.ArrayList;
import java.util.List;

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
    public List<Tasks> getTaskList(){
        return this.taskList;
    }

}
