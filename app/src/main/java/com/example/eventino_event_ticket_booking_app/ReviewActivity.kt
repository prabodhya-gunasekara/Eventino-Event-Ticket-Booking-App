package com.example.eventino_event_ticket_booking_app

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddReviewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_review)


        findViewById<Button>(R.id.submitButton).setOnClickListener {
            Toast.makeText(this, "Review submitted successfully!", Toast.LENGTH_SHORT).show()
        }
    }
}
