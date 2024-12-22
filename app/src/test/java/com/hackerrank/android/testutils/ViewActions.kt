package com.hackerrank.android.testutils

import androidx.annotation.IdRes
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility

fun @receiver:IdRes Int.click(inDialog: Boolean = false) {
    onView(this, inDialog).check { view, _ ->
        view.performClick()
    }
}

fun String.click() {
    onView(this).perform(ViewActions.click())
}

fun @receiver:IdRes Int.checkText(matchingText: String, inDialog: Boolean) {
    onView(this, matchingText, inDialog).check(matches(isDisplayed()))
}

fun @receiver:IdRes Int.checkIfVisible() {
    onView(this).check(matches(isDisplayed()))
}

fun @receiver:IdRes Int.checkIfNotVisible() {
    onView(this).check(ViewAssertions.doesNotExist())
}

fun @receiver:IdRes Int.checkIfGone() {
    onView(this).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
}