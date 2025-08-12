package com.ur4nium.daksh19

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ur4nium.daksh19.databinding.ActivityCalculatorBinding

class Calculator : AppCompatActivity() {

    private lateinit var binding: ActivityCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // imageslider

        val imageList = ArrayList<SlideModel>()
        val db = Firebase.firestore

        db.collection("calculator")
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

                 //image slider code ends here

        // Back button
        binding.backButton.setOnClickListener { finish() 
        }

        // Custom buttons
        binding.customButton1.setOnClickListener {
            val intent = Intent(this, TaxOnIncome::class.java)
            startActivity(intent)
            true
        }

        binding.customButton2.setOnClickListener {
            val intent = Intent(this, Emi::class.java)
            startActivity(intent)
            true
        }

        binding.customButton3.setOnClickListener {
            val intent = Intent(this, GST::class.java)
            startActivity(intent)
            true
        }

        binding.customButton4.setOnClickListener {
            val intent = Intent(this, SavingsActivity::class.java)
            startActivity(intent)
            true
        }





        // Bottom navigation
        val bottomNavigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {


                R.id.nav_home -> {
                    val intent = Intent(this, dashboard_app::class.java)
                    startActivity(intent)
                    true
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
