package com.ur4nium.daksh19

import android.content.Intent
import android.os.Bundle
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity

class cybersectionvideo1 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cybervideosection1) // Replace with your XML file name

        val lesson1 = findViewById<RelativeLayout>(R.id.videoCyber)
        val lesson2 = findViewById<RelativeLayout>(R.id.Phishv2)
        val lesson3 = findViewById<RelativeLayout>(R.id.video_lesson_3)

        // Lesson 1 → Firestore document "video1" → VideoCyber activity
        lesson1.setOnClickListener {
            val intent = Intent(this, VideoCyber::class.java)
            intent.putExtra("DOC_ID", "video1")
            startActivity(intent)
        }

        // Lesson 2 → Firestore document "video2" → Phishv2 activity
        lesson2.setOnClickListener {
            val intent = Intent(this, Phishv2::class.java)
            intent.putExtra("DOC_ID", "video2")
            startActivity(intent)
        }

        // Lesson 3 → Firestore document "video3" → VideoCyber activity (or whichever you want)
        lesson3.setOnClickListener {
            val intent = Intent(this, VideoCyber::class.java)
            intent.putExtra("DOC_ID", "video3")
            startActivity(intent)
        }
        }
}