package com.forkingcode.espresso.contrib;

import android.support.test.espresso.NoMatchingViewException;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewFinder;
import android.support.test.espresso.util.HumanReadables;
import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

import static android.support.test.internal.util.Checks.checkNotNull;

/**
 * An action that wraps a check assertion against a matching descendant view. This class exists to
 * cover cases where the only option is to perform an action, but not a check() such as with
 * RecyclerViewActions
 */
public final class CheckDescendantViewAction implements ViewAction {

    private final Matcher<View> viewMatcher;
    private final ViewAssertion viewAssertion;

    public CheckDescendantViewAction(Matcher<View> viewMatcher, ViewAssertion viewAssertion) {
        this.viewMatcher = viewMatcher;
        this.viewAssertion = viewAssertion;
    }

    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return new StringDescription()
                .appendText("Check descendant view ")
                .appendDescriptionOf(viewMatcher)
                .toString();
    }

    @Override
    public void perform(UiController uiController, View view) {
        checkNotNull(viewAssertion);

        ViewFinder viewFinder = ViewFinderHelper.buildViewFinder(viewMatcher, view);

        View descendantView = null;
        NoMatchingViewException noMatchingViewException = null;

        try {
            descendantView = viewFinder.getView();
        }
        catch (NoMatchingViewException e) {
            noMatchingViewException = e;
        }

        try {
            viewAssertion.check(descendantView, noMatchingViewException);
        }
        catch (Throwable e) {
            throw new PerformException.Builder()
                    .withActionDescription(getDescription())
                    .withViewDescription(HumanReadables.describe(descendantView))
                    .withCause(e)
                    .build();
        }
    }
}
