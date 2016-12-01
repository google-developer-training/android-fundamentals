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

package com.example.android.powerreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Broadcast Receiver implementation that delivers a custom Toast
 * message when it receives any of the registered broadcasts
 */
public class CustomReceiver extends BroadcastReceiver {

    //String constant that defines the custom Broadcast Action
    static final String ACTION_CUSTOM_BROADCAST =
            "com.example.android.powerreceiver.ACTION_CUSTOM_BROADCAST";


    //Empty constructor
    public CustomReceiver() {
    }


    /**
     * This method gets called when the Broadcast Receiver receives a broadcast that
     * it is registered for.
     *
     * @param context The context of the application when the broadcast is received.
     * @param intent The broadcast is delivered in the form of an intent which contains
     *               the broadcast action.
     */
    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();
        String toastMessage = null;
        switch (intentAction){
            case Intent.ACTION_POWER_CONNECTED:
                toastMessage = context.getString(R.string.power_connected);
                break;
            case Intent.ACTION_POWER_DISCONNECTED:
                toastMessage = context.getString(R.string.power_disconnected);
                break;
            case ACTION_CUSTOM_BROADCAST:
                toastMessage = context.getString(R.string.custom_broadcast_toast);
                break;
        }

        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
    }

}

