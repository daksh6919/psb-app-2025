package com.ur4nium.daksh19

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.ur4nium.daksh19.databinding.ActivityDashboardAppBinding

class dashboard_app : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardAppBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ✅ Correct View Binding setup first
        binding = ActivityDashboardAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // ✅ Image slider setup after layout is set
        val slideModels = arrayListOf(
            SlideModel(R.drawable.test1, ScaleTypes.FIT),
            SlideModel(R.drawable.test2, ScaleTypes.FIT),
            SlideModel(R.drawable.test3, ScaleTypes.FIT)
        )
        binding.imageSlider.setImageList(slideModels, ScaleTypes.FIT)

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
            startActivity(Intent(this, GameActivity::class.java))
        }

        binding.customButton5.setOnClickListener {
            Toast.makeText(this, "Loan clicked", Toast.LENGTH_SHORT).show()
        }

        binding.customButton6.setOnClickListener {
            val intent = Intent(this, BudgetSavingActivity::class.java)
            startActivity(intent)
            true
        }

        binding.customButton7.setOnClickListener {
            startActivity(Intent(this, Calculator::class.java))
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
                    Toast.makeText(this, "Home clicked", Toast.LENGTH_SHORT).show()
                    true
                }
                R.id.nav_alerts -> {
                    Toast.makeText(this, "Alerts clicked", Toast.LENGTH_SHORT).show()
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
