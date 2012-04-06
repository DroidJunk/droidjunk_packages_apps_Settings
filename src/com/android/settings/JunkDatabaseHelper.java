/*
 * Copyright (C) 2010 The Android Open Source Project
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

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

/**
 * Helper class for opening the database from multiple providers.  Also provides
 * some common functionality.
 */
class JunkDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "junk.db";
    private static final int DATABASE_VERSION = 1;

    public JunkDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE quiettime (" +
                   "_id INTEGER PRIMARY KEY," +
                   "enabled INTEGER, " +
                   "starthour INTEGER, " +
                   "startmin INTEGER, " +
                   "stophour INTEGER, " +
                   "stopmin INTEGER, " +
                   "ledon INTEGER, " +
                   "soundon INTEGER, " +
                   "vibrateon INTEGER);");

        // insert default values
        String insertMe = "INSERT INTO quiettime " +
                "(enabled, starthour, startmin, stophour, stopmin, ledon, " +
                " soundon, vibrateon) VALUES ";
        db.execSQL(insertMe + "(0, 21, 0, 7, 0, 1, 1, 1);");
        
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
            int currentVersion) {
    	Log.v("Upgrading tranquilice database version " +
                oldVersion + " to " + currentVersion 
                ," which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS quiettime");
        onCreate(db);
    }

    Uri commonInsert(ContentValues values) {
        SQLiteDatabase db = getWritableDatabase();
        long rowId = db.insert("quiettime", "TranqilIce", values);
        if (rowId < 0) {
            throw new SQLException("Failed to insert row");
        }
        

        return ContentUris.withAppendedId(QuietTime.Columns.CONTENT_URI, rowId);
    }
}
