package com.example.f.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.f.Fragment.FavoriteFragment
import com.example.f.Fragment.HomeFragment
import com.example.f.Fragment.ProfileFragment
import com.example.f.Model.SharedPref
import com.example.f.R
import com.example.f.databinding.ActivityNavigationBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseAuth

class NavigationActivity: AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBinding
    private lateinit var googleAuth: GoogleSignInClient
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var mAuthStateListener: FirebaseAuth.AuthStateListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationBinding.inflate(layoutInflater)

        setupBottomNav()
        setupGoogleAuth()
        isUserLoggedIn()

        setContentView(binding.root)
    }

    override fun onStop() {
        super.onStop()
        firebaseAuth.removeAuthStateListener(mAuthStateListener)
    }

    override fun onStart() {
        super.onStart()
        firebaseAuth = FirebaseAuth.getInstance()
        val user = firebaseAuth.currentUser

        if (user == null) {
            val i = Intent(this, SignInActivity::class.java)
            startActivity(i)
            finish()
        }

    }

    private fun setupBottomNav() {
        addFragment(HomeFragment())
        with(binding.navBottom) {
            show(0)
            add(MeowBottomNavigation.Model(0, R.drawable.ic_home))
//            add(MeowBottomNavigation.Model(1, R.drawable.ic_search_black_24dp))
            add(MeowBottomNavigation.Model(1, R.drawable.ic_bookmark))
            add(MeowBottomNavigation.Model(2, R.drawable.ic_profile))

            setOnClickMenuListener {
                when(it.id) {
                    0 -> {
                        replaceFragment(HomeFragment())
                    }
//                    1 -> {
//                        replaceFragment(BrowseFragment())
//                    }
                    1 -> {
                        replaceFragment(FavoriteFragment())
                    }
                    2 -> {
                        replaceFragment(ProfileFragment())
                    }
                    else -> {
                        replaceFragment(HomeFragment())
                    }
                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(Fragment::class.java.simpleName).commit()
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment).addToBackStack(Fragment::class.java.simpleName).commit()
    }

    private fun setupGoogleAuth() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build()

        googleAuth = GoogleSignIn.getClient(this, gso)
    }

    private fun isUserLoggedIn() {
        firebaseAuth = FirebaseAuth.getInstance()
        val user = firebaseAuth.currentUser
        // if there is an authentication state, like the user is trying to sign-in or sign-out
        // the AuthStateListener will be called and gets invoked in the UI thread on changes in the authentication state
        mAuthStateListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            if (user != null) {
                // checking if user login using email password or google sign-in
                for (uInfo in user.providerData) {
                    if (uInfo.providerId == ("password")) {
                        val userEmail = firebaseAuth.currentUser
                        Toast.makeText(this, "Welcome ${userEmail?.email}", Toast.LENGTH_LONG).show()
                    }
                    if (uInfo.providerId == ("google.com")) {
                        val userGoogle = GoogleSignIn.getLastSignedInAccount(this)
                        Toast.makeText(this, "Welcome ${userGoogle?.email}", Toast.LENGTH_LONG).show()
                    }
                }
            }
            else {
                // if the user already logout, the app will take the user back to SignInActivity
                val i = Intent(this, SignInActivity::class.java)
                startActivity(i)
                finish()
            }
        }
        firebaseAuth.addAuthStateListener(mAuthStateListener)
    }
}