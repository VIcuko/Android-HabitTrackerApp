package com.example.android.habittrackerapp;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.habittrackerapp.data.HabitContract.HabitEntry;
import com.example.android.habittrackerapp.data.HabitDbHelper;

public class MainActivity extends AppCompatActivity {

    private HabitDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDbHelper = new HabitDbHelper(this);
    }

    private void insertHabit() {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, "Learn Spanish up to level B2");
        values.put(HabitEntry.COLUMN_HABIT_FREQUENCY, "M,W,F");
        values.put(HabitEntry.COLUMN_HABIT_DAYS_OBJECTIVE, 30);
        values.put(HabitEntry.COLUMN_HABIT_DAYS_COMPLETED, 4);
        values.put(HabitEntry.COLUMN_HABIT_REMINDER, HabitEntry.REMINDER_ACTIVE);

        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);

        Log.v("MainActivity", "New row ID " + newRowId);
    }

    private String selectHabits() {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                HabitEntry._ID,
                HabitEntry.COLUMN_HABIT_NAME,
                HabitEntry.COLUMN_HABIT_FREQUENCY,
                HabitEntry.COLUMN_HABIT_DAYS_OBJECTIVE,
                HabitEntry.COLUMN_HABIT_DAYS_COMPLETED,
                HabitEntry.COLUMN_HABIT_REMINDER
        };

        String selection = HabitEntry.COLUMN_HABIT_REMINDER + "=?";
        String[] selectionArgs = {"1"};

        String sortOrder =
                HabitEntry.COLUMN_HABIT_DAYS_COMPLETED + " ASC";

        Cursor cursor = db.query(
                HabitEntry.TABLE_NAME,
                projection,
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        int idColumnIndex = cursor.getColumnIndex(HabitEntry._ID);
        int nameColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_NAME);
        int frequencyColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_FREQUENCY);
        int objectiveColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DAYS_OBJECTIVE);
        int completedColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_DAYS_COMPLETED);
        int reminderColumnIndex = cursor.getColumnIndex(HabitEntry.COLUMN_HABIT_REMINDER);

        String results = "The results for the query are: \n";

        while (cursor.moveToNext()) {
            // Use that index to extract the String or Int value of the word
            // at the current row the cursor is on.
            int currentID = cursor.getInt(idColumnIndex);
            String currentName = cursor.getString(nameColumnIndex);
            String currentFrequency = cursor.getString(frequencyColumnIndex);
            int currentObjective = cursor.getInt(objectiveColumnIndex);
            int currentCompleted = cursor.getInt(completedColumnIndex);
            int currentReminder = cursor.getInt(reminderColumnIndex);

            // Display the values from each column of the current row in the cursor in the TextView
            results.concat("\n" + currentID + " - " +
                    currentName + " - " +
                    currentFrequency + " - " +
                    currentObjective + " - " +
                    currentCompleted + " - " +
                    currentReminder);
        }

        cursor.close();
        return results;
    }
}
