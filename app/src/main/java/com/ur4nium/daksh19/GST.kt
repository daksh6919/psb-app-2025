package com.ur4nium.daksh19

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ur4nium.daksh19.databinding.ActivityCalculatorBinding
import com.ur4nium.daksh19.databinding.ActivitySavingsBinding
import com.ur4nium.daksh19.databinding.ActivityGstBinding

class GST: AppCompatActivity() {

    private lateinit var amountEditText: EditText
    private lateinit var GSTText: TextView
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

    private lateinit var binding: ActivityGstBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGstBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Back button
        binding.backButton.setOnClickListener { finish() }

        // View binding (now through binding, not findViewById)
        amountEditText = binding.amountEditText
        GSTText = binding.GSTText
        dropdownIcon = binding.dropdownIcon
        calculateButton = binding.calculateButton

        plusIcon1 = binding.plusIcon1
        plusIcon2 = binding.plusIcon2
        plusIcon3 = binding.plusIcon3

        faqAnswer1 = binding.faq1Answer
        faqAnswer2 = binding.faq2Answer
        faqAnswer3 = binding.faq3Answer

        bottomNavigation = binding.bottomNavigation

        // Regime dropdown
        val taxRegimes = arrayOf("5%", "12%", "18%", "28%")
        val popupMenu = PopupMenu(this, dropdownIcon)
        taxRegimes.forEach { regime -> popupMenu.menu.add(regime) }

        dropdownIcon.setOnClickListener { popupMenu.show() }
        popupMenu.setOnMenuItemClickListener { menuItem ->
            GSTText.text = menuItem.title
            true
        }

        // Calculate button
        binding.calculateButton.setOnClickListener {
            try {
                val amountStr = binding.amountEditText.text.toString().trim()

                if (amountStr.isEmpty()) {
                    Toast.makeText(this, "Please enter amount", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val amount = amountStr.toDouble()

                val gstTextValue = binding.GSTText.text.toString().replace("%", "").trim()
                if (gstTextValue.isEmpty()) {
                    Toast.makeText(this, "Please select a GST rate", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val gstRate = gstTextValue.toDouble()

                val gstAmount = (amount * gstRate) / 100
                val totalAmount = amount + gstAmount

                binding.resultGST.text = "GST Amount: ₹${"%,.2f".format(gstAmount)}"
                binding.resultTotal.text = "Total Amount: ₹${"%,.2f".format(totalAmount)}"

            } catch (e: NumberFormatException) {
                Toast.makeText(this, "Please enter valid numbers", Toast.LENGTH_SHORT).show()
            }
        }


        // FAQ expand/collapse
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
                R.id.nav_points -> true
                R.id.nav_search -> true
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




}



