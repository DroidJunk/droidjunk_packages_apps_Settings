/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.settings;

import android.database.Cursor;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;

public final class QuietTime implements Parcelable {

    //////////////////////////////
    // Parcelable apis
    //////////////////////////////
    public static final Parcelable.Creator<QuietTime> CREATOR
            = new Parcelable.Creator<QuietTime>() {
                public QuietTime createFromParcel(Parcel p) {
                    return new QuietTime(p);
                }

                public QuietTime[] newArray(int size) {
                    return new QuietTime[size];
                }
            };

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel p, int flags) {
    	
   	
    	
        p.writeInt(id);
        p.writeInt(enabled ? 1 : 0);
        p.writeInt(starthour);
        p.writeInt(startmin);
        p.writeInt(stophour);
        p.writeInt(stopmin);
        p.writeInt(ledon ? 1 : 0);
        p.writeInt(soundon ? 1 : 0);
        p.writeInt(vibrateon ? 1 : 0);
        p.writeInt(mode);
        p.writeInt(allow ? 1 : 0);
        p.writeInt(extra1);
        p.writeInt(extra2);
      
    }
    //////////////////////////////
    // end Parcelable apis
    //////////////////////////////

    //////////////////////////////
    // Column definitions
    //////////////////////////////
    public static class Columns implements BaseColumns {
        /**
         * The content:// style URL for this table
         */
        public static final Uri CONTENT_URI =
                Uri.parse("content://com.android.settings/quiettime");

        /**
         * True if quiettime is active
         * <P>Type: BOOLEAN</P>
         */
        public static final String ENABLED = "enabled";

        /**
         * Start Hour in 24-hour localtime 0 - 23.
         * <P>Type: INTEGER</P>
         */
        public static final String START_HOUR = "starthour";

        /**
         * Start Minutes in localtime 0 - 59
         * <P>Type: INTEGER</P>
         */
        public static final String START_MIN = "startmin";

        /**
         * Stop Hour in 24-hour localtime 0 - 23.
         * <P>Type: INTEGER</P>
         */
        public static final String STOP_HOUR = "stophour";

        /**
         * Stop Minutes in localtime 0 - 59
         * <P>Type: INTEGER</P>
         */
        public static final String STOP_MIN = "stopmin";


        /**
         * True if the led should be on
         * <P>Type: BOOLEAN</P>
         */
        public static final String LED_ON = "ledon";
        
        /**
         * True if the sound should be on
         * <P>Type: BOOLEAN</P>
         */
        public static final String SOUND_ON = "soundon";

        /**
         * True if vibration should be on
         * <P>Type: BOOLEAN</P>
         */
        public static final String VIBRATE_ON = "vibrateon";       
        
        /**
         * True if quiet is should be on now
         * <P>Type: INTEGER</P>
         */
        public static final String MODE = "mode";
        
        /**
         * True if allow notifications
         * <P>Type: BOOLEAN</P>
         */
        public static final String ALLOW = "allow";
        
        /**
         * Future Use
         * <P>Type: INTEGER</P>
         */
        public static final String EXTRA_1 = "extra1";
        
        /**
         * Future Use
         * <P>Type: INTEGER</P>
         */
        public static final String EXTRA_2 = "extra2";
        
        

        static final String[] QUIET_TIME_QUERY_COLUMNS = {
            _ID, ENABLED, START_HOUR, START_MIN, STOP_HOUR, STOP_MIN,
            LED_ON, SOUND_ON, VIBRATE_ON, MODE, ALLOW, EXTRA_1, EXTRA_2 };

        /**
         * These save calls to cursor.getColumnIndexOrThrow()
         * THEY MUST BE KEPT IN SYNC WITH ABOVE QUERY COLUMNS
         */
        public static final int QUIETTIME_ID_INDEX = 0;
        public static final int QUIETTIME_ENABLED_INDEX = 1;
        public static final int QUIETTIME_START_HOUR_INDEX = 2;
        public static final int QUIETTIME_START_MIN_INDEX = 3;
        public static final int QUIETTIME_STOP_HOUR_INDEX = 4;
        public static final int QUIETTIME_STOP_MIN_INDEX = 5;
        public static final int QUIETTIME_LED_ON_INDEX = 6;
        public static final int QUIETTIME_SOUND_ON_INDEX = 7;
        public static final int QUIETTIME_VIBRATE_ON_INDEX = 8;
        public static final int QUIETTIME_MODE_INDEX = 9;
        public static final int QUIETTIME_ALLOW_INDEX = 10;
        public static final int QUIETTIME_EXTRA_1_INDEX = 11;
        public static final int QUIETTIME_EXTRA_2_INDEX = 12;
        
    }
    //////////////////////////////
    // End column definitions
    //////////////////////////////

    // Public fields
    public int        id;
    public boolean    enabled;
    public int        starthour;
    public int        startmin;
    public int        stophour;
    public int        stopmin;
    public boolean    ledon;
    public boolean    soundon;
    public boolean    vibrateon;
    public int		  mode;
    public boolean    allow;
    public int        extra1;
    public int		  extra2;
    

    public QuietTime(Cursor c) {
        id = c.getInt(Columns.QUIETTIME_ID_INDEX);
        enabled = c.getInt(Columns.QUIETTIME_ENABLED_INDEX) == 1;
        starthour = c.getInt(Columns.QUIETTIME_START_HOUR_INDEX);
        startmin = c.getInt(Columns.QUIETTIME_START_MIN_INDEX);
        stophour = c.getInt(Columns.QUIETTIME_STOP_HOUR_INDEX);
        stopmin = c.getInt(Columns.QUIETTIME_STOP_MIN_INDEX);
        ledon = c.getInt(Columns.QUIETTIME_LED_ON_INDEX) == 1;
        soundon = c.getInt(Columns.QUIETTIME_SOUND_ON_INDEX) == 1;
        vibrateon = c.getInt(Columns.QUIETTIME_VIBRATE_ON_INDEX)  == 1;
        mode = c.getInt(Columns.QUIETTIME_MODE_INDEX);
        allow = c.getInt(Columns.QUIETTIME_ALLOW_INDEX)  == 1;
        extra1 = c.getInt(Columns.QUIETTIME_EXTRA_1_INDEX);
        extra2 = c.getInt(Columns.QUIETTIME_EXTRA_2_INDEX);

    }

    public QuietTime(Parcel p) {
        id = p.readInt();
        enabled = p.readInt() == 1;
        starthour = p.readInt();
        startmin = p.readInt();
        stophour = p.readInt();
        stopmin = p.readInt();
        ledon = p.readInt() == 1;
        soundon = p.readInt() == 1;
        vibrateon = p.readInt() == 1;
        mode = p.readInt();
        allow = p.readInt() == 1;
        extra1 = p.readInt();
        extra2 = p.readInt();
        
    }

    // Creates a default quiet time.
    public QuietTime() {
        id = -1;
        enabled = false;
        starthour = 21;
        startmin = 0;
        stophour = 7;
        stopmin = 0;
        ledon = true;
        soundon = true;
        vibrateon = true;
        mode = 0;
        allow = false;
        extra1 = 0;
        extra2 = 0;
    }


    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof QuietTime)) return false;
        final QuietTime other = (QuietTime) o;
        return id == other.id;
    }



}
