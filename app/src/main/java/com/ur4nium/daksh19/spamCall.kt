package com.ur4nium.daksh19

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.graphics.Color
class SpamCallActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spam_call) // replace with your actual XML file name

        // Back button
        val backButton: ImageView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }




        // FAQ toggle logic
        val faq1Answer: TextView = findViewById(R.id.faq1_answer)
        val faq2Answer: TextView = findViewById(R.id.faq2_answer)
        val faq3Answer: TextView = findViewById(R.id.faq3_answer)

        val plusIcon1: ImageView = findViewById(R.id.plus_icon1)
        val plusIcon2: ImageView = findViewById(R.id.plus_icon2)
        val plusIcon3: ImageView = findViewById(R.id.plus_icon3)

        plusIcon1.setOnClickListener {
            toggleVisibility(faq1Answer)
        }
        plusIcon2.setOnClickListener {
            toggleVisibility(faq2Answer)
        }
        plusIcon3.setOnClickListener {
            toggleVisibility(faq3Answer)
        }

        val phoneNumberEditText: EditText = findViewById(R.id.phoneEditText)
        val checkButton: Button = findViewById(R.id.CheckButton)
        val resultTextView: TextView = findViewById(R.id.ResultEditText)

        // A hardcoded list of "spam" numbers for demonstration
        val spamNumbers = setOf(
            "6283231120",
            "9876543210",
            "5551234567"
        )

        // Set up the button click listener
        checkButton.setOnClickListener {
            // Get the text from the EditText and trim whitespace
            val enteredNumber = phoneNumberEditText.text.toString().trim()

            if (enteredNumber.isEmpty()) {
                resultTextView.text = "Please enter a number."
                resultTextView.setTextColor(Color.parseColor("#FFA500")) // Orange
                return@setOnClickListener
            }

            // Check if the entered number is in our spam list
            if (spamNumbers.contains(enteredNumber)) {
                resultTextView.text = "Verified as a SPAM Number."
                resultTextView.setTextColor(Color.parseColor("#FF0000")) // Red
            } else {
                resultTextView.text = "NOT a SPAM Number."
                resultTextView.setTextColor(Color.parseColor("#008000")) // Green
            }
        }

        val customButton2: RelativeLayout = findViewById(R.id.customButton2)

        customButton2.setOnClickListener {
            val intent = Intent(this, ReportSpamPhone::class.java)
            startActivity(intent)
        }
        // Bottom Navigation
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

    private fun toggleVisibility(view: View) {
        view.visibility = if (view.visibility == View.VISIBLE) View.GONE else View.VISIBLE
    }
}
