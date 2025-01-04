package com.example.eventino_event_ticket_booking_app.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eventino_event_ticket_booking_app.BookedEventDetailsActivity
import com.example.eventino_event_ticket_booking_app.R
import com.example.eventino_event_ticket_booking_app.models.Event
import java.text.SimpleDateFormat
import java.util.*

class EventAdapter2(
    private val context: Context,
    private val events: List<Event>
) : RecyclerView.Adapter<EventAdapter2.EventViewHolder>() {

    // ViewHolder class for holding views for each item
    inner class EventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvEventName: TextView = view.findViewById(R.id.event_name)
        val tvEventDate: TextView = view.findViewById(R.id.event_date_time)
        val tvEventAbout: TextView = view.findViewById(R.id.event_detail)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]

        // Set event name
        holder.tvEventName.text = event.eventName

        // Format and set event date
        val sdf = SimpleDateFormat("dd MMM yyyy, hh:mm a", Locale.getDefault())
        event.eventDate?.let {
            holder.tvEventDate.text = sdf.format(it.toDate())
        }

        // Set event details
        holder.tvEventAbout.text = event.aboutEvent

        // Handle item click to navigate to details activity
        holder.itemView.setOnClickListener {
            val intent = Intent(context, BookedEventDetailsActivity::class.java)
            intent.putExtra("eventId", event.eventId)
            intent.putExtra("eventName", event.eventName)
            intent.putExtra("eventDate", event.eventDate?.toDate()?.time) // Convert Timestamp to milliseconds
            intent.putExtra("organizer", event.organizer)
            intent.putExtra("location", event.location)
            intent.putExtra("aboutEvent", event.aboutEvent)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = events.size
}
