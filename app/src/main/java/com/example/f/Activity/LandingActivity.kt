package com.example.f.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.f.R

class LandingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.landing_activity)

        setupBtn()
    }

     fun setupBtn() {
        val signUp = findViewById<Button>(R.id.btn_sign_up)
        val signIn = findViewById<TextView>(R.id.tv_sign_in)

        signUp.setOnClickListener {
            val go = Intent(this, SignUpActivity::class.java)
            startActivity(go)
        }

        signIn.setOnClickListener {
            val go = Intent(this, SignInActivity::class.java)
            startActivity(go)
        }

    }
}