package com.example.eventino_event_ticket_booking_app

import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.example.eventino_event_ticket_booking_app.databinding.ActivityLoadBinding

class LoadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.StartButton.setOnClickListener {
                startActivity(Intent(this@LoadActivity, MainActivity::class.java))
        }
        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )

    }
}