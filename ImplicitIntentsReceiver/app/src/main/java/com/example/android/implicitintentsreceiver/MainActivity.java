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
package com.example.android.implicitintentsreceiver;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * The ImplicitIntentsReceiver app registers itself for implicit intents that come from browsable
 * links (URLs) with the scheme:http and host:developer.android.com (see AndroidManifest.xml).
 *
 * If it receives that intent, the app just prints the incoming URI to a textview.
 * ImplicitIntentsReceiver is intended to be used in conjunction with the ImplicitIntents app, but
 * will receive a matching implicit intent from any app (for example, a link in an email.)
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Initializes the activity.
     *
     * @param savedInstanceState The current state data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();
        Uri uri = intent.getData();
        if (uri != null) {
            TextView textView = (TextView) findViewById(R.id.text_uri_message);
            textView.setText("URI: " + uri.toString());
        }
    }
}
