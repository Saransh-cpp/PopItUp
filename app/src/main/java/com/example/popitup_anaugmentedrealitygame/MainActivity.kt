package com.example.popitup_anaugmentedrealitygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val leaderboardFragment = LeaderboardFragment()
        val aboutFragment = AboutFragment()

        replaceFragment(homeFragment)

        bottomNav.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.menu_home -> {
                    replaceFragment(homeFragment)
                }
                R.id.menu_leaderboard -> {
                    replaceFragment(leaderboardFragment)
                }
                R.id.menu_about -> {
                    replaceFragment(aboutFragment)
                }
            }
            true
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()

    }

}