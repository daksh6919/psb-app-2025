package com.ur4nium.daksh19

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ur4nium.daksh19.databinding.ActivityEditprofileBinding
import java.util.*

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditprofileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditprofileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()

            ViewCompat.setOnApplyWindowInsetsListener(binding.editprofile) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
                insets
            }

            // 🔹 Birthdate picker
            binding.birthDateEditText.setOnClickListener {
                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH)
                val day = calendar.get(Calendar.DAY_OF_MONTH)

                val datePicker =
                    DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                        val birthdate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                        binding.birthDateEditText.setText(birthdate)
                    }, year, month, day)

                datePicker.datePicker.maxDate = System.currentTimeMillis()
                datePicker.show()
            }

            // 🔘 Save button logic
            binding.saveButton.setOnClickListener {
                val name = binding.usernameEditText.text.toString().trim()
                val phone = binding.phoneEditText.text.toString().trim()
                val dob = binding.birthDateEditText.text.toString().trim()

                Toast.makeText(this, "Profile Updated:\n$name\n$phone\n$dob", Toast.LENGTH_SHORT)
                    .show()
            }

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
}

//<!--daksh-->
