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
package com.example.android.transitionsandanimations;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.transition.Explode;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;


public abstract class BaseClass extends AppCompatActivity{

    //Constant used for the Intent which dictates the kind of transition to use.
    private static final String TRANSITION_TYPE = "Transition Type";

    //Constants for shared element transitions.
    private static final String ANDROID_TRANSITION = "switchAndroid";
    private static final String BLUE_TRANSITION = "switchBlue";


    /**
     * Initializes the activity.
     * @param savedInstanceState Contains information about the state of the app.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Get the Transition type from the Intent and set it
        if (getIntent().hasExtra(TRANSITION_TYPE)) {
            switch (getIntent().getStringExtra(TRANSITION_TYPE)) {
                case "Explode":
                    getWindow().setEnterTransition(new Explode());
                    break;
                case "Slide":
                    getWindow().setEnterTransition(new Slide());
                    break;
                case "Fade":
                    getWindow().setEnterTransition(new Fade());
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * Method for relaunching the activity with an Explode animation
     * @param context The application context
     * @param imageView The imageView that was clicked
     */
    protected void explodeTransition(final Context context, ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Relaunches the activity with the transition information
                Intent intent = new Intent(context,context.getClass());
                intent.putExtra(TRANSITION_TYPE,"Explode");
                getWindow().setExitTransition(new Explode());
                startActivity(intent, ActivityOptions.
                        makeSceneTransitionAnimation((Activity)context).toBundle());
            }
        });
    }
    /**
     * Method for relaunching the activity with an Fade animation
     * @param context The application context
     * @param imageView The imageView that was clicked
     */
    protected void fadeTransition(final Context context, ImageView imageView){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Relaunches the activity with the transition information
                Intent intent = new Intent(context,context.getClass());
                intent.putExtra(TRANSITION_TYPE,"Fade");
                getWindow().setExitTransition(new Fade());
                startActivity(intent, ActivityOptions.
                        makeSceneTransitionAnimation((Activity)context).toBundle());
            }
        });
    }

    /**
     * Method for animating an ImageView 360 degrees and back
     * @param imageView The ImageView that was clicked
     */
    protected void rotateView(ImageView imageView){
        //Create the object animator with desired options
        final ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, View.ROTATION, 0f, 360f);
        animator.setDuration(1000);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(1);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Start it when the view is clicked
                animator.start();
            }
        });
    }

    /**
     * Method for creating a shared element transition between activities with two common elements
     * @param androidImage Android image that sets off the shared element transition when clicked
     * @param otherImage The ImageView that will swap with the Android ImageView
     * @param intent The intent containing the current and target activity
     * @param context The application context
     */
    protected void switchAnimation(final ImageView androidImage, final ImageView otherImage,
                                   final Intent intent, final Context context){
        androidImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Set the transition details and start the second activity
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation
                        ((Activity)context, Pair.create((View)androidImage,ANDROID_TRANSITION),
                                Pair.create((View)otherImage,BLUE_TRANSITION));
                startActivity(intent,options.toBundle());
            }
        });

    }

}
