package com.ur4nium.daksh19

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView

class BudgetSavingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_budget_saving)

        // Find the back button and set a click listener to close the activity
        val backButton = findViewById<ImageView>(R.id.back_button)
        backButton.setOnClickListener {
            // This will close the current activity and return to the previous one
            finish()
        }
    }
}

