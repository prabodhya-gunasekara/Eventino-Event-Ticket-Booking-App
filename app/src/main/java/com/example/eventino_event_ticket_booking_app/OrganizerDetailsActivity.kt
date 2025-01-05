package com.example.eventino_event_ticket_booking_app

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class OrganizerDetailsActivity : AppCompatActivity() {

    private lateinit var backBtn: ImageView
    private lateinit var imgOrganizer: ImageView
    private lateinit var title: TextView
    private lateinit var details: TextView
    private lateinit var reservationDetails: EditText
    private lateinit var sendBtn: Button
    private lateinit var database: DatabaseReference

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_organizer_details)

        // Initialize views
        backBtn = findViewById(R.id.back_btn)
        imgOrganizer = findViewById(R.id.img_organizer)
        title = findViewById(R.id.organizer_title)
        details = findViewById(R.id.organizer_details)
        reservationDetails = findViewById(R.id.organizer_reservation)
        sendBtn = findViewById(R.id.btn_send_reservation)

        // Get organizer data from intent
        val organizerName = intent.getStringExtra("name") ?: "Organizer"
        val organizerDetails = intent.getStringExtra("details") ?: "Details"
        val organizerImage = intent.getStringExtra("imageUrl") ?: ""

        // Set data to views
        title.text = organizerName
        details.text = organizerDetails
        Glide.with(this).load(organizerImage).placeholder(R.drawable.img_organizer).into(imgOrganizer)

        // Back button
        backBtn.setOnClickListener { finish() }

        // Send reservation
        sendBtn.setOnClickListener {
            val reservationText = reservationDetails.text.toString()
            if (reservationText.isEmpty()) {
                Toast.makeText(this, "Please enter reservation details", Toast.LENGTH_SHORT).show()
            } else {
                saveReservation(organizerName, reservationText)
            }
        }
    }

    private fun saveReservation(organizerName: String, reservationDetails: String) {
        database = FirebaseDatabase.getInstance().getReference("reservations")
        val reservationId = database.push().key ?: return
        val reservationData = mapOf(
            "id" to reservationId,
            "organizer" to organizerName,
            "details" to reservationDetails
        )
        database.child(reservationId).setValue(reservationData).addOnSuccessListener {
            Toast.makeText(this, "Reservation sent successfully!", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to send reservation", Toast.LENGTH_SHORT).show()
        }
    }
}
