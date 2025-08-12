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

class SpamEmail : AppCompatActivity() {

    // --- It's better to declare your views at the class level ---
    private lateinit var emailAddressEditText: EditText
    private lateinit var checkButton: Button
    private lateinit var resultTextView: TextView
    // --- Optional but recommended: Add a ProgressBar to your XML layout ---
    // private lateinit var loadingIndicator: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spam_email)

        // --- Initialize your views here ---
        emailAddressEditText = findViewById(R.id.EmailEditText)
        checkButton = findViewById(R.id.CheckButton)
        resultTextView = findViewById(R.id.ResultEditText)
        // loadingIndicator = findViewById(R.id.loadingIndicator) // Uncomment if you add a ProgressBar

        // Back button
        val backButton: ImageView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }

        // FAQ toggle logic (This remains the same)
        val faq1Answer: TextView = findViewById(R.id.faq1_answer)
        val faq2Answer: TextView = findViewById(R.id.faq2_answer)
        val faq3Answer: TextView = findViewById(R.id.faq3_answer)
        val plusIcon1: ImageView = findViewById(R.id.plus_icon1)
        val plusIcon2: ImageView = findViewById(R.id.plus_icon2)
        val plusIcon3: ImageView = findViewById(R.id.plus_icon3)
        plusIcon1.setOnClickListener { toggleVisibility(faq1Answer) }
        plusIcon2.setOnClickListener { toggleVisibility(faq2Answer) }
        plusIcon3.setOnClickListener { toggleVisibility(faq3Answer) }

        // Report Spam Button (This remains the same)
        val customButton2: RelativeLayout = findViewById(R.id.customButton2)
        customButton2.setOnClickListener {
            val intent = Intent(this, ReportEmailSpam::class.java)
            startActivity(intent)
        }

        // Bottom Navigation (This remains the same)
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

        // --- NEW: Set up the button click listener with API logic ---
        setupCheckButton()
    }

    private fun setupCheckButton() {
        checkButton.setOnClickListener {
            val enteredEmail = emailAddressEditText.text.toString().trim()

            if (enteredEmail.isEmpty()) {
                resultTextView.text = "Please enter an Email Address."
                resultTextView.setTextColor(Color.parseColor("#FFA500")) // Orange
                return@setOnClickListener
            }

            // --- Start the API call logic ---
            checkEmailWithApi(enteredEmail)
        }
    }

    private fun checkEmailWithApi(email: String) {
        // IMPORTANT: Paste your actual API Key here
        val myApiKey = "hfy6MB6S1wm3AQjHWFFe7gtQKJElxkvS"


        // Use lifecycleScope to launch a coroutine safely within the Activity's lifecycle
        lifecycleScope.launch {
            // Show a loading state on the UI
            resultTextView.text = "Checking..."
            resultTextView.setTextColor(Color.GRAY)
            // loadingIndicator.visibility = View.VISIBLE // Show progress bar

            try {
                val response = RetrofitClient.apiService.checkEmailReputation(myApiKey, email)

                // Hide loading state
                // loadingIndicator.visibility = View.GONE

                if (response.isSuccessful) {
                    val reputationData = response.body()
                    if (reputationData != null) {
                        // --- Update UI based on the API response ---
                        when {
                            !reputationData.isValid -> {
                                resultTextView.text = "Invalid Email Address format."
                                resultTextView.setTextColor(Color.parseColor("#FFA500")) // Orange
                            }
                            reputationData.fraudScore > 75 || reputationData.isDisposable -> {
                                resultTextView.text = "Verified as a SPAM Email Address."
                                resultTextView.setTextColor(Color.parseColor("#FF0000")) // Red
                            }
                            else -> {
                                resultTextView.text = "NOT a SPAM Email Address."
                                resultTextView.setTextColor(Color.parseColor("#008000")) // Green
                            }
                        }
                    } else {
                        // Handle case where API returns a success code but an empty body
                        resultTextView.text = "Error: Received empty response."
                        resultTextView.setTextColor(Color.parseColor("#FF0000")) // Red
                    }
                } else {
                    // Handle API errors (e.g., bad request, key invalid)
                    resultTextView.text = "Error: Could not verify email."
                    resultTextView.setTextColor(Color.parseColor("#FF0000")) // Red
                }
            } catch (e: Exception) {
                // Handle network errors (e.g., no internet connection)
                // loadingIndicator.visibility = View.GONE
                resultTextView.text = "Error: Network issue or host not found."
                resultTextView.setTextColor(Color.parseColor("#FF0000")) // Red
            }
        }
    }

    private fun toggleVisibility(view: View) {
        view.visibility = if (view.visibility == View.VISIBLE) View.GONE else View.VISIBLE
    }
}