package com.ur4nium.daksh19

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ur4nium.daksh19.databinding.ActivityCalculatorBinding
import com.ur4nium.daksh19.databinding.ActivitySavingsBinding


class SavingsActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySavingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_savings) // Replace with your actual XML file name

        binding = ActivitySavingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener { finish()
        }

        // Inputs
        val incomeEditText = findViewById<EditText>(R.id.incomeEditText)
        val expenseEditText = findViewById<EditText>(R.id.ExpenseEditText)
        val savingEditText = findViewById<EditText>(R.id.SavingEditText)
        val calculateButton = findViewById<Button>(R.id.calculateButton)

        // FAQ Items
        val faqPairs = listOf(
            Pair(findViewById<ImageView>(R.id.plus_icon1), findViewById<TextView>(R.id.faq1_answer)),
            Pair(findViewById<ImageView>(R.id.plus_icon2), findViewById<TextView>(R.id.faq2_answer)),
            Pair(findViewById<ImageView>(R.id.plus_icon3), findViewById<TextView>(R.id.faq3_answer)),
            Pair(findViewById<ImageView>(R.id.plus_icon4), findViewById<TextView>(R.id.faq4_answer)),
            Pair(findViewById<ImageView>(R.id.plus_icon5), findViewById<TextView>(R.id.faq5_answer))
        )

        // Toggle FAQ visibility
        faqPairs.forEach { (icon, answer) ->
            icon.setOnClickListener {
                if (answer.visibility == View.GONE) {
                    answer.visibility = View.VISIBLE
                    icon.setImageResource(R.drawable.minus) // Change icon to minus
                } else {
                    answer.visibility = View.GONE
                    icon.setImageResource(R.drawable.add_sharp) // Change back to plus
                }
            }
        }

        // Calculate Button Logic
        calculateButton.setOnClickListener {
            val incomeStr = incomeEditText.text.toString()
            val expenseStr = expenseEditText.text.toString()
            val savingGoalStr = savingEditText.text.toString()

            if (incomeStr.isEmpty() || expenseStr.isEmpty() || savingGoalStr.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val income = incomeStr.toDouble()
            val expense = expenseStr.toDouble()
            val savingGoal = savingGoalStr.toDouble()
            val monthlySaving = income - expense

            if (monthlySaving <= 0) {
                Toast.makeText(this, "Your expenses are equal or more than income", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            val monthsNeeded = savingGoal / monthlySaving

            binding.resultGST.text = "Months Required: ${"%,.2f".format(monthsNeeded)}"


        }

        // Bottom Navigation
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_points -> {
                    // Navigate to Points
                    true
                }
                R.id.nav_search -> {
                    // Navigate to Search
                    true
                }
                R.id.nav_home -> {
                    val intent = Intent(this, dashboard_app::class.java)
                    startActivity(intent)
                    true
                }
                R.id.nav_alerts -> {
                    // Navigate to Alerts
                    true
                }
                R.id.nav_settings -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
}
