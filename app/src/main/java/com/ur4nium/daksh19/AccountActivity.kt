package com.ur4nium.daksh19.ui.login

import android.content.Intent
import android.os.Bundle
import android.text.InputType
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.ur4nium.daksh19.R
import com.ur4nium.daksh19.dashboard_app
import com.ur4nium.daksh19.databinding.ActivityAccountactivityBinding

class AccountActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAccountactivityBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountactivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        // 🔐 Register Button Click
        binding.signinButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val email = binding.phoneEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            val confirmPassword = binding.confirmPasswordEditText.text.toString()

            when {
                username.isEmpty() -> {
                    Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_SHORT).show()
                }
                email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    Toast.makeText(this, "Enter a valid email address", Toast.LENGTH_SHORT).show()
                }
                password.isEmpty() || confirmPassword.isEmpty() -> {
                    Toast.makeText(this, "Password and Confirm Password cannot be empty", Toast.LENGTH_SHORT).show()
                }
                password != confirmPassword -> {
                    Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show()
                }
                else -> {
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show()
                                val intent = Intent(this, dashboard_app::class.java)
                                startActivity(intent)
                                finish()
                            } else {
                                Toast.makeText(this, "Registration failed: ${task.exception?.message}", Toast.LENGTH_LONG).show()
                            }
                        }
                }
            }
        }

        // 👁️ Password Visibility Toggle
        var isPasswordVisible = false
        binding.passwordToggle.setOnClickListener {
            isPasswordVisible = !isPasswordVisible
            binding.passwordEditText.inputType =
                if (isPasswordVisible)
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                else
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

            binding.passwordToggle.setImageResource(
                if (isPasswordVisible) R.drawable.eye_open else R.drawable.img16
            )
            binding.passwordEditText.setSelection(binding.passwordEditText.text?.length ?: 0)
        }

        // 👁️ Confirm Password Visibility Toggle
        var isConfirmPasswordVisible = false
        binding.confirmPasswordToggle.setOnClickListener {
            isConfirmPasswordVisible = !isConfirmPasswordVisible
            binding.confirmPasswordEditText.inputType =
                if (isConfirmPasswordVisible)
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
                else
                    InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD

            binding.confirmPasswordToggle.setImageResource(
                if (isConfirmPasswordVisible) R.drawable.eye_open else R.drawable.img16
            )
            binding.confirmPasswordEditText.setSelection(binding.confirmPasswordEditText.text?.length ?: 0)
        }

        // 🔄 Navigate to LoginActivity
        binding.signInTextView.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

