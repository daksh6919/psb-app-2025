package com.ur4nium.daksh19

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.*

class EditProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editprofile)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.editprofile)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 🔹 Full name
        val usernameEditText = findViewById<EditText>(R.id.usernameEditText)

        // 🔹 Email

        // 🔹 Phone
        val phoneEditText = findViewById<EditText>(R.id.phoneEditText)

        // 🔹 Birthdate
        val birthDateEditText = findViewById<EditText>(R.id.birthDateEditText)

        birthDateEditText.setOnClickListener {
            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDay ->
                val birthdate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                birthDateEditText.setText(birthdate)
            }, year, month, day)

            datePicker.datePicker.maxDate = System.currentTimeMillis() // Prevent future dates
            datePicker.show()
        }

        // 🔘 Optional: Save button logic (add this if you create a Save button)
        val saveButton = findViewById<Button?>(R.id.saveButton) // only if you added a button
        saveButton?.setOnClickListener {
            val name = usernameEditText.text.toString().trim()
            val phone = phoneEditText.text.toString().trim()
            val dob = birthDateEditText.text.toString().trim()

            // 👉 You can now validate & upload this to Firebase
            Toast.makeText(this, "Profile Updated:\n$name\n$phone\n$dob", Toast.LENGTH_SHORT).show()
        }
    }
}
