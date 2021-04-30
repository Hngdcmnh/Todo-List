package com.example.testapp;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
    static int id;
    private String name;
    private String target;
    private Date dueDate;
    private String idTask;
    private boolean status = false;

    public Task(String name, String target, Date dueDate) {
        this.name = name;
        this.target = target;
        this.dueDate = dueDate;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        id++;
        this.setIdTask( String.valueOf(id) +"_"+ dateFormat.format(dueDate));
    }

    public Task(String name, String target, Date dueDate, String idTask) {
        this.name = name;
        this.target = target;
        this.dueDate = dueDate;
        this.idTask = idTask;

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        id++;
        this.setIdTask( String.valueOf(id) +"_"+ dateFormat.format(dueDate));
    }

    public Task() {
//        this.name = name;
//        this.target = target;
//        this.dueDate = dueDate;
//        this.idTask = idTask;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        id++;
//        this.setIdTask( String.valueOf(id) +"_"+ dateFormat.format(dueDate));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getIdTask() {
        return idTask;
    }

    public void setIdTask(String idTask) {
        this.idTask = idTask;
    }

    public static int getId() {
        return id;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
