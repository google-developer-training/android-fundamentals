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

package com.android.example.wordlistsql;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

/**
 * Implements a RecyclerView that displays a list of words from a SQL database.
 * - Clicking the fab button opens a second activity to add a word to the database.
 * - Clicking the Edit button opens an activity to edit the current word in the database.
 * - Clicking the Delete button deletes the current word from the database.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    public static final int WORD_EDIT = 1;
    public static final int WORD_ADD = -1;

    private WordListOpenHelper mDB;
    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;
    private int mLastPosition;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDB = new WordListOpenHelper(this);

        // Create recycler view.
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        // Create an mAdapter and supply the data to be displayed.
        mAdapter = new WordListAdapter(this, /* mDB.getAllEntries(),*/ mDB);
        // Connect the mAdapter with the recycler view.
        mRecyclerView.setAdapter(mAdapter);
        // Give the recycler view a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Add a floating action click handler for creating new entries.
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Starts empty edit activity.
                Intent intent = new Intent(getBaseContext(), EditWordActivity.class);
                startActivityForResult(intent, WORD_EDIT);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == WORD_EDIT) {
            if (resultCode == RESULT_OK) {
                String word = data.getStringExtra(EditWordActivity.EXTRA_REPLY);

                // Update the database.
                if (!TextUtils.isEmpty(word)) {
                    int id = data.getIntExtra(WordListAdapter.EXTRA_ID, -99);

                    if (id == WORD_ADD) {
                        mDB.insert(word);
                    } else if (id >= 0) {
                        mDB.update(id, word);
                    }
                    // Update the UI.
                    mAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(
                            getApplicationContext(),
                            R.string.empty_not_saved,
                            Toast.LENGTH_LONG).show();
                }
            }
        }
    }
}