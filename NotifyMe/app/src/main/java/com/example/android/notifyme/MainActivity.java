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

package com.example.android.notifyme;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


/**
 * MainActivity for the Notify Me! app. Contains three buttons that deliver,
 * update and cancel notification
 */
public class MainActivity extends AppCompatActivity {


    private NotificationManager mNotifyManager;

    /*Constants for the notification id, the web url for a button in the notification, and the
    custom notification actions for buttons in the notification*/
    private static final int NOTIFICATION_ID = 0;
    private static final String NOTIFICATION_GUIDE_URL =
            "https://developer.android.com/design/patterns/notifications.html";
    private static final String ACTION_UPDATE_NOTIFICATION =
            "com.example.android.notifyme.ACTION_UPDATE_NOTIFICATION";
    private static final String ACTION_CANCEL_NOTIFICATION =
            "com.example.android.notifyme.ACTION_CANCEL_NOTIFICATION";

    private Button mNotifyButton;
    private Button mUpdateButton;
    private Button mCancelButton;

    private NotificationReceiver mReceiver = new NotificationReceiver();

    /**
     * Initializes the activity
     * @param savedInstanceState The current state data
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mNotifyManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        mNotifyButton = (Button) findViewById(R.id.notify);
        mUpdateButton = (Button) findViewById(R.id.update);
        mCancelButton = (Button) findViewById(R.id.cancel);

        mNotifyButton.setEnabled(true);
        mUpdateButton.setEnabled(false);
        mCancelButton.setEnabled(true);

        //Initialize and register the notification receiver
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_UPDATE_NOTIFICATION);
        intentFilter.addAction(ACTION_CANCEL_NOTIFICATION);
        registerReceiver(mReceiver, intentFilter);


        //Set OnClick methods.
        mNotifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendNotification();
            }
        });

        mUpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateNotification();
            }
        });

        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelNotification();
            }
        });

    }

    /**
     * Unregister the receiver when the app is destroyed
     */
    @Override
    protected void onDestroy() {
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    /**
     * OnClick method for the "Notify Me!" button. Creates and delivers a simple notification
     */
    public void sendNotification() {

        //Sets up the pending intent that is delivered when the notification is clicked
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity
                (this, NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        // Sets up the pending intent to cancel the notification,
        // delivered when the user dismisses the notification
        Intent cancelIntent = new Intent(ACTION_CANCEL_NOTIFICATION);
        PendingIntent cancelPendingIntent = PendingIntent.getBroadcast
                (this, NOTIFICATION_ID, cancelIntent, PendingIntent.FLAG_ONE_SHOT);

        //Sets up the pending intent associated with the Learn More notification action,
        //uses an implicit intent to go to the web.
        Intent learnMoreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(NOTIFICATION_GUIDE_URL));
        PendingIntent learnMorePendingIntent = PendingIntent.getActivity
                (this, NOTIFICATION_ID, learnMoreIntent, PendingIntent.FLAG_ONE_SHOT);

        //Sets up the pending intent to update the notification. Corresponds to a press of the
        //Update Me! button
        Intent updateIntent = new Intent(ACTION_UPDATE_NOTIFICATION);
        PendingIntent updatePendingIntent = PendingIntent.getBroadcast
                (this, NOTIFICATION_ID, updateIntent, PendingIntent.FLAG_ONE_SHOT);

        //Builds the notification with all of the parameters
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_text))
                .setSmallIcon(R.drawable.ic_android)
                .setContentIntent(notificationPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .addAction(R.drawable.ic_learn_more, getString(R.string.learn_more),
                        learnMorePendingIntent)
                .addAction(R.drawable.ic_update, getString(R.string.update), updatePendingIntent)
                .setDeleteIntent(cancelPendingIntent);

        //Delivers the notification
        mNotifyManager.notify(NOTIFICATION_ID, notifyBuilder.build());

        //Enables the update and cancel buttons but disables the "Notify Me!" button
        mNotifyButton.setEnabled(false);
        mUpdateButton.setEnabled(true);
        mCancelButton.setEnabled(true);

    }

    /**
     * OnClick method for the "Update Me!" button. Updates the existing notification to show a
     * picture.
     */
    private void updateNotification() {

        //Load the drawable resource into the a bitmap image
        Bitmap androidImage = BitmapFactory.decodeResource(getResources(),R.drawable.mascot_1);

        //Sets up the pending intent that is delivered when the notification is clicked
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent notificationPendingIntent = PendingIntent.getActivity
                (this, NOTIFICATION_ID, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);


        // Sets up the pending intent to cancel the notification,
        // delivered when the user dismisses the notification
        Intent cancelIntent = new Intent(ACTION_CANCEL_NOTIFICATION);
        PendingIntent cancelPendingIntent = PendingIntent.getBroadcast
                (this, NOTIFICATION_ID, cancelIntent, PendingIntent.FLAG_ONE_SHOT);

        //Sets up the pending intent associated with the Learn More notification action,
        //uses an implicit intent to go to the web.
        Intent learnMoreIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(NOTIFICATION_GUIDE_URL));
        PendingIntent learnMorePendingIntent = PendingIntent.getActivity
                (this, NOTIFICATION_ID, learnMoreIntent, PendingIntent.FLAG_ONE_SHOT);

        //Build the updated notification
        NotificationCompat.Builder notifyBuilder = new NotificationCompat.Builder(this)
                .setContentTitle(getString(R.string.notification_title))
                .setContentText(getString(R.string.notification_text))
                .setSmallIcon(R.drawable.ic_android)
                .setContentIntent(notificationPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setDeleteIntent(cancelPendingIntent)
                .addAction(R.drawable.ic_learn_more, getString(R.string.learn_more),
                        learnMorePendingIntent)
                .setStyle(new NotificationCompat.BigPictureStyle()
                        .bigPicture(androidImage)
                        .setBigContentTitle(getString(R.string.notification_updated)));


        //Disable the update button, leaving only the option to cancel
        mNotifyButton.setEnabled(false);
        mUpdateButton.setEnabled(false);
        mCancelButton.setEnabled(true);

        //Deliver the notification
        Notification myNotification = notifyBuilder.build();
        mNotifyManager.notify(NOTIFICATION_ID, myNotification);

    }

    /**
     * OnClick method for the "Cancel Me!" button. Cancels the notification
     */
    private void cancelNotification() {
        //Cancel the notification
        mNotifyManager.cancel(NOTIFICATION_ID);

        //Resets the buttons
        mNotifyButton.setEnabled(true);
        mUpdateButton.setEnabled(false);
        mCancelButton.setEnabled(false);
    }


    /**
     * The broadcast receiver class for notifications. Responds to the update notification and
     * cancel notification pending intents actions.
     */
    private class NotificationReceiver extends BroadcastReceiver {

        /**
         * Gets the action from the incoming broadcast intent and responds accordingly
         * @param context Context of the app when the broadcast is received.
         * @param intent The broadcast intent containing the action.
         */
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            switch (action){
                case ACTION_CANCEL_NOTIFICATION:
                    cancelNotification();
                    break;
                case ACTION_UPDATE_NOTIFICATION:
                    updateNotification();
                    break;
            }
        }
    }
}
