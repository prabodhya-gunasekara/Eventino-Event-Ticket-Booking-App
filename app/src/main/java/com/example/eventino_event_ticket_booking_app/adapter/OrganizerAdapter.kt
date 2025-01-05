package com.example.eventino_event_ticket_booking_app.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.eventino_event_ticket_booking_app.R
import com.example.eventino_event_ticket_booking_app.models.Organizer

class OrganizerAdapter(
    private val context: Context,
    private val organizers: List<Organizer>,
    private val onItemClicked: (Organizer) -> Unit
) : RecyclerView.Adapter<OrganizerAdapter.OrganizerViewHolder>() {

    class OrganizerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgOrganizer: ImageView = view.findViewById(R.id.img_organizer)
        val name: TextView = view.findViewById(R.id.organizer_name)
        val event: TextView = view.findViewById(R.id.organizer_event)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrganizerViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_organizer, parent, false)
        return OrganizerViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrganizerViewHolder, position: Int) {
        val organizer = organizers[position]
        holder.name.text = organizer.name
        holder.event.text = organizer.event

        // Load organizer image using Glide
        Glide.with(context)
            .load(organizer.imageUrl)
            .placeholder(R.drawable.img_organizer)
            .into(holder.imgOrganizer)

        // Set click listener
        holder.itemView.setOnClickListener { onItemClicked(organizer) }
    }

    override fun getItemCount(): Int = organizers.size
}
