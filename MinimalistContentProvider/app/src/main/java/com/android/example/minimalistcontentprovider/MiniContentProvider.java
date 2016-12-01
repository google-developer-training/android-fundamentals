package com.android.example.minimalistcontentprovider;
/*
 * Copyright (C) 2016 Google Inc.
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

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;

import static java.lang.Integer.parseInt;

/**
 * Created by vhaecky on 5/27/16.
 *
 * The purpose of a content provider is to act as an intermediary between data and a UI.
 * As such, you can use any repository for the data you want, though an SQL database is by far one
 * of the more common ones, where the data is in tables and the response a cursor.
 *
 * However, for the purpose of this practical, and the focus on the mechanics of implementing a
 * basic content provider, this class uses generated data stored in a list.
 */

public class MiniContentProvider extends ContentProvider {

    private static final String TAG = MiniContentProvider.class.getSimpleName();
    public String[] mData;

    // UriMatcher is a helper class for processing the accepted Uri schemes
    // for this content provider.
    // https://developer.android.com/reference/android/content/UriMatcher.html
    private static UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);


    @Override
    public boolean onCreate() {
        // Set up the URI scheme for this content provider.
        initializeUriMatching();
        Context context = getContext();
        mData = context.getResources().getStringArray(R.array.words);
        return true;
    }

    /**
     * Defines the accepted Uri schemes for this content provider.
     * Calls addURI()for all of the content URI patterns that the provide should recognize.
     */
    private void initializeUriMatching(){
        // Matches a URI that references one word in the list by its index.
        // The # symbol matches a string of numeric characters of any length.
        // For this sample, this references the first, second, etc. words in the list.
        // For a database, this could be an ID.
        // Note that addURI expects separate authority and path arguments.
        // The last argument is the integer code to assign to this URI pattern.
        sUriMatcher.addURI(Contract.AUTHORITY, Contract.CONTENT_PATH + "/#", 1);

        // Matches a URI that is just the authority + the path, triggering the return of all words.
        sUriMatcher.addURI(Contract.AUTHORITY, Contract.CONTENT_PATH, 0);
    }

    /**
     * Matches the URI, converts it to a query, executes the query, and returns the result.
     *
     * The arguments to this method represent the parts of an SQL query.
     * If you are using another kind of backend, you must still accept a query in this style,
     * but handle the arguments appropriately.
     *
     * @param uri The complete URI queried. This cannot be null.
     * @param projection Indicates which columns/attributes you want to access.
     * @param selection Indicates which rows/records of the objects you want to access
     * @param selectionArgs The binding parameters to the previous selection argument.
     * @param sortOrder Whether to sort, and if so, whether ascending or descending.
     * @return a Cursor of any kind with the response data inside.
     */
    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        int id = -1;

        // Analyze the URI and build a query from the arguments.
        // This code changes depending on your backend.
        // This example uses the matcher to get the integer code for this URI pattern.

        // Determine which integer code corresponds to the URI, then switch on it.
        switch (sUriMatcher.match(uri)) {
            case 0:
                // Matches URI to get all of the entries.
                id = Contract.ALL_ITEMS;
                // Look at the remaining arguments to see whether there are constraints.
                // In this example, we only support getting a specific entry by id. Not full search.
                // For a real-life app, you need error-catching code; here we assume that the
                // value we need is actually in selectionArgs and valid.
                if (selection != null){
                    id = Integer.parseInt(selectionArgs[0]);
                }
                break;

            case 1:
                // The URI ends in a numeric value, which represents an id.
                // Parse the URI to extract the value of the last, numeric part of the path,
                // and set the id to that value.
                id = parseInt(uri.getLastPathSegment());
                // With a database, you would then use this value and the path to build a query.
                break;

            case UriMatcher.NO_MATCH:
                // You should do some error handling here.
                Log.d(TAG, "NO MATCH FOR THIS URI IN SCHEME.");
                id = -1;
                break;
            default:
                // You should do some error handling here.
                Log.d(TAG, "INVALID URI - URI NOT RECOGNZED.");
                id = -1;
        }
        Log.d(TAG, "query: " + id);
        return populateCursor(id);
    }

    private Cursor populateCursor(int id) {
        // The query() method must return a cursor.
        // If you are not using data storage that returns a cursor,
        // you can use a simple MatrixCursor to hold the data to return.
        // https://developer.android.com/reference/android/database/MatrixCursor.html
        MatrixCursor cursor = new MatrixCursor(new String[] { Contract.CONTENT_PATH });

        // If there is a valid query, execute it and add the result to the cursor.
        if (id == Contract.ALL_ITEMS) {
            for (int i = 0; i < mData.length; i++) {
                String word = mData[i];
                cursor.addRow(new Object[]{word});
            }
        } else if (id >= 0) {
            // Execute the query to get the requested word.
            String word = mData[id];
            // Add the result to the cursor.
            cursor.addRow(new Object[]{word});
        }
        return cursor;
    }

    // getType must be implemented.
    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case 0:
                return Contract.MULTIPLE_RECORDS_MIME_TYPE;
            case 1:
                return Contract.SINGLE_RECORD_MIME_TYPE;
            default:
                // Alternatively, throw an exception.
                return null;
        }
    }

    @Nullable
    @Override
    // Inserts the values into the provider.
    // Returns a URI that points to the newly inserted record.
    // We will implement this method in the next practical.
    public Uri insert(Uri uri, ContentValues values) {
        Log.e(TAG, "Not implemented: insert uri: " + uri.toString());
        return null;
    }

    // Deletes records(s) specified by either the URI or selection/selectionArgs combo.
    // Returns the number of records affected.
    // We will implement this method in the next practical.
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.e(TAG, "Not implemented: delete uri: " + uri.toString());
        return 0;
    }

    // Updates records(s) specified by either the URI or selection/selectionArgs combo.
    // Returns the number of records affected.
    // We will implement this method in the next practical.
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        Log.e(TAG, "Not implemented: update uri: " + uri.toString());
        return 0;
    }
}
