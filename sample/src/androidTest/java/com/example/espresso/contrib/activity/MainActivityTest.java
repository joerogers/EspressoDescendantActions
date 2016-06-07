/*
 * Copyright 2016 Joe Rogers
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.espresso.contrib.activity;

import android.support.test.rule.ActivityTestRule;

import com.example.espresso.contrib.R;
import com.forkingcode.espresso.contrib.DescendantViewActions;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.assertion.ViewAssertions.selectedDescendantsMatch;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Sample test case
 */
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class, true, true);

    @Test
    public void testRecyclerAction() throws Exception {
        // By default you could only test direct actions on a recycler view. For example
        // you could "click" the view, navigate to another activity and verify conditions.

        // First position recycler view
        onView(withId(R.id.recyclerView))
                .perform(
                        scrollToPosition(15)
                );

        // Perform action at position
        onView(withId(R.id.recyclerView))
                .perform(
                        actionOnItemAtPosition(15, click())
                );


        // Check detail view
        onView(withId(R.id.favoriteStatus)).check(matches(withText(R.string.unfavorite)));

        // return to main activity
        pressBack();
    }

    @Test
    public void testFavoriteToggle() throws Exception {
        // More advanced test case testing toggling the favorite status on a particular row

        // Again first position recycler view
        onView(withId(R.id.recyclerView))
                .perform(
                        scrollToPosition(25)
                );

        // With the descendant actions provided, you can check the status of a descendant view using
        // a standard check
        onView(withId(R.id.recyclerView))
                .perform(
                        actionOnItemAtPosition(25, DescendantViewActions.checkViewAction(
                                selectedDescendantsMatch(withId(R.id.favoriteButton),
                                        withContentDescription(R.string.favorite))))
                );

        // Or perform an action on a descendant view.
        onView(withId(R.id.recyclerView))
                .perform(
                        actionOnItemAtPosition(25,
                                DescendantViewActions.performDescendantAction(withId(R.id.favoriteButton), click()))
                );

        // Then check to see the status change
        onView(withId(R.id.recyclerView))
                .perform(
                        actionOnItemAtPosition(25, DescendantViewActions.checkViewAction(
                                selectedDescendantsMatch(withId(R.id.favoriteButton),
                                        withContentDescription(R.string.unfavorite))))
                );

    }
}