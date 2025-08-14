package com.ur4nium.daksh19

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.ur4nium.daksh19.databinding.ActivityProfileBinding
import android.net.Uri
import com.ur4nium.daksh19.ui.login.LoginActivity

class ProfileActivity : AppCompatActivity() {
    private lateinit var dropdownIcon: ImageView

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


        binding.AboutUsButton.setOnClickListener {
            val intent = Intent(this, AboutUs::class.java)
            startActivity(intent)
        }

        val emailText = findViewById<TextView>(R.id.customer)

        emailText.setOnClickListener {
            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:trishulxur4nium@gmail.com")
                putExtra(Intent.EXTRA_SUBJECT, "Feedback about TrishulX")
                putExtra(Intent.EXTRA_TEXT, "Hello Team,")
            }
            startActivity(Intent.createChooser(intent, "Send Email"))
        }






        // 🌗 Dark Mode Switch
        val sharedPref = getSharedPreferences("Settings", MODE_PRIVATE)



        // 🚪 Logout
        binding.LogoutButton.setOnClickListener {
            FirebaseAuth.getInstance().signOut()
            val intent = Intent(this, first_page::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)
        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, dashboard_app::class.java))
                    true
                }


                R.id.nav_settings -> {

                    true
                }
                else -> false
            }
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