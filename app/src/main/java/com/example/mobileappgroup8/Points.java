package com.example.mobileappgroup8;

import androidx.annotation.NonNull;

import java.util.Date;

public class Points {

    private String points;
    private Date date;

    pprivate String points;
    private String date;
    private String result;

    public Points(String points, String date, String result) {
        this.points = points;
        this.date = date;
        this.result = result;
    }

    public String getPoints() {
        return points;
    }

    public String getDate() {
        return date;
    }

    public String getResult() {return result;}

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}