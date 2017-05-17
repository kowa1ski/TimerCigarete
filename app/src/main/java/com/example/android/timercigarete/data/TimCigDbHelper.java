package com.example.android.timercigarete.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Usuario on 17/05/2017.
 */

public class TimCigDbHelper extends SQLiteOpenHelper {

    private static final String TAG = "TimCigDbHelper";

    //** Name of the database file */
    private static final String DATABASE_NAME = "trakcig.db";

    /*
    Database version. If you change the database schema, you must
    increment the database version.
     */
    private static final int  DATABASE_VERSION = 1;

    public TimCigDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is called for the first time
     * @param db is variable for database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Create a String that contains the SQL statements to create the table
        String SQL_CREATE_TRAKCIG_TABLE = "CREATE TABLE" + TimCigContract.TimCigEntry.TABLE_NAME +
                " (" + TimCigContract.TimCigEntry._ID + " INTEGER PRIMARE KEY AUTOINCREMENT, "
                + TimCigContract.TimCigEntry.COLUM_PLACE + " TEXT NOT NULL, "
                +TimCigContract.TimCigEntry.COLUM_TIME + " INTEGER NOT NULL DEFAULT 0);";

        // Execute the SQL satement
        db.execSQL(SQL_CREATE_TRAKCIG_TABLE);

    }

    /**
     * This is called when the database needs to be upgrade.
     * @param db variable for database
     * @param oldVersion will be a old version
     * @param newVersion will be a new version
     */

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is stull at version 1, so there´s
        // notheng to do done here.

    }
}
