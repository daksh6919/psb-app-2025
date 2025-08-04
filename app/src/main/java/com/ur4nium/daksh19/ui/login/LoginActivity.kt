package com.ur4nium.daksh19.ui.login

import android.content.Intent
import com.ur4nium.daksh19.R
import android.text.InputType
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ur4nium.daksh19.ForgotPasswordActivity
import com.ur4nium.daksh19.dashboard_app
import com.ur4nium.daksh19.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth
class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseauth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
       firebaseauth = FirebaseAuth.getInstance()
        // Access views directly using binding


        // Handle Sign In Button Click
        binding.signinButton.setOnClickListener {

            val phone = binding.phoneEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            if (phone.isEmpty() || password.isEmpty()) {
                firebaseauth.signInWithEmailAndPassword(phone, password).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, dashboard_app::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                    }
                }
                Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(phone).matches()) {
                Toast.makeText(this, "Please enter a valid email", Toast.LENGTH_SHORT).show()
            }
            else {
                val intent = Intent(this, dashboard_app::class.java)
                startActivity(intent)
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
