package com.example.testapp;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ModelTask {
    private Date choosedDate;
    static ModelTask Instance = null;
    private ArrayList<Task> taskList;
    private SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy");
    private ModelTask()
    {
        taskList = new ArrayList<>();
        choosedDate =new Date(System.currentTimeMillis());
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    public void setTaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    public static ModelTask getInstance()
    {
        if(Instance==null)
        {
            Instance = new ModelTask();
        }
        return Instance;
    }

    public ArrayList<Task> getDayList(Date date)
    {
        ArrayList<Task> dayList = new ArrayList<>();
        for(Task a : taskList) {
            if (a.getDueDate().toString().substring(0, 10).equals(date.toString().substring(0, 10))) {
                dayList.add(a);
            }
        }
        return dayList;

    }

    public ArrayList<Task> getMonthList(Date date)
    {
        ArrayList <Task> monthList = new ArrayList<>();
        for(Task a: taskList)
        {
            if(a.getDueDate().toString().substring(4, 8).equals(date.toString().substring(4, 8)))
            {
                monthList.add(a);
            }
        }
        return monthList;
    }

    public ArrayList<Task> getWeekList(Date date)
    {
        ArrayList <Task> weekList = new ArrayList<>();

        int idStart;
        int idFinish;
        Date currentDate = new Date(System.currentTimeMillis());
        switch (currentDate.toString().substring(0, 3)) {
            case "Mon": {
                idStart=0;
                idFinish=6;
            }
            break;
            case "Tue": {
                idStart=-1;
                idFinish=5;
            }
            break;
            case "Wed": {
                idStart=-2;
                idFinish=4;
            }
            break;
            case "Thu": {
                idStart=-3;
                idFinish=3;
            }
            break;
            case "Fri": {
                idStart=-4;
                idFinish=2;
            }
            break;
            case "Sat": {
                idStart=-5;
                idFinish=1;
            }
            break;
            case "Sun": {
                idStart=-6;
                idFinish=0;
            }
            break;
            default:
                idStart=0;
                idFinish=0;
        }

        for(int i=idStart;i<=idFinish;i++)
        {
            Calendar calendar1 = Calendar.getInstance();
            calendar1.add(Calendar.DAY_OF_YEAR, i);
            Date nextDay = calendar1.getTime();
            weekList.addAll(ModelTask.getInstance().getDayList(nextDay));
        }
//        Log.e("bug", weekList.size()+"" );
        return weekList;
//        ArrayList <Task> monthList = new ArrayList<>();
//        for(Task a: taskList)
//        {
//            if(a.getDueDate().toString().substring(4, 8).equals(date.toString().substring(4, 8)))
//            {
//                monthList.add(a);
//            }
//        }
//        return monthList;
    }

    public void addTask(Task newTask)
    {
        this.taskList.add(newTask);
    }

    public Date getChoosedDate() {
        return choosedDate;
    }

    public void setChoosedDate(Date choosedDate) {
        this.choosedDate = choosedDate;
    }
}
