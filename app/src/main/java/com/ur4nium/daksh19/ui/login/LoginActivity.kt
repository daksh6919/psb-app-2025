package com.ur4nium.daksh19.ui.login

import android.content.Intent
import com.ur4nium.daksh19.R
import android.text.InputType
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ur4nium.daksh19.ForgotPasswordActivity
import com.ur4nium.daksh19.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Access views directly using binding


        // Handle Sign In Button Click
        binding.signinButton.setOnClickListener {

            val phone = binding.phoneEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (phone.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please enter phone and password", Toast.LENGTH_SHORT).show()
            } else if (phone.length != 10 || !phone.all { it.isDigit() }) {
                Toast.makeText(this, "Phone number must be exactly 10 digits", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Signing in with:\nPhone: $phone\nPassword: $password", Toast.LENGTH_LONG).show()
                // Add actual sign-in logic here
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


        // Handle Forgot Password Click
        binding.forgotpassword.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
            finish() //
        }

        binding.signUptext.setOnClickListener {
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
            finish() //
        }

    }
}
