package com.example.eventino_event_ticket_booking_app

import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.eventino_event_ticket_booking_app.adapter.SliderAdapter
import com.example.eventino_event_ticket_booking_app.databinding.ActivityMainBinding
import com.example.eventino_event_ticket_booking_app.models.SliderItem
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var  database: FirebaseDatabase
    private val sliderHandler=Handler()
    private val sliderRunnable= Runnable{
        binding.viewpage2.currentItem=binding.viewpage2.currentItem+1

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database=FirebaseDatabase.getInstance()

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        initBanner()
    }
    private fun initBanner(){
        val myRef:DatabaseReference=database.getReference("Banners")
        binding.progressBarSlider.visibility=View.VISIBLE
        myRef.addListenerForSingleValueEvent(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                val lists= mutableListOf<SliderItem>()
                for (childSnapshot in snapshot.children){
                    val list =childSnapshot.getValue(SliderItem::class.java)
                    if (list!=null){
                        lists.add(list)
                    }
                }

                binding.progressBarSlider.visibility=View.GONE
                banners(lists)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun banners(lists: MutableList<SliderItem>) {
        binding.viewpage2.adapter=SliderAdapter(lists,binding.viewpage2)
        binding.viewpage2.offscreenPageLimit=3
        binding.viewpage2.clipToPadding=false
        binding.viewpage2.clipChildren=false
        binding.viewpage2.getChildAt(0).overScrollMode= RecyclerView.OVER_SCROLL_NEVER


        val compositePageTransformer=CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
            addTransformer(ViewPager2.PageTransformer { page, position ->
                val r=1-Math.abs(position)
                page.scaleY=0.85f+r*0.15f

            })
        }
        binding.viewpage2.setPageTransformer(compositePageTransformer)
        binding.viewpage2.currentItem=1
        binding.viewpage2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                sliderHandler.removeCallbacks(sliderRunnable)
            }
        })

    }

    override fun onPause() {
        super.onPause()
        sliderHandler.removeCallbacks(sliderRunnable)
    }

    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(sliderRunnable,2000)

    }
}