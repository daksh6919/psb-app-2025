package com.ur4nium.daksh19

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore

class ReportEmailSpam : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reprotspamemail)

        // UI references
        val backButton: ImageView = findViewById(R.id.backButton)
        val emailEditText: EditText = findViewById(R.id.EmailEditText)
        val reportButton: Button = findViewById(R.id.ReportButton)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // Back button
        backButton.setOnClickListener { finish() }

        // Report button click
        reportButton.setOnClickListener {
            val emailAddress = emailEditText.text.toString().trim()
            val userId = FirebaseAuth.getInstance().currentUser?.uid

            if (userId == null) {
                Toast.makeText(this, "You must be logged in to report.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(emailAddress).matches()) {
                Toast.makeText(this, "Please enter a valid email address.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val db = FirebaseFirestore.getInstance()
            val docRef = db.collection("email_reports").document(emailAddress)

            db.runTransaction { transaction ->
                val snapshot = transaction.get(docRef)

                if (snapshot.exists()) {
                    val currentCount = snapshot.getLong("reportcount") ?: 0
                    val reporters = snapshot.get("reporters") as? MutableList<String> ?: mutableListOf()

                    if (!reporters.contains(userId)) {
                        reporters.add(userId)
                        transaction.update(docRef, mapOf(
                            "reportcount" to currentCount + 1,
                            "last_reported_at" to FieldValue.serverTimestamp(),
                            "reporters" to reporters
                        ))
                    }
                } else {
                    val newData = mapOf(
                        "reportcount" to 1,
                        "last_reported_at" to FieldValue.serverTimestamp(),
                        "reporters" to listOf(userId)
                    )
                    transaction.set(docRef, newData)
                }

                // Ensure we return something (avoids Kotlin 'if' expression error)
                null
            }.addOnSuccessListener {
                Toast.makeText(this, "Email reported successfully!", Toast.LENGTH_SHORT).show()
                finish()
            }.addOnFailureListener { e ->
                Toast.makeText(this, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }

        // Bottom navigation
        bottomNavigationView.setOnItemSelectedListener { item ->
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
