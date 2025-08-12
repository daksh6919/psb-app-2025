package com.ur4nium.daksh19

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

// This class correctly uses the SpamRepository you created in the other file.
class SpamCall : AppCompatActivity() {

    // This now correctly creates an instance of SpamRepository with a lowercase name.
    private val spamRepository = SpamRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spam_call)

        // Find views for the spam checker
        val phoneNumberEditText: EditText = findViewById(R.id.phoneEditText)
        val checkButton: Button = findViewById(R.id.CheckButton)
        val resultTextView: TextView = findViewById(R.id.ResultEditText)

        // Set up the button click listener
        checkButton.setOnClickListener {
            var enteredNumber = phoneNumberEditText.text.toString().trim()

            if (enteredNumber.isEmpty()) {
                resultTextView.text = "Please enter a number."
                resultTextView.setTextColor(Color.parseColor("#FFA500")) // Orange
                return@setOnClickListener
            }

            // Standardize the number to match Firestore's format (+91...)
            if (!enteredNumber.startsWith("+")) {
                enteredNumber = "+91$enteredNumber"
            }

            lifecycleScope.launch {
                resultTextView.text = "Checking..."
                resultTextView.setTextColor(Color.BLACK)

                // This now uses the correct variable name to call the function.
                val isSpam = spamRepository.checkIfSpam(enteredNumber)

                if (isSpam) {
                    resultTextView.text = "Verified as a SPAM Number."
                    resultTextView.setTextColor(Color.parseColor("#FF0000")) // Red
                } else {
                    resultTextView.text = "NOT a SPAM Number."
                    resultTextView.setTextColor(Color.parseColor("#008000")) // Green
                }
            }
        }

        // Other setup code
        setupBackButton()
        setupFaq()
        setupBottomNav()
        setupReportButton()
    }

    // --- Your other setup functions ---
    private fun setupBackButton() {
        val backButton: ImageView = findViewById(R.id.backButton)
        backButton.setOnClickListener { finish() }
    }

    private fun toggleVisibility(view: View) {
        view.visibility = if (view.visibility == View.VISIBLE) View.GONE else View.VISIBLE
    }

    private fun setupFaq() {
        val faq1Answer: TextView = findViewById(R.id.faq1_answer)
        val faq2Answer: TextView = findViewById(R.id.faq2_answer)
        val faq3Answer: TextView = findViewById(R.id.faq3_answer)
        val plusIcon1: ImageView = findViewById(R.id.plus_icon1)
        val plusIcon2: ImageView = findViewById(R.id.plus_icon2)
        val plusIcon3: ImageView = findViewById(R.id.plus_icon3)

        plusIcon1.setOnClickListener { toggleVisibility(faq1Answer) }
        plusIcon2.setOnClickListener { toggleVisibility(faq2Answer) }
        plusIcon3.setOnClickListener { toggleVisibility(faq3Answer) }
    }

    private fun setupReportButton() {
        val customButton2: RelativeLayout = findViewById(R.id.customButton2)
        customButton2.setOnClickListener {
            val intent = Intent(this, ReportSpamPhone::class.java)
            startActivity(intent)
        }
    }

    private fun setupBottomNav() {
        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNav.setOnItemSelectedListener { item ->
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