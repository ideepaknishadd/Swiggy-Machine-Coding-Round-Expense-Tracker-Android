package com.hackerrank.android.addexpense

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.hackerrank.android.R
import com.hackerrank.android.databinding.FragmentAddExpenseBinding
import com.hackerrank.android.models.Expense

class AddEditExpenseFragment internal constructor() : BottomSheetDialogFragment() {
    private lateinit var expenseSavedListener: (Expense) -> Unit

    fun registerListener(listener: (Expense) -> Unit) {
        expenseSavedListener = listener
    }

    private lateinit var binding: FragmentAddExpenseBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddExpenseBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        binding.apply {
            // Create an ArrayAdapter using the string array and a default spinner layout.
            ArrayAdapter.createFromResource(
                requireContext(),
                R.array.categories,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears.
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner.
                expenseCategory.adapter = adapter
            }
            arguments?.getSerializable(ARG_EXPENSE)?.let {
                val expense = it as Expense
                title.setText(R.string.edit_expense_title)
                description.setText(expense.description)
                expenseAmount.setText(expense.amount.toString())
                expenseCategory.setSelection(
                    resources.getStringArray(R.array.categories).indexOf(expense.category)
                )
                addEditBtn.setText(R.string.save_expense_cta)
            } ?: run {
                title.setText(R.string.add_expense_title)
                addEditBtn.setText(R.string.add_expense_cta)
            }
            addEditBtn.setOnClickListener {
                val finalExpense = Expense(
                    description.text.toString(),
                    expenseAmount.text.toString().toFloat(),
                    expenseCategory.selectedItem.toString()
                )
                expenseSavedListener.invoke(finalExpense)
                dismiss()
            }
        }
    }

    companion object {

        const val ARG_EXPENSE = "ARG_EXPENSE"

        fun newInstance(
            expense: Expense? = null,
            listener: (Expense) -> Unit
        ): AddEditExpenseFragment {
            val instance = AddEditExpenseFragment()
            instance.registerListener(listener)
            expense?.let {
                instance.arguments = Bundle().apply {
                    putSerializable(ARG_EXPENSE, it)
                }
            }
            return instance
        }
    }
}