package com.example.f.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.f.R

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_in_activity)

        setupBtn()
        setupActionBar()
        signIn()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun setupBtn() {
        val signup = findViewById<TextView>(R.id.tv_sign_up)

        signup.setOnClickListener {
            val go = Intent(this, SignUpActivity::class.java)
            startActivity(go)
        }
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

    fun signIn() {
        val signin = findViewById<Button>(R.id.btn_signin)

        signin.setOnClickListener {
            val go = Intent(this, NavigationActivity::class.java)
            startActivity(go)
        }
        val cont = findViewById<TextView>(R.id.tv_sign_up)

        cont.setOnClickListener {
            val go = Intent(this, SignUpActivity::class.java)
            startActivity(go)
        }
    }

}