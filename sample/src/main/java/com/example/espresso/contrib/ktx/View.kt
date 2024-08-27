package com.example.espresso.contrib.ktx

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.doOnAttach

fun View.doOnApplyWindowInsetsToPadding(
    f: (
        View,
        WindowInsetsCompat,
        InitialPadding
    ) -> Unit
) {
    // Create a snapshot of the view's padding state
    val initialPadding = InitialPadding(
        left = paddingLeft,
        top = paddingTop,
        right = paddingRight,
        bottom = paddingBottom
    )

    // Set an actual OnApplyWindowInsetsListener which proxies to the given
    // lambda, also passing in the original padding state
    ViewCompat.setOnApplyWindowInsetsListener(this) { view, insets ->
        f(view, insets, initialPadding)
        // Always return the insets, so that children can also use them
        insets
    }
    // request some insets once view attached to window
    doOnAttach { view ->
        ViewCompat.requestApplyInsets(view)
    }
}

data class InitialPadding(val left: Int, val top: Int, val right: Int, val bottom: Int)