package com.example.android.habittrackerapp.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.habittrackerapp.data.HabitContract.HabitEntry;

/**
 * Created by Vicuko on 22/8/17.
 */

public class HabitDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "shelter.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + HabitEntry.TABLE_NAME+ " (" +
                    HabitEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    HabitEntry.COLUMN_HABIT_NAME + " TEXT NOT NULL," +
                    HabitEntry.COLUMN_HABIT_FREQUENCY + " TEXT," +
                    HabitEntry.COLUMN_HABIT_DAYS_OBJECTIVE + " INTEGER NOT NULL," +
                    HabitEntry.COLUMN_HABIT_DAYS_COMPLETED + " INTEGER NOT NULL DEFAULT 0)," +
                    HabitEntry.COLUMN_HABIT_REMINDER+ " INTEGER NOT NULL DEFAULT 0);";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + HabitEntry.TABLE_NAME;

    public HabitDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
}
