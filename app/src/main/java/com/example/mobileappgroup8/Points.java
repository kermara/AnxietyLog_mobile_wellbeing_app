package com.example.mobileappgroup8;

import android.annotation.SuppressLint;

import androidx.annotation.NonNull;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * returns Points Object and its methods
 *
 * @author Kerttuli
 * created in 10.3.2020
 */
public class Points {

    private String points;
    private String date;
    private String result;

    public Points() {
    }

    /**
     * @param points
     * @param date
     * @param result
     */

    public Points(String points, String date, String result) {
        this.points = points;
        this.date = date;
        this.result = result;
    }

    /**
     * @return points
     */

    public String getPoints() {
        return points;
    }

    /**
     * @return date
     */

    public String getDate() {
        return date;
    }

    /**
     * @return result
     */

    public String getResult() {
        return result;
    }

    /**
     * @return sets new result
     */

    public String setResult(String setResult) {
        return setResult;
    }

    /**
     * @return returns new points
     */

    public String getNewPoints(String newPoints) {
        return newPoints;
    }

    /**
     * @return sets dates and formats it
     */

    public String getNewDate() {
        @SuppressLint("SimpleDateFormat") DateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date currentDate = new Date();
        String newDate = format.format(currentDate);
        return newDate;
    }

    /**
     * @return sets result in text
     */

    public String getNewResult(String newResult) {
        setResult(newResult);
        int testPoint = Integer.valueOf(newResult);
        if (testPoint <= 4) {
            return ("No anxiety");
        } else if (testPoint >= 5 && testPoint <= 9) {
            return ("Mild anxiety");
        } else if (testPoint >= 10 && testPoint <= 14) {
            return ("Moderate anxiety");
        } else if (testPoint > 15) {
            return ("Severe anxiety");
        }
        return newResult;
    }

    /**
     * @return toString();
     */

    @NonNull
    @Override
    public String toString() {
        return super.toString();
    }
}