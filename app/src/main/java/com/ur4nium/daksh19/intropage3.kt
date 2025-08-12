package com.ur4nium.daksh19
import android.widget.Button
import android.content.Intent
import android.os.Bundle
import android.widget.RelativeLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class intropage3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intropage3)

        val nextButton: Button = findViewById(R.id.nextButton)

        nextButton.setOnClickListener {
            val intent = Intent(this, intropage4::class.java)
            startActivity(intent)
        }

        val SkipButton: Button = findViewById(R.id.SkipButton)

        SkipButton.setOnClickListener {
            val intent = Intent(this, first_page::class.java)
            startActivity(intent)
        }
    }
}
