package com.ur4nium.daksh19

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ur4nium.daksh19.databinding.ActivityDashboardAppBinding
import android.widget.RelativeLayout
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
                // Loop through documents and add URLs to the list
                for (document in documents) {
                    val imageUrl = document.getString("url")
                    if (imageUrl != null) {
                        imageList.add(SlideModel(imageUrl, ScaleTypes.CENTER_CROP))
                    }
                }
                // Set the fetched images to the slider
                binding.imageSlider.setImageList(imageList)
            }
            .addOnFailureListener { exception ->
                Log.w("Firestore", "Error getting documents: ", exception)
            }
        // --- NEW FIREBASE IMAGE SLIDER CODE ENDS HERE ---


        val dropdownIcon = findViewById<ImageView>(R.id.dropdown_icon)

        dropdownIcon.setOnClickListener { view ->
            val popup = PopupMenu(this, view)
            popup.menu.add("Good Day")
            popup.menu.add("नमस्ते")
            popup.menu.add("ਸਤ ਸ੍ਰੀ ਅਕਾਲ ")
            popup.setOnMenuItemClickListener { item ->
                Toast.makeText(this, "Selected: ${item.title}", Toast.LENGTH_SHORT).show()
                true
            }

            popup.show()
        }

        // ✅ ALL YOUR EXISTING BUTTON LISTENERS ARE KEPT BELOW
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
// Find your Loan button

        // ✅ YOUR EXISTING BOTTOM NAVIGATION CODE IS KEPT
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

    }
}