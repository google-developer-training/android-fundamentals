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

package com.android.example.wordlistsqlwithcontentprovider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import static com.android.example.wordlistsqlwithcontentprovider.Contract.ALL_ITEMS;
import static com.android.example.wordlistsqlwithcontentprovider.Contract.AUTHORITY;
import static com.android.example.wordlistsqlwithcontentprovider.Contract.CONTENT_PATH;
import static com.android.example.wordlistsqlwithcontentprovider.Contract.CONTENT_URI;
import static com.android.example.wordlistsqlwithcontentprovider.Contract.COUNT;
import static com.android.example.wordlistsqlwithcontentprovider.Contract.MULTIPLE_RECORDS_MIME_TYPE;
import static com.android.example.wordlistsqlwithcontentprovider.Contract.SINGLE_RECORD_MIME_TYPE;
import static java.lang.Integer.parseInt;

/**
 * You don't need a provider to use an SQLite database if the use is entirely
 * within your own application. It is necessary if you want to allow other apps to read and edit
 * words.
 *
 * ADVANCED: Note that if you only want to expose some functionality, say, you don't want users to delete,
 * but they can add and update, after discussion with Dan, the best way is to have the delete
 * method return an informative message that deletion failed. And the "owning" app can do
 * deletes directly through the OpenHelper. You could also implement your own permission checking.
 * Also, document this in the Contract.
 *
 */
public class WordListContentProvider  extends ContentProvider {

    private static final String TAG = WordListContentProvider.class.getSimpleName();

    private static UriMatcher sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    // Moved all the interaction with the OpenHelper from MainActivity into the ContentProvider.
    // The MainActivity does not know anything about the backend and talks to the resolver instead.
    private WordListOpenHelper mDB;

    private static final int URI_ALL_ITEMS_CODE = 10;
    private static final int URI_ONE_ITEM_CODE = 20;
    private static final int URI_COUNT_CODE = 30;

    @Override
    public boolean onCreate() {
        // Database interface object
        mDB = new WordListOpenHelper(getContext());
        initializeUriMatching();
        return true;
    }

    /**
     * Defines the accepted Uri schemes for this content provider.
     * Calls addURI() for all of the content URI patterns that the provide should recognize.
     */
    private void initializeUriMatching() {

        // Matches a URI that is just the authority + the path, triggering the return of all words.
        sUriMatcher.addURI(AUTHORITY, CONTENT_PATH, URI_ALL_ITEMS_CODE);

        // Matches a URI that references one word in the list by its index.
        sUriMatcher.addURI(AUTHORITY, CONTENT_PATH + "/#", URI_ONE_ITEM_CODE);

        // Matches a URI that returns the number of rows in the table.
        sUriMatcher.addURI(AUTHORITY, CONTENT_PATH + "/" + COUNT, URI_COUNT_CODE);
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs,
                        String sortOrder) {

        Cursor cursor = null;

        // Determine integer code from the URI matcher and switch on it.
        switch (sUriMatcher.match(uri)) {
            case URI_ALL_ITEMS_CODE:
                cursor =  mDB.query(ALL_ITEMS);
                Log.d(TAG, "case all items " + cursor);
                break;
            case URI_ONE_ITEM_CODE:
                cursor =  mDB.query(parseInt(uri.getLastPathSegment()));
                Log.d(TAG, "case one item " + cursor);
                break;
            case URI_COUNT_CODE:
                cursor = mDB.count();
                Log.d(TAG, "case count " + cursor);
                break;
            case UriMatcher.NO_MATCH:
                // You should do some error handling here.
                Log.d(TAG, "NO MATCH FOR THIS URI IN SCHEME: " + uri);
                break;
            default:
                // You should do some error handling here.
                Log.d(TAG, "INVALID URI - URI NOT RECOGNIZED: "  + uri);
        }
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        switch (sUriMatcher.match(uri)) {
            case URI_ALL_ITEMS_CODE:
                return MULTIPLE_RECORDS_MIME_TYPE;
            case URI_ONE_ITEM_CODE:
                return SINGLE_RECORD_MIME_TYPE;
            default:
                return null;
        }
    }

    /**
     * Inserts one row.
     *
     * @param uri Uri for insertion.
     * @param values Container for Column/Row key/value pairs.
     * @return URI for the newly created entry.
     */
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        long id = mDB.insert(values);
        return Uri.parse(CONTENT_URI + "/" + id);
    }

    /**
     * Deletes records(s) specified by selectionArgs.
     *
     * @param uri URI for deletion.
     * @param selection Where clause.
     * @param selectionArgs Where clause arguments.
     * @return Number of records affected.
     */
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return mDB.delete(parseInt(selectionArgs[0]));
    }

    /**
     * Updates records(s) specified by selection/selectionArgs combo.
     *
     * @param uri URI for update.
     * @param values Container for Column/Row key/value pairs.
     * @param selection Where clause.
     * @param selectionArgs Where clause arguments.
     * @return Number of records affected.
     */
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return mDB.update(parseInt(selectionArgs[0]), values.getAsString("word"));
    }
}
