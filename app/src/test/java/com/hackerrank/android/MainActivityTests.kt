package com.hackerrank.android

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hackerrank.android.models.Expense
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTests {
    private val addEditExpenseRobot = AddEditExpenseRobot()
    private val expenseListRobot = ExpenseListRobot()

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun testAddExpenseAndTrack() {
        val expenseToAdd = Expense("Cola", 12.0f, "Food")
        expenseListRobot.clickAddExpense()
        addEditExpenseRobot.addNewExpense(expenseToAdd)
        expenseListRobot.verifyExpenseInList(0, expenseToAdd)
    }

    @Test
    fun testAddMultipleExpensesAndTrack() {
        val expensesToAdd = listOf(
            Expense("Cola", 12.0f, "Food"),
            Expense("Singapore", 100000.0f, "Travel"),
            Expense("Dolo 650", 60.5f, "Wellness"),
        )
        expensesToAdd.forEachIndexed { pos, it ->
            expenseListRobot.clickAddExpense()
            addEditExpenseRobot.addNewExpense(it)
            expenseListRobot.verifyExpenseInList(pos, it)
        }
    }

    @Test
    fun testAddAndEditExpenseDescription() {
        val expenseToAdd = Expense("Bali", 12000.0f, "Travel")
        val updatedExpense = expenseToAdd.copy(description = "France")
        expenseListRobot.run {
            addEditExpenseRobot.run {
                clickAddExpense()
                addNewExpense(expenseToAdd)
                verifyExpenseInList(0, expenseToAdd)
                clickEditExpense(0)
                editExpense(updatedExpense)
                verifyExpenseInList(0, updatedExpense)
            }
        }
    }

    @Test
    fun testAddAndEditExpenseAmount() {
        val expenseToAdd = Expense("Bali", 12000.0f, "Travel")
        val updatedExpense = expenseToAdd.copy(amount = 10000.0f)
        expenseListRobot.run {
            addEditExpenseRobot.run {
                clickAddExpense()
                addNewExpense(expenseToAdd)
                verifyExpenseInList(0, expenseToAdd)
                clickEditExpense(0)
                editExpense(updatedExpense)
                verifyExpenseInList(0, updatedExpense)
            }
        }
    }

    @Test
    fun testAddAndEditExpenseCategory() {
        val expenseToAdd = Expense("Bali", 12000.0f, "Travel")
        val updatedExpense = expenseToAdd.copy(category = "Others")
        expenseListRobot.run {
            addEditExpenseRobot.run {
                clickAddExpense()
                addNewExpense(expenseToAdd)
                verifyExpenseInList(0, expenseToAdd)
                clickEditExpense(0)
                editExpense(updatedExpense)
                verifyExpenseInList(0, updatedExpense)
            }
        }
    }
}