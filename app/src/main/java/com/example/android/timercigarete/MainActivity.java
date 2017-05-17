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
import android.widget.TextView;

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

        TextView displayView = (TextView) findViewById(R.id.ponerTextoAqui);

        try {
            // Create a header in the TextView.
            // The table contains <number of rows in Cursor>.

            //In the while loop below, iterate through the rows of the cursor and display
            // the information from each colum in this order.
            displayView.setText("the table contains "+ cursor.getCount() + " cigarettes.\n\n");
            displayView.append(TimCigContract.TimCigEntry._ID + " - " +
                    TimCigContract.TimCigEntry.COLUM_PLACE + " - " +
                    TimCigContract.TimCigEntry.COLUM_TIME + "\n");

            // Figure out the index of each colum
            int idColumIndex = cursor.getColumnIndex(TimCigContract.TimCigEntry._ID);
            int placeColumIndex = cursor.getColumnIndex(TimCigContract.TimCigEntry.COLUM_PLACE);
            int timeColumIndex = cursor.getColumnIndex(TimCigContract.TimCigEntry.COLUM_TIME);

            // Iterate trough all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or int value of the word
                // at the current row the cursor in on.
                int currentID = cursor.getInt(idColumIndex);
                String currentPlace = cursor.getString(placeColumIndex);
                int currentTime = cursor.getInt(timeColumIndex);

                //Display the values from each column of the current row in the cursor
                displayView.append(
                        "\n" +
                                currentID + " - " +
                                currentPlace + " - " +
                                currentTime
                );
            }
        } finally {
            //Allways close the cursor if you have done all things allready.
            cursor.close();

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
