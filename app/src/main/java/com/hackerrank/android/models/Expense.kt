package com.hackerrank.android.models

import java.io.Serializable

data class Expense(
    val description: String,
    val amount: Float,
    val category: String
) : Serializable
