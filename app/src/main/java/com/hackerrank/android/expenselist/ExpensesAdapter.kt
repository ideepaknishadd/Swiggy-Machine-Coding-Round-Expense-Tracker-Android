package com.hackerrank.android.expenselist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.hackerrank.android.R
import com.hackerrank.android.boldify
import com.hackerrank.android.databinding.ExpenseItemBinding
import com.hackerrank.android.models.Expense

class ExpensesAdapter(private val itemClickListener: (Int) -> Unit) :
    ListAdapter<Expense, ExpensesAdapter.ExpenseViewHolder>(ExpenseDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpenseViewHolder {
        val binding = ExpenseItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ExpenseViewHolder(binding)
    }

    override fun onCurrentListChanged(
        previousList: MutableList<Expense>, currentList: MutableList<Expense>
    ) {
        super.onCurrentListChanged(previousList, currentList)
    }

    override fun onBindViewHolder(holder: ExpenseViewHolder, position: Int) {
        holder.onBind(currentList[position])
        holder.binding.editBtn.setOnClickListener {
            itemClickListener.invoke(position)
        }
    }

    class ExpenseViewHolder(val binding: ExpenseItemBinding) : ViewHolder(binding.root) {

        fun onBind(expense: Expense) {
            binding.apply {
                with(root.context) {
                    description.text = expense.description
                    amount.text = getString(
                        R.string.titled_amount, expense.amount.toString()
                    ).boldify("Amount:")
                    category.text =
                        getString(R.string.titled_category, expense.category).boldify("Category:")
                }
            }
        }
    }

    private class ExpenseDiffCallBack : DiffUtil.ItemCallback<Expense>() {
        // TODO update logic to make the Adapter update only the items changed.
        override fun areItemsTheSame(oldItem: Expense, newItem: Expense): Boolean {
            // ensure proper comparison of items
            return oldItem.description == newItem.description
        }

        // TODO update logic to make the Adapter update only the items changed.
        override fun areContentsTheSame(oldItem: Expense, newItem: Expense): Boolean {
            // compare all relevant fields to determine if content has changed
            return oldItem == newItem
        }
    }
}