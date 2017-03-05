package com.example.android.habittracker.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.android.habittracker.data.HabitTrackerContract.HabitTrackerEntry;
import com.example.android.habittracker.data.HabitTrackerContract.UserInformationEntry;


public class HabitTrackerHelper extends SQLiteOpenHelper {
    //Name of the database file.
    public static final String DATABASE_NAME = "HabitTracker.db";

    //Database version. If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;

    public HabitTrackerHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table.
        String SQL_CREATE_HABIT_TRACKER_TABLE = "CREATE TABLE " + HabitTrackerEntry.TABLE_NAME_HABITS
                + "("
                + HabitTrackerEntry._ID + " INTEGER" + " PRIMARY KEY" + " AUTOINCREMENT,"
                + HabitTrackerEntry.HABIT + " TEXT" + " NOT NULL,"
                + HabitTrackerEntry.HABIT_DESCRIPTION + " TEXT,"
                + HabitTrackerEntry.FREQUENCY + " TEXT" + " DEFAULT MONTHLY"
                + ");";

        String SQL_CREATE_USER_INFORMATION_TABLE = "CREATE TABLE " + UserInformationEntry.TABLE_NAME_USER_INFO
                + "("
                + UserInformationEntry._ID + " INTEGER" + " PRIMARY KEY" + " AUTOINCREMENT,"
                + UserInformationEntry.COLUMN_USER_NAME + " TEXT" + " NOT NULL,"
                + UserInformationEntry.COLUMN_USER_LAST_NAME + " TEXT" + " NOT NULL,"
                + UserInformationEntry.COLUMN_GENDER + " TEXT" + " NOT NULL"
                + ");";

        db.execSQL(SQL_CREATE_HABIT_TRACKER_TABLE);
        db.execSQL(SQL_CREATE_USER_INFORMATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }

    public Cursor readAllHabits() {
        SQLiteDatabase db = getReadableDatabase();
        // Create a cursor object that selects all records from habits table.
        Cursor cursorHabits = db.rawQuery("SELECT * FROM " + HabitTrackerEntry.TABLE_NAME_HABITS, null);
        return cursorHabits;
    }

    public Cursor readAllUsers() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursorUsers = db.rawQuery("SELECT * FROM " + UserInformationEntry.TABLE_NAME_USER_INFO, null);
        return cursorUsers;
    }

    public long insertHabit(String habit, String description, String frequency) {
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a ContentValues object where column names are the keys.
        ContentValues valuesHabits = new ContentValues();
        valuesHabits.put(HabitTrackerEntry.HABIT, habit);
        valuesHabits.put(HabitTrackerEntry.HABIT_DESCRIPTION, description);
        valuesHabits.put(HabitTrackerEntry.FREQUENCY, frequency);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(HabitTrackerEntry.TABLE_NAME_HABITS, null, valuesHabits);
        return newRowId;
    }

    public long insertUser(String name, String lastName, String gender) {
        // Gets the data repository in write mode
        SQLiteDatabase db = getWritableDatabase();

        // Create a ContentValues object where column names are the keys,
        ContentValues valuesUser = new ContentValues();
        valuesUser.put(UserInformationEntry.COLUMN_USER_NAME, name);
        valuesUser.put(UserInformationEntry.COLUMN_USER_LAST_NAME, lastName);
        valuesUser.put(UserInformationEntry.COLUMN_GENDER, gender);

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(UserInformationEntry.TABLE_NAME_USER_INFO, null, valuesUser);
        return newRowId;
    }
}
