package com.example.android.habittracker.data;

import android.provider.BaseColumns;


public class HabitTrackerContract {

    //An empty private constructor makes sure that the class is not going to be initialised.
    private HabitTrackerContract() {
    }

    public static abstract class UserInformationEntry implements BaseColumns {
        public static final String TABLE_NAME_USER_INFO = "UserInformation";
        public static final String COLUMN_USER_NAME = "Name";
        public static final String COLUMN_USER_LAST_NAME = "LastName";
        public static final String COLUMN_GENDER = "Gender";

    }

    public static abstract class HabitTrackerEntry implements BaseColumns {
        public static final String TABLE_NAME_HABITS = "Habits";
        public static final String HABIT = "Habit";
        public static final String HABIT_DESCRIPTION = "HabitDescription";
        public static final String FREQUENCY = "Frequency";

        //Possible values for frequency
        public static final int DAILY = 1;
        public static final int WEEKLY = 2;
        public static final int MONTHLY = 3;
    }
}
