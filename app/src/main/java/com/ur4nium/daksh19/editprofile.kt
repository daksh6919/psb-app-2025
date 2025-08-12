package com.ur4nium.daksh19

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import com.ur4nium.daksh19.databinding.ActivityEditprofileBinding
import java.util.*


class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditprofileBinding
    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("EditProfileActivity", "onCreate started")

        binding = ActivityEditprofileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up window insets once during activity creation
        ViewCompat.setOnApplyWindowInsetsListener(binding.editprofile) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 🔙 Back button logic
        binding.backButton.setOnClickListener {
            finish()
        }

        // 📅 Birthdate picker
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

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {


                R.id.nav_home -> {
                    val intent = Intent(this, dashboard_app::class.java)
                    startActivity(intent)
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

        // 💾 Save button logic with phone number validation
        binding.saveButton.setOnClickListener {
            val name = binding.usernameEditText.text.toString().trim()
            val phone = binding.phoneEditText.text.toString().trim()
            val dob = binding.birthDateEditText.text.toString().trim()

            if (name.isEmpty() || phone.isEmpty() || dob.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // ⚠️ Phone number validation
            if (phone.length != 10) {
                Toast.makeText(this, "Phone number must be exactly 10 digits", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // ✅ Combine the country code and phone number
            val fullPhoneNumber = "+91" + phone

            val userUpdates = hashMapOf(
                "first" to name,
                "mobileno" to phone, // Save the combined number to Firestore
                "born" to dob
            )

            // ⭐ CRITICAL CHANGE: Use the current user's UID to update the document.
            val userId = FirebaseAuth.getInstance().currentUser?.uid

            if (userId != null) {
                db.collection("users")
                    .document(userId) // <-- This line finds the specific document
                    .set(userUpdates) // <-- This line updates the document
                    .addOnSuccessListener {
                        Toast.makeText(this, "Profile Updated", Toast.LENGTH_SHORT).show()
                        finish() // Go back to the profile screen
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this, "Error updating profile: $e", Toast.LENGTH_SHORT).show()
                    }
            } else {
                Toast.makeText(this, "User not authenticated.", Toast.LENGTH_SHORT).show()
            }
        }

    }
}