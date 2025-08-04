package com.ur4nium.daksh19

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class check_mail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_mail)

        val email = intent.getStringExtra("EMAIL_KEY")
        val emailTextView = findViewById<TextView>(R.id.emailTextView)
        emailTextView.text = email ?: "Email not found"

        val gmailButton = findViewById<Button>(R.id.openGmailButton)

        gmailButton.setOnClickListener {
            try {
                val intent = Intent(Intent.ACTION_VIEW)
                intent.setClassName("com.google.android.gm", "com.google.android.gm.ConversationListActivityGmail")
                startActivity(intent)
            } catch (e: Exception) {
                // If Gmail is not installed, open Gmail in browser
                val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://mail.google.com"))
                startActivity(webIntent)
            }

        }


    }

}
