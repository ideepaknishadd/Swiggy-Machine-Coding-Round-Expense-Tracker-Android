package com.hackerrank.android

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan

fun String.boldify(boldText: String): Spannable {
    return SpannableString(this).apply {
        setSpan(
            StyleSpan(Typeface.BOLD),
            this.indexOf(boldText),
            boldText.length,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
}