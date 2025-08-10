package com.ur4nium.daksh19

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.widget.RelativeLayout
import com.ur4nium.daksh19.databinding.ActivityCalculatorBinding
import com.ur4nium.daksh19.databinding.ActivityTaxOnIncome2Binding

class TaxOnIncome2 : AppCompatActivity() {

    private lateinit var binding: ActivityTaxOnIncome2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tax_on_income2)

        binding = ActivityTaxOnIncome2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Back button
        binding.backButton.setOnClickListener { finish()
        }

        val income = intent.getIntExtra("income", 0)
        val expense = intent.getIntExtra("expense", 0)
        val goal = intent.getIntExtra("goal", 0)
        val months = intent.getIntExtra("months", 0)
        val tax = intent.getIntExtra("tax", 0)

        val incomeView = findViewById<TextView>(R.id.incomeText)
        val expenseView = findViewById<TextView>(R.id.expenseText)
        val goalView = findViewById<TextView>(R.id.goalText)
        val monthsView = findViewById<TextView>(R.id.monthsText)


        incomeView.text = "Income: $${income}"
        expenseView.text = "Expense: $${expense}"
        goalView.text = "Goal: $${goal}"
        monthsView.text = "Months Required: ${months} months"



        val bottomNav = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> { true }
                R.id.nav_settings -> { true }
                else -> true
            }
        }

        val toolbar = findViewById<RelativeLayout>(R.id.toolbar)
        toolbar.setOnClickListener {
            onBackPressed()
        }

    }
}

