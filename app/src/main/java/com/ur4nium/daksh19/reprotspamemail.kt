package com.ur4nium.daksh19
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ReportEmailSpam : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Link the Kotlin code to your XML layout file
        setContentView(R.layout.activity_reprotspamemail)

        // 1. Get references to the UI elements
        val backButton: ImageView = findViewById(R.id.backButton)
        val EmailEditText: EditText = findViewById(R.id.EmailEditText)
        val reportButton: Button = findViewById(R.id.ReportButton)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // 2. Set up the back button's click listener
        backButton.setOnClickListener {

            finish()
        }

        // 3. Set up the "Report" button's click listener
        reportButton.setOnClickListener {
            // Get the phone number from the EditText and trim any whitespace
            val EmailAddress = EmailEditText.text.toString().trim()

            if (EmailAddress.isNotEmpty()) {
                // Here is where you would add your logic to report the number.
                // For this example, we'll just show a Toast message.
                // You would replace this with an API call to a backend service.
                Toast.makeText(this, "Reporting spam Email: $EmailAddress", Toast.LENGTH_SHORT).show()
            } else {
                // If the EditText is empty, show a warning to the user.
                Toast.makeText(this, "Please enter a Email-Address to report.", Toast.LENGTH_SHORT).show()
            }
        }

        // 4. Handle clicks on the bottom navigation bar
        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                // Add cases for each menu item in your bottom_nav_menu
                R.id.nav_home -> {
                    startActivity(Intent(this, dashboard_app::class.java))
                    true
                }
                R.id.nav_settings -> {
                    startActivity(Intent(this, ProfileActivity::class.java))
                    true
                }
                // Add more cases for other menu items
                else -> false
            }
        }
    }
}