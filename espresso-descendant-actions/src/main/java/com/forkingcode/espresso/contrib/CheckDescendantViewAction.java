package com.forkingcode.espresso.contrib;

import android.view.View;

import org.hamcrest.Matcher;
import org.hamcrest.StringDescription;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.ViewFinder;
import androidx.test.espresso.util.HumanReadables;

/**
 * An action that wraps a check assertion against a matching descendant view. This class exists to
 * cover cases where the only option is to perform an action, but not a check() such as with
 * RecyclerViewActions
 */
/* package */ final class CheckDescendantViewAction implements ViewAction {

    private final Matcher<View> viewMatcher;
    private final ViewAssertion viewAssertion;

    CheckDescendantViewAction(Matcher<View> viewMatcher, ViewAssertion viewAssertion) {
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
        if (viewAssertion == null) {
            throw new NullPointerException("View assertion is null");
        }

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
