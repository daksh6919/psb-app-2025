package com.ur4nium.daksh19

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ur4nium.daksh19.databinding.ActivityDashboardAppBinding
import android.widget.ImageView

class dashboard_app : AppCompatActivity() {

    private lateinit var binding: ActivityDashboardAppBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDashboardAppBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageList = ArrayList<SlideModel>()
        val db = Firebase.firestore

        db.collection("dashboard_slider")
            .get()
            .addOnSuccessListener { documents ->
                if (documents.isEmpty) {
                    Log.d("Firestore", "No images found in the collection.")
                    return@addOnSuccessListener
                }
                for (document in documents) {
                    val imageUrl = document.getString("url")
                    if (imageUrl != null) {
                        imageList.add(SlideModel(imageUrl, ScaleTypes.CENTER_CROP))
                    }
                }
                binding.imageSlider.setImageList(imageList)
            }
            .addOnFailureListener { exception ->
                Log.w("Firestore", "Error getting documents: ", exception)
            }

        // --- FIX #1: Use View Binding to get the icon ---
        val dropdownIcon = findViewById<ImageView>(R.id.dropdown_icon)

        dropdownIcon.setOnClickListener { view ->
            val popup = PopupMenu(this, view)
            popup.menu.add("Hello")
            popup.menu.add("नमस्ते")
            popup.menu.add("ਸਤਿ ਸ੍ਰੀ ਅਕਾਲ")

            popup.setOnMenuItemClickListener { item ->
                when (item.title.toString()) {
                    "Hello" -> {
                        updateLocale("en")
                        true
                    }
                    "नमस्ते" -> {
                        updateLocale("hi")
                        true
                    }
                    "ਸਤਿ ਸ੍ਰੀ ਅਕਾਲ" -> {
                        updateLocale("pa")
                        true
                    }
                    else -> false
                }
            }
            popup.show()
        }

        // Your existing button listeners are all correct
        binding.customButton1.setOnClickListener {
            startActivity(Intent(this, Spam::class.java))
        }

        binding.customButton2.setOnClickListener {
            startActivity(Intent(this, Calculator::class.java))
        }

        binding.customButton3.setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java))
        }

        binding.customButton4.setOnClickListener {
            startActivity(Intent(this, GameActivity::class.java))
        }

        binding.customButton50.setOnClickListener {
            startActivity(Intent(this, ExampleActivity::class.java))
        }

        binding.customButton6.setOnClickListener {
            startActivity(Intent(this, LoanActivity::class.java))
        }

        binding.customButton7.setOnClickListener {
            startActivity(Intent(this, BudgetSavingActivity::class.java))
        }

        binding.customButton8.setOnClickListener {
            Toast.makeText(this, "INVESTMENT clicked", Toast.LENGTH_SHORT).show()
        }

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
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
    } // The onCreate method ends here

    // --- FIX #2: Add the missing updateLocale function ---
    private fun updateLocale(languageCode: String) {
        val localeList = LocaleListCompat.forLanguageTags(languageCode)
        AppCompatDelegate.setApplicationLocales(localeList)
    }
}