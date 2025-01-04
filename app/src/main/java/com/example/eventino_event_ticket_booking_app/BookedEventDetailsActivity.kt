package com.example.eventino_event_ticket_booking_app

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.*

class BookedEventDetailsActivity : AppCompatActivity() {

    private lateinit var backBtn: ImageView
    private lateinit var eventTitle: TextView
    private lateinit var eventDateTime: TextView
    private lateinit var eventLocation: TextView
    private lateinit var eventOrganizer: TextView
    private lateinit var eventDetail: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booked_event_details)

        backBtn = findViewById(R.id.back_btn)
        eventTitle = findViewById(R.id.event_title)
        eventDateTime = findViewById(R.id.event_date_time)
        eventLocation = findViewById(R.id.event_location)
        eventOrganizer = findViewById(R.id.event_organizer)
        eventDetail = findViewById(R.id.event_detail)

        backBtn.setOnClickListener { finish() }

        val eventName = intent.getStringExtra("eventName")
        val eventDateTimestamp = intent.getLongExtra("eventDate", -1)
        val eventLocationStr = intent.getStringExtra("location")
        val eventOrganizerStr = intent.getStringExtra("organizer")
        val eventAbout = intent.getStringExtra("aboutEvent")

        if (eventName != null && eventDateTimestamp != -1L) {

            eventTitle.text = eventName
            val sdf = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
            val eventDate = Date(eventDateTimestamp)
            eventDateTime.text = sdf.format(eventDate)
            eventLocation.text = eventLocationStr
            eventOrganizer.text = eventOrganizerStr
            eventDetail.text = eventAbout
        } else {
            Toast.makeText(this, "Failed to load event details!", Toast.LENGTH_SHORT).show()
        }
    }
}
