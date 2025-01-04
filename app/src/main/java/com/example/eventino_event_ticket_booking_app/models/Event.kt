package com.example.eventino_event_ticket_booking_app.models

import com.google.firebase.Timestamp

data class Event(
    val eventId: String = "",
    val eventName: String = "",
    val eventDate: Timestamp? = null,
    val organizer: String = "",
    val location: String = "",
    val aboutEvent: String = ""
)
