package com.ur4nium.daksh19

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class ReportSpamPhone : AppCompatActivity() {

    // 1. Create an instance of the repository to talk to the database
    private val spamRepository = SpamRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reportspamphone)

        // Get references to the UI elements
        val backButton: ImageView = findViewById(R.id.backButton)
        val phoneEditText: EditText = findViewById(R.id.phoneEditText)
        val reportButton: Button = findViewById(R.id.ReportButton)

        backButton.setOnClickListener {
            finish()
        }

        // 2. Set up the "Report" button's click listener
        reportButton.setOnClickListener {
            // Get the phone number that the user typed on THIS screen
            var phoneNumber = phoneEditText.text.toString().trim()

            if (phoneNumber.isNotEmpty()) {
                // Standardize the number to match Firestore's format (+91...)
                if (!phoneNumber.startsWith("+")) {
                    phoneNumber = "+91$phoneNumber"
                }

                // Use lifecycleScope to call the repository function
                lifecycleScope.launch {
                    try {
                        spamRepository.reportNumber(phoneNumber)
                        Toast.makeText(this@ReportSpamPhone, "Report successful!", Toast.LENGTH_LONG).show()
                        finish() // Close this screen and go back
                    } catch (e: Exception) {
                        Toast.makeText(this@ReportSpamPhone, "Report failed: ${e.message}", Toast.LENGTH_LONG).show()
                    }
                }
            } else {
                Toast.makeText(this, "Please enter a phone number to report.", Toast.LENGTH_SHORT).show()
            }
        }

        setupBottomNav()
    }

    private fun setupBottomNav() {
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigationView.setOnItemSelectedListener { item ->
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