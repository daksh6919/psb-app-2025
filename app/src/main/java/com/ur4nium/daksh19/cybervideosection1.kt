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
        val lesson2 = findViewById<RelativeLayout>(R.id.video_lesson_2)
        val lesson3 = findViewById<RelativeLayout>(R.id.video_lesson_3)

        // Lesson 1 → Firestore document "video1"
        lesson1.setOnClickListener {
            openVideo("video1")
        }

        // Lesson 2 → Firestore document "video2"
        lesson2.setOnClickListener {
            openVideo("video2")
        }

        // Lesson 3 → Firestore document "video3"
        lesson3.setOnClickListener {
            openVideo("video3")
        }
    }

    private fun openVideo(documentId: String) {
        val intent = Intent(this, VideoCyber::class.java)
        intent.putExtra("DOC_ID", documentId)
        startActivity(intent)
    }
}
