package com.ur4nium.daksh19

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.ur4nium.daksh19.databinding.ActivityDashboardAppBinding

class dashboard_app : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardAppBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ Correct View Binding setup
        binding = ActivityDashboardAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 🔘 Setup Image Slider





        // 🔘 Custom Button Click Listeners
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
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }

        binding.customButton5.setOnClickListener {
            Toast.makeText(this, "Loan clicked", Toast.LENGTH_SHORT).show()
        }

        binding.customButton6.setOnClickListener {
            Toast.makeText(this, "Saving clicked", Toast.LENGTH_SHORT).show()
        }

        binding.customButton7.setOnClickListener {
            val intent = Intent(this, Calculator::class.java)
            startActivity(intent)
            true
        }

        binding.customButton8.setOnClickListener {
            Toast.makeText(this, "Advisor clicked", Toast.LENGTH_SHORT).show()
        }

        // 🔘 Bottom Navigation
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_points -> {
                    Toast.makeText(this, "Points clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_search -> {
                    Toast.makeText(this, "Search clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_home -> {
                    Toast.makeText(this, "home clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_alerts -> {
                    Toast.makeText(this, "Alerts clicked", Toast.LENGTH_SHORT).show()
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