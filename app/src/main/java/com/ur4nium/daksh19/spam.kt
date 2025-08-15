package com.ur4nium.daksh19

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.ur4nium.daksh19.databinding.ActivityDashboardAppBinding
import com.ur4nium.daksh19.databinding.ActivitySpamBinding

class Spam : AppCompatActivity() {
    private lateinit var binding: ActivitySpamBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpamBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // --- NEW FIREBASE IMAGE SLIDER CODE STARTS HERE ---
        // This block replaces your old static image list.
        val imageList = ArrayList<SlideModel>()
        val db = Firebase.firestore

        db.collection("spamsection")
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
        // Top back button
        val backButton: ImageView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            onBackPressed()
        }

        // Title
        val title: TextView = findViewById(R.id.title)

        // Four icons
        val icon1: ImageButton = findViewById(R.id.icon1) // Call
        val icon2: ImageButton = findViewById(R.id.icon2) // Email
        val icon3: ImageButton = findViewById(R.id.icon3) // Link
       // Message

        // First big card
        val customButton1: RelativeLayout = findViewById(R.id.customButton1)

        // Second card


        // Bottom navigation
        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // ---- Click Listeners ----
        icon1.setOnClickListener {
            val intent = Intent(this, SpamCall::class.java)
            startActivity(intent)
        }
        icon2.setOnClickListener {
            val intent = Intent(this, SpamEmail::class.java)
            startActivity(intent)
        }
        icon3.setOnClickListener {
            val intent = Intent(this, SpamUrlActivity::class.java)
            startActivity(intent)
        }


        customButton1.setOnClickListener {
            Toast.makeText(this, "Check Spam card clicked", Toast.LENGTH_SHORT).show()
        }





        // ---- Bottom Navigation Handling ----
        bottomNav.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    startActivity(Intent(this, dashboard_app::class.java))
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
