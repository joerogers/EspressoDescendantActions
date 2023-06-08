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
package com.example.espresso.contrib.activity

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withContentDescription
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.espresso.contrib.R
import com.forkingcode.espresso.contrib.DescendantViewActions.checkDescendantViewAction
import com.forkingcode.espresso.contrib.DescendantViewActions.checkViewAction
import com.forkingcode.espresso.contrib.DescendantViewActions.performDescendantAction
import org.junit.Rule
import org.junit.Test

/**
 * Sample test case
 */
class MainActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testRecyclerActionFavorite() {
        // By default you could only test direct actions on a recycler view. For example
        // you could "click" the view, navigate to another activity and verify conditions.

        // Chaining several RecyclerViewActions together.
        Espresso.onView(withId(R.id.recyclerView))
            .perform(
                // First position the recycler view. Necessary to allow the layout
                // manager perform the scroll operation
                scrollToPosition<RecyclerView.ViewHolder>(15),
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    15,
                    checkDescendantViewAction(
                        withId(R.id.title),
                        matches(withText("Item 16"))
                    )
                ),

                // Click the item to trigger navigation to detail view
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    15,
                    ViewActions.click()
                )
            )

        // Check detail view
        Espresso.onView(withId(R.id.favoriteStatus))
            .check(
                matches(withText(R.string.unfavorite))
            )

        // return to main activity
        Espresso.pressBack()
    }

    @Test
    fun testRecyclerActionUnFavorite() {
        // By default you could only test direct actions on a recycler view. For example
        // you could "click" the view, navigate to another activity and verify conditions.

        // Chaining several RecyclerViewActions together.
        Espresso.onView(withId(R.id.recyclerView))
            .perform(
                // First position the recycler view. Necessary to allow the layout
                // manager perform the scroll operation
                scrollToPosition<RecyclerView.ViewHolder>(10),

                // Using the check view action, you can now test conditions of the view at position 25
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    10,
                    checkViewAction(matches(isCompletelyDisplayed()))
                ),

                // With the descendant actions provided, you can check the status of a descendant view using
                // a standard check. Just provide way to find the descendant view and how you want to validate
                // the view.
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    10,
                    checkDescendantViewAction(
                        withId(R.id.favoriteButton),
                        matches(withContentDescription(R.string.favorite))
                    )
                ),

                // Or perform an action on a descendant view.
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    10,
                    performDescendantAction(
                        withId(R.id.favoriteButton),
                        ViewActions.click()
                    )
                ),

                // With the descendant actions provided, you can check the status of a descendant view using
                // a standard check. Just provide way to find the descendant view and how you want to validate
                // the view.
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    10,
                    checkDescendantViewAction(
                        withId(R.id.favoriteButton),
                        matches(withContentDescription(R.string.unfavorite))
                    )
                ),

                // Click the item to trigger navigation to detail view
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    10,
                    ViewActions.click()
                )
            )

        // Check detail view
        Espresso.onView(withId(R.id.favoriteStatus)).check(
            matches(
                withText(R.string.favorite)
            )
        )

        // return to main activity
        Espresso.pressBack()
    }

    @Test
    fun testFavoriteToggle() {
        // More advanced test case testing toggling the favorite status on a particular row

        // Chaining several actions together on the recycler view
        Espresso.onView(withId(R.id.recyclerView))
            .perform(

                // First position the recycler view
                scrollToPosition<RecyclerView.ViewHolder>(25),  // Using the check view action, you can now test conditions of the view at position 25
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    25,
                    checkViewAction(matches(isCompletelyDisplayed()))
                ),

                // With the descendant actions provided, you can check the status of a descendant view using
                // a standard check. Just provide way to find the descendant view and how you want to validate
                // the view.
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    25,
                    checkDescendantViewAction(
                        withId(R.id.favoriteButton),
                        matches(withContentDescription(R.string.favorite))
                    )
                ),

                // Or perform an action on a descendant view.
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    25,
                    performDescendantAction(
                        withId(R.id.favoriteButton),
                        ViewActions.click()
                    )
                ),

                // Then check to see the status change
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    25,
                    checkDescendantViewAction(
                        withId(R.id.favoriteButton),
                        matches(withContentDescription(R.string.unfavorite))
                    )
                ),

                // and non-existence of a view
                actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    25,
                    checkDescendantViewAction(
                        withId(R.id.favoriteStatus),
                        doesNotExist()
                    )
                )
            )
    }
}