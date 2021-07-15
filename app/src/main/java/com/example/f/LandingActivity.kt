package com.example.f

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class LandingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.landing_activity)

        val signUp = findViewById<Button>(R.id.btn_sign_up)

        signUp.setOnClickListener {
            val go = Intent(this, SignUpActivity::class.java)
            startActivity(go)
        }
    }
}