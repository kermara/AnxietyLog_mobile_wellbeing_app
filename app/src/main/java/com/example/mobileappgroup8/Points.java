package com.example.mobileappgroup8;

import androidx.annotation.NonNull;

import java.util.Date;

public class Points {

    private String points;
    private Date date;

    public Points(String points, Date date) {
        this.points = points;
        this.date = date;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}