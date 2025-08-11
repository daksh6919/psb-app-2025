package com.ur4nium.daksh19

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.graphics.Color
class SpamUrlActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_url_spam)

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

        val UrlEditText: EditText = findViewById(R.id.UrlEditText)
        val checkButton: Button = findViewById(R.id.CheckButton)
        val resultTextView: TextView = findViewById(R.id.ResultEditText)

        // A hardcoded list of "spam" numbers for demonstration
        val spamUrl = setOf(
            "http://fraud-bank-login-example.test"

        )

        // Set up the button click listener
        checkButton.setOnClickListener {
            // Get the text from the EditText and trim whitespace
            val enteredUrl = UrlEditText.text.toString().trim()

            if (enteredUrl.isEmpty()) {
                resultTextView.text = "Please enter Url."
                resultTextView.setTextColor(Color.parseColor("#FFA500")) // Orange
                return@setOnClickListener
            }

            // Check if the entered number is in our spam list
            if (spamUrl.contains(enteredUrl)) {
                resultTextView.text = "Verified as a SPAM URL."
                resultTextView.setTextColor(Color.parseColor("#FF0000")) // Red
            } else {
                resultTextView.text = "NOT a SPAM Url."
                resultTextView.setTextColor(Color.parseColor("#008000")) // Green
            }
        }

        val customButton2: RelativeLayout = findViewById(R.id.customButton2)

        customButton2.setOnClickListener {
            val intent = Intent(this, ReportUrlSpam::class.java)
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
