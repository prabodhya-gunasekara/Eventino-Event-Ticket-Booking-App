package com.example.eventino_event_ticket_booking_app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId", "WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val editProfile = findViewById<Button>(R.id.editProfileButton)
        val logout = findViewById<Button>(R.id.logoutOptionLayout)
        val back = findViewById<ImageView>(R.id.backButton)
        val home = findViewById<ImageView>(R.id.homeOptionLayout)
        val reservation = findViewById<Button>(R.id.reservationOptionLayout)
        val changePassword = findViewById<Button>(R.id.changePasswordOptionLayout)

        editProfile.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }

        logout.setOnClickListener {
            val intent = Intent(this, LoadActivity::class.java)
            startActivity(intent)
            finish()
        }

        home.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        reservation.setOnClickListener {
            val intent = Intent(this, BookedEventsActivity::class.java)
            startActivity(intent)
        }

        changePassword.setOnClickListener {
            val intent = Intent(this, EditProfileActivity::class.java)
            startActivity(intent)
        }
    }
}
