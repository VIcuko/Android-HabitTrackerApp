package com.example.android.habittrackerapp;

import android.content.ContentValues;
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
    }

    private void insertHabit(){
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(HabitEntry.COLUMN_HABIT_NAME, "Learn Spanish up to level B2");
        values.put(HabitEntry.COLUMN_HABIT_FREQUENCY, "M,W,F");
        values.put(HabitEntry.COLUMN_HABIT_DAYS_COMPLETED, 4);
        values.put(HabitEntry.COLUMN_HABIT_DAYS_OBJECTIVE, 7);
        values.put(HabitEntry.COLUMN_HABIT_REMINDER, HabitEntry.REMINDER_ACTIVE);

        long newRowId = db.insert(HabitEntry.TABLE_NAME, null, values);

        Log.v("MainActivity", "New row ID " + newRowId);
    }
}
