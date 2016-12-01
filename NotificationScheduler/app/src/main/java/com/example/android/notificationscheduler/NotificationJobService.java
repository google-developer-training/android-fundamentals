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

package com.example.android.notificationscheduler;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * The Service that JobScheduler runs once the conditions are met.
 * In this case it posts a notification.
 */
public class NotificationJobService extends JobService {

    /**
     * This is called by the system once it determines it is time to run the job.
     * @param jobParameters Contains the information about the job
     * @return Boolean indicating whether or not the job was offloaded to a separate thread.
     * In this case, it is false since the notification can be posted on the main thread.
     */
    @Override
    public boolean onStartJob(JobParameters jobParameters) {

        //Set up the notification content intent to launch the app when clicked
        PendingIntent contentPendingIntent = PendingIntent.getActivity
                (this, 0, new Intent(this, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle(getString(R.string.job_service))
                .setContentText(getString(R.string.job_running))
                .setContentIntent(contentPendingIntent)
                .setSmallIcon(R.drawable.ic_job_running)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setAutoCancel(true);

        manager.notify(0, builder.build());

        return false;
    }

    /**
     * Called by the system when the job is running but the conditions are no longer met.
     * In this example it is never called since the job is not offloaded to a different thread.
     * @param jobParameters Contains the information about the job
     * @return Boolean indicating whether the job needs rescheduling
     */
    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        return true;
    }
}
