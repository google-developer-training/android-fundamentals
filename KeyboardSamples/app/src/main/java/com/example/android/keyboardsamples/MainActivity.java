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

package com.example.android.keyboardsamples;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Displays the entered text in a toast message.
 * This app is designed to show different keyboards when entering text.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    /**
     * Shows the text entered into editText_main in a Toast message.
     * @param view  View with editText_main.
     */
    public void showText(View view) {
        // Associate editText with the editText_main EditText element.
        EditText editText = (EditText) findViewById(R.id.editText_main);
        if (editText != null) {
            // Assign showString to the text that was entered.
            String showString = editText.getText().toString();
            // Make the Toast message with showString.
            Toast.makeText(this, showString, Toast.LENGTH_SHORT).show();
        }
    }
}
