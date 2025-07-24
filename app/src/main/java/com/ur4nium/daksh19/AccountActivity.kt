package com.ur4nium.daksh19.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ur4nium.daksh19.R
import com.ur4nium.daksh19.databinding.ActivityAccountactivityBinding




class AccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountactivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAccountactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Access views directly using binding




        // Handle Sign In Button Click
        binding.signinButton.setOnClickListener {

            binding.signinButton.setOnClickListener {

                val username = binding.usernameEditText.text.toString()
                val phone = binding.phoneEditText.text.toString()
                val password = binding.passwordEditText.text.toString()
                val confirmPassword = binding.confirmPasswordEditText.text.toString()

                when {
                    username.isEmpty() -> {
                        Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_SHORT).show()
                    }
                    phone.isEmpty() || phone.length != 10 || !phone.all { it.isDigit() } -> {
                        Toast.makeText(this, "Enter a valid 10-digit phone number", Toast.LENGTH_SHORT).show()
                    }
                    password.isEmpty() || confirmPassword.isEmpty() -> {
                        Toast.makeText(this, "Password and Confirm Password cannot be empty", Toast.LENGTH_SHORT).show()
                    }
                    password != confirmPassword -> {
                        Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Toast.makeText(this, "Signing in with:\nUsername: $username\nPhone: $phone", Toast.LENGTH_LONG).show()
                        // Add actual sign-in logic here
                    }
                }
            }


        }




        var isPasswordVisible = false

        binding.passwordToggle.setOnClickListener {
            isPasswordVisible = !isPasswordVisible

            if (isPasswordVisible) {
                binding.passwordEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                binding.passwordToggle.setImageResource(R.drawable.eye_open)
            } else {
                binding.passwordEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.passwordToggle.setImageResource(R.drawable.img16)
            }

            // Move cursor to end
            binding.passwordEditText.setSelection(binding.passwordEditText.text?.length ?: 0)
        }



        val confirmPasswordEditText = findViewById<EditText>(R.id.confirmPasswordEditText)
        val confirmPasswordToggle = findViewById<ImageView>(R.id.confirmPasswordToggle)

        var isconfirmPasswordVisible = false

        binding.confirmPasswordToggle.setOnClickListener {
            isconfirmPasswordVisible = !isconfirmPasswordVisible

            if (isconfirmPasswordVisible) {
                binding.confirmPasswordEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                binding.confirmPasswordToggle.setImageResource(R.drawable.eye_open)
            } else {
                binding.confirmPasswordEditText.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
                binding.confirmPasswordToggle.setImageResource(R.drawable.img16)
            }

            // Move cursor to end
            binding.confirmPasswordEditText.setSelection(binding.confirmPasswordEditText.text?.length ?: 0)
        }


        binding.signInTextView.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish() //
        }





    }
}
