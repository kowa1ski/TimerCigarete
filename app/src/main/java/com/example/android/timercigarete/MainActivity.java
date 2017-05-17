package com.example.android.timercigarete;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.timercigarete.data.TimCigContract;
import com.example.android.timercigarete.data.TimCigDbHelper;

public class MainActivity extends AppCompatActivity {

    private TimCigDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        mDbHelper = new TimCigDbHelper(this);
    }

    private void displayDatabaseInfo() {
        // To access our database, we instantiate our subclass if SQLiteOpenHelper
        // and pass the context, which is the current activity.
        TimCigDbHelper mDbHelper = new TimCigDbHelper(this);

        // Create and /or open a databse to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String[] projection = {
                TimCigContract.TimCigEntry._ID,
                TimCigContract.TimCigEntry.COLUM_PLACE,
                TimCigContract.TimCigEntry.COLUM_TIME,
        };

        Cursor cursor = db.query(
                TimCigContract.TimCigEntry.TABLE_NAME, // the table to query
                projection, // the colums to return
                null, // the colums for the WHERE clause
                null, // the values for the WHERE clause
                null, // Dont group the rows
                null, // Dont filter by row groups
                null  // The sort order
        );

        try {
            // Create a header
        }



    }

    private void insertTrack() {
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(TimCigContract.TimCigEntry.COLUM_PLACE, "work");
        values.put(TimCigContract.TimCigEntry.COLUM_TIME, 6);

        long newRowID = db.insert(TimCigContract.TimCigEntry.TABLE_NAME, null, values);

        Log.v("MainActivity", "New Row ID " + newRowID);

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
