package com.hackerrank.android

import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.hackerrank.android.models.Expense
import com.hackerrank.android.testutils.actions.clickNthItemChild
import com.hackerrank.android.testutils.checkIfVisible
import com.hackerrank.android.testutils.click
import com.hackerrank.android.testutils.matchers.viewHolderChildMatches
import com.hackerrank.android.testutils.onView
import org.hamcrest.Matchers

class ExpenseListRobot {
    private val expenseList = R.id.expenses
    private val addExpense = R.id.add_expense_btn

    fun clickAddExpense() {
        addExpense.click()
    }

    fun verifyExpenseListVisible() {
        expenseList.checkIfVisible()
    }

    fun verifyExpenseInList(position: Int, expense: Expense) {
        verifyExpenseListVisible()
        onView(expenseList).check(
            matches(
                Matchers.allOf(
                    viewHolderChildMatches(
                        position,
                        R.id.description,
                        withText(expense.description)
                    ),
                    viewHolderChildMatches(
                        position,
                        R.id.amount,
                        withText("Amount: ₹${expense.amount}")
                    ),
                    viewHolderChildMatches(
                        position,
                        R.id.category,
                        withText("Category: ${expense.category}")
                    )
                )
            )
        )
    }

    fun clickEditExpense(position: Int) {
        expenseList.clickNthItemChild(position, R.id.edit_btn)
    }
}