package com.ur4nium.daksh19

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SpamUrlActivity : AppCompatActivity() {

    // --- Views declared at the class level ---
    private lateinit var urlEditText: EditText
    private lateinit var checkButton: Button
    private lateinit var resultTextView: TextView

    // --- A dedicated Retrofit instance for the Google Safe Browse API ---
    private val safeBrowseService: GoogleSafeBrowseApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://safebrowsing.googleapis.com/")
            // Google's base URL
            .addConverterFactory(GsonConverterFactory.create())
            // If you still have the logging interceptor, you can add the OkHttpClient here too
            .build()
            .create(GoogleSafeBrowseApi::class.java)
    }

      override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_url_spam)

        // Initialize views
        urlEditText = findViewById(R.id.UrlEditText)
        checkButton = findViewById(R.id.CheckButton)
        resultTextView = findViewById(R.id.ResultEditText)

        // Back button
        val backButton: ImageView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }

        // FAQ toggle logic
        setupFaqs()




        // Bottom Navigation
        setupBottomNavigation()

        // Handle incoming shared URL/text intent
        val intent = intent
        val action = intent.action
        val type = intent.type

        if (Intent.ACTION_SEND == action && type == "text/plain") {
            val sharedText = intent.getStringExtra(Intent.EXTRA_TEXT)
            if (sharedText != null) {
                urlEditText.setText(sharedText)
                checkUrlWithGoogle(sharedText.trim())  // Auto-check shared URL
            }
        }

        // Check button click listener
        checkButton.setOnClickListener {
            val enteredUrl = urlEditText.text.toString().trim()
            if (enteredUrl.isEmpty()) {
                resultTextView.text = "Please enter Url."
                resultTextView.setTextColor(Color.parseColor("#FFA500")) // orange color
                return@setOnClickListener
            }
            checkUrlWithGoogle(enteredUrl)
        }
    }


    private fun checkUrlWithGoogle(url: String) {
        // <<< IMPORTANT: Paste your Google API Key here
        val myApiKey = "AIzaSyDp04TLaN-G_xJcHNUmLTgd4MlBy_GSaiw"

        resultTextView.text = "Checking..."
        resultTextView.setTextColor(Color.GRAY)

        // 1. Build the request object that Google's API expects
        val request = SafeBrowseRequest(
            client = ClientInfo(clientId = "com.ur4nium.daksh19", clientVersion = "1.0.0"),
            threatInfo = ThreatInfo(
                threatTypes = listOf("MALWARE", "SOCIAL_ENGINEERING", "UNWANTED_SOFTWARE", "POTENTIALLY_HARMFUL_APPLICATION"),
                platformTypes = listOf("ANY_PLATFORM"),
                threatEntries = listOf(ThreatEntry(url = url))
            )
        )

        // 2. Launch a coroutine to make the network call
        lifecycleScope.launch {
            try {
                val response = safeBrowseService.checkUrl(myApiKey, request)
                if (response.isSuccessful) {
                    val responseBody = response.body()

                    // If the 'matches' list is null or empty, the URL is considered safe
                    if (responseBody?.matches.isNullOrEmpty()) {
                        resultTextView.text = "URL is SAFE."
                        resultTextView.setTextColor(Color.parseColor("#008000")) // Green
                    } else {
                        // If there is a match, the URL is dangerous
                        val threatType = responseBody?.matches?.get(0)?.threatType ?: "DANGEROUS"
                        resultTextView.text = "Verified as a SPAM URL ($threatType)."
                        resultTextView.setTextColor(Color.parseColor("#FF0000")) // Red
                    }
                } else {
                    resultTextView.text = "Error: API could not check URL."
                    resultTextView.setTextColor(Color.parseColor("#FF0000"))
                    Log.e("SafeBrowse", "API Error: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                resultTextView.text = "Error: Network issue."
                resultTextView.setTextColor(Color.parseColor("#FF0000"))
                Log.e("SafeBrowse", "Network Exception", e)
            }
        }
    }

    // --- Helper functions to keep onCreate clean ---

    private fun setupFaqs() {
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

    private fun setupBottomNavigation() {
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