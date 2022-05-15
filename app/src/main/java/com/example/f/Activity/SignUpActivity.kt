package com.example.f.Activity

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.f.Model.Firebase.User
import com.example.f.R
import com.example.f.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import kotlin.math.log

class SignUpActivity:AppCompatActivity() {

    companion object {
        const val firebase_url = "https://booktest-320710-default-rtdb.asia-southeast1.firebasedatabase.app/"
    }

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var database: FirebaseDatabase
    private lateinit var reference: DatabaseReference
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)

//        usernameFocusListener()
//        emailFocusListener()
//        passwordFocusListener()
//        confirmPasswordFocusListener()
        setupActionBar()
        createAcc()

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

    private fun createAcc() {
//        with(binding) {
//            textLayoutUsername.helperText = validUsername()
//            textLayoutEmail.helperText = validEmail()
//            textLayoutPassword.helperText = validPassword()
//            textLayoutPasswordConf.helperText = validConfirmPassword()
//        }
        binding.btnCreateAcc.setOnClickListener {
            val etUserName = binding.signUpUsername.text
            val etUserEmail = binding.signUpEmail.text
            val etUserPassword = binding.signUpPassword.text
            val etUserPasswordConf = binding.signUpPasswordConf.text

//            val uNameHelperNull = binding.textLayoutUsername.helperText.isNullOrBlank()
//            val emailHelperNull = binding.textLayoutEmail.helperText.isNullOrBlank()
//            val passHelperNull = binding.textLayoutPassword.helperText.isNullOrBlank()
            val passConfHelperNull = binding.textLayoutPasswordConf.helperText.isNullOrBlank()

            val userName = etUserName.toString().trim()
            val userEmail = etUserEmail.toString().trim()
            val userPassword = etUserPassword.toString().trim()

            database = FirebaseDatabase.getInstance(firebase_url)
            reference = database.getReference("Users")
            auth = FirebaseAuth.getInstance()

            if (etUserName.isNullOrBlank()) {
                Toast.makeText(
                        this@SignUpActivity,
                        "Username cannot be empty!",
                        Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (etUserEmail.isNullOrBlank()) {
                Toast.makeText(
                        this@SignUpActivity,
                        "Email cannot be empty!",
                        Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (!Patterns.EMAIL_ADDRESS.matcher(etUserEmail).matches()) {
                Toast.makeText(
                        this@SignUpActivity,
                        "Please insert a valid Email!",
                        Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (etUserPassword.isNullOrBlank()) {
                Toast.makeText(
                        this@SignUpActivity,
                        "Password cannot be empty!",
                        Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (!passConfHelperNull && etUserPasswordConf.isNullOrBlank()) {
                Toast.makeText(
                        this@SignUpActivity,
                        "Please re-enter your Password!",
                        Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (etUserPassword.toString() != etUserPasswordConf.toString()) {
                Toast.makeText(
                        this@SignUpActivity,
                        "Password Didn't match!",
                        Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }
            if (userName.isNotEmpty()) {
                reference.orderByChild("username").equalTo(userName).addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {

                        if (snapshot.exists()) {
                            Toast.makeText(
                                    this@SignUpActivity,
                                    "Username already taken!",
                                    Toast.LENGTH_SHORT
                            ).show()
                        }
                        else {
                            reference.orderByChild("email").equalTo(userEmail).addListenerForSingleValueEvent(object : ValueEventListener {
                                override fun onDataChange(snapshot: DataSnapshot) {
                                    if (snapshot.exists()) {
                                        Toast.makeText(
                                                this@SignUpActivity,
                                                "Email already in use!",
                                                Toast.LENGTH_SHORT
                                        ).show()
                                    }
                                    else {
                                        auth.createUserWithEmailAndPassword(userEmail, userPassword).addOnCompleteListener { task ->
                                            if (task.isSuccessful) {
                                                val userData = User(userName, userEmail, userPassword)
                                                reference.child(auth.currentUser!!.uid).setValue(userData)
                                                    .addOnCompleteListener { t ->
                                                        if (t.isSuccessful) {
                                                            with(binding) {
                                                                signUpUsername.text?.clear()
                                                                signUpEmail.text?.clear()
                                                                signUpPassword.text?.clear()
                                                                signUpPasswordConf.text?.clear()
                                                                Toast.makeText(
                                                                        this@SignUpActivity,
                                                                        "Account created!",
                                                                        Toast.LENGTH_SHORT
                                                                ).show()
                                                            }
                                                            val i = Intent(
                                                                    this@SignUpActivity,
                                                                    SignInActivity::class.java
                                                            )
                                                            startActivity(i)
                                                        }
                                                        else {
                                                            Toast.makeText(
                                                                    this@SignUpActivity,
                                                                    "Failed to create account!",
                                                                    Toast.LENGTH_SHORT
                                                            ).show()
                                                        }
                                                    }
                                            }
                                        }
                                    }
                                }
                                override fun onCancelled(error: DatabaseError) {
                                    Toast.makeText(
                                            this@SignUpActivity,
                                            "An error has occurred, please try again!",
                                            Toast.LENGTH_LONG
                                    ).show()
                                    Log.d("test", error.details + " " + error.message)
                                }
                            })
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        Log.d(TAG, error.message)
                    }
                })
            }
        }
    }

//    private fun emailFocusListener() {
//        binding.signUpEmail.setOnFocusChangeListener { _, focus ->
//            if (!focus) {
//                binding.textLayoutEmail.helperText = validEmail()
//            }
//        }
//    }

//    private fun validEmail(): String? {
//        val email = binding.signUpEmail.text.toString().trim()
//        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
//            return "Invalid Email Address!"
//        }
//        return null
//    }
//
//    private fun usernameFocusListener() {
//        binding.signUpUsername.setOnFocusChangeListener { _, focus ->
//            if (!focus) {
//                binding.textLayoutUsername.helperText = validUsername()
//            }
//        }
//    }
//
//    private fun validUsername(): String? {
//        val username = binding.signUpUsername.text.toString().trim()
//
//        if (username.length > 16) {
//            return "Username can't be more than 16 characters "
//        }
//        return null
//    }
//
//    private fun passwordFocusListener() {
//        binding.signUpPassword.setOnFocusChangeListener { _, focus ->
//            if (!focus) {
//                binding.textLayoutPassword.helperText = validPassword()
//            }
//        }
//    }
//
//    private fun validPassword(): String? {
//        val pass = binding.signUpPassword.text.toString().trim()
//        if (pass.length < 8) {
//            return  "Password can't be less than 8 character!"
//        }
//        if (!pass.matches(".*[0-9].*".toRegex())) {
//            return  "Password must contain atleast 1 digit number!"
//        }
//        else {
//            return null
//        }
//    }
//
//    private fun confirmPasswordFocusListener() {
//        binding.signUpPasswordConf.setOnFocusChangeListener { _, focus ->
//            if (!focus) {
//                binding.textLayoutPasswordConf.helperText = validConfirmPassword()
//            }
//        }
//    }
//
//    private fun validConfirmPassword(): String? {
//        val passConf = binding.signUpPasswordConf.text.toString().trim()
//        val pass = binding.signUpPassword.text.toString().trim()
//        if (passConf != pass) {
//            return  "Password didn't match!"
//        }
//        else {
//            return null
//        }
//    }

//    private fun generateRandomId(): String {
//        val source = ('A'..'Z') + ('a'..'z') + ('0'..'9')
//        return(1..10).map {
//            source.random()
//        }.joinToString("")
//    }

}