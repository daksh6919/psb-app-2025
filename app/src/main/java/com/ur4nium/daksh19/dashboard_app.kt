package com.ur4nium.daksh19

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ur4nium.daksh19.R

class dashboard_app : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard_app) // your XML filename

        // Search functionality


        // Row buttons

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
                    // Navigate to Home
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
