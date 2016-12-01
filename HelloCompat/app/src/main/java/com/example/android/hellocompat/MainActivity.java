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
package com.example.android.hellocompat;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.util.Random;

/**
 * HelloCompat demonstrates the use of the ContextCompat class, part of the
 * V4 support library, to help demonstrate why the support libraries are useful.
 */
public class MainActivity extends AppCompatActivity {
    // Text view for Hello World.
    private TextView mHelloTextView;

    // array of color names, these match the color resources in color.xml
    private String[] mColorArray = {"red",
            "pink",
            "purple",
            "deep_purple",
            "indigo",
            "blue",
            "light_blue",
            "cyan",
            "teal",
            "green",
            "light_green",
            "lime",
            "yellow",
            "amber",
            "orange",
            "deep_orange",
            "brown",
            "grey",
            "blue_grey",
            "black" };

    /**
     * Initializes the activity.
     *
     * @param savedInstanceState The current state data.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the main text view
        mHelloTextView = (TextView) findViewById(R.id.hello_textview);

        // Restore saved instance state (the text color)
        if (savedInstanceState != null) {
            mHelloTextView.setTextColor(savedInstanceState.getInt("color"));
        }
    }

    /**
     * Handles the onClick for the change color button. Chooses a random color name from
     * the color array and gets the color value for that name from the resources. Sets
     * the Hello World textview to that color.
     *
     * @param view The view (Button) that was clicked.
     */
    public void changeColor(View view) {
        // get a random color name from the color array (20 colors)
        Random random = new Random();
        String colorName = mColorArray[random.nextInt(20)];

        // get the color identifier that matches the color name
        int colorResourceName = getResources().getIdentifier(colorName,
                "color", getApplicationContext().getPackageName());

        // get the color ID from the resources
        // The pre API 23 way
        // int colorRes = getResources().getColor(colorResourceName);
        // The post API way
        // int colorRes = getResources().getColor(colorResourceName, this.getTheme());
        // Compatible way
        int colorRes = ContextCompat.getColor(this, colorResourceName);

        // Set the text color
        mHelloTextView.setTextColor(colorRes);
    }

    /**
     * Save the instance state if the activity is restarted (for example, on device rotation).
     * Saves the color of the hello world text.
     *
     * @param outState The state data.
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // save the current text color
        outState.putInt("color", mHelloTextView.getCurrentTextColor());
    }
}
