package com.example.f.Activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import com.example.f.R
import com.example.f.databinding.LandingActivityBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class LandingActivity : AppCompatActivity() {

    private lateinit var binding: LandingActivityBinding
    private lateinit var googleAuth : GoogleSignInClient
    private lateinit var auth : FirebaseAuth
//    private val RC = 123

    companion object{
        private const val TAG = "auth"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = LandingActivityBinding.inflate(layoutInflater)
        auth = Firebase.auth

        isUserLogin()
        setupBtn()
        setupGoogleAuth()

        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val intent: Intent? = result.data
            val task = GoogleSignIn.getSignedInAccountFromIntent(intent)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        val i = Intent(this, NavigationActivity::class.java)
                        startActivity(i)
                        finish()
                        Log.d(TAG, "signInWithCredential:success")
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w(TAG, "signInWithCredential:failure", task.exception)
                    }
                }
    }

    private fun updateUI(user: FirebaseUser?) {

    }

    private fun setupGoogleAuth() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleAuth = GoogleSignIn.getClient(this, gso)

    }

    private fun setupBtn() {
        binding.btnSignUp.setOnClickListener {
            val go = Intent(this, SignUpActivity::class.java)
            startActivity(go)
        }

        binding.tvSignIn.setOnClickListener {
            val go = Intent(this, SignInActivity::class.java)
            startActivity(go)
        }

        binding.btnAuthGoogle.setOnClickListener {
            val intent = googleAuth.signInIntent
            resultLauncher.launch(intent)
        }
    }

    private fun isUserLogin() {
        val user = auth.currentUser

        if (user != null) {
            val i = Intent(this, NavigationActivity::class.java)
            startActivity(i)
        }
    }

}