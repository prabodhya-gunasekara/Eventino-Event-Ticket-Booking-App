package com.example.eventino_event_ticket_booking_app

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eventino_event_ticket_booking_app.adapter.EventAdapter2
import com.example.eventino_event_ticket_booking_app.models.Event
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query

class BookedEventsActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var eventAdapter: EventAdapter2
    private val events = mutableListOf<Event>()
    private val firestore = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booked_events)

        recyclerView = findViewById(R.id.recycler_view_events)
        recyclerView.layoutManager = LinearLayoutManager(this)
        eventAdapter = EventAdapter2(this, events)
        recyclerView.adapter = eventAdapter

        fetchBookedEvents()
    }

    private fun fetchBookedEvents() {
        val today = com.google.firebase.Timestamp.now()

        firestore.collection("bookedEvents")
            .orderBy("eventDate", Query.Direction.ASCENDING)
            .whereGreaterThanOrEqualTo("eventDate", today)
            .get()
            .addOnSuccessListener { querySnapshot ->
                events.clear()
                for (document in querySnapshot) {
                    val event = document.toObject(Event::class.java)
                    events.add(event)
                }
                eventAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Failed to fetch events: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }
}
