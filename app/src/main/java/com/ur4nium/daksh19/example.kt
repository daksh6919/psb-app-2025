package com.ur4nium.daksh19

import android.content.Intent
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class ExampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_example) // change if your XML name is different

        // ----- Header -----
        val notificationIcon: ImageView = findViewById(R.id.notification) // add id to ImageView in XML if missing

        // ----- Search Bar -----



        // ----- Popular Lessons -----
        val lessonImage1: ImageView = findViewById(R.id.lesson_image1)
        val lessonTitle1: TextView = findViewById(R.id.lesson_title1)
        val lessonSubtitle1: TextView = findViewById(R.id.lesson_subtitle1)

        val lessonImage2: ImageView = findViewById(R.id.lesson_image2)
        val lessonTitle2: TextView = findViewById(R.id.lesson_title2)
        val lessonSubtitle2: TextView = findViewById(R.id.lesson_subtitle2)

        // ----- Bottom Navigation -----
        val bottomNav: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // Example: Handle Search Click


        // Example: Lesson Click
        lessonImage1.setOnClickListener {
            Toast.makeText(this, "Opening ${lessonTitle1.text}", Toast.LENGTH_SHORT).show()
        }

        lessonImage2.setOnClickListener {
            Toast.makeText(this, "Opening ${lessonTitle2.text}", Toast.LENGTH_SHORT).show()
        }

        // Bottom Navigation Clicks
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
                R.id.nav_alerts -> {
                    Toast.makeText(this, "Profile selected", Toast.LENGTH_SHORT).show()
                    true
                }
                else -> false
            }
        }
    }
}
