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
package com.example.android.twoactivitieslifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * SecondActivity defines the second activity in the app. It is launched from an intent
 * with a message, and sends an intent back with a second message.
 */
public class SecondActivity extends AppCompatActivity {
    // Unique tag for the intent reply.
    public static final String EXTRA_REPLY =
            "com.example.android.twoactivitieslifecycle.extra.REPLY";

    // Class name for Log tag.
    private static final String LOG_TAG = SecondActivity.class.getSimpleName();

    // EditText for the reply.
    private EditText mReply = null;

    /**
     * Initializes the activity.
     *
     * @param savedInstanceState The current state data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Initialize view variables.
        mReply = (EditText) findViewById(R.id.editText_second);

        // Get the intent that launched this activity, and the message in
        // the intent extra.
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        // Put that message into the text_message TextView
        TextView textView = (TextView) findViewById(R.id.text_message);
        if (textView != null) {
            textView.setText(message);
        }

        Log.d(LOG_TAG, "-------");
        Log.d(LOG_TAG, "onCreate");
    }

    /**
     * Handle the onClick for the "Reply" button. Gets the message from the second EditText,
     * creates an intent, and returns that message back to the main activity.
     *
     * @param view The view (Button) that was clicked.
     */
    public void returnReply(View view) {
        // Get the reply message from the edit text.
        String reply = mReply.getText().toString();

        // Create a new intent for the reply, add the reply message to it as an extra,
        // set the intent result, and close the activity.
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, reply);
        setResult(RESULT_OK, replyIntent);

        Log.d(LOG_TAG, "End SecondaryActivity");

        finish();
    }

    /**
     * Lifecycle callback for start.
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(LOG_TAG, "onStart");
    }

    /**
     * Lifecycle callback for restart.
     */
    @Override
    public void onRestart() {
        super.onRestart();
        Log.d(LOG_TAG, "onRestart");
    }

    /**
     * Lifecycle callback for resume.
     */
    @Override
    public void onResume() {
        super.onResume();
        Log.d(LOG_TAG, "onResume");
    }

    /**
     * Lifecycle callback for pause.
     */
    @Override
    public void onPause() {
        super.onPause();
        Log.d(LOG_TAG, "onPause");
    }

    /**
     * Lifecycle callback for stop.
     */
    @Override
    public void onStop() {
        super.onStop();
        Log.d(LOG_TAG, "onStop");
    }

    /**
     * Lifecycle callback for destroy.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(LOG_TAG, "onDestroy");
    }
}
