package com.hackerrank.android.testutils.matchers

import android.view.View
import android.widget.RatingBar
import org.hamcrest.Description
import org.hamcrest.TypeSafeMatcher

fun withRating(expectedRating: Float) = object : TypeSafeMatcher<View>() {
    override fun describeTo(description: Description) {
        description.appendText("")
    }

    override fun matchesSafely(item: View): Boolean {
        return item is RatingBar && item.rating == expectedRating
    }
}