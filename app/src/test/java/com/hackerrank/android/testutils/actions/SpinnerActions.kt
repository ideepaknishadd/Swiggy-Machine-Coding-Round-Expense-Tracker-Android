package com.hackerrank.android.testutils.actions

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onData
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers.withSpinnerText
import com.hackerrank.android.testutils.click
import com.hackerrank.android.testutils.onView
import org.hamcrest.CoreMatchers.containsString
import org.hamcrest.CoreMatchers.instanceOf
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.core.AllOf.allOf


fun @receiver:IdRes Int.selectItem(text: String, inDialog: Boolean = false) {
    this.click(inDialog)
    onData(allOf(`is`(instanceOf(String::class.java)), `is`(text))).inRoot(RootMatchers.isDialog())
        .perform(ViewActions.click())
    onView(this, inDialog).check(matches(withSpinnerText(containsString(text))))
    ViewActions.closeSoftKeyboard()
}
