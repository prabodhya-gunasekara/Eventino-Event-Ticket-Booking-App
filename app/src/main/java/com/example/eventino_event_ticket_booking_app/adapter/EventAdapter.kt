package com.example.eventino_event_ticket_booking_app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eventino_event_ticket_booking_app.R
import com.example.eventino_event_ticket_booking_app.models.EventModel

class EventAdapter(private val eventList: List<EventModel>) :
    RecyclerView.Adapter<EventAdapter.NewEventViewHolder>() {

    class NewEventViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val eventImage: ImageView = view.findViewById(R.id.event_image)
        val eventName: TextView = view.findViewById(R.id.eventName)
        val eventDate: TextView = view.findViewById(R.id.event_date)
        val eventTime: TextView = view.findViewById(R.id.event_time)
        val eventLocation: TextView = view.findViewById(R.id.event_location)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewEventViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_event, parent, false)
        return NewEventViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewEventViewHolder, position: Int) {
        val event = eventList[position]

        Glide.with(holder.eventImage.context)
            .load(event.imgURL)
            .placeholder(R.drawable.search_bg_dashboard)
            .into(holder.eventImage)

        holder.eventName.text = event.eventName
        holder.eventDate.text = event.date
        holder.eventTime.text = event.time
        holder.eventLocation.text = event.location
    }

    override fun getItemCount(): Int = eventList.size
}
