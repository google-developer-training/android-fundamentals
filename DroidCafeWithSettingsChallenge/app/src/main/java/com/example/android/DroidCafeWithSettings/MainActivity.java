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

package com.example.android.DroidCafeWithSettings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

/**
 * This app shows images used as buttons to launch a second activity
 * and displays a Toast message showing the delivery method chosen
 * for a Droid Cafe food order. It also shows how to set up
 * the options menu and floating action button.
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Creates the content view, the toolbar, and
     * the floating action button.
     * This method is provided in the Basic Activity template.
     *
     * @param savedInstanceState Saved instance.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayMap();
            }
        });

        // Sets default values only once, first time this is called.
        // The third argument is a boolean that indicates whether the default values
        // should be set more than once. When false, the system sets the default values
        // only if this method has never been called in the past.
        PreferenceManager.setDefaultValues(this, R.xml.pref_general, false);
        PreferenceManager.setDefaultValues(this, R.xml.pref_notification, false);
        PreferenceManager.setDefaultValues(this, R.xml.pref_data_sync, false);

        // Read the Account "market" setting and display a toast message.
        SharedPreferences sharedPref =
                PreferenceManager.getDefaultSharedPreferences(this);
        String marketPref = sharedPref.getString("sync_frequency", "-1");
        Boolean switchPref = sharedPref.getBoolean("example_switch", false);
        String message = getString(R.string.market_message) + marketPref +
                getString(R.string.recommend_message) + switchPref;
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Inflates the menu, and adds items to the action bar if it is present.
     *
     * @param menu Menu to inflate.
     * @return Returns true if the menu inflated.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * Handles app bar item clicks.
     *
     * @param item  Item clicked.
     * @return True if one of the defined items was clicked.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle app bar item clicks here. The app bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_order:
                displayToast(getString(R.string.action_order_message));
                return true;
            case R.id.action_status:
                displayToast(getString(R.string.action_status_message));
                return true;
            case R.id.action_favorites:
                displayToast(getString(R.string.action_favorites_message));
                return true;
            case R.id.action_contact:
                displayToast(getString(R.string.action_contact_message));
                return true;
            case R.id.action_settings:
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                return true;
            default:
                // Do nothing
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Shows a message that the donut image was clicked.
     */
    public void showDonutOrder(View view) {
        showFoodOrder(getString(R.string.donut_order_message));
    }

    /**
     * Shows a message that the ice cream sandwich image was clicked.
     */
    public void showIceCreamOrder(View view) {
        showFoodOrder(getString(R.string.ice_cream_order_message));
    }

    /**
     * Shows a message that the froyo image was clicked.
     */
    public void showFroyoOrder(View view) {
        showFoodOrder(getString(R.string.froyo_order_message));
    }

    /**
     * Displays a Toast message for the food order and starts the OrderActivity activity.
     *
     * @param message Message to display.
     */
    public void showFoodOrder(String message) {
        displayToast(message);
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
    }

    /**
     * Displays a Toast message.
     *
     * @param message Message to display.
     */
    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void displayMap() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        // Using the coordinates for Google headquarters.
        String data = getString(R.string.google_mtv_coord_zoom12);
        intent.setData(Uri.parse(data));
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
