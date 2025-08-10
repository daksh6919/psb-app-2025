package com.ur4nium.daksh19

import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import coil.load // Import Coil for loading images
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class BudgetSavingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_budget_saving)

        // --- Your Existing Back Button Logic ---
        val backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            // This will close the current activity and return to the previous one
            finish()
        }

        // --- NEW CODE TO LOAD IMAGES FROM FIRESTORE ---

        // Get your ImageViews from the layout
        val budgetImageView: ImageView = findViewById(R.id.budgetImageView)
        val savingImageView: ImageView = findViewById(R.id.savingImageView)

        val db = Firebase.firestore

        // --- Load the First Image (from 'budgetsaving1') ---
        // V V V IMPORTANT: REPLACE THE DOCUMENT ID IN THE LINE BELOW V V V
        db.collection("budgetsaving1").document("savingimage2")
            .get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val imageUrl = document.getString("url")
                    budgetImageView.load(imageUrl) {
                        // Optional: you can add placeholder images here
                        // placeholder(R.drawable.placeholder_image)
                        // error(R.drawable.error_image)
                    }
                } else {
                    Log.d("Firestore", "Budget image document not found")
                }
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error loading budget image", e)
            }


        // --- Load the Second Image (from 'budgetandsaving') ---
        // This path is correct based on your earlier screenshot.
        db.collection("budgetandsaving").document("savingimage1")
            .get()
            .addOnSuccessListener { document ->
                if (document != null && document.exists()) {
                    val imageUrl = document.getString("url")
                    savingImageView.load(imageUrl) {
                        // Optional: you can add placeholder images here
                        // placeholder(R.drawable.placeholder_image)
                        // error(R.drawable.error_image)
                    }
                } else {
                    Log.d("Firestore", "Saving image document not found")
                }
            }
            .addOnFailureListener { e ->
                Log.w("Firestore", "Error loading saving image", e)
            }
    }
}