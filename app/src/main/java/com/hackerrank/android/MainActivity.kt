package com.hackerrank.android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.hackerrank.android.databinding.ActivityMainBinding
import com.hackerrank.android.expenselist.ExpenseListFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the view hierarchy and bind the object to it
        binding = ActivityMainBinding.inflate(layoutInflater)
        // Set the view hierarchy as the current layout for the activity
        setContentView(binding.root)
        // Setup and add the HackerRank logo in the toolbar
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_logo)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        showExpenseList()
    }

    private fun showExpenseList() {
        supportFragmentManager.beginTransaction()
            .replace(binding.container.id, ExpenseListFragment()).commit()
    }
}