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
import android.support.test.espresso.base.ViewFinderImpl_Factory;
import android.support.test.espresso.util.HumanReadables;
import android.view.View;

import org.hamcrest.Matcher;

import javax.inject.Provider;

/**
 * Perform one or more view actions on a descendant view. Useful when interacting with a recycler
 * view associated with cards with buttons or toggles
 */
public class DescendantViewAction implements ViewAction {

    private final DescendantViewMatcherProvider descendantViewMatcherProvider;
    private final ViewAction[] subActions;

    public DescendantViewAction(Matcher<View> viewMatcher, ViewAction[] subActions) {
        this.descendantViewMatcherProvider = new DescendantViewMatcherProvider(viewMatcher);
        this.subActions = subActions;
    }

    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Descendant action";
    }

    @Override
    public void perform(UiController uiController, View view) {

        ViewProvider viewProvider = new ViewProvider(view);

        // Use Dagger's generated factory to reuse the view finder code configured
        // to start at this view instead of root.
        // Slight risk to breakage if Dagger changes its signature
        ViewFinder viewFinder = ViewFinderImpl_Factory.create(descendantViewMatcherProvider, viewProvider).get();

        View childView = viewFinder.getView();

        if (childView == null) {
            throw new PerformException.Builder()
                    .withActionDescription(getDescription())
                    .withViewDescription(HumanReadables.describe(view))
                    .withCause(new RuntimeException("Descendant view not found"))
                    .build();
        }

        for (ViewAction subAction : subActions) {
            subAction.perform(uiController, childView);
        }
    }

    // Provider classes to satisfy the Dagger generated factory method
    static class ViewProvider implements Provider<View> {

        private final View view;

        public ViewProvider(View view) {
            this.view = view;
        }

        @Override
        public View get() {
            return view;
        }
    }

    static class DescendantViewMatcherProvider implements Provider<Matcher<View>> {
        private final Matcher<View> viewMatcher;

        public DescendantViewMatcherProvider(Matcher<View> viewMatcher) {
            this.viewMatcher = viewMatcher;
        }

        @Override
        public Matcher<View> get() {
            return viewMatcher;
        }
    }
}
