package com.example.f.Activity

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.f.R
import com.example.f.databinding.ActivitySignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {

    companion object {
        const val firebase_url = "https://booktest-320710-default-rtdb.asia-southeast1.firebasedatabase.app/"
    }

    private lateinit var binding: ActivitySignInBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySignInBinding.inflate(layoutInflater)

//        emailFocusListener()
//        passFocusListener()
        setupActionBar()
        signIn()
        signUp()

        setContentView(binding.root)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun setupActionBar() {
        val actionbar = supportActionBar

        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true)
            actionbar.setDisplayShowHomeEnabled(true)
            actionbar.setHomeAsUpIndicator(R.drawable.ic_back)
            actionbar.elevation = 0f
        }
    }

    private fun signIn() {
//        emailFocusListener()
//        passFocusListener()
//        with(binding) {
//            textLayoutEmail.helperText = emailValidation()
//            textLayoutPassword.helperText = passValidation()
//        }
        val mDialogView = Dialog(this)
        mDialogView.apply {
            setCancelable(false)
            setContentView(R.layout.loading_process)
        }

        binding.btnSignin.setOnClickListener {
            val etEmail = binding.signInEmail.text
            val etPass = binding.signInPassword.text
            val userEmail = etEmail.toString()
            val userPass = etPass.toString()

            auth = FirebaseAuth.getInstance()

            mDialogView.show()

            if (etEmail.isNullOrBlank()) {
                mDialogView.dismiss()
                Toast.makeText(this, "Email cannot be empty!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            if (etPass.isNullOrBlank()) {
                mDialogView.dismiss()
                Toast.makeText(this, "Pass cannot be empty!", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            else {
                auth.signInWithEmailAndPassword(userEmail, userPass).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        mDialogView.dismiss()
                        val i = Intent(this, NavigationActivity::class.java)
                        startActivity(i)
                        finish()
                    }
                    else {
                        mDialogView.dismiss()
                        Toast.makeText(baseContext, "Login failed, please check your input", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    private fun signUp() {
        binding.tvSignUp.setOnClickListener {
            val go = Intent(this, SignUpActivity::class.java)
            startActivity(go)
        }
    }

//    private fun emailValidation(): String? {
//        val email = binding.signInEmail.text.toString().trim()
//        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            return "Please insert a valid email!"
//        }
//        if (email.length < 0) {
//            return "Please insert your email!"
//        }
//        return null
//    }

//    private fun emailFocusListener() {
//        binding.signInEmail.setOnFocusChangeListener { _, focus ->
//            if (!focus) {
//                binding.textLayoutEmail.helperText = emailValidation()
//            }
//        }
//    }

//    private fun passValidation(): String? {
//        val pass = binding.signInPassword.text.toString().trim()
//        if (pass.length < 0) {
//            return "Please insert your password!"
//        }
//        return null
//    }
//
//    private fun passFocusListener() {
//        binding.signInEmail.setOnFocusChangeListener { _, focus ->
//            if (!focus) {
//                binding.textLayoutEmail.error = passValidation()
//            }
//        }
//    }

}