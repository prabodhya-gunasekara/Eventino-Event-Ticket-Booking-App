package com.example.eventino_event_ticket_booking_app

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.eventino_event_ticket_booking_app.databinding.ActivityLoadBinding

class LoadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            StartButton.setOnClickListener {
                val intent = Intent(this@LoadActivity, MainActivity::class.java)
                startActivity(intent)
            }
        }
    }
}