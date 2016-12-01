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

package com.example.android.phonenumberspinner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * This app is a copy of KeyboardSamples that
 * adds a spinner to the layout to appear right next to the phone number field.
 * The spinner lets the user choose the type of phone number:
 * Home, Work, Mobile, and Other.
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    // Define TAG for logging.
    private static final String TAG = MainActivity.class.getSimpleName();
    // Define mSpinnerLabel for the label (the spinner item that the user chooses).
    private String mSpinnerLabel = "";

    /**
     * Set the content view, create the spinner, and create the array adapter for the spinner.
     * @param savedInstanceState    Saved instance.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the spinner.
        Spinner spinner = (Spinner) findViewById(R.id.label_spinner);
        if (spinner != null) {
            spinner.setOnItemSelectedListener(this);
        }

        // Create ArrayAdapter using the string array and default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.labels_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource
                (android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner.
        if (spinner != null) {
            spinner.setAdapter(adapter);
        }

    }

    /**
     * Retrieves the text and spinner item and shows them in text_phonelabel.
     * @param view  The view containing editText_main.
     */
    public void showText(View view) {
        EditText editText = (EditText) findViewById(R.id.editText_main);
        if (editText != null) {
            // Assign to showString both the entered string and mSpinnerLabel.
            String showString = (editText.getText().toString() + " - " + mSpinnerLabel);
            // Assign to phoneNumberResult the view for text_phonelabel to prepare to show it.
            TextView phoneNumberResult = (TextView) findViewById(R.id.text_phonelabel);
            // Show the showString in the phoneNumberResult.
            if (phoneNumberResult != null) phoneNumberResult.setText(showString);
        }
    }

    /**
     * Retrieves the selected item in the spinner using getItemAtPosition,
     * and assigns it to mSpinnerLabel.
     * @param adapterView   The adapter for the spinner, where the selection occurred.
     * @param view          The view within the adapterView that was clicked.
     * @param pos             The position of the view in the adapter.
     * @param id             The row id of the item that is selected (not used here).
     */
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long id) {
        mSpinnerLabel = adapterView.getItemAtPosition(pos).toString();
        showText(view);
    }

    /**
     * Logs the fact that nothing was selected in the spinner.
     * @param adapterView The adapter for the spinner, where the selection should have occurred.
     */
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Log.d(TAG, getString(R.string.nothing_selected));
    }
}
