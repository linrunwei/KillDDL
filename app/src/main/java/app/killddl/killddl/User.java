package app.killddl.killddl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable{

    protected String email;
    protected int avatar;
    protected int taskCount;
//    protected List<Tasks> taskList = new ArrayList<Tasks>();

    //for addValue
    public User(){
    }
    public User(String email){
        this.email = email;
        this.taskCount = 0;
    }
    public User(String email, int avatar, int taskCount){
        this.email = email;
        this.avatar = avatar;
        this.taskCount = taskCount;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public int getAvatar(){
        return this.avatar;
    }
    public void setAvatar(int a){
        this.avatar = a;
    }
    public int getTaskCount() {
        return this.taskCount;
    }
    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
    }
    /*
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
    */

}
