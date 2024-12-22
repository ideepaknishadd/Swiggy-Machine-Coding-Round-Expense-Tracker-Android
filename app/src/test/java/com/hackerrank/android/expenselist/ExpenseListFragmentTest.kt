import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.core.view.isVisible
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.hackerrank.android.AddEditExpenseRobot
import com.hackerrank.android.ExpenseListRobot
import com.hackerrank.android.R
import com.hackerrank.android.addexpense.AddEditExpenseFragment
import com.hackerrank.android.expenselist.ExpenseListFragment
import com.hackerrank.android.expenselist.viewmodel.ExpenseListStateEvent
import com.hackerrank.android.expenselist.viewmodel.ExpensesViewModel
import com.hackerrank.android.models.Expense
import io.mockk.Runs
import io.mockk.clearAllMocks
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.mockkConstructor
import io.mockk.mockkObject
import io.mockk.slot
import io.mockk.spyk
import io.mockk.verify
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExpenseListFragmentTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var scenario: FragmentScenario<ExpenseListFragment>
    private lateinit var mockViewModel: ExpensesViewModel
    private val expenseListRobot = ExpenseListRobot()

    val _updateViewLiveData = MutableLiveData<ExpenseListStateEvent>()
    private val updateViewLiveData: LiveData<ExpenseListStateEvent> = _updateViewLiveData

    @Before
    fun setUp() {
        // Create a mock ViewModel using MockK
        mockViewModel = mockk(relaxed = true, relaxUnitFun = true) {
            every { updateView } returns updateViewLiveData
            every { addExpense(any()) } just Runs
        }

        // Set up the scenario with the mock ViewModel
        scenario = launchFragmentInContainer(themeResId = R.style.Theme_AppCompat) {
            ExpenseListFragment(getViewModelFactory())
        }
    }

    @After
    fun teardown() {
        clearAllMocks()
    }

    @Suppress("UNCHECKED_CAST")
    private fun getViewModelFactory() = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return mockViewModel as T
        }
    }

    @Test
    fun testExpenseList() {
        scenario.onFragment { fragment ->
            assertEquals(fragment.binding.expenses.isVisible, false)
            _updateViewLiveData.value =
                ExpenseListStateEvent.UpdateExpenses(true, listOf(Expense("Cola", 12.0f, "Food")))
            assertEquals(fragment.binding.expenses.isVisible, true)
            expenseListRobot.verifyExpenseInList(0, Expense("Cola", 12.0f, "Food"))
        }
    }

    @Test
    fun testAddExpenseButtonClick() {
        mockkObject(AddEditExpenseFragment)
        expenseListRobot.clickAddExpense()
        verify { AddEditExpenseFragment.newInstance(null, any()) }
    }
}
