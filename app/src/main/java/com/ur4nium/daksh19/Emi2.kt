package com.ur4nium.daksh19

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.widget.RelativeLayout
import com.ur4nium.daksh19.databinding.ActivityEmi2Binding
import kotlin.math.pow

class Emi2 : AppCompatActivity() {

    private lateinit var binding: ActivityEmi2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmi2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Back button
        binding.backButton.setOnClickListener { finish() }

        // Get values from intent
        val loanAmount = intent.getDoubleExtra("loan", 0.0)
        val annualInterest = intent.getDoubleExtra("interest", 0.0)
        val tenureYears = intent.getIntExtra("months", 0)
        val emi = intent.getDoubleExtra("emi", 0.0)


        // EMI calculation
        val tenureMonths = tenureYears * 12
        val monthlyRate = annualInterest / 12 / 100

        val emi1 = if (monthlyRate > 0) {
            (loanAmount * monthlyRate * (1 + monthlyRate).pow(tenureMonths)) /
                    ((1 + monthlyRate).pow(tenureMonths) - 1)
        } else {
            loanAmount / tenureMonths
        }

        // Display results
        binding.LoanText.text = "Loan Amount: ₹${"%.2f".format(loanAmount)}"
        binding.InterestText.text = "Interest Rate: ${annualInterest}%"
        binding.TenureText.text = "Tenure: $tenureMonths months"
        binding.EmiText.text = "Monthly EMI: ₹${"%.2f".format(emi)}"

        // Bottom navigation
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> { true }
                R.id.nav_settings -> { true }
                else -> true
            }
        }


    }
}
