package com.ur4nium.daksh19

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class Spam : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spam) // Replace with your XML filename

        // Top back button
        val backButton: ImageView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            onBackPressed()
        }

        // Title
        val title: TextView = findViewById(R.id.title)

        // Four icons
        val icon1: ImageButton = findViewById(R.id.icon1) // Call
        val icon2: ImageButton = findViewById(R.id.icon2) // Email
        val icon3: ImageButton = findViewById(R.id.icon3) // Link
        val icon4: ImageButton = findViewById(R.id.icon4) // Message

        // First big card
        val customButton1: RelativeLayout = findViewById(R.id.customButton1)

        // Second card


        // Bottom navigation
        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // ---- Click Listeners ----
        icon1.setOnClickListener {
            val intent = Intent(this, SpamCall::class.java)
            startActivity(intent)
        }
        icon2.setOnClickListener {
            val intent = Intent(this, SpamEmailActivity::class.java)
            startActivity(intent)
        }
        icon3.setOnClickListener {
            val intent = Intent(this, SpamUrlActivity::class.java)
            startActivity(intent)
        }
        icon4.setOnClickListener {
            Toast.makeText(this, "Message icon clicked", Toast.LENGTH_SHORT).show()
        }

        customButton1.setOnClickListener {
            Toast.makeText(this, "Check Spam card clicked", Toast.LENGTH_SHORT).show()
        }





        // ---- Bottom Navigation Handling ----
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
