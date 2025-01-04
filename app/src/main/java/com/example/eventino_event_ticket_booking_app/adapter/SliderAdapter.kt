package com.example.eventino_event_ticket_booking_app.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.eventino_event_ticket_booking_app.databinding.ViewholderSliderBinding
import com.example.eventino_event_ticket_booking_app.models.SliderItem
import com.google.ai.client.generativeai.common.RequestOptions

class SliderAdapter(private var sliderItem:MutableList<SliderItem>
,private val viewPager2:ViewPager2
):RecyclerView.Adapter<SliderAdapter.SliderViewHolder>(){
    private var content:Context?=null
    private val runnable=Runnable{
        sliderItem.addAll(sliderItem)
        notifyDataSetChanged()
    }
    inner class SliderViewHolder(private val binding: ViewholderSliderBinding):
        RecyclerView.ViewHolder(binding.root) {

            fun bind(sliderItem: SliderItem){
                content?.let {
                    Glide.with(it)
                        .load(sliderItem.url)
                        .apply{RequestOptions().transform(CenterCrop(), RoundedCorners(60))}
                        .into(binding.imageSlide)
                }
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SliderAdapter.SliderViewHolder {
            content=parent.context
            val binding=ViewholderSliderBinding.inflate(LayoutInflater.from(content),parent,false)
            return SliderViewHolder(binding)
    }

    override fun getItemCount(): Int = sliderItem.size

    override fun onBindViewHolder(holder: SliderViewHolder, position: Int) {
        holder.bind(sliderItem[position])
        if (position==sliderItem.size-2){
            viewPager2.post(runnable)
        }
    }
}