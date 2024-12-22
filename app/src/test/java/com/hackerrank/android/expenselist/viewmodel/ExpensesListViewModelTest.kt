import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.hackerrank.android.expenselist.viewmodel.ExpenseListStateEvent
import com.hackerrank.android.expenselist.viewmodel.ExpensesViewModel
import com.hackerrank.android.models.Expense
import io.mockk.spyk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExpensesViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: ExpensesViewModel

    @Before
    fun setup() {
        viewModel = spyk()
    }

    @Test
    fun `test updateView with emptyList`() {
        viewModel.updateView(emptyList())
        assertThat(viewModel.updateView.value).isEqualTo(
            ExpenseListStateEvent.UpdateExpenses(
                false,
                emptyList()
            )
        )
    }

    @Test
    fun `test updateView with valid list`() {
        val validList = listOf(Expense("Cola", 12.0f, "Food"))
        viewModel.updateView(validList)
        assertThat(viewModel.updateView.value).isEqualTo(
            ExpenseListStateEvent.UpdateExpenses(
                true,
                validList
            )
        )
    }

    @Test
    fun `test addExpense`() {
        val expense1 = Expense("Expense 1", 75.0f, "Food")
        val expense2 = Expense("Expense 2", 75.0f, "Food")
        viewModel.addExpense(expense1)
        viewModel.addExpense(expense2)
        val expectedList = listOf(expense1, expense2)
        assertEquals(expectedList, viewModel.addedExpenses.value)
    }

    @Test
    fun `test editExpense`() {
        val expense = Expense("Expense 1", 75.0f, "Food")
        val editedExpense = Expense("Expense 2", 75.0f, "Food")
        viewModel.addExpense(expense)
        viewModel.editExpense(0, editedExpense)
        assertEquals(listOf(editedExpense), viewModel.addedExpenses.value)
    }
}
