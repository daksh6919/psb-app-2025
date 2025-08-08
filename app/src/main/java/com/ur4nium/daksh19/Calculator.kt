package com.ur4nium.daksh19

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ur4nium.daksh19.databinding.ActivityCalculatorBinding

class Calculator : AppCompatActivity() {

    private lateinit var binding: ActivityCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Back button
        binding.backButton.setOnClickListener {
            finish()
        }

        // Custom buttons
        binding.customButton1.setOnClickListener {
            Toast.makeText(this, "Spam clicked", Toast.LENGTH_SHORT).show()
        }

        binding.customButton2.setOnClickListener {
            Toast.makeText(this, "Cyber Help clicked", Toast.LENGTH_SHORT).show()
        }

        binding.customButton3.setOnClickListener {
            Toast.makeText(this, "Headlines clicked", Toast.LENGTH_SHORT).show()
        }

        binding.customButton4.setOnClickListener {
            Toast.makeText(this, "Simulation clicked", Toast.LENGTH_SHORT).show()
        }

        binding.customButton5.setOnClickListener {
            Toast.makeText(this, "Calculator clicked", Toast.LENGTH_SHORT).show()
        }

        // Bottom navigation
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_points -> {
                    // Navigate to Points
                    true
                }
                R.id.nav_search -> {
                    // Navigate to Search
                    true
                }
                R.id.nav_home -> {
                    val intent = Intent(this, dashboard_app::class.java)
                    startActivity(intent)
                    true
                    true
                }
                R.id.nav_alerts -> {
                    // Navigate to Alerts
                    true
                }
                R.id.nav_settings -> {
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }

                else -> false
            }
        }
    }
}
