package com.ur4nium.daksh19

import com.ur4nium.daksh19.databinding.ActivityProfileBinding
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth // 🔥 Add this import
import com.ur4nium.daksh19.dashboard_app
import com.ur4nium.daksh19.ui.login.LoginActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 🔙 Back Button
        binding.backButton.setOnClickListener {
            finish()
        }

        // 📝 Edit Profile
        binding.editProfileButton.setOnClickListener {
            Toast.makeText(this, "Edit Profile Clicked", Toast.LENGTH_SHORT).show()
        }

        // 🌗 Dark Mode Switch

        val sharedPref = getSharedPreferences("Settings", MODE_PRIVATE)
        val isDarkMode = sharedPref.getBoolean("dark_mode", false) // false = default OFF

        binding.darkModeSwitch.isChecked = isDarkMode

        binding.darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Save dark mode state
            val sharedPref = getSharedPreferences("Settings", MODE_PRIVATE)
            with(sharedPref.edit()) {
                putBoolean("dark_mode", isChecked)
                apply()
            }

            Toast.makeText(this, if (isChecked) "Dark Mode ON" else "Dark Mode OFF", Toast.LENGTH_SHORT).show()
        }



        // 🚪 Logout
        binding.LogoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()

            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()

        }

        // ⚓ Bottom Navigation
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> {
                    val intent = Intent(this, dashboard_app::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }

        val editProfileButton = findViewById<Button>(R.id.editProfileButton)

        editProfileButton.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }
    }
}
