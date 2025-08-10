package com.ur4nium.daksh19

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ur4nium.daksh19.databinding.ActivityEmiBinding
import kotlin.math.pow

class Emi : AppCompatActivity() {

    private lateinit var binding: ActivityEmiBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ Use correct EMI layout
        binding = ActivityEmiBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Back button
        binding.backButton.setOnClickListener { finish() }

        // Inputs
        val loanEditText = findViewById<EditText>(R.id.LoanEditText)
        val interestEditText = findViewById<EditText>(R.id.InterestEditText)
        val tenureEditText = findViewById<EditText>(R.id.TenureEditText)
        val calculateButton = findViewById<Button>(R.id.calculateButton)

        // FAQ Toggle
        val faqPairs = listOf(
            Pair(findViewById<ImageView>(R.id.plus_icon1), findViewById<TextView>(R.id.faq1_answer)),
            Pair(findViewById<ImageView>(R.id.plus_icon2), findViewById<TextView>(R.id.faq2_answer)),
            Pair(findViewById<ImageView>(R.id.plus_icon3), findViewById<TextView>(R.id.faq3_answer)),
            Pair(findViewById<ImageView>(R.id.plus_icon4), findViewById<TextView>(R.id.faq4_answer)),
            Pair(findViewById<ImageView>(R.id.plus_icon5), findViewById<TextView>(R.id.faq5_answer))
        )

        faqPairs.forEach { (icon, answer) ->
            icon.setOnClickListener {
                if (answer.visibility == View.GONE) {
                    answer.visibility = View.VISIBLE
                    icon.setImageResource(R.drawable.minus)
                } else {
                    answer.visibility = View.GONE
                    icon.setImageResource(R.drawable.add_sharp)
                }
            }
        }

        // ✅ EMI Calculation Logic
        calculateButton.setOnClickListener {calculateButton.setOnClickListener {
            val loanAmount = loanEditText.text.toString().toDoubleOrNull()
            val annualInterest = interestEditText.text.toString().toDoubleOrNull()
            val tenureYears = tenureEditText.text.toString().toIntOrNull()

            if (loanAmount == null || annualInterest == null || tenureYears == null || tenureYears <= 0) {
                Toast.makeText(this, "Please enter valid numbers and tenure > 0", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val tenureMonths = tenureYears * 12
            val monthlyRate = annualInterest / 12 / 100

            val emi1 = if (monthlyRate > 0) {
                (loanAmount * monthlyRate * (1 + monthlyRate).pow(tenureMonths)) /
                        ((1 + monthlyRate).pow(tenureMonths) - 1)
            } else {
                loanAmount / tenureMonths
            }

            binding.resultGST.text = "EMI Per Month: ${"%,.2f".format(emi1)}"


        }

        }


        // Bottom Navigation
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, dashboard_app::class.java))
                    true
                }
                R.id.nav_settings -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }
}
