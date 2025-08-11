package com.ur4nium.daksh19

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.ur4nium.daksh19.databinding.ActivityProfileBinding
import com.ur4nium.daksh19.ui.login.LoginActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 🔙 Back Button
        binding.backButton.setOnClickListener {
            finish()
        }

        // 📝 Edit Profile Button
        binding.editProfileButton.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }



        // 🌗 Dark Mode Switch
        val sharedPref = getSharedPreferences("Settings", MODE_PRIVATE)
        val isDarkMode = sharedPref.getBoolean("dark_mode", false)
        binding.darkModeSwitch.isChecked = isDarkMode

        binding.darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            val editor = sharedPref.edit()
            editor.putBoolean("dark_mode", isChecked)
            editor.apply()
            Toast.makeText(this, if (isChecked) "Dark Mode ON" else "Dark Mode OFF", Toast.LENGTH_SHORT).show()
        }

        // 🚪 Logout
        binding.LogoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, first_page::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }



    // ⭐ Fetch data every time the screen becomes visible
    override fun onResume() {
        super.onResume()
        fetchUserProfile()
    }

    private fun fetchUserProfile() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            db.collection("users").document(currentUser.uid)
                .get()
                .addOnSuccessListener { document ->
                    // Check if the document exists before trying to read from it
                    if (document != null && document.exists()) {
                        val name = document.getString("first")
                        val phone = document.getString("mobileno")
                        val dob = document.getString("born")

                        // Update your TextViews with the fetched data
                        binding.usernameTextView.text = name
                        binding.phoneTextView.text = phone
                        binding.birthdateTextView.text = dob

                        Toast.makeText(this, "Profile data loaded!", Toast.LENGTH_SHORT).show()
                    } else {
                        // Handle the case where the user document does not exist
                        Toast.makeText(this, "User profile not found.", Toast.LENGTH_SHORT).show()
                    }
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Failed to load profile data: $e", Toast.LENGTH_SHORT).show()
                }
        }
    }
}

//hitesh 9.8.25