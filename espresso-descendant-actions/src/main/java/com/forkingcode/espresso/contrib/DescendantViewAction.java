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

import android.support.test.espresso.PerformException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewFinder;
import android.support.test.espresso.util.HumanReadables;
import android.view.View;

import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

import static android.support.test.internal.util.Checks.checkNotNull;

/**
 * Perform one or more view actions on a descendant view. Useful when interacting with a recycler
 * view associated with cards with buttons or toggles
 */
/* package */ final class DescendantViewAction implements ViewAction {

    private final Matcher<View> viewMatcher;
    private final ViewAction viewAction;

    DescendantViewAction(Matcher<View> viewMatcher, ViewAction viewAction) {
        this.viewMatcher = viewMatcher;
        this.viewAction = viewAction;
    }

    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return new StringDescription()
                .appendText("Perform action ")
                .appendText(viewAction != null ? viewAction.getDescription() : "null")
                .appendText(" on descendant view ")
                .appendDescriptionOf(viewMatcher)
                .toString();
    }

    @Override
    public void perform(UiController uiController, View view) {

        checkNotNull(viewAction);

        ViewFinder viewFinder = ViewFinderHelper.buildViewFinder(viewMatcher, view);

        View descendantView = viewFinder.getView();

        if (descendantView == null) {
            throw new PerformException.Builder()
                    .withActionDescription(getDescription())
                    .withViewDescription(HumanReadables.describe(view))
                    .withCause(new RuntimeException("Descendant view not found"))
                    .build();
        }

        try {
            viewAction.perform(uiController, descendantView);
        }
        catch (Throwable t) {
            throw new PerformException.Builder()
                    .withActionDescription(getDescription())
                    .withViewDescription(HumanReadables.describe(descendantView))
                    .withCause(t)
                    .build();
        }
    }
}
