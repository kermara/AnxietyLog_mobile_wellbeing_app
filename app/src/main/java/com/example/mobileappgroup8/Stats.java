package com.example.mobileappgroup8;

import android.database.Cursor;
import android.widget.TextView;

import static com.example.mobileappgroup8.DatabaseHelper.DB_TABLE;
import static com.example.mobileappgroup8.DatabaseHelper.KEY_POINTS;

/**
 * Class is used for extraction of some values from database and calculation of simple descriptive statistics
 *
 * @author Pauli Vuolle-Apiala, Irina Konovalova
 * @version 1.1 3/2020
 */
public class Stats {

    /**
     * @param s the string to be converted to float
     * @return the converted float value
     */
    public static float stringToFloat(String s) {
        float f = Float.valueOf(s);
        return f;
    }

    /**
     * cursorLast moves to the last row of the database and selects entries from the points column.
     * The method converts the latest entry to a float and repeats the same to the second last entry.
     * Method calculates and returns the difference.
     *
     * @param db DatabaseHelper object is used to access the database
     * @return the difference between second last and last entry
     */
    public static float lastEntriesDifference(DatabaseHelper db) {
        String change = "SELECT(" + KEY_POINTS + ") FROM " + DB_TABLE;
        Cursor cursorLast = db.getDb().rawQuery(change, null);
        cursorLast.moveToLast();

        float lastEntryFloat = stringToFloat(cursorLast.getString(0));
        cursorLast.moveToPosition(cursorLast.getCount() - 2);
        float secondLastEntryFloat = stringToFloat(cursorLast.getString(0));
        float difference = lastEntryFloat - secondLastEntryFloat;
        return difference;
    }

    /**
     * cursorLast moves to the last row of the database and selects entries from the points column.
     * Cursor's getCount method is used to check if there are more than one entries in the database.
     *
     * @param db DatabaseHelper object is used to access the database
     * @return true if there are more than one entries in the database
     */
    public static boolean checkEntries(DatabaseHelper db) {
        String change = "SELECT(" + KEY_POINTS + ") FROM " + DB_TABLE;
        Cursor cursorLast = db.getDb().rawQuery(change, null);
        cursorLast.moveToLast();
        if (cursorLast.getCount() > 1) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * cursorAvg moves to the first row of the database and counts the average of the points column.
     * The method then stores the average points from the cursor to a string and rounds it up to 2 decimal points.
     * The method then converts the string to a float and returns it.
     *
     * @param db DatabaseHelper object is used to access the database
     * @return the average of the points column as a float
     */
    public static Float entriesAverage(DatabaseHelper db) {
        String average = "SELECT AVG(" + KEY_POINTS + ") FROM " + DB_TABLE;
        Cursor cursorAvg = db.getDb().rawQuery(average, null);
        cursorAvg.moveToFirst();
        String averagePointsString = String.format("%.2f", Float.valueOf(cursorAvg.getString(0)));
        Float averagePoints = Float.valueOf(averagePointsString);
        return averagePoints;
    }

    /**
     * This method categorises the points and displays the correct description based on the category
     *
     * @param points                 the result scores obtained from quiz
     * @param ThroughHistoryActivity determines where the method displays the description
     * @param a                      text view that displays the appropriate string
     * @param b                      text view that displays the appropriate string
     * @param c                      text view that displays the appropriate string
     */
    public static void whichAnxietyLevel(float points, boolean ThroughHistoryActivity, TextView a, TextView b, TextView c) {
        if (ThroughHistoryActivity == true) {
            c.setText(null);
            if (points <= 4) {
                a.setText("No anxiety");
            } else if (points >= 5 && points <= 9) {
                a.setText("Mild anxiety");
                b.setText("                     Monitor");
            } else if (points >= 10 && points < 15) {
                a.setText("Moderate anxiety");
                b.setText("Possible clinically significant condition");

            } else if (points >= 15) {
                a.setText("Severe anxiety");
                b.setText("Active treatment probably warranted");
            }
        } else {
            if (points <= 4) {
                a.setText("No anxiety");
            } else if (points >= 5 && points <= 9) {
                a.setText("Mild anxiety");
            } else if (points >= 10 && points < 15) {
                a.setText("Moderate anxiety");
            } else if (points > 15) {
                a.setText("Severe anxiety");
            }
        }
    }
}
