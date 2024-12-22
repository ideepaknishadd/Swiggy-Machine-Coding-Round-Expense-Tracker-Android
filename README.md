# HackerRank Kotlin Android

# Expense Tracker Application - Expense List Screen

## Task Description
Develop UI for the Expense list screen in an Expense Tracker application.

### Key Features
The Expense list screen comprises:
1. A list of expense items.
2. A floating action button to add a new expense.
3. Each expense item includes:
    - **Description**
    - **Amount**
    - **Category**
    - A button to edit the expense.

---

## Implementation Details

### **ExpenseListFragment.kt**
1. **Observe Data**:
    - Complete the `observeData()` method to consume the `updateView` LiveData value.
    - Display the UI based on the properties of `ExpenseListStateEvent.UpdateExpenses`.

      Properties:
        - **`showList`**: A boolean to determine whether to display the expenses (RecyclerView) and hide the `noExpenses` TextView.
        - **`list`**: A list to be submitted to the `expensesAdapter`.

2. **Fix Setup Listeners**:
    - Update the `setupListeners()` method to display the Add Expense version of `AddEditExpenseFragment`. This can be inferred from the code in `AddEditExpenseFragment`.

---

### **ExpensesViewModel.kt**
1. **Update View**:
    - Implement the `updateView()` method to publish values to the `updateView` LiveData based on the following conditions:
        - **`showList`**: Set to `true` if the list passed to the method is not empty, otherwise `false`.
        - **`list`**: Pass a copy of the list to ensure `adapter.submitList` triggers a view update in the adapter.

2. **Add Expense**:
    - Implement the `addExpense()` method to add an expense to the list inside the `addedExpenses` LiveData.
    - Republish the updated list.

3. **Edit Expense**:
    - Implement the `editExpense()` method to update an expense in the `addedExpenses` LiveData.
    - Republish the updated list.

---

### **ExpensesAdapter.kt**
1. **Update DiffUtil Logic**:
    - Update the `DiffUtil` logic of the adapter to ensure accurate updates in the RecyclerView when any items are changed.

---