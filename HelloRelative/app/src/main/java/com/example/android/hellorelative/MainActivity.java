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

package com.example.android.hellorelative;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Displays two buttons and a text view.
 * - Pressing the TOAST button shows a toast.
 * - Pressing the COUNT button increases the displayed mCount.
 *
 * Note: Fixing behavior when device is rotated is a challenge exercise for the student.
 */

public class MainActivity extends AppCompatActivity {

    private int mCount = 0;
    private TextView mShowCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mShowCount = (TextView) findViewById(R.id.show_count);
    }

    /**
    * Increments the number in the text view when the COUNT button is clicked.
    *
    * @param view The view that triggered this onClick handler.
    * Since the count always appears in the text view, the passed in view is not used.
    */
    public void countUp(View view) {
        mCount++;
        if (mShowCount != null)
            mShowCount.setText(Integer.toString(mCount));
    }

    /**
    * Shows a toast when the TOAST button is clicked.
    *
    * @param view The view that triggered this onClick handler.
    * Since a toast always shows on the top, the passed in view is not used.
    */
    public void showToast(View view) {
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(this, R.string.toast_button_toast, duration);
        toast.show();
    }
}
