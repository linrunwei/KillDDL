package app.killddl.killddl;

import com.google.android.gms.tasks.Task;
import com.google.firebase.Timestamp;
import com.google.type.Date;

import java.io.Serializable;
public class Tasks implements Serializable{
    // member variables
    protected int id;
    protected int priority;
    protected int color;
    protected int frequency = -1;
    protected Boolean isFinished;
    protected String name;
    protected String description;
    protected String date;
    protected String time;
    protected Timestamp deadline;
    protected Timestamp createTime;
    protected Timestamp notification;
    protected Timestamp finishTime;

    // default constructor
    public Tasks(){}

    // override default constructor
    public Tasks(int id, Timestamp deadline){
        this.id = id;
        this.createTime = Timestamp.now();
        this.deadline = deadline;
        this.isFinished = false;
    }

    // copy constructor
    public Tasks(Tasks rhs) {
        this.id = rhs.id;
        this.isFinished = rhs.isFinished;
        this.name = rhs.name;
        this.description = rhs.description;
        this.deadline = rhs.deadline;
        this.priority = rhs.priority;
        this.notification = rhs.notification;
        this.color = rhs.color;
        this.frequency = rhs.frequency;
        this.createTime = rhs.createTime;
        this.date = rhs.date;
        this.time = rhs.time;
    }

    // setters
    public void EditName(String name){
        this.name = name;
    }
    public void EditIsFinished(Boolean isFinished){
        this.isFinished = isFinished;
    }
    public void EditDescription(String description){
        this.description = description;
    }
    public void EditDeadline(Timestamp deadline){
        this.deadline = deadline;
    }
    public void EditPriority(int priority){
        this.priority = priority;
    }
    public void EditNotification(Timestamp notification){
        this.notification = notification;
    }
    public void EditColor(int color){
        this.color = color;
    }
    public void EditFrequency(int frequency){
        this.frequency = frequency;
    }
    public void EditFinishTime(Timestamp time) {this.finishTime = time;}

    // getters
    public int getId(){
        return this.id;
    }
    public int getPriority(){
        return this.priority;
    }
    public int getColor(){
        return this.color;
    }
    public int getFrequency(){
        return this.frequency;
    }
    public Boolean getIsFinished(){
        return this.isFinished;
    }
    public String getDescription(){
        return this.description;
    }
    public String getName(){
        return this.name;
    }
    public Timestamp getCreateTime(){
        return this.createTime;
    }
    public Timestamp getDeadline(){
        return this.deadline;
    }
    public Timestamp getNotification(){
        return this.notification;
    }
    public Timestamp getFinishTime() { return this.finishTime; }
}
