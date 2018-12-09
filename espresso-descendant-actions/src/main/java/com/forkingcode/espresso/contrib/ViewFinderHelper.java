package com.forkingcode.espresso.contrib;

import androidx.test.espresso.ViewFinder;
import androidx.test.espresso.base.ViewFinderImpl_Factory;
import android.view.View;

import org.hamcrest.Matcher;

import javax.inject.Provider;

/**
 * Helper class that returns an implementation of Espresso's ViewFinder
 */
/* package */ final class ViewFinderHelper {

    static ViewFinder buildViewFinder(Matcher<View> viewMatcher, View view) {
        // Some risk since using Dagger generated factory directly.
        return ViewFinderImpl_Factory.create(new DescendantViewMatcherProvider(viewMatcher), new ViewProvider(view)).get();
    }

    // Provider classes to satisfy the Dagger generated factory method
    @SuppressWarnings("WeakerAccess")
    static class ViewProvider implements Provider<View> {

        private final View view;

        ViewProvider(View view) {
            this.view = view;
        }

        @Override
        public View get() {
            return view;
        }
    }

    @SuppressWarnings("WeakerAccess")
    static class DescendantViewMatcherProvider implements Provider<Matcher<View>> {
        private final Matcher<View> viewMatcher;

        DescendantViewMatcherProvider(Matcher<View> viewMatcher) {
            this.viewMatcher = viewMatcher;
        }

        @Override
        public Matcher<View> get() {
            return viewMatcher;
        }
    }
}
