package com.hackerrank.android.addexpense

import android.os.Bundle
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragment
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.hackerrank.android.AddEditExpenseRobot
import com.hackerrank.android.R
import com.hackerrank.android.databinding.FragmentAddExpenseBinding
import com.hackerrank.android.models.Expense
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddEditExpenseFragmentTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var scenario: FragmentScenario<AddEditExpenseFragment>
    private val addEditExpenseRobot = AddEditExpenseRobot()

    @Before
    fun setUp() {
        scenario = launchFragment(themeResId = R.style.Theme_AppCompat)
    }

    @Test
    fun testFragmentInitialization() {
        scenario.onFragment { fragment ->
            val binding = FragmentAddExpenseBinding.bind(fragment.requireView())
            // Verify that views are initialized properly
            assertEquals(binding.title.text, "Add Expense")
            assertEquals(binding.addEditBtn.text, "ADD")
        }
    }

    @Test
    fun testAvailableCategories() {
        scenario.onFragment {
            addEditExpenseRobot.checkAllCategories()
        }
    }

    @Test
    fun testExpenseAdded() {
        val expense = Expense("Cocacola", 10.0f, "Food")
        scenario.onFragment { fragment ->
            fragment.registerListener {
                assertThat(it).isEqualTo(expense)
            }
            addEditExpenseRobot.addNewExpense(expense)
            addEditExpenseRobot.verifyElementsNotVisible()
        }
    }

    @Test
    fun testEditExpense() {
        val expense = Expense("Description1", 50.0f, "Food")
        val args = Bundle().apply {
            putSerializable(AddEditExpenseFragment.ARG_EXPENSE, expense)
        }

        scenario = launchFragment(themeResId = R.style.Theme_AppCompat, fragmentArgs = args)

        scenario.onFragment { fragment ->
            val binding = FragmentAddExpenseBinding.bind(fragment.requireView())
            // Verify that views are initialized properly for editing expense
            assertEquals(binding.title.text, "Edit Expense")
            assertEquals(binding.addEditBtn.text, "SAVE")
            assertEquals(binding.description.text.toString(), expense.description)
            assertEquals(binding.expenseAmount.text.toString(), expense.amount.toString())
            assertEquals(binding.expenseCategory.selectedItem.toString(), expense.category)
        }
    }

    @Test
    fun testExpenseEdited() {
        val expense = Expense("Description1", 50.0f, "Food")
        val newExpense = Expense("Description2", 60.0f, "Travel")

        val args = Bundle().apply {
            putSerializable(AddEditExpenseFragment.ARG_EXPENSE, expense)
        }

        scenario = launchFragment(themeResId = R.style.Theme_AppCompat, fragmentArgs = args)

        scenario.onFragment { fragment ->
            fragment.registerListener {
                assertThat(it).isEqualTo(newExpense)
            }
            addEditExpenseRobot.editExpense(newExpense)
            addEditExpenseRobot.verifyElementsNotVisible()
        }
    }
}
