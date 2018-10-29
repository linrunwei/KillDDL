package app.killddl.killddl;

import com.google.firebase.Timestamp;
import com.google.type.Date;

import java.io.Serializable;
public class Tasks implements Serializable{
    protected int id;
    protected Boolean isFinished;
    protected String name;
    protected String description;
    protected Timestamp deadline;
    protected int priority;
    protected Timestamp notification;
    protected int color;
    protected int frequency = 0;
    protected Timestamp createTime;
    protected String date;
    protected String time;
    public Tasks(){

    }
    public Tasks(int id, Timestamp deadline){
        this.id = id;
        createTime = Timestamp.now();
        this.deadline = deadline;
        isFinished = false;
    }

    public int getId(){
        return this.id;
    }
    public Timestamp getCreateTime(){
        return this.createTime;
    }
    public void EditIsFinished(Boolean isFinished){
        this.isFinished = isFinished;
    }
    public Boolean getIsFinished(){
        return this.isFinished;
    }
    public void EditName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void EditDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return this.description;
    }
    public void EditDeadline(Timestamp deadline){
        this.deadline = deadline;
    }
    public Timestamp getDeadline(){
        return this.deadline;
    }
    public void EditPriority(int priority){
        this.priority = priority;
    }
    public int getPriority(){
        return this.priority;
    }
    public void EditNotification(Timestamp notification){
        this.notification = notification;
    }
    public Timestamp getNotification(){
        return this.notification;
    }
    public void EditColor(int color){
        this.color = color;
    }
    public int getColor(){
        return this.color;
    }
    public void EditFrequency(int frequency){
        this.frequency = frequency;
    }
    public int getFrequency(){
        return this.frequency;
    }

}
