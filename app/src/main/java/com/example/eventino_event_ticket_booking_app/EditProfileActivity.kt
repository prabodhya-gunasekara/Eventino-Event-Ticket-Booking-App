package com.example.eventino_event_ticket_booking_app

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class EditProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)
        // Back button functionality to navigate back to MainActivity
        val BackIconEditProfile: ImageView = findViewById(R.id.back_icon_edit_profile)
        BackIconEditProfile.setOnClickListener {
            // Explicitly navigate to MainActivity
            val intent = Intent(this, ProfileActivity::class.java)
            startActivity(intent)
            finish()  // Optional: Use this if you don't want to keep ProfileActivity in the back stack
        }
    }
}