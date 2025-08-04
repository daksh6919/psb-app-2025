package com.ur4nium.daksh19
import com.ur4nium.daksh19.databinding.ActivityProfileBinding
import android.os.Bundle
import android.content.Intent


import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ur4nium.daksh19.dashboard_app


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
        binding.darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            Toast.makeText(this, if (isChecked) "Dark Mode ON" else "Dark Mode OFF", Toast.LENGTH_SHORT).show()
        }

        // 🚪 Logout


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

    }
}
