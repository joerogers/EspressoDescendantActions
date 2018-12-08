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
import static android.support.test.espresso.assertion.ViewAssertions.doesNotExist;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
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
    public void testRecyclerActionFavorite() throws Exception {
        // By default you could only test direct actions on a recycler view. For example
        // you could "click" the view, navigate to another activity and verify conditions.

        // Chaining several RecyclerViewActions together.
        onView(withId(R.id.recyclerView)).perform(

                // First position the recycler view. Necessary to allow the layout
                // manager perform the scroll operation
                scrollToPosition(15),

                actionOnItemAtPosition(15, DescendantViewActions.checkDescendantViewAction(
                        withId(R.id.title), matches(withText("Item 16")))),

                // Click the item to trigger navigation to detail view
                actionOnItemAtPosition(15, click())
        );

        // Check detail view
        onView(withId(R.id.favoriteStatus)).check(matches(withText(R.string.unfavorite)));

        // return to main activity
        pressBack();
    }

    @Test
    public void testRecyclerActionUnFavorite() throws Exception {
        // By default you could only test direct actions on a recycler view. For example
        // you could "click" the view, navigate to another activity and verify conditions.

        // Chaining several RecyclerViewActions together.
        onView(withId(R.id.recyclerView)).perform(

                // First position the recycler view. Necessary to allow the layout
                // manager perform the scroll operation
                scrollToPosition(10),

                // Using the check view action, you can now test conditions of the view at position 25
                actionOnItemAtPosition(10, DescendantViewActions.checkViewAction(matches(isCompletelyDisplayed()))),

                // With the descendant actions provided, you can check the status of a descendant view using
                // a standard check. Just provide way to find the descendant view and how you want to validate
                // the view.
                actionOnItemAtPosition(10, DescendantViewActions.checkDescendantViewAction(
                        withId(R.id.favoriteButton), matches(withContentDescription(R.string.favorite)))),

                // Or perform an action on a descendant view.
                actionOnItemAtPosition(10,
                        DescendantViewActions.performDescendantAction(withId(R.id.favoriteButton), click())),

                // With the descendant actions provided, you can check the status of a descendant view using
                // a standard check. Just provide way to find the descendant view and how you want to validate
                // the view.
                actionOnItemAtPosition(10, DescendantViewActions.checkDescendantViewAction(
                        withId(R.id.favoriteButton), matches(withContentDescription(R.string.unfavorite)))),

                // Click the item to trigger navigation to detail view
                actionOnItemAtPosition(10, click())
        );

        // Check detail view
        onView(withId(R.id.favoriteStatus)).check(matches(withText(R.string.favorite)));

        // return to main activity
        pressBack();
    }

    @Test
    public void testFavoriteToggle() throws Exception {
        // More advanced test case testing toggling the favorite status on a particular row

        // Chaining several actions together on the recycler view
        onView(withId(R.id.recyclerView)).perform(

                // First position the recycler view
                scrollToPosition(25),

                // Using the check view action, you can now test conditions of the view at position 25
                actionOnItemAtPosition(25, DescendantViewActions.checkViewAction(matches(isCompletelyDisplayed()))),

                // With the descendant actions provided, you can check the status of a descendant view using
                // a standard check. Just provide way to find the descendant view and how you want to validate
                // the view.
                actionOnItemAtPosition(25, DescendantViewActions.checkDescendantViewAction(
                        withId(R.id.favoriteButton), matches(withContentDescription(R.string.favorite)))),

                // Or perform an action on a descendant view.
                actionOnItemAtPosition(25,
                        DescendantViewActions.performDescendantAction(withId(R.id.favoriteButton), click())),

                // Then check to see the status change
                actionOnItemAtPosition(25, DescendantViewActions.checkDescendantViewAction(
                        withId(R.id.favoriteButton), matches(withContentDescription(R.string.unfavorite)))),

                // and non-existence of a view
                actionOnItemAtPosition(25, DescendantViewActions.checkDescendantViewAction(withId(R.id.favoriteStatus), doesNotExist()))

        );
    }
}