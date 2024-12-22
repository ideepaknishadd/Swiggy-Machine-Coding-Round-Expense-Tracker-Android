package com.hackerrank.android.expenselist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.hackerrank.android.addexpense.AddEditExpenseFragment
import com.hackerrank.android.common.VerticalDecoration
import com.hackerrank.android.databinding.FragmentExpenseListBinding
import com.hackerrank.android.expenselist.viewmodel.ExpenseListStateEvent
import com.hackerrank.android.expenselist.viewmodel.ExpensesViewModel

class ExpenseListFragment(viewModelFactory: ViewModelProvider.Factory? = null) : Fragment() {

    private val expensesViewModel by viewModels<ExpensesViewModel> {
        viewModelFactory ?: defaultViewModelProviderFactory
    }
    private lateinit var expensesAdapter: ExpensesAdapter

    @VisibleForTesting
    lateinit var binding: FragmentExpenseListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentExpenseListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeData()
        setupListeners()
    }

    private fun observeData() {
        expensesViewModel.addedExpenses.observe(viewLifecycleOwner) {
            expensesViewModel.updateView(it)
        }

        expensesViewModel.updateView.observe(viewLifecycleOwner) {
            when (it) {
                // TODO Write code to update the visibility of the views and update the expenses list in the Adapter
                is ExpenseListStateEvent.UpdateExpenses -> {
                    if (it.showList) {
                        binding.expenses.visibility = View.VISIBLE
                        binding.noExpensesText.visibility = View.GONE
                        expensesAdapter.submitList(it.list)
                    } else {
                        binding.expenses.visibility = View.GONE
                        binding.noExpensesText.visibility = View.VISIBLE
                    }
                }

                else -> {}
            }
        }
    }

    private fun setupViews() {
        expensesAdapter = ExpensesAdapter { position ->
            childFragmentManager.let { fragmentManager ->
                AddEditExpenseFragment.newInstance(
                    expensesViewModel.addedExpenses.value?.get(
                        position
                    )
                ) {
                    expensesViewModel.editExpense(position, it)
                }.show(fragmentManager, null)
            }
        }
        binding.expenses.layoutManager = LinearLayoutManager(requireContext())
        binding.expenses.addItemDecoration(VerticalDecoration(30))
        binding.expenses.adapter = expensesAdapter
    }

    private fun setupListeners() {
        binding.addExpenseBtn.setOnClickListener {
            childFragmentManager.let { fragmentManager ->
                // TODO Fix code to show Add Expense version of AddEditExpenseFragment
                /*AddEditExpenseFragment.newInstance(expensesViewModel.addedExpenses.value?.get(
                    0
                )) {
                    expensesViewModel.addExpense(it)
                }.show(fragmentManager, null)*/

                // In this part I have to pass null instead of list
                AddEditExpenseFragment.newInstance(null) {
                    expensesViewModel.addExpense(it)
                }.show(fragmentManager, null)

            }
        }
    }
}