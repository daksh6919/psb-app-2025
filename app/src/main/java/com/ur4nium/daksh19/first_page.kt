package com.ur4nium.daksh19

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.content.Intent
import android.widget.Button
import com.ur4nium.daksh19.ui.login.LoginActivity
import com.ur4nium.daksh19.ui.login.AccountActivity
import com.google.firebase.auth.FirebaseAuth

class first_page : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first_page)

        // Check if user is already logged in
        val currentUser = FirebaseAuth.getInstance().currentUser

        if (currentUser != null) {
            // Already logged in → Go to Dashboard/HomeActivity
            val intent = Intent(this, dashboard_app::class.java)
            startActivity(intent)
            finish() // Prevent back to welcome screen
            return
        }

        // Not logged in → Setup buttons
        val signInButton = findViewById<Button>(R.id.signInButton)
        val signUpButton = findViewById<Button>(R.id.signUpButton)

        signInButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        signUpButton.setOnClickListener {
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        }
    }
}
