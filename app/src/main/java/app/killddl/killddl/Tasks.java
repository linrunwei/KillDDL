package app.killddl.killddl;

import android.graphics.Color;

import com.google.firebase.Timestamp;

import java.io.Serializable;

public class Tasks implements Serializable{
    protected int id;
    protected Boolean isFinished;
    protected String name;
    protected String description;
    protected Timestamp deadline;
    protected Timestamp notification;
    protected int color;//1 is blue; 2 is red; 3 is yellow; 4 is purple; 5 is green
    //protected int recurringFrequency;//0 is None; 1 is Daily; 2 is Weekly; 3 is Monthly
    protected int notificationFrequency;//0 is None; 1 is Daily; 2 is Weekly; 3 is Monthly
    protected Timestamp createTime;
    public Tasks(){

    }
    public Tasks(int id, Timestamp createTime){
        this.id = id;
        this.createTime = createTime;
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
    /*
    public void EditRecurringFrequency(int recurringFrequency){
        this.recurringFrequency = recurringFrequency;
    }
    public int getRecurringFrequency(){
        return this.recurringFrequency;
    }*/
    public void EditNotificationFrequency(int notificationFrequency){
        this.notificationFrequency = notificationFrequency;
    }
    public int getNotificationFrequency(){
        return this.notificationFrequency;
    }
}
