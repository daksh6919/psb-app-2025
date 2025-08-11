package com.ur4nium.daksh19

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class SpamCallActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spam_call) // replace with your actual XML file name

        // Back button
        val backButton: ImageView = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            finish()
        }




        // FAQ toggle logic
        val faq1Answer: TextView = findViewById(R.id.faq1_answer)
        val faq2Answer: TextView = findViewById(R.id.faq2_answer)
        val faq3Answer: TextView = findViewById(R.id.faq3_answer)

        val plusIcon1: ImageView = findViewById(R.id.plus_icon1)
        val plusIcon2: ImageView = findViewById(R.id.plus_icon2)
        val plusIcon3: ImageView = findViewById(R.id.plus_icon3)

        plusIcon1.setOnClickListener {
            toggleVisibility(faq1Answer)
        }
        plusIcon2.setOnClickListener {
            toggleVisibility(faq2Answer)
        }
        plusIcon3.setOnClickListener {
            toggleVisibility(faq3Answer)
        }

        // Bottom Navigation
        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)
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

    private fun toggleVisibility(view: View) {
        view.visibility = if (view.visibility == View.VISIBLE) View.GONE else View.VISIBLE
    }
}
