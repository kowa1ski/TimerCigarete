package com.example.android.timercigarete.data;

import android.provider.BaseColumns;

/**
 * Created by Usuario on 17/05/2017.
 */

public class TimCigContract {

    //To prevent someone accidentally instantiating the contract class
    //give it an empty constructor.

    private  TimCigContract(){}

    /**
     * Inner class that defines constant values for de tracks database table.
     * Each entry in the table represent a single track.
     * This app track the time spend while you smoke.
     */

    public static final class TimCigEntry implements BaseColumns {

        /** Name database */
        public static final String TABLE_NAME = "timecigarettes";

        /**
         * Unique ID number for de track one cigarette,
         * only for use in database table).
         *
         * Type: INTEGER
         */
        public final static String _ID = BaseColumns._ID;

        /**
         * place where you smoke last cigarette
         *
         * Type: TEXT
         */
        public final static String COLUM_PLACE = "place";

        /**
         * time you spend (in minutes)
         *
         * Type: Integer
         */
        public static final String COLUM_TIME = "minutes";



    }




}
