package com.example.f.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.f.R
import com.example.f.databinding.RecyclerItemBooksBinding
import com.example.f.databinding.SignUpActivityBinding
import com.google.firebase.database.FirebaseDatabase

class SignUpActivity:AppCompatActivity() {

    private lateinit var binding: SignUpActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SignUpActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
    fun setupActionBar() {
        val actionbar = supportActionBar

        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.setDisplayShowHomeEnabled(true)
            actionbar.setHomeAsUpIndicator(R.drawable.ic_back)
            actionbar.elevation = 0f
        }
    }

    fun setupCreateAccount() {
        val userName = binding.signUpUsername.text.toString().trim()
        val userEmail = binding.signUpEmail.text.toString().trim()
        val userPassword = binding.signUpPassword.text.toString().trim()
        val userPassword2 = binding.signUpPassword2.text.toString().trim()

        binding.btnCreateAcc.setOnClickListener {
            if (userName.isEmpty() || userName.isBlank()) {
                binding.textLayoutUsername.error = "Please insert your username!"
                return@setOnClickListener
            }
            if (!isEmailValid(userEmail)) {
                binding.textLayoutUsername.error = "Please insert valid email!"
                return@setOnClickListener
            }
            if (userEmail.isEmpty() || userEmail.isBlank()) {
                binding.textLayoutUsername.error = "Please insert your email!"
                return@setOnClickListener
            }
            if (userPassword.isEmpty() || userPassword.isBlank()) {
                binding.textLayoutPassword.error = "Please insert your password!"
                return@setOnClickListener
            }
            if (userPassword2 != userPassword) {
                binding.textLayoutPassword.error = "Password didn't match!"
                return@setOnClickListener
            }
            else {
                val ref = FirebaseDatabase.getInstance().getReference("user account")

                val userId = ref.push().key
            }
        }

    }

    fun isEmailValid(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
}