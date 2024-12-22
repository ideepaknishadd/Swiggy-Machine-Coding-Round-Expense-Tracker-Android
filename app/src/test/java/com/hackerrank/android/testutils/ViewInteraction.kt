package com.hackerrank.android.testutils

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.hamcrest.Matchers.allOf

fun onView(@IdRes id: Int, inDialog: Boolean = false): ViewInteraction =
    Espresso.onView(withId(id)).apply { if (inDialog) inRoot(RootMatchers.isDialog()) }

fun onView(text: String): ViewInteraction = Espresso.onView(withText(text))

fun onView(@IdRes id: Int, text: String, inDialog: Boolean = false): ViewInteraction =
    Espresso.onView(allOf(withId(id), withText(text)))
        .apply { if (inDialog) inRoot(RootMatchers.isDialog()) }