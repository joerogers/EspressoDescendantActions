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
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.util.HumanReadables;
import android.view.View;

import org.hamcrest.Matcher;

/**
 * An action that wraps a check assertion against a view. This class exists to cover
 * cases where the only option is to perform an action, but not a check() such as with
 * RecyclerViewActions
 */
public class CheckAssertionAction implements ViewAction {

    private final ViewAssertion assertion;

    public CheckAssertionAction(ViewAssertion assertion) {
        this.assertion = assertion;
    }

    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Performing child view assertion";
    }

    @Override
    public void perform(UiController uiController, View view) {
        try {
            assertion.check(view, null);
        }
        catch (Throwable e) {
            throw new PerformException.Builder()
                    .withActionDescription(getDescription())
                    .withViewDescription(HumanReadables.describe(view))
                    .withCause(e)
                    .build();
        }
    }
}
