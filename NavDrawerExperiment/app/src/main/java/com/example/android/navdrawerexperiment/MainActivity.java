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

package com.example.android.navdrawerexperiment;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * This app sets up a navigation drawer with the following choices: Import, Gallery,
 * Slideshow, Tools, Share, and Send. Choosing any choice closes the navigation drawer
 * and displays a toast message showing which choice was selected.
 * This app was created using the Navigation Drawer Activity template.
 */
public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * Creates the content view and toolbar, sets up the drawer layout and the
     * action bar toggle, and sets up the navigation view.
     * @param savedInstanceState    Saved instance.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab != null) {
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                }
            });
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        if (drawer != null) {
            drawer.addDrawerListener(toggle);
        }
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(this);
        }
    }

    /**
     * Handles the Back button: closes the nav drawer.
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer != null) {
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }
    }

    /**
     * Inflates the options menu.
     * @param menu  Menu to inflate
     * @return      Returns true if menu is inflated.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * Handles a click on the Settings item in the options menu.
     * @param item  Item in options menu that was clicked.
     * @return      Returns true if the item was Settings.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Handles a navigation drawer item click. It detects which item was
     * clicked and displays a toast message showing which item.
     * @param item  Item in the navigation drawer
     * @return      Returns true after closing the nav drawer
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.nav_camera:
                // Handle the camera import action (for now display a toast).
                drawer.closeDrawer(GravityCompat.START);
                displayToast(getString(R.string.chose_camera));
                return true;
            case R.id.nav_gallery:
                // Handle the gallery action (for now display a toast).
                drawer.closeDrawer(GravityCompat.START);
                displayToast(getString(R.string.chose_gallery));
                return true;
            case R.id.nav_slideshow:
                // Handle the slideshow action (for now display a toast).
                drawer.closeDrawer(GravityCompat.START);
                displayToast(getString(R.string.chose_slideshow));
                return true;
            case R.id.nav_manage:
                // Handle the tools action (for now display a toast).
                drawer.closeDrawer(GravityCompat.START);
                displayToast(getString(R.string.chose_tools));
                return true;
            case R.id.nav_share:
                // Handle the share action (for now display a toast).
                drawer.closeDrawer(GravityCompat.START);
                displayToast(getString(R.string.chose_share));
                return true;
            case R.id.nav_send:
                // Handle the send action (for now display a toast).
                drawer.closeDrawer(GravityCompat.START);
                displayToast(getString(R.string.chose_send));
                return true;
            default:
                return false;
        }
    }

    /**
     * Displays a toast message.
     * @param message   Message to display in toast
     */
    public void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
