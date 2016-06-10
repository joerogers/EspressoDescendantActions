package com.forkingcode.espresso.contrib;

import android.support.test.espresso.ViewFinder;
import android.support.test.espresso.base.ViewFinderImpl_Factory;
import android.view.View;

import org.hamcrest.Matcher;

import javax.inject.Provider;

/**
 * Helper class that returns an implementation of Espresso's ViewFinder
 */
/* package */ final class ViewFinderHelper {

    public static ViewFinder buildViewFinder(Matcher<View> viewMatcher, View view) {
        // Some risk since using Dagger generated factory directly.
        return ViewFinderImpl_Factory.create(new DescendantViewMatcherProvider(viewMatcher), new ViewProvider(view)).get();
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
