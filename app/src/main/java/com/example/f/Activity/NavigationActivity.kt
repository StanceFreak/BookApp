package com.example.f.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.f.Fragment.FavoriteFragment
import com.example.f.Fragment.BrowseFragment
import com.example.f.Fragment.HomeFragment
import com.example.f.Fragment.ProfileFragment
import com.example.f.R
import com.example.f.databinding.NavigationActivityBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class NavigationActivity: AppCompatActivity() {

    private lateinit var binding: NavigationActivityBinding
    private lateinit var googleAuth: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = NavigationActivityBinding.inflate(layoutInflater)

        setupBottomNav()
        setupGoogleAuth()
        isUserLogout()

        setContentView(binding.root)
    }

    private fun setupBottomNav() {
        addFragment(HomeFragment())
        with(binding.navBottom) {
            show(0)
            add(MeowBottomNavigation.Model(0, R.drawable.ic_home))
            add(MeowBottomNavigation.Model(1, R.drawable.ic_explore))
            add(MeowBottomNavigation.Model(2, R.drawable.ic_bookmark))
            add(MeowBottomNavigation.Model(3, R.drawable.ic_profile))

            setOnClickMenuListener {
                when(it.id) {
                    0 -> {
                        replaceFragment(HomeFragment())
                    }
                    1 -> {
                        replaceFragment(BrowseFragment())
                    }
                    2 -> {
                        replaceFragment(FavoriteFragment())
                    }
                    3 -> {
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

    private fun isUserLogout() {
        val acc = GoogleSignIn.getLastSignedInAccount(this)

        if (acc == null) {
            val i = Intent(this, SignInActivity::class.java)
            startActivity(i)
            finish()
        }
    }
}