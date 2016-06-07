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

package com.forkingcode.espresso.contrib;

import android.support.annotation.NonNull;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.action.ViewActions;
import android.view.View;

import org.hamcrest.Matcher;

/**
 * {@link ViewAction}s to interact with descendant view. For example when working with a
 * RecyclerView composed of cards, each card may have actions that could be performed. The current
 * RecyclerViewActions in espresso contrib only lets you perform an action on the top level view
 * for each item in the RecyclerView.
 * <p>
 * This class provides two additional actions so that a test could "check" the state the specific
 * view at position XX in a recycler view or one of its descendants. It also provides the capability
 * to perform an action on a descendant view. While this class is designed to fill the gap
 * missing with RecyclerViews, there is no dependencies on the Recycler view and these actions
 * could be performed on any view that has descendants.
 * </p>
 */
public class DescendantViewActions {

    /**
     * Perform an action on a descendant view.
     *
     * @param viewMatcher used to select the descendant view.
     * @param actions     one or more actions to execute
     * @return A ViewAction object that should be performed on the parent view
     */
    public static ViewAction performDescendantAction(@NonNull Matcher<View> viewMatcher, @NonNull ViewAction... actions) {
        return ViewActions.actionWithAssertions(new DescendantViewAction(viewMatcher, actions));
    }

    /**
     * Perform a check against a view that only allows actions such as a view found by
     * RecyclerViewActions
     *
     * @param assertion the assertion to check.
     * @return The ViewAction to perform
     */
    public static ViewAction checkViewAction(ViewAssertion assertion) {
        return ViewActions.actionWithAssertions(new CheckAssertionAction(assertion));
    }
}
