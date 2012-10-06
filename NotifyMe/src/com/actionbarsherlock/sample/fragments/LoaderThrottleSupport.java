/*
 * Copyright (C) 2011 The Android Open Source Project
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

package com.actionbarsherlock.sample.fragments;


import java.util.HashMap;
import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v4.app.FragmentManager;
import android.support.v4.database.DatabaseUtilsCompat;
import android.text.TextUtils;
import android.util.Log;
import com.actionbarsherlock.app.SherlockFragmentActivity;

/**
 * Demonstration of bottom to top implementation of a content provider holding
 * structured data through displaying it in the UI, using throttling to reduce
 * the number of queries done when its data changes.
 */
public class LoaderThrottleSupport extends SherlockFragmentActivity {
    // Debugging.
    static final String TAG = "LoaderThrottle";

    /**
     * The authority we use to get to our sample provider.
     */
    public static final String AUTHORITY = "com.example.android.apis.supportv4.app.LoaderThrottle";

    /**
     * Definition of the contract for the main table of our provider.
     */
    public static final class MainTable implements BaseColumns {

        // This class cannot be instantiated
        private MainTable() {}

        /**
         * The table name offered by this provider
         */
        public static final String TABLE_NAME = "main";

        /**
         * The content:// style URL for this table
         */
        public static final Uri CONTENT_URI =  Uri.parse("content://" + AUTHORITY + "/main");

        /**
         * The content URI base for a single row of data. Callers must
         * append a numeric row id to this Uri to retrieve a row
         */
        public static final Uri CONTENT_ID_URI_BASE
                = Uri.parse("content://" + AUTHORITY + "/main/");

        /**
         * The MIME type of {@link #CONTENT_URI}.
         */
        public static final String CONTENT_TYPE
                = "vnd.android.cursor.dir/vnd.example.api-demos-throttle";

        /**
         * The MIME type of a {@link #CONTENT_URI} sub-directory of a single row.
         */
        public static final String CONTENT_ITEM_TYPE
                = "vnd.android.cursor.item/vnd.example.api-demos-throttle";
        /**
         * The default sort order for this table
         */
        public static final String DEFAULT_SORT_ORDER = "data COLLATE LOCALIZED ASC";

        /**
         * Column name for the single column holding our data.
         * <P>Type: TEXT</P>
         */
        public static final String COLUMN_NAME_DATA = "data";
    }

   
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fm = getSupportFragmentManager();

        // Create the list fragment and add it as our sole content.
        if (fm.findFragmentById(android.R.id.content) == null) {
            //ThrottledLoaderListFragment list = new ThrottledLoaderListFragment();
            //fm.beginTransaction().add(android.R.id.content, list).commit();
        }
    }

    
}

