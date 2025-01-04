package com.example.eventino_event_ticket_booking_app

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.eventino_event_ticket_booking_app.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set full screen without status bar and navigation bar
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

        // Set up Bottom Navigation
        binding.bottomNavigation.setOnNavigationItemSelectedListener { item: MenuItem ->
            when (item.itemId) {
                R.id.profile -> {
                    // Start ProfileActivity when profile item is clicked
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.explore -> {
                    // Handle Explore item click
                    true
                }
                R.id.review -> {
                    // Handle Review item click
                    true
                }
                else -> false
            }
        }

        // Set up Navigation Drawer Menu
        binding.navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.profile -> {
                    // Start ProfileActivity when profile item is clicked from drawer
                    val intent = Intent(this, ProfileActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> false
            }
        }
    }
}
