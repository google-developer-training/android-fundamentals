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
package com.example.android.implicitintents;

import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

/**
 * The ImplicitIntents app contains three buttons for sending implicit intents:
 * - Open a URL in a browser
 * - Find a location on a map
 * - Share a text string
 */
public class MainActivity extends AppCompatActivity {

    // EditText view for the website URI
    private EditText mWebsiteEditText;
    // EditText view for the location URI
    private EditText mLocationEditText;
    // EditText view for the share text
    private EditText mShareTextEditText;

    /**
     * Initializes the activity.
     *
     * @param savedInstanceState The current state data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWebsiteEditText = (EditText) findViewById(R.id.website_edittext);
        mLocationEditText = (EditText) findViewById(R.id.location_editext);
        mShareTextEditText = (EditText) findViewById(R.id.share_edittext);
    }

    /**
     * Handles the onClick for the "Open Website" button.  Gets the URI
     * from the edit text and sends an implicit intent for that URL.
     *
     * @param view The view (Button) that was clicked.
     */
    public void openWebsite(View view) {
        // Get the URL text.
        String url = mWebsiteEditText.getText().toString();

        // Parse the URI and create the intent.
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);

        // Find an activity to hand the intent and start that activity.
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }

    /**
     * Handles the onClick for the "Open Location" button.  Gets the location
     * text from the edit text and sends an implicit intent for that location.
     *
     * The location text can be any searchable geographic location.
     *
     * @param view The view (Button) that was clicked.
     */
    public void openLocation(View view) {
        // Get the string indicating a location.  Input is not validated; it is
        // passed to the location handler intact.
        String loc = mLocationEditText.getText().toString();

        // Parse the location and create the intent.
        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);

        // Find an activity to handle the intent, and start that activity.
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }

    /**
     * Handles the onClick for the "Share This Text" button.  The
     * implicit intent here is created by the  {@link ShareCompat.IntentBuilder}
     * class.  An app chooser appears with the available options for sharing.
     *
     * ShareCompat.IntentBuilder is from the v4 Support Library.
     *
     * @param view The view (Button) that was clicked.
     */
    public void shareText(View view) {
        // Get the shared text.
        String txt = mShareTextEditText.getText().toString();

        // Build the share intent with the mimetype text/plain and launch
        // a chooser for the user to pick an app.
        ShareCompat.IntentBuilder
                .from(this)
                .setType("text/plain")
                .setChooserTitle("Share this text with: ")
                .setText(txt)
                .startChooser();
    }
}
