package com.example.android.habittrackerapp.data;

import android.provider.BaseColumns;

/**
 * Created by Vicuko on 22/8/17.
 */

public class HabitContract {
    public static abstract class HabitEntry implements BaseColumns {

        public static final String TABLE_NAME = "habits";

        public static final String _ID = BaseColumns._ID;
        public static final String COLUMN_HABIT_NAME = "name";
        public static final String COLUMN_HABIT_FREQUENCY = "frequency";
        public static final String COLUMN_HABIT_DAYS_OBJECTIVE = "objective";
        public static final String COLUMN_HABIT_DAYS_COMPLETED = "completed";
        public static final String COLUMN_HABIT_REMINDER = "reminder";

        /*
        Possible status for the reminder
         */
        public static final int REMINDER_ACTIVE = 0;
        public static final int REMINDER_INACTIVE = 1;
    }
}
