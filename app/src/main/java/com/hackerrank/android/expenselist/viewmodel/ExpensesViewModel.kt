package com.hackerrank.android.expenselist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hackerrank.android.models.Expense

class ExpensesViewModel : ViewModel() {
    val updateView: LiveData<ExpenseListStateEvent>
        get() = _updateView
    private val _updateView = MutableLiveData<ExpenseListStateEvent>()
    val addedExpenses: LiveData<List<Expense>>
        get() = _addedExpenses
    private val _addedExpenses = MutableLiveData<List<Expense>>()

    fun updateView(list: List<Expense>?) {
        /** TODO Write code to dispatch ExpenseListStateEvent.UpdateExpenses to the updateView LiveData based on [list] **/
        val showList = list?.isNotEmpty() ?: false
        _updateView.value = ExpenseListStateEvent.UpdateExpenses(showList, list ?: emptyList())
    }

    fun addExpense(expense: Expense) {
        /**
         * TODO Write code to dispatch latest expense list to the addedExpenses LiveData
         * by adding [expense] at the end of the list
         **/

        val currentList = _addedExpenses.value.orEmpty().toMutableList()
        currentList.add(expense)
        _addedExpenses.value = currentList
        updateView(currentList) // dispatch the updated list to the UI

    }

    fun editExpense(position: Int, expense: Expense) {
        /**
         * TODO Write code to dispatch latest expense list to the addedExpenses LiveData
         * by replacing [expense] at the [position] index of the list
         **/

        val currentList = _addedExpenses.value.orEmpty().toMutableList()

        if (position in currentList.indices) {
            currentList[position] = expense
            _addedExpenses.value = currentList
            updateView(currentList) // dispatch the updated list to the UI
        }

    }
}