package com.hackerrank.android

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers
import androidx.test.espresso.matcher.ViewMatchers
import com.hackerrank.android.models.Expense
import com.hackerrank.android.testutils.actions.enterText
import com.hackerrank.android.testutils.actions.selectItem
import com.hackerrank.android.testutils.checkIfNotVisible
import com.hackerrank.android.testutils.checkText
import com.hackerrank.android.testutils.click
import org.hamcrest.CoreMatchers
import org.hamcrest.core.AllOf.allOf

class AddEditExpenseRobot {
    private val title = R.id.title
    private val description = R.id.description
    private val amount = R.id.expense_amount
    private val category = R.id.expense_category
    private val addEdit = R.id.add_edit_btn

    fun addNewExpense(expense: Expense) {
        expense.let {
            title.checkText("Add Expense", true)
            description.enterText(it.description, true)
            amount.enterText(it.amount.toString(), true)
            category.selectItem(expense.category, true)
            addEdit.click(true)
        }
    }

    fun editExpense(expense: Expense) {
        expense.let {
            title.checkText("Edit Expense", true)
            description.enterText(it.description, true)
            amount.enterText(it.amount.toString(), true)
            category.selectItem(expense.category, true)
            addEdit.click(true)
        }
    }

    fun verifyElementsNotVisible() {
        title.checkIfNotVisible()
        description.checkIfNotVisible()
        amount.checkIfNotVisible()
        category.checkIfNotVisible()
        addEdit.checkIfNotVisible()
    }

    fun checkAllCategories() {
        category.click(true)
        val categories = listOf("Food", "Travel", "Wellness", "Others")
        categories.forEach {
            Espresso.onData(
                allOf(
                    CoreMatchers.`is`(CoreMatchers.instanceOf(String::class.java)),
                    CoreMatchers.`is`(it)
                )
            ).inRoot(RootMatchers.isDialog()).check(matches(ViewMatchers.isDisplayed()))
        }

    }
}