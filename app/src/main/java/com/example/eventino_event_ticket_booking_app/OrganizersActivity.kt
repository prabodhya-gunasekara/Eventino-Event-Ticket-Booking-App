package com.example.eventino_event_ticket_booking_app

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.eventino_event_ticket_booking_app.adapter.OrganizerAdapter
import com.example.eventino_event_ticket_booking_app.models.Organizer
import com.google.firebase.database.*

class OrganizersActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var organizerAdapter: OrganizerAdapter
    private lateinit var database: DatabaseReference
    private val organizerList = mutableListOf<Organizer>()

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.organizer_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        organizerAdapter = OrganizerAdapter(this, organizerList) { organizer ->
            val intent = Intent(this, OrganizerDetailsActivity::class.java)
            intent.putExtra("name", organizer.name)
            intent.putExtra("details", organizer.details)
            intent.putExtra("imageUrl", organizer.imageUrl)
            startActivity(intent)
        }
        recyclerView.adapter = organizerAdapter

        fetchOrganizersFromFirebase()
    }

    private fun fetchOrganizersFromFirebase() {
        database = FirebaseDatabase.getInstance().getReference("organizers")
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                organizerList.clear()
                for (organizerSnapshot in snapshot.children) {
                    val organizer = organizerSnapshot.getValue(Organizer::class.java)
                    if (organizer != null) organizerList.add(organizer)
                }
                organizerAdapter.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                // Handle database error
            }
        })
    }
}
