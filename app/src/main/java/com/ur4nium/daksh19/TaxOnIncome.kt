package com.ur4nium.daksh19

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ur4nium.daksh19.databinding.ActivityCalculatorBinding
import com.ur4nium.daksh19.databinding.ActivitySavingsBinding
import com.ur4nium.daksh19.databinding.ActivityTaxOnIncomeBinding

class TaxOnIncome : AppCompatActivity() {

    private lateinit var incomeEditText: EditText
    private lateinit var regimeText: TextView
    private lateinit var dropdownIcon: ImageView
    private lateinit var calculateButton: Button

    private lateinit var plusIcon1: ImageView
    private lateinit var plusIcon2: ImageView
    private lateinit var plusIcon3: ImageView

    private lateinit var faqAnswer1: TextView
    private lateinit var faqAnswer2: TextView
    private lateinit var faqAnswer3: TextView

    private lateinit var bottomNavigation: BottomNavigationView

    private var isExpanded1 = false
    private var isExpanded2 = false
    private var isExpanded3 = false

    private lateinit var binding: ActivityTaxOnIncomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tax_on_income)

        binding = ActivityTaxOnIncomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Back button
        binding.backButton.setOnClickListener { finish()
        }

        // View binding
        incomeEditText = findViewById(R.id.incomeEditText)
        regimeText = findViewById(R.id.regime_text)
        dropdownIcon = findViewById(R.id.dropdown_icon)
        calculateButton = findViewById(R.id.calculateButton)

        plusIcon1 = findViewById(R.id.plus_icon1)
        plusIcon2 = findViewById(R.id.plus_icon2)
        plusIcon3 = findViewById(R.id.plus_icon3)

        faqAnswer1 = findViewById(R.id.faq1_answer)
        faqAnswer2 = findViewById(R.id.faq2_answer)
        faqAnswer3 = findViewById(R.id.faq3_answer)

        bottomNavigation = findViewById(R.id.bottom_navigation)

        // Regime dropdown
        val taxRegimes = arrayOf("Old Regime", "New Regime")
        val popupMenu = PopupMenu(this, dropdownIcon)
        taxRegimes.forEach { regime ->
            popupMenu.menu.add(regime)
        }

        dropdownIcon.setOnClickListener {
            popupMenu.show()
        }

        popupMenu.setOnMenuItemClickListener { menuItem ->
            regimeText.text = menuItem.title
            true
        }

        // Calculate button
        calculateButton.setOnClickListener {
            val incomeStr = incomeEditText.text.toString()
            val regime = regimeText.text.toString()

            if (incomeStr.isBlank() || regime.isBlank()) {
                Toast.makeText(this, "Please enter all fields", Toast.LENGTH_SHORT).show()
            } else {
                val income = incomeStr.toLongOrNull()
                if (income == null) {
                    Toast.makeText(this, "Invalid income entered", Toast.LENGTH_SHORT).show()
                } else {
                    val tax = calculateTax(income, regime)
                    Toast.makeText(this, "Estimated Tax: ₹$tax", Toast.LENGTH_LONG).show()

                    // Pass data to next screen

                }
            }
        }


        // FAQ expand/collapse logic

        plusIcon1.setOnClickListener {
            isExpanded1 = !isExpanded1
            faqAnswer1.visibility = if (isExpanded1) View.VISIBLE else View.GONE
            plusIcon1.setImageResource(if (isExpanded1) R.drawable.minus else R.drawable.add_sharp)
        }

        plusIcon2.setOnClickListener {
            isExpanded2 = !isExpanded2
            faqAnswer2.visibility = if (isExpanded2) View.VISIBLE else View.GONE
            plusIcon2.setImageResource(if (isExpanded2) R.drawable.minus else R.drawable.add_sharp)
        }

        plusIcon3.setOnClickListener {
            isExpanded3 = !isExpanded3
            faqAnswer3.visibility = if (isExpanded3) View.VISIBLE else View.GONE
            plusIcon3.setImageResource(if (isExpanded3) R.drawable.minus else R.drawable.add_sharp)
        }

        // Bottom Navigation
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {

                R.id.nav_home -> {
                    startActivity(Intent(this, dashboard_app::class.java))
                    true
                }
                R.id.nav_alerts -> true
                R.id.nav_settings -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                else -> false
            }
        }
    }

    private fun calculateTax(income: Long, regime: String): Long {
        return if (regime == "Old Regime") {
            when {
                income <= 250000 -> 0
                income <= 500000 -> ((income - 250000) * 0.05).toLong()
                income <= 1000000 -> (12500 + (income - 500000) * 0.2).toLong()
                else -> (112500 + (income - 1000000) * 0.3).toLong()
            }
        } else {
            when {
                income <= 250000 -> 0
                income <= 500000 -> ((income - 250000) * 0.05).toLong()
                income <= 750000 -> (12500 + (income - 500000) * 0.1).toLong()
                income <= 1000000 -> (37500 + (income - 750000) * 0.15).toLong()
                income <= 1250000 -> (75000 + (income - 1000000) * 0.2).toLong()
                income <= 1500000 -> (125000 + (income - 1250000) * 0.25).toLong()
                else -> (187500 + (income - 1500000) * 0.3).toLong()
            }
        }
    }


}



