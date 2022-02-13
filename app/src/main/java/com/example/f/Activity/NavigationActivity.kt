package com.example.f.Activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.f.Fragment.FavoriteFragment
import com.example.f.Fragment.BrowseFragment
import com.example.f.Fragment.HomeFragment
import com.example.f.Fragment.ProfileFragment
import com.example.f.R

class NavigationActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_activity)

        val bottomNavigationView = findViewById<MeowBottomNavigation>(R.id.nav_bottom)

        addFragment(HomeFragment())
        bottomNavigationView.show(0)
        bottomNavigationView.add(MeowBottomNavigation.Model(0, R.drawable.ic_home))
        bottomNavigationView.add(MeowBottomNavigation.Model(1, R.drawable.ic_explore))
        bottomNavigationView.add(MeowBottomNavigation.Model(2, R.drawable.ic_bookmark))
        bottomNavigationView.add(MeowBottomNavigation.Model(3, R.drawable.ic_profile))

        bottomNavigationView.setOnClickMenuListener {
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

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack(Fragment::class.java.simpleName).commit()
    }

    private fun addFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, fragment).addToBackStack(Fragment::class.java.simpleName).commit()
    }
}