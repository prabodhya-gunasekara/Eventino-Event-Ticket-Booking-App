package com.example.eventino_event_ticket_booking_app

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Back button functionality to navigate back to MainActivity
        val backIcon: ImageView = findViewById(R.id.back_icon)
        backIcon.setOnClickListener {
            // Explicitly navigate to MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()  // Optional: Use this if you don't want to keep ProfileActivity in the back stack
        }

        // Logout button functionality
        val logoutButton: Button = findViewById(R.id.logout_button)
        logoutButton.setOnClickListener {
            // Navigate to ActivityLoad when the logout button is clicked
            val intent = Intent(this, LoadActivity::class.java)
            startActivity(intent)
            finish()  // Optional: Use this if you don't want to keep ProfileActivity in the back stack
        }

        val EditProfileButton: Button = findViewById(R.id.edit_profile_button)
        EditProfileButton.setOnClickListener {
            // Navigate to ActivityLoad when the logout button is clicked
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
            finish()  // Optional: Use this if you don't want to keep ProfileActivity in the back stack
        }
    }
}
