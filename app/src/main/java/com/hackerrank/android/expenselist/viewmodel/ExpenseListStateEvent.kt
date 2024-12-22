package com.hackerrank.android.expenselist.viewmodel

import com.hackerrank.android.models.Expense

sealed class ExpenseListStateEvent {
    data class UpdateExpenses(val showList: Boolean, val list: List<Expense>) :
        ExpenseListStateEvent()
}
