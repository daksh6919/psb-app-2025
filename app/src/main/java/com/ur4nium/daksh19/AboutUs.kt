package com.ur4nium.daksh19

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class AboutUs : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us) // replace with your XML file name

        // Back Button Click
        val backButton = findViewById<ImageView>(R.id.backButton)
        backButton.setOnClickListener {
            finish() // closes AboutUs and returns to previous screen
        }

        // Make email clickable
        val aboutTextView = findViewById<TextView>(R.id.AboutUsTextView) // add an id in XML
        val aboutText = """
            <a href="mailto:trishulxur4nium@gmail.com">trishulxur4nium@gmail.com</a>.
        """.trimIndent()

        aboutTextView.text = Html.fromHtml(aboutText)
        aboutTextView.movementMethod = LinkMovementMethod.getInstance()

        // Bottom Navigation
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_navigation)
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
