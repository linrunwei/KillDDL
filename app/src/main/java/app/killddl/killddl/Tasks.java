package app.killddl.killddl;

import android.graphics.Color;

import com.google.firebase.Timestamp;


public class Tasks {
    protected int id;
    protected Boolean isFinished;
    protected String name;
    protected String description;
    protected Timestamp deadline;
    protected int priority;
    protected Timestamp notification;
    protected Color color;
    protected Enum frequency;
    protected Timestamp createTime;

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
    public void EditColor(Color color){
        this.color = color;
    }
    public Color getColor(){
        return this.color;
    }
    public void EditFrequency(Enum frequency){
        this.frequency = frequency;
    }
    public Enum getFrequency(){
        return this.frequency;
    }
}
