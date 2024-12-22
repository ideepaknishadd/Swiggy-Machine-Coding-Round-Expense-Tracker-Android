package com.hackerrank.android.testutils.actions

import androidx.annotation.IdRes
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import com.hackerrank.android.testutils.onView

fun @receiver:IdRes Int.enterText(text: String, inDialog: Boolean = false) {
    onView(this, inDialog).perform(clearText(), replaceText(text))
    closeSoftKeyboard()
}



