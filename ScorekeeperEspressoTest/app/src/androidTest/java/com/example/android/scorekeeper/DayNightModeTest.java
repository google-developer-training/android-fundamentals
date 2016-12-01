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

package com.example.android.scorekeeper;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

/**
 * This test clicks the Night Mode button in Scorekeeper and then
 * checks to see if "Day Mode" appears. It then clicks the
 * Day Mode button and checks to see if "Night Mode" appears.
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class DayNightModeTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void dayNightModeTest() {
        onView(allOf(withId(R.id.night_mode), withText("Night Mode"),
                withContentDescription("Night Mode"), isDisplayed())).perform(click());

        onView(allOf(withId(R.id.night_mode), withText("Day Mode"),
                withContentDescription("Day Mode"), isDisplayed()))
                .check(matches(withText("Day Mode")));

        onView(allOf(withId(R.id.night_mode), withText("Day Mode"),
                withContentDescription("Day Mode"), isDisplayed())).perform(click());

        onView(allOf(withId(R.id.night_mode), withText("Night Mode"),
                withContentDescription("Night Mode"), isDisplayed()))
                .check(matches(withText("Night Mode")));

    }

}
