package com.example.eventino_event_ticket_booking_app

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PastEventsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_past_events)

        val btnViewPastEvent: Button = findViewById(R.id.btn_past_events)

        btnViewPastEvent.setOnClickListener {
            val intent = Intent(this, PastEventDetailsActivity::class.java)
            startActivity(intent)
        }
    }
}
