package com.example.testapp;

import java.util.Date;

public class DayBarItem {

    private Date dayItem;

    public DayBarItem() {
    }

    public DayBarItem(Date dayItem) {
        this.dayItem = dayItem;
    }

    public Date getDayItem() {
        return dayItem;
    }

    public void setDayItem(Date dayItem) {
        this.dayItem = dayItem;
    }
}

