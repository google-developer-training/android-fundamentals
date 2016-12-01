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


package com.example.android.keyboarddialphone;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app accepts a phone number and sends implicit intent to dial the number.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    /**
     * Create the activity and set the view, then set the listener to detect if
     * key was pressed in the EditText view.
     * @param savedInstanceState    Instance state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Find the editText_main view and assign it to editText.
        EditText editText = (EditText) findViewById(R.id.editText_main);
        // If view is found, set the listener for editText.
        if (editText != null)
            editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

                /**
                 * Responds to the pressed key and calls a method to dial the phone number.
                 * @param textView  The view that was clicked.
                 * @param actionId  Identifier of the action.
                 * @param keyEvent  If triggered by an Enter key, this is the event.
                 * @return          True, the key was entered, or false.
                 */
                @Override
                public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                    // Start with no event - false.
                    boolean mHandled = false;
                    // If the actionID is IME_ACTION_SEND...
                    if (actionId == EditorInfo.IME_ACTION_SEND) {
                        // Call the dialNumber method and return true.
                        dialNumber();
                        mHandled = true;
                    }
                    return mHandled;
                }
            });

    }

    /**
     * Creates a string (mPhoneNum) for the phone number to dial,
     * and performs an implicit intent to dial the number.
     */
    private void dialNumber() {
        // Find the editText_main view and assign it to editText.
        EditText editText = (EditText) findViewById(R.id.editText_main);
        // Initialize the string mPhoneNum.
        String mPhoneNum = null;
        // If the editText field is not null, concatenate "tel: " with the phone number string.
        if (editText != null) mPhoneNum = "tel:" + editText.getText().toString();
        // Log the concatenated phone number for dialing.
        Log.d(TAG, "dialNumber: " + mPhoneNum);
        // Specify the intent.
        Intent intent = new Intent(Intent.ACTION_DIAL);
        // Set the data for the intent as the phone number.
        intent.setData(Uri.parse(mPhoneNum));
        // If the intent resolves to a package (app), start the activity with the intent.
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d(TAG, "ImplicitIntents: Can't handle this!");
        }
    }

    /**
     * Show the phone number as text in a toast message.
     * @param view  The view that was clicked.
     */
    public void showText(View view) {
        EditText editText = (EditText) findViewById(R.id.editText_main);
        if (editText != null) {
            String showString = editText.getText().toString();
            Toast.makeText(this, showString, Toast.LENGTH_SHORT).show();
        }
    }
}
