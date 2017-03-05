package com.example.android.habittracker;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.android.habittracker.data.HabitTrackerContract.HabitTrackerEntry;
import com.example.android.habittracker.data.HabitTrackerHelper;

public class MainActivity extends AppCompatActivity {
    public static final String LOG_TAG = MainActivity.class.getSimpleName();
    private HabitTrackerHelper mHabitTrackerHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mHabitTrackerHelper = new HabitTrackerHelper(this);
        mHabitTrackerHelper.insertHabit("Walking", "Walking for fun", HabitTrackerEntry.WEEKLY);
        mHabitTrackerHelper.insertUser("Yadira", "Alvarez", "Female");
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayTableCountInfo();
    }

    private void displayTableCountInfo() {
        Cursor cursorHabits = mHabitTrackerHelper.readAllHabits();
        Cursor cursorUser = mHabitTrackerHelper.readAllUsers();
        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // tables in the database).
            String resultHabits = "Number of rows in habits database table: " + cursorHabits.getCount();
            String resultUser = "Number of rows in user database table: " + cursorUser.getCount();
            Log.e(LOG_TAG, resultHabits + " " + resultUser);
            moveCursor(cursorHabits);
            moveCursor(cursorUser);
        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursorHabits.close();
            cursorUser.close();
        }
    }

    private void moveCursor(Cursor cursor) {
        // Rows
        while (cursor.moveToNext()) {
            StringBuilder builder = new StringBuilder();
            // columns
            for (int i = 0; i < cursor.getColumnCount(); i++) {
                builder.append(cursor.getString(i));
            }
            Log.e(LOG_TAG, builder.toString());
        }
    }
}
