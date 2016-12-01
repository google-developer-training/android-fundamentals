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
package com.example.android.standup;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private NotificationManager mNotificationManager;

    private static final int NOTIFICATION_ID = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        final AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        ToggleButton alarmToggle = (ToggleButton) findViewById(R.id.alarmToggle);

        //Set up the Notification Broadcast Intent
        Intent notifyIntent = new Intent(this, AlarmReceiver.class);

        //Check if the Alarm is already set, and check the toggle accordingly
        boolean alarmUp = (PendingIntent.getBroadcast(this, 0, notifyIntent,
                PendingIntent.FLAG_NO_CREATE) != null);

        alarmToggle.setChecked(alarmUp);

        //Set up the PendingIntent for the AlarmManager
        final PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
                (this, NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);



        alarmToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                String toastMessage;
                if(isChecked){

                    long triggerTime = SystemClock.elapsedRealtime()
                            + AlarmManager.INTERVAL_FIFTEEN_MINUTES;

                    long repeatInterval = AlarmManager.INTERVAL_FIFTEEN_MINUTES;

                    //If the Toggle is turned on, set the repeating alarm with a 15 minute interval
                    alarmManager.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                            triggerTime, repeatInterval, notifyPendingIntent);

                    //Set the toast message for the "on" case
                    toastMessage = getString(R.string.alarm_on_toast);
                } else {
                    //Cancel the alarm and notification if the alarm is turned off
                    alarmManager.cancel(notifyPendingIntent);
                    mNotificationManager.cancelAll();

                    //Set the toast message for the "off" case
                    toastMessage = getString(R.string.alarm_off_toast);
                }

                //Show a toast to say the alarm is turned on or off
                Toast.makeText(MainActivity.this, toastMessage, Toast.LENGTH_SHORT)
                        .show();
            }
        });


    }
}
